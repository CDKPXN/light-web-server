package com.company.project.device.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.device.dto.QueryDto;
import com.company.project.device.dto.ResultDto;
import com.company.project.device.service.DeviceService;
import com.company.project.model.Devicedata;
import com.company.project.model.Light;
import com.company.project.utils.HttpUtils;
import com.company.project.vo.HeartReport;

@RestController
@RequestMapping("/api/cmd")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
	private static final Logger LOG = LoggerFactory.getLogger(DeviceController.class);
	
//	@GetMapping("/heart")
//	public Result refreshHeart(@RequestParam String attrNum,@RequestParam Integer heartfrequency) {
//		LOG.info("刷新心跳，attrNum={}，heartfrequency={}",attrNum,heartfrequency);
//		
//		Light light = deviceService.setHeart(attrNum, heartfrequency);
//		
//		LOG.info("返回={}",light);
//		if (light == null) {
//			return ResultGenerator.genFailResult("数据返回超时");
//		}
//		return ResultGenerator.genSuccessResult(light);
//	}
//	
//	@PutMapping("/heart")
//	public Result setHeart(@RequestParam String attrNum,@RequestParam Integer heartfrequency) {
//		LOG.info("设置心跳，attrNum={}，heartfrequency={}",attrNum,heartfrequency);
//		Light light = deviceService.setHeart(attrNum, heartfrequency);
//		LOG.info("返回={}",light);
//		if (light == null) {
//			return ResultGenerator.genFailResult("数据返回超时");
//		}
//		return ResultGenerator.genSuccessResult(light);
//	}
//	
//	@GetMapping("/refresh4g")
//	public Result refresh4G(@RequestParam String attrNum) {
//		LOG.info("刷新{}的4G",attrNum);
//		Light light = deviceService.refresh4G(attrNum);
//		LOG.info("返回={}",light);
//		if (light == null) {
//			return ResultGenerator.genFailResult("数据返回超时");
//		}
//		return ResultGenerator.genSuccessResult(light);
//	}
//	
//	@GetMapping("/refreshgps")
//	public Result refreshGPS(@RequestParam String attrNum) {
//		LOG.info("刷新{}的GPS",attrNum);
//		
//		Light light = deviceService.refreshGPS(attrNum);
//		LOG.info("返回={}",light);
//		if (light == null) {
//			return ResultGenerator.genFailResult("数据返回超时");
//		}
//		return ResultGenerator.genSuccessResult(light);
//	}
//	
//	@PutMapping("/buzzer")
//	public Result setBuzzer(@RequestParam String attrNum, @RequestParam Integer lampBuzzerDay, @RequestParam Integer lampBuzzerNight) {
//		LOG.info("蜂鸣器控制,attrnum={},lampBuzzerDay={},lampBuzzerNight={}",attrNum,lampBuzzerDay,lampBuzzerNight);
//		
//		Light light = deviceService.setBuzzer(attrNum,lampBuzzerDay,lampBuzzerNight);
//		LOG.info("返回={}",light);
//		if (light == null) {
//			return ResultGenerator.genFailResult("数据返回超时");
//		}
//		return ResultGenerator.genSuccessResult(light);
//	}
//	
//	@PutMapping("/light")
//	public Result setLight(@RequestParam String attrNum,@RequestParam Integer lampDayFrequency,
//			@RequestParam Integer lampNightFrequency,@RequestParam Integer lampDayState, @RequestParam Integer lampNightState) {
//		LOG.info("灯具状态控制，attrNum={},lampDayFrequency={},lampNightFrequency={},lampDayState={},lampNightState={}",attrNum,lampDayFrequency,
//				lampNightFrequency,lampDayState,lampNightState);
//		
//		Light light = deviceService.setLight(attrNum,lampDayFrequency,lampNightFrequency,lampDayState,lampNightState);
//		LOG.info("返回={}",light);
//		if (light == null) {
//			return ResultGenerator.genFailResult("数据返回超时");
//		}
//		return ResultGenerator.genSuccessResult(light);
//	}
//	
//	@PostMapping("/heart")
//	public Result heartAutoReport(@RequestBody Light light) {
//		LOG.info("上报心跳，Light={}",light);
//		
//		deviceService.heartAutoReport(light);
//		
//		return ResultGenerator.genSuccessResult();
//	}
//	
//	@PutMapping("/on")
//	public Result on(@RequestParam String attrNum) {
//		LOG.info("设备={}上线通知",attrNum);
//		
//		deviceService.lightOn(attrNum);
//		
//		return ResultGenerator.genSuccessResult();
//	}
//	
//	@PutMapping("/off")
//	public Result off(@RequestParam String attrNum) {
//		LOG.info("设备={}下线通知",attrNum);
//		
//		deviceService.lightOff(attrNum);
//		
//		return ResultGenerator.genSuccessResult();
//	}
	
	@GetMapping("/query")
	public Result query(QueryDto queryDto) {
		
		if (queryDto == null) {
			return ResultGenerator.genFailResult("参数错误");
		}
		
		Result result = deviceService.query(queryDto);
		
		return result;
	}
}
