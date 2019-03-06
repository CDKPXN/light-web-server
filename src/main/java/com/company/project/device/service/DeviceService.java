package com.company.project.device.service;

import java.util.List;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.device.dto.QueryDto;
import com.company.project.device.dto.ResultDto;
import com.company.project.model.Devicedata;
import com.company.project.model.Light;

public interface DeviceService extends Service<Devicedata> {

	/**
	 * 设置心跳
	 * @param attrNum
	 * @param heartfrequency
	 * @return
	 */
	Light setHeart(String attrNum, Integer heartfrequency);

	/**
	 * 刷新4G信息
	 * @param attrNum
	 * @return
	 */
	Light refresh4G(String attrNum);

	/**
	 * 刷新GPS
	 * @param attrNum
	 * @return
	 */
	Light refreshGPS(String attrNum);

	/**
	 * 设置蜂鸣器状态
	 * @param attrNum
	 * @param lampBuzzerDay
	 * @param lampBuzzerNight
	 * @return
	 */
	Light setBuzzer(String attrNum, Integer lampBuzzerDay, Integer lampBuzzerNight);

	/**
	 * 设置灯具状态
	 * @param attrNum
	 * @param lampDayFrequency
	 * @param lampNightFrequency
	 * @param lampDayState
	 * @param lampNightState
	 * @return
	 */
	Light setLight(String attrNum, Integer lampDayFrequency, Integer lampNightFrequency, Integer lampDayState,
			Integer lampNightState);

	/**
	 * 心跳自动上报
	 * @param light
	 */
	void heartAutoReport(Light light);

	/**
	 * 设备上线
	 * @param attrNum
	 */
	void lightOn(String attrNum);

	/**
	 * 设备下线
	 * @param attrNum
	 */
	void lightOff(String attrNum);

	/**
	 * 查询
	 * @param queryDto
	 * @return
	 */
	Result query(QueryDto queryDto);

}
