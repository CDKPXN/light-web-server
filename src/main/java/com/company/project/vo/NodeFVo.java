package com.company.project.vo;

import java.util.List;

import com.company.project.model.Node;

public class NodeFVo extends Node{
	private List<NodeFVo> fNodeNode;

	
	public List<NodeFVo> getfNodeNode() {
		return fNodeNode;
	}


	public void setfNodeNode(List<NodeFVo> fNodeNode) {
		this.fNodeNode = fNodeNode;
	}


	@Override
	public String toString() {
		return "NodeFVo [fNodeNode=" + fNodeNode + "]";
	}
	
}
