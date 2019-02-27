package com.company.project.vo;

import java.util.List;

import com.company.project.model.User;

public class UserVo extends User {
	
	private List<Integer> nodeids;

	private Integer authority;

	public List<Integer> getNodeids() {
		return nodeids;
	}

	public void setNodeids(List<Integer> nodeids) {
		this.nodeids = nodeids;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "UserVo [nodeids=" + nodeids + ", authority=" + authority + ", toString()=" + super.toString() + "]";
	}

}
