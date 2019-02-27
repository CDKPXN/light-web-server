package com.company.project.service.impl;

import com.company.project.dao.AuthorityMapper;
import com.company.project.dao.NodeUserMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Authority;
import com.company.project.model.NodeUser;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.vo.UserVo;
import com.company.project.core.AbstractService;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private AuthorityMapper authorityMapper;
    
    @Resource
    private NodeUserMapper nodeUserMapper;
    
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 查询所有用户
     */
	public List<User> findByFilter(String filter) {
		List<User> users = userMapper.findByFilter(filter);
		return users;
	}

	/**
	 * 根据id查询用户详情  
	 */
	public UserVo getUserById(Integer id) {
		LOG.info("根据id查询用户详情 id={}",id);
		
		//根据id查user
		User user = userMapper.findByUserId(id);
		
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(user, userVo);
		
		userVo.setAuthority(authorityMapper.selectOnlyAuthorityByUid(id));
		
		//根据id查节点
		List<Integer> nodeIds = userMapper.findNode(id);

		userVo.setNodeids(nodeIds);
		return userVo;
	}
	
	

	@Override
	public void delete(Integer id) {
	    LOG.info("根据id删除用户 id={}",id);
	    
	    userMapper.delete(id);
	    
	    authorityMapper.deleteByUserId(id);
	    
	    nodeUserMapper.deleteByUserId(id);
	    
		
	}

	@Override
	public Integer add(UserVo userVo) {

		Integer authority = userVo.getAuthority();
		List<Integer> nodeids = userVo.getNodeids();
		
		if (authority == null && nodeids.isEmpty()) {
			return -1;
		}
		
		try {
			
			User user = new User();
			BeanUtils.copyProperties(userVo, user);
			LOG.info("copyPropertiest之后user={}",user);
			save(user);
			
			Integer uid = user.getId();
			if (uid == null) {
				return -2;
			}
			
			List<Authority> authorities = new ArrayList<>();
			List<NodeUser> nodeUsers = new ArrayList<>();
			
			nodeids.forEach(nodeid -> {
				Authority authority2 = new Authority();
				authority2.setAuthority(authority);
				authority2.setNodeid(nodeid);
				authority2.setUid(uid);
				authority2.setCtime(new Date());
				authorities.add(authority2);
				
				NodeUser nodeUser = new NodeUser();
				nodeUser.setNodeid(nodeid);
				nodeUser.setUserid(uid);
				nodeUser.setCtime(new Date());
				nodeUsers.add(nodeUser);
			});
			
			authorityMapper.insertList(authorities);
			nodeUserMapper.insertList(nodeUsers);
		} catch (Exception e) {
			LOG.error("添加用户时插入多张表发生异常={}",e.getMessage());
			// 回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -2;
		}
		
		return 0;
	}
	

}
