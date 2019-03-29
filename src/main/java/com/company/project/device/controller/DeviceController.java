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
import com.company.project.device.dto.DataDto;
import com.company.project.device.dto.QueryDto;
import com.company.project.device.dto.ResultDto;
import com.company.project.device.dto.SettingDto;
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
	
	@GetMapping("/query")
	public Result query(QueryDto queryDto) {
		
		LOG.info("发送查询指令={}",queryDto);
		
		if (queryDto == null) {
			LOG.debug("参数错误");
			return ResultGenerator.genFailResult("参数错误");
		}
		
		Result result = deviceService.query(queryDto);
		
		return result;
	}
	
	@PutMapping("/setting")
	public Result setting (@RequestBody SettingDto settingDto) {
		LOG.info("发送控制指令={}",settingDto);
		if (settingDto == null) {
			return ResultGenerator.genFailResult("参数错误");
		}
		Result result = deviceService.setting(settingDto);
		return result;
	}
	
	@PostMapping("/data")
	public Result getData(@RequestBody DataDto dataDto) {
		LOG.info("接收数据={}",dataDto);
		
		if (dataDto == null) {
			return ResultGenerator.genFailResult("参数错误");
		}
		
		Result result = deviceService.handleData(dataDto);
		return result;
	}
}
