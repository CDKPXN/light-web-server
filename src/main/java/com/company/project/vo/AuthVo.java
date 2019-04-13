package com.company.project.vo;

import java.util.List;

import com.company.project.model.Authority;

public class AuthVo
{
	private String Token; // token
	private boolean isAdmin; // 是否是超级管理员
	private Integer msgauth; // 消息权限
	private Integer uid; // 用户id
	private List<Authority> authority; // 节点及权限的list
	private String username; // 用户名
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getToken()
	{
		return Token;
	}
	public void setToken(String token)
	{
		Token = token;
	}
	public boolean isAdmin()
	{
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	public Integer getMsgauth()
	{
		return msgauth;
	}
	public void setMsgauth(Integer msgauth)
	{
		this.msgauth = msgauth;
	}
	public List<Authority> getAuthority()
	{
		return authority;
	}
	public void setAuthority(List<Authority> authority)
	{
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "AuthVo [Token=" + Token + ", isAdmin=" + isAdmin + ", msgauth=" + msgauth + ", uid=" + uid
				+ ", authority=" + authority + "]";
	}
	
}
