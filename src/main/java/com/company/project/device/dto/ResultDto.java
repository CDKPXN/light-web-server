package com.company.project.device.dto;

public class ResultDto {
	private String attrNum; // 设备编号
	private Integer code; // 成功或失败，200：成功；400：失败
	public String getAttrNum() {
		return attrNum;
	}
	public void setAttrNum(String attrNum) {
		this.attrNum = attrNum;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "ResultDto [attrNum=" + attrNum + ", code=" + code + "]";
	}
	
}
