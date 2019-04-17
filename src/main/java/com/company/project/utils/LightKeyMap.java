package com.company.project.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LightKeyMap {
	
	private static volatile LightKeyMap lightKeyMap = null;
	
	public Map<String, String> keyMap = new ConcurrentHashMap<>();
	
	private LightKeyMap () {
		this.keyMap.put("id", "灯具id");
		this.keyMap.put("attrName", "灯具名称");
		this.keyMap.put("attrNum", "灯具编号");
		this.keyMap.put("attrPhone", "手机号");
		this.keyMap.put("attrDeadtime", "过期时间");
		this.keyMap.put("attrNodeid", "节点id");
		this.keyMap.put("attrLongitude", "经度设置值");
		this.keyMap.put("attrLatitude", "纬度设置值");
		this.keyMap.put("gpsLongitude", "经度GPS上报值");
		this.keyMap.put("gpsLatitude", "纬度GPS上报值");
		this.keyMap.put("gpsElevation", "海拔GPS上报值");
		this.keyMap.put("gpsLock", "是否锁定");
		this.keyMap.put("fgSignal", "4G强度");
		this.keyMap.put("fgIccid", "ICCID码");
		this.keyMap.put("totalElectricity", "总电流");
		this.keyMap.put("lampsElectricity", "灯电流");
		this.keyMap.put("totalVoltage", "总电压");
		this.keyMap.put("lampsVoltage", "灯电压");
		this.keyMap.put("temperature", "温度");
		this.keyMap.put("faultIndicate", "故障指示");
		this.keyMap.put("dayIndicate", "白天夜间指示");
		this.keyMap.put("heartfrequency", "心跳频率");
		this.keyMap.put("lampDayFrequency", "白天灯具闪烁频率");
		this.keyMap.put("lampNightFrequency", "夜间灯具闪烁频率");
		this.keyMap.put("lampDayState", "白天灯具状态");
		this.keyMap.put("lampNightState", "夜间灯具状态");
		this.keyMap.put("lampBuzzerDay", "白天蜂鸣器状态");
		this.keyMap.put("lampBuzzerNight", "夜间蜂鸣器状态");
		this.keyMap.put("setDayFrequency", "白天灯具闪烁频率设定值");
		this.keyMap.put("setNightFrequency", "夜间灯具闪烁频率设定值");
		this.keyMap.put("setDayState", "白天灯具状态设定值");
		this.keyMap.put("setNightState", "夜间灯具状态设定值");
		this.keyMap.put("setDayBuzzer", "白天蜂鸣器状态设定值");
		this.keyMap.put("setNightBuzzer", "夜间蜂鸣器状态设定值");
		this.keyMap.put("setHeartfrequency", "心跳频率设定值");
		this.keyMap.put("ctime", "创建时间");
		this.keyMap.put("heartupdatetime", "心跳更新时间");
		this.keyMap.put("updatetime", "灯具修改时间");
		this.keyMap.put("isDel", "是否被删除");
	}
	
	public static LightKeyMap getInstance () {
		
		if (lightKeyMap == null) {
			synchronized(LightKeyMap.class) {
				if (lightKeyMap == null) {
					lightKeyMap = new LightKeyMap();
				}
			}
		}
		return lightKeyMap;
	}
}
