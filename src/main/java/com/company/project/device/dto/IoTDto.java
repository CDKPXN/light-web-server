package com.company.project.device.dto;

public class IoTDto {
	private String attrNum;
	private String type;
	private String setHeartfrequency;
	private String setDayFrequency;
	private String setNightFrequency;
	private String setDayState;
	private String setNightState;
	private String setDayBuzzer;
	private String setNightBuzzer;
	public String getAttrNum() {
		return attrNum;
	}
	public void setAttrNum(String attrNum) {
		this.attrNum = attrNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSetHeartfrequency() {
		return setHeartfrequency;
	}
	public void setSetHeartfrequency(String setHeartfrequency) {
		this.setHeartfrequency = setHeartfrequency;
	}
	public String getSetDayFrequency() {
		return setDayFrequency;
	}
	public void setSetDayFrequency(String setDayFrequency) {
		this.setDayFrequency = setDayFrequency;
	}
	public String getSetNightFrequency() {
		return setNightFrequency;
	}
	public void setSetNightFrequency(String setNightFrequency) {
		this.setNightFrequency = setNightFrequency;
	}
	public String getSetDayState() {
		return setDayState;
	}
	public void setSetDayState(String setDayState) {
		this.setDayState = setDayState;
	}
	public String getSetNightState() {
		return setNightState;
	}
	public void setSetNightState(String setNightState) {
		this.setNightState = setNightState;
	}
	public String getSetDayBuzzer() {
		return setDayBuzzer;
	}
	public void setSetDayBuzzer(String setDayBuzzer) {
		this.setDayBuzzer = setDayBuzzer;
	}
	public String getSetNightBuzzer() {
		return setNightBuzzer;
	}
	public void setSetNightBuzzer(String setNightBuzzer) {
		this.setNightBuzzer = setNightBuzzer;
	}
	@Override
	public String toString() {
		return "IoTDto [attrNum=" + attrNum + ", type=" + type + ", setHeartfrequency=" + setHeartfrequency
				+ ", setDayFrequency=" + setDayFrequency + ", setNightFrequency=" + setNightFrequency + ", setDayState="
				+ setDayState + ", setNightState=" + setNightState + ", setDayBuzzer=" + setDayBuzzer
				+ ", setNightBuzzer=" + setNightBuzzer + "]";
	}
	
}
