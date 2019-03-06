package com.company.project.device.dto;

import java.util.List;

public class QueryDto {
	
	/**
	 * 查询的类型（值可以为：4G、GPS、heart）
	 */
	private String type; 
	
	/**
	 * 需要刷新的灯具编号
	 */
	private List<String> attrNum;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getAttrNum() {
		return attrNum;
	}

	public void setAttrNum(List<String> attrNum) {
		this.attrNum = attrNum;
	}

	@Override
	public String toString() {
		return "QueryVo [type=" + type + ", attrNum=" + attrNum + "]";
	}
	
}
