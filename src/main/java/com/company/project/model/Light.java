package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Light {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 灯具名称
     */
    @Column(name = "attr_name")
    private String attrName;

    /**
     * 灯具编号
     */
    @Column(name = "attr_num")
    private String attrNum;

    /**
     * 手机号
     */
    @Column(name = "attr_phone")
    private String attrPhone;

    /**
     * 过期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "attr_deadtime")
    private Date attrDeadtime;

    /**
     * 线路节点id
     */
    @Column(name = "attr_nodeid")
    private Integer attrNodeid;

    /**
     * 经度
     */
    @Column(name = "attr_longitude")
    private Double attrLongitude;

    /**
     * 纬度
     */
    @Column(name = "attr_latitude")
    private Double attrLatitude;

    /**
     * GPS上报经度
     */
    @Column(name = "gps_longitude")
    private Double gpsLongitude;

    /**
     * GPS上报纬度
     */
    @Column(name = "gps_latitude")
    private Double gpsLatitude;

    /**
     * GPS上报海拔
     */
    @Column(name = "gps_elevation")
    private Integer gpsElevation;

    /**
     * 锁定指示（0：未锁定；1：已锁定）
     */
    @Column(name = "gps_lock")
    private Byte gpsLock;

    /**
     * 4G强度
     */
    @Column(name = "4g_signal")
    private String fgSignal;

    /**
     * ICCID码
     */
    @Column(name = "4g_iccid")
    private String fgIccid;

    /**
     * 总电流(单位毫安）
     */
    @Column(name = "total_electricity")
    private Integer totalElectricity;

    /**
     * 灯具电流
     */
    @Column(name = "lamps_electricity")
    private Integer lampsElectricity;

    /**
     * 总电压(单位mv）
     */
    @Column(name = "total_voltage")
    private Integer totalVoltage;

    /**
     * 灯具电压
     */
    @Column(name = "lamps_voltage")
    private Integer lampsVoltage;

    /**
     * 温度
     */
    private Integer temperature;

    /**
     * 故障指示
     */
    @Column(name = "fault_indicate")
    private Integer faultIndicate;

    /**
     * 白天夜间指示
     */
    @Column(name = "day_indicate")
    private Integer dayIndicate;

    /**
     * 心跳频率上报值
     */
    private Integer heartfrequency;

    /**
     * 白天灯具频率上报值
     */
    @Column(name = "lamp_day_frequency")
    private Integer lampDayFrequency;

    /**
     * 夜间灯具频率上报值
     */
    @Column(name = "lamp_night_frequency")
    private Integer lampNightFrequency;

    /**
     * 白天灯具状态上报值
     */
    @Column(name = "lamp_day_state")
    private Integer lampDayState;

    /**
     * 夜间灯具状态上报值
     */
    @Column(name = "lamp_night_state")
    private Integer lampNightState;

    /**
     * 白天蜂鸣器状态上报值
     */
    @Column(name = "lamp_buzzer_day")
    private Integer lampBuzzerDay;

    /**
     * 夜间蜂鸣器状态上报值
     */
    @Column(name = "lamp_buzzer_night")
    private Integer lampBuzzerNight;

    /**
     * 白天心跳设定值
     */
    @Column(name = "set_day_frequency")
    private Integer setDayFrequency;

    /**
     * 夜间心跳设定值
     */
    @Column(name = "set_night_frequency")
    private Integer setNightFrequency;

    /**
     * 白天灯具状态设定状态
     */
    @Column(name = "set_day_state")
    private Integer setDayState;

    /**
     * 夜间灯具状态设定值
     */
    @Column(name = "set_night_state")
    private Integer setNightState;

    /**
     * 白天蜂鸣器状态设定值
     */
    @Column(name = "set_day_buzzer")
    private Integer setDayBuzzer;

    /**
     * 夜间蜂鸣器状态设定值
     */
    @Column(name = "set_night_buzzer")
    private Integer setNightBuzzer;

    /**
     * 心跳发送频率设置值
     */
    @Column(name = "set_heartfrequency")
    private Integer setHeartfrequency;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 心跳更新时间
     */
    private Date heartupdatetime;

    /**
     * 灯具修改时间
     */
    private Date updatetime;

    /**
     * 是否被删除（0：未被删除；1：已经被删除）
     */
    @Column(name = "is_del")
    private Byte isDel;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取归属名称
     *
     * @return attr_belonging - 归属名称
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * 设置归属名称
     *
     * @param attrBelonging 归属名称
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * 获取灯具编号
     *
     * @return attr_num - 灯具编号
     */
    public String getAttrNum() {
        return attrNum;
    }

    /**
     * 设置灯具编号
     *
     * @param attrNum 灯具编号
     */
    public void setAttrNum(String attrNum) {
        this.attrNum = attrNum;
    }

    /**
     * 获取手机号
     *
     * @return attr_phone - 手机号
     */
    public String getAttrPhone() {
        return attrPhone;
    }

    /**
     * 设置手机号
     *
     * @param attrPhone 手机号
     */
    public void setAttrPhone(String attrPhone) {
        this.attrPhone = attrPhone;
    }

    /**
     * 获取过期时间
     *
     * @return attr_deadtime - 过期时间
     */
    public Date getAttrDeadtime() {
        return attrDeadtime;
    }

    /**
     * 设置过期时间
     *
     * @param attrDeadtime 过期时间
     */
    public void setAttrDeadtime(Date attrDeadtime) {
        this.attrDeadtime = attrDeadtime;
    }

    /**
     * 获取线路节点id
     *
     * @return attr_nodeid - 线路节点id
     */
    public Integer getAttrNodeid() {
        return attrNodeid;
    }

    /**
     * 设置线路节点id
     *
     * @param attrNodeid 线路节点id
     */
    public void setAttrNodeid(Integer attrNodeid) {
        this.attrNodeid = attrNodeid;
    }

    /**
     * 获取经度
     *
     * @return attr_longitude - 经度
     */
    public Double getAttrLongitude() {
        return attrLongitude;
    }

    /**
     * 设置经度
     *
     * @param attrLongitude 经度
     */
    public void setAttrLongitude(Double attrLongitude) {
        this.attrLongitude = attrLongitude;
    }

    /**
     * 获取纬度
     *
     * @return attr_latitude - 纬度
     */
    public Double getAttrLatitude() {
        return attrLatitude;
    }

    /**
     * 设置纬度
     *
     * @param attrLatitude 纬度
     */
    public void setAttrLatitude(Double attrLatitude) {
        this.attrLatitude = attrLatitude;
    }

    /**
     * 获取GPS上报经度
     *
     * @return gps_longitude - GPS上报经度
     */
    public Double getGpsLongitude() {
        return gpsLongitude;
    }

    /**
     * 设置GPS上报经度
     *
     * @param gpsLongitude GPS上报经度
     */
    public void setGpsLongitude(Double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    /**
     * 获取GPS上报纬度
     *
     * @return gps_latitude - GPS上报纬度
     */
    public Double getGpsLatitude() {
        return gpsLatitude;
    }

    /**
     * 设置GPS上报纬度
     *
     * @param gpsLatitude GPS上报纬度
     */
    public void setGpsLatitude(Double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    /**
     * 获取GPS上报海拔
     *
     * @return gps_elevation - GPS上报海拔
     */
    public Integer getGpsElevation() {
        return gpsElevation;
    }

    /**
     * 设置GPS上报海拔
     *
     * @param gpsElevation GPS上报海拔
     */
    public void setGpsElevation(Integer gpsElevation) {
        this.gpsElevation = gpsElevation;
    }

    /**
     * 获取锁定指示（0：未锁定；1：已锁定）
     *
     * @return gps_lock - 锁定指示（0：未锁定；1：已锁定）
     */
    public Byte getGpsLock() {
        return gpsLock;
    }

    /**
     * 设置锁定指示（0：未锁定；1：已锁定）
     *
     * @param gpsLock 锁定指示（0：未锁定；1：已锁定）
     */
    public void setGpsLock(Byte gpsLock) {
        this.gpsLock = gpsLock;
    }

    /**
     * 获取4G强度
     *
     * @return 4g_signal - 4G强度
     */
    public String get4gSignal() {
        return fgSignal;
    }

    /**
     * 设置4G强度
     *
     * @param 4gSignal 4G强度
     */
    public void set4gSignal(String fgSignal) {
        this.fgSignal = fgSignal;
    }

    /**
     * 获取ICCID码
     *
     * @return 4g_iccid - ICCID码
     */
    public String get4gIccid() {
        return fgIccid;
    }

    /**
     * 设置ICCID码
     *
     * @param 4gIccid ICCID码
     */
    public void set4gIccid(String fgIccid) {
        this.fgIccid = fgIccid;
    }

    /**
     * 获取总电流(单位毫安）
     *
     * @return total_electricity - 总电流(单位毫安）
     */
    public Integer getTotalElectricity() {
        return totalElectricity;
    }

    /**
     * 设置总电流(单位毫安）
     *
     * @param totalElectricity 总电流(单位毫安）
     */
    public void setTotalElectricity(Integer totalElectricity) {
        this.totalElectricity = totalElectricity;
    }

    /**
     * 获取灯具电流
     *
     * @return lamps_electricity - 灯具电流
     */
    public Integer getLampsElectricity() {
        return lampsElectricity;
    }

    /**
     * 设置灯具电流
     *
     * @param lampsElectricity 灯具电流
     */
    public void setLampsElectricity(Integer lampsElectricity) {
        this.lampsElectricity = lampsElectricity;
    }

    /**
     * 获取总电压(单位mv）
     *
     * @return total_voltage - 总电压(单位mv）
     */
    public Integer getTotalVoltage() {
        return totalVoltage;
    }

    /**
     * 设置总电压(单位mv）
     *
     * @param totalVoltage 总电压(单位mv）
     */
    public void setTotalVoltage(Integer totalVoltage) {
        this.totalVoltage = totalVoltage;
    }

    /**
     * 获取灯具电压
     *
     * @return lamps_voltage - 灯具电压
     */
    public Integer getLampsVoltage() {
        return lampsVoltage;
    }

    /**
     * 设置灯具电压
     *
     * @param lampsVoltage 灯具电压
     */
    public void setLampsVoltage(Integer lampsVoltage) {
        this.lampsVoltage = lampsVoltage;
    }

    /**
     * 获取温度
     *
     * @return temperature - 温度
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * 设置温度
     *
     * @param temperature 温度
     */
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    /**
     * 获取故障指示
     *
     * @return fault_indicate - 故障指示
     */
    public Integer getFaultIndicate() {
        return faultIndicate;
    }

    /**
     * 设置故障指示
     *
     * @param faultIndicate 故障指示
     */
    public void setFaultIndicate(Integer faultIndicate) {
        this.faultIndicate = faultIndicate;
    }

    /**
     * 获取白天夜间指示
     *
     * @return day_indicate - 白天夜间指示
     */
    public Integer getDayIndicate() {
        return dayIndicate;
    }

    /**
     * 设置白天夜间指示
     *
     * @param dayIndicate 白天夜间指示
     */
    public void setDayIndicate(Integer dayIndicate) {
        this.dayIndicate = dayIndicate;
    }

    /**
     * 获取心跳频率上报值
     *
     * @return heartfrequency - 心跳频率上报值
     */
    public Integer getHeartfrequency() {
        return heartfrequency;
    }

    /**
     * 设置心跳频率上报值
     *
     * @param heartfrequency 心跳频率上报值
     */
    public void setHeartfrequency(Integer heartfrequency) {
        this.heartfrequency = heartfrequency;
    }

    /**
     * 获取白天灯具频率上报值
     *
     * @return lamp_day_frequency - 白天灯具频率上报值
     */
    public Integer getLampDayFrequency() {
        return lampDayFrequency;
    }

    /**
     * 设置白天灯具频率上报值
     *
     * @param lampDayFrequency 白天灯具频率上报值
     */
    public void setLampDayFrequency(Integer lampDayFrequency) {
        this.lampDayFrequency = lampDayFrequency;
    }

    /**
     * 获取夜间灯具频率上报值
     *
     * @return lamp_night_frequency - 夜间灯具频率上报值
     */
    public Integer getLampNightFrequency() {
        return lampNightFrequency;
    }

    /**
     * 设置夜间灯具频率上报值
     *
     * @param lampNightFrequency 夜间灯具频率上报值
     */
    public void setLampNightFrequency(Integer lampNightFrequency) {
        this.lampNightFrequency = lampNightFrequency;
    }

    /**
     * 获取白天灯具状态上报值
     *
     * @return lamp_day_state - 白天灯具状态上报值
     */
    public Integer getLampDayState() {
        return lampDayState;
    }

    /**
     * 设置白天灯具状态上报值
     *
     * @param lampDayState 白天灯具状态上报值
     */
    public void setLampDayState(Integer lampDayState) {
        this.lampDayState = lampDayState;
    }

    /**
     * 获取夜间灯具状态上报值
     *
     * @return lamp_night_state - 夜间灯具状态上报值
     */
    public Integer getLampNightState() {
        return lampNightState;
    }

    /**
     * 设置夜间灯具状态上报值
     *
     * @param lampNightState 夜间灯具状态上报值
     */
    public void setLampNightState(Integer lampNightState) {
        this.lampNightState = lampNightState;
    }

    /**
     * 获取白天蜂鸣器状态上报值
     *
     * @return lamp_buzzer_day - 白天蜂鸣器状态上报值
     */
    public Integer getLampBuzzerDay() {
        return lampBuzzerDay;
    }

    /**
     * 设置白天蜂鸣器状态上报值
     *
     * @param lampBuzzerDay 白天蜂鸣器状态上报值
     */
    public void setLampBuzzerDay(Integer lampBuzzerDay) {
        this.lampBuzzerDay = lampBuzzerDay;
    }

    /**
     * 获取夜间蜂鸣器状态上报值
     *
     * @return lamp_buzzer_night - 夜间蜂鸣器状态上报值
     */
    public Integer getLampBuzzerNight() {
        return lampBuzzerNight;
    }

    /**
     * 设置夜间蜂鸣器状态上报值
     *
     * @param lampBuzzerNight 夜间蜂鸣器状态上报值
     */
    public void setLampBuzzerNight(Integer lampBuzzerNight) {
        this.lampBuzzerNight = lampBuzzerNight;
    }

    /**
     * 获取白天心跳设定值
     *
     * @return set_day_frequency - 白天心跳设定值
     */
    public Integer getSetDayFrequency() {
        return setDayFrequency;
    }

    /**
     * 设置白天心跳设定值
     *
     * @param setDayFrequency 白天心跳设定值
     */
    public void setSetDayFrequency(Integer setDayFrequency) {
        this.setDayFrequency = setDayFrequency;
    }

    /**
     * 获取夜间心跳设定值
     *
     * @return set_night_frequency - 夜间心跳设定值
     */
    public Integer getSetNightFrequency() {
        return setNightFrequency;
    }

    /**
     * 设置夜间心跳设定值
     *
     * @param setNightFrequency 夜间心跳设定值
     */
    public void setSetNightFrequency(Integer setNightFrequency) {
        this.setNightFrequency = setNightFrequency;
    }

    /**
     * 获取白天灯具状态设定状态
     *
     * @return set_day_state - 白天灯具状态设定状态
     */
    public Integer getSetDayState() {
        return setDayState;
    }

    /**
     * 设置白天灯具状态设定状态
     *
     * @param setDayState 白天灯具状态设定状态
     */
    public void setSetDayState(Integer setDayState) {
        this.setDayState = setDayState;
    }

    /**
     * 获取夜间灯具状态设定值
     *
     * @return set_night_state - 夜间灯具状态设定值
     */
    public Integer getSetNightState() {
        return setNightState;
    }

    /**
     * 设置夜间灯具状态设定值
     *
     * @param setNightState 夜间灯具状态设定值
     */
    public void setSetNightState(Integer setNightState) {
        this.setNightState = setNightState;
    }

    /**
     * 获取白天蜂鸣器状态设定值
     *
     * @return set_day_buzzer - 白天蜂鸣器状态设定值
     */
    public Integer getSetDayBuzzer() {
        return setDayBuzzer;
    }

    /**
     * 设置白天蜂鸣器状态设定值
     *
     * @param setDayBuzzer 白天蜂鸣器状态设定值
     */
    public void setSetDayBuzzer(Integer setDayBuzzer) {
        this.setDayBuzzer = setDayBuzzer;
    }

    /**
     * 获取夜间蜂鸣器状态设定值
     *
     * @return set_night_buzzer - 夜间蜂鸣器状态设定值
     */
    public Integer getSetNightBuzzer() {
        return setNightBuzzer;
    }

    /**
     * 设置夜间蜂鸣器状态设定值
     *
     * @param setNightBuzzer 夜间蜂鸣器状态设定值
     */
    public void setSetNightBuzzer(Integer setNightBuzzer) {
        this.setNightBuzzer = setNightBuzzer;
    }

    /**
     * 获取心跳发送频率设置值
     *
     * @return set_heartfrequency - 心跳发送频率设置值
     */
    public Integer getSetHeartfrequency() {
        return setHeartfrequency;
    }

    /**
     * 设置心跳发送频率设置值
     *
     * @param setHeartfrequency 心跳发送频率设置值
     */
    public void setSetHeartfrequency(Integer setHeartfrequency) {
        this.setHeartfrequency = setHeartfrequency;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取心跳更新时间
     *
     * @return heartupdatetime - 心跳更新时间
     */
    public Date getHeartupdatetime() {
        return heartupdatetime;
    }

    /**
     * 设置心跳更新时间
     *
     * @param heartupdatetime 心跳更新时间
     */
    public void setHeartupdatetime(Date heartupdatetime) {
        this.heartupdatetime = heartupdatetime;
    }

    /**
     * 获取灯具修改时间
     *
     * @return updatetime - 灯具修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置灯具修改时间
     *
     * @param updatetime 灯具修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取是否被删除（0：未被删除；1：已经被删除）
     *
     * @return is_del - 是否被删除（0：未被删除；1：已经被删除）
     */
    public Byte getIsDel() {
        return isDel;
    }

    /**
     * 设置是否被删除（0：未被删除；1：已经被删除）
     *
     * @param isDel 是否被删除（0：未被删除；1：已经被删除）
     */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

	@Override
	public String toString()
	{
		return "Light [id=" + id + ", attrName=" + attrName + ", attrNum=" + attrNum + ", attrPhone="
				+ attrPhone + ", attrDeadtime=" + attrDeadtime + ", attrNodeid=" + attrNodeid + ", attrLongitude="
				+ attrLongitude + ", attrLatitude=" + attrLatitude + ", gpsLongitude=" + gpsLongitude + ", gpsLatitude="
				+ gpsLatitude + ", gpsElevation=" + gpsElevation + ", gpsLock=" + gpsLock + ", fgSignal=" + fgSignal
				+ ", fgIccid=" + fgIccid + ", totalElectricity=" + totalElectricity + ", lampsElectricity="
				+ lampsElectricity + ", totalVoltage=" + totalVoltage + ", lampsVoltage=" + lampsVoltage
				+ ", temperature=" + temperature + ", faultIndicate=" + faultIndicate + ", dayIndicate=" + dayIndicate
				+ ", heartfrequency=" + heartfrequency + ", lampDayFrequency=" + lampDayFrequency
				+ ", lampNightFrequency=" + lampNightFrequency + ", lampDayState=" + lampDayState + ", lampNightState="
				+ lampNightState + ", lampBuzzerDay=" + lampBuzzerDay + ", lampBuzzerNight=" + lampBuzzerNight
				+ ", setDayFrequency=" + setDayFrequency + ", setNightFrequency=" + setNightFrequency + ", setDayState="
				+ setDayState + ", setNightState=" + setNightState + ", setDayBuzzer=" + setDayBuzzer
				+ ", setNightBuzzer=" + setNightBuzzer + ", setHeartfrequency=" + setHeartfrequency + ", ctime=" + ctime
				+ ", heartupdatetime=" + heartupdatetime + ", updatetime=" + updatetime + ", isDel=" + isDel + "]";
	}
}