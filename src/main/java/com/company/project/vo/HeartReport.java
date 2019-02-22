package com.company.project.vo;

public class HeartReport {
	private String lightnum;
	private Integer total_electricity; // 总电流
	private Integer lamps_electricity; // 灯电流
	private Integer total_voltage; // 总电压
	private Integer lamps_voltage; // 灯电压
	private Integer temperature; // 温度
	private Integer fault_indicate; // 错误指示
	private Integer day_indicate; // 白天夜间指示
	private Integer heartfrequency; // 心跳频率
	private Integer lamp_day_frequency; // 灯具白天频率
	private Integer lamp_night_frequency; // 灯具夜间频率
	private Integer lamp_day_state; // 灯具白天状态
	private Integer lamp_night_state; // 灯具夜间状态
	private Integer lamp_buzzer_day; // 蜂鸣器白天状态
	private Integer lamp_buzzer_night; // 蜂鸣器夜间状态
	public String getLightnum() {
		return lightnum;
	}
	public void setLightnum(String lightnum) {
		this.lightnum = lightnum;
	}
	public Integer getTotal_electricity() {
		return total_electricity;
	}
	public void setTotal_electricity(Integer total_electricity) {
		this.total_electricity = total_electricity;
	}
	public Integer getLamps_electricity() {
		return lamps_electricity;
	}
	public void setLamps_electricity(Integer lamps_electricity) {
		this.lamps_electricity = lamps_electricity;
	}
	public Integer getTotal_voltage() {
		return total_voltage;
	}
	public void setTotal_voltage(Integer total_voltage) {
		this.total_voltage = total_voltage;
	}
	public Integer getLamps_voltage() {
		return lamps_voltage;
	}
	public void setLamps_voltage(Integer lamps_voltage) {
		this.lamps_voltage = lamps_voltage;
	}
	public Integer getTemperature() {
		return temperature;
	}
	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}
	public Integer getFault_indicate() {
		return fault_indicate;
	}
	public void setFault_indicate(Integer fault_indicate) {
		this.fault_indicate = fault_indicate;
	}
	public Integer getDay_indicate() {
		return day_indicate;
	}
	public void setDay_indicate(Integer day_indicate) {
		this.day_indicate = day_indicate;
	}
	public Integer getHeartfrequency() {
		return heartfrequency;
	}
	public void setHeartfrequency(Integer heartfrequency) {
		this.heartfrequency = heartfrequency;
	}
	public Integer getLamp_day_frequency() {
		return lamp_day_frequency;
	}
	public void setLamp_day_frequency(Integer lamp_day_frequency) {
		this.lamp_day_frequency = lamp_day_frequency;
	}
	public Integer getLamp_night_frequency() {
		return lamp_night_frequency;
	}
	public void setLamp_night_frequency(Integer lamp_night_frequency) {
		this.lamp_night_frequency = lamp_night_frequency;
	}
	public Integer getLamp_day_state() {
		return lamp_day_state;
	}
	public void setLamp_day_state(Integer lamp_day_state) {
		this.lamp_day_state = lamp_day_state;
	}
	public Integer getLamp_night_state() {
		return lamp_night_state;
	}
	public void setLamp_night_state(Integer lamp_night_state) {
		this.lamp_night_state = lamp_night_state;
	}
	public Integer getLamp_buzzer_day() {
		return lamp_buzzer_day;
	}
	public void setLamp_buzzer_day(Integer lamp_buzzer_day) {
		this.lamp_buzzer_day = lamp_buzzer_day;
	}
	public Integer getLamp_buzzer_night() {
		return lamp_buzzer_night;
	}
	public void setLamp_buzzer_night(Integer lamp_buzzer_night) {
		this.lamp_buzzer_night = lamp_buzzer_night;
	}
	@Override
	public String toString() {
		return "HeartReport [lightnum=" + lightnum + ", total_electricity=" + total_electricity + ", lamps_electricity="
				+ lamps_electricity + ", total_voltage=" + total_voltage + ", lamps_voltage=" + lamps_voltage
				+ ", temperature=" + temperature + ", fault_indicate=" + fault_indicate + ", day_indicate="
				+ day_indicate + ", heartfrequency=" + heartfrequency + ", lamp_day_frequency=" + lamp_day_frequency
				+ ", lamp_night_frequency=" + lamp_night_frequency + ", lamp_day_state=" + lamp_day_state
				+ ", lamp_night_state=" + lamp_night_state + ", lamp_buzzer_day=" + lamp_buzzer_day
				+ ", lamp_buzzer_night=" + lamp_buzzer_night + "]";
	}
	
}
