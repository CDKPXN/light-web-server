package com.company.project.device.dto;

public class DataDto {
	private String type;
	private String data;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "DataDto [type=" + type + ", data=" + data + "]";
	}
}
