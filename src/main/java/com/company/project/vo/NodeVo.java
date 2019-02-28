package com.company.project.vo;

import java.util.List;

import com.company.project.model.Node;

public class NodeVo extends Node {
	private List<NodeVo> children;

	public List<NodeVo> getChildren() {
		return children;
	}

	public void setChildren(List<NodeVo> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "NodeVo [children=" + children + "]";
	}
	
}
