package com.company.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.interfaces.Claim;
import com.company.project.dao.AuthorityMapper;
import com.company.project.dao.LogSysMapper;
import com.company.project.dao.UserMapper;
import com.company.project.log.LOGEnum;
import com.company.project.model.Authority;
import com.company.project.model.LogSys;
import com.company.project.model.User;
import com.company.project.service.AuthService;
import com.company.project.utils.LogUtils;
import com.company.project.utils.RequestUtils;
import com.company.project.utils.TokenUtils;
import com.company.project.vo.AuthVo;

@Service
@Transactional
public class AuthServiceImpl implements AuthService
{
	private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private LogSysMapper logSysMapper;
	
	/**
	 * 登录
	 */
	@Override
	public AuthVo Login(User user)
	{
		String username = user.getUsername();
		String userpass = user.getUserpass();
		
		if (username == null || userpass == null) {
			LOG.info("用户名或密码为null");
			return null;
		}
		User userByselect = null;
		
		try {
			userByselect = userMapper.selectOne(user);
		} catch (Exception e) {
			LOG.info("查询到多个用户，发生异常");
		}
		
		if (userByselect == null) {
			LOG.info("用户名或密码不正确");
			return null;
		}
		
		// 构建返回对象
		Integer uid = userByselect.getId();
		Boolean isAdmin = userByselect.getIsAdmin();
		Integer msgauth = userByselect.getMesgAuthority();
		String token = TokenUtils.getToken(uid + "");
		List<Authority> authorities = authorityMapper.selectAuthorityByUid(uid);
		
		AuthVo authVo = new AuthVo();
		authVo.setAdmin(isAdmin);
		authVo.setAuthority(authorities);
		authVo.setMsgauth(msgauth);
		authVo.setToken(token);
		
		// 登录日志
		String sysLogNum = LogUtils.getSysLogNum();
		String ipAddress = RequestUtils.getIpAddress(request);
		LogSys logSys = new LogSys();
		logSys.setContent(LOGEnum.AUTH_POST.toString());
		logSys.setIpaddress(ipAddress);
		logSys.setNum(sysLogNum);
		logSys.setOperator(username);
		logSys.setResult("成功");
		logSysMapper.insertSelective(logSys);
		
		
		return authVo;
	}

	/**
	 * 修改密码
	 */
	@Override
	public Integer changePassword(String preUserpass, String userpass)
	{
		// 查询原密码是否正确，不正确返回-1；
		String token = request.getHeader("token");
		Map<String, Claim> claims = TokenUtils.verifyToken(token);
		
		if (claims == null) { // 解析Token失败了
			return -2;
		}
		String uid = TokenUtils.getInfo(claims, "uid");
		int id = Integer.parseInt(uid);
		User user = userMapper.selectByPrimaryKey(id);
		String preUserpass2 = user.getUserpass();
		
		if (!preUserpass.equals(preUserpass2)) {
			return -1;
		}
		
		// 修改密码，修改失败返回 -2；
		
		user.setUserpass(userpass);
		int res = -2;
		try {
			res = userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			LOG.info("修改密码发生异常，Exception={}",e.getMessage());
		}
		
		if (res != 1) {
			return -2;
		}
		
		return res;
	}
	
}
