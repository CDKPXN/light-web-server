package com.company.project.service;

import com.company.project.model.User;
import com.company.project.vo.AuthVo;

public interface AuthService
{
	/**
	 * 登录
	 * @return
	 */
	AuthVo Login(User user);

	/**
	 * 修改密码
	 * @param preUserpass 原密码
	 * @param userpass 新密码
	 * @return
	 */
	Integer changePassword(String preUserpass, String userpass);
}
