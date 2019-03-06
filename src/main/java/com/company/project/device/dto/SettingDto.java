package com.company.project.device.dto;

import java.util.List;

import com.company.project.model.Light;

public class SettingDto {
	private List<String> attrNum; // 要设置的灯具编号
	private String type; // 要设置的类型
	private Light data; // 要设置的参数
	
	public List<String> getAttrNum() {
		return attrNum;
	}
	public void setAttrNum(List<String> attrNum) {
		this.attrNum = attrNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Light getData() {
		return data;
	}
	public void setData(Light data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "SettingVo [attrNum=" + attrNum + ", type=" + type + ", data=" + data + "]";
	}
}
