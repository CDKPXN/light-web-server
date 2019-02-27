package com.company.project.vo;

import java.util.List;

import com.company.project.model.User;

public class UserVo extends User {
	
	private List<Integer> nodeids;

	public List<Integer> getNodeids() {
		return nodeids;
	}

	public void setNodeids(List<Integer> nodeids) {
		this.nodeids = nodeids;
	}

	@Override
	public String toString() {
		return "UserVo [nodeids=" + nodeids + "]";
	}
	
	
	
	

}
