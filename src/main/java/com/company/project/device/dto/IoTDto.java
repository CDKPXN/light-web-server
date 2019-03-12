package com.company.project.device.dto;

public class IoTDto {
	private String attrNum;
	private String type;
	private Integer setHeartfrequency;
	private Integer setDayFrequency;
	private Integer setNightFrequency;
	private Integer setDayState;
	private Integer setNightState;
	private Integer setDayBuzzer;
	private Integer setNightBuzzer;
	
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
	public Integer getSetHeartfrequency() {
		return setHeartfrequency;
	}
	public void setSetHeartfrequency(Integer setHeartfrequency) {
		this.setHeartfrequency = setHeartfrequency;
	}
	public Integer getSetDayFrequency() {
		return setDayFrequency;
	}
	public void setSetDayFrequency(Integer setDayFrequency) {
		this.setDayFrequency = setDayFrequency;
	}
	public Integer getSetNightFrequency() {
		return setNightFrequency;
	}
	public void setSetNightFrequency(Integer setNightFrequency) {
		this.setNightFrequency = setNightFrequency;
	}
	public Integer getSetDayState() {
		return setDayState;
	}
	public void setSetDayState(Integer setDayState) {
		this.setDayState = setDayState;
	}
	public Integer getSetNightState() {
		return setNightState;
	}
	public void setSetNightState(Integer setNightState) {
		this.setNightState = setNightState;
	}
	public Integer getSetDayBuzzer() {
		return setDayBuzzer;
	}
	public void setSetDayBuzzer(Integer setDayBuzzer) {
		this.setDayBuzzer = setDayBuzzer;
	}
	public Integer getSetNightBuzzer() {
		return setNightBuzzer;
	}
	public void setSetNightBuzzer(Integer setNightBuzzer) {
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
