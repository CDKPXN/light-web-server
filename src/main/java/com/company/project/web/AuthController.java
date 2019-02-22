package com.company.project.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.AuthService;
import com.company.project.vo.AuthVo;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthService authService;
	
	@PostMapping
	public Result login(@RequestBody User user) {
		LOG.info("用户登录，user={}",user);
		if (user == null) {
			return ResultGenerator.genFailResult("参数不正确");
		}
		
		AuthVo authVo = authService.Login(user);
		
		if (authVo == null) {
			return ResultGenerator.genFailResult("用户名或密码不正确");
		}
		return ResultGenerator.genSuccessResult(authVo);
	}
	
	@PutMapping
	public Result changePassword(@RequestBody Map<String,String> map) {
		
		String preUserpass = map.get("preUserpass");
		String userpass = map.get("userpass");
		
		LOG.info("用户修改密码，原密码={}，新密码={}",preUserpass,userpass);
		
		if (StringUtils.isBlank(preUserpass) || StringUtils.isBlank(userpass)) {
			LOG.info("参数不正确");
			return ResultGenerator.genFailResult("参数不正确");
		}
		
		Integer res = authService.changePassword(preUserpass,userpass);
		
		if (res == -1) {
			LOG.info("原密码错误");
			return ResultGenerator.genFailResult("原密码错误");
		} else if (res == -2) {
			LOG.info("密码修改失败");
			return ResultGenerator.genFailResult("密码修改失败");
		}
		
		return ResultGenerator.genSuccessResult();
	}
}
