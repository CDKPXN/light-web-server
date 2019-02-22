package com.company.project.web;

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

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Light;
import com.company.project.service.DeviceService;
import com.company.project.vo.HeartReport;

@RestController
@RequestMapping("/api/cmd")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
	private static final Logger LOG = LoggerFactory.getLogger(DeviceController.class);
	
	
	@GetMapping("/heart")
	public Result refreshHeart(@RequestParam String lightnum,@RequestParam Integer heartfrequency) {
		return ResultGenerator.genSuccessResult();
	}
	
	@PutMapping("/heart")
	public Result setHeart(@RequestParam String lightnum,@RequestParam Integer heartfrequency) {
		return ResultGenerator.genSuccessResult();
	}
	
	@GetMapping("/refresh4g")
	public Result refresh4G(@RequestParam String lightnum) {
		return ResultGenerator.genSuccessResult();
	}
	
	@GetMapping("/refreshgps")
	public Result refreshGPS(@RequestParam String lightnum) {
		return ResultGenerator.genSuccessResult();
	}
	
	@PutMapping("/buzzer")
	public Result setBuzzer(@RequestParam String lightnum, @RequestParam Integer lamp_buzzer_day, @RequestParam Integer lamp_buzzer_night) {
		return ResultGenerator.genSuccessResult();
	}
	
	@PutMapping("/light")
	public Result setLight(@RequestParam String lightnum,@RequestParam Integer heartfrequency) {
		return ResultGenerator.genSuccessResult();
	}
	
	@PostMapping("/heart")
	public Result heartAutoReport(@RequestBody Light light) {
		LOG.info("上报心跳");
		return ResultGenerator.genSuccessResult();
	}
	
	@PutMapping("/on")
	public Result on(@RequestParam String lightnum) {
		return ResultGenerator.genSuccessResult();
	}
	
	@PutMapping("/off")
	public Result off(@RequestParam String lightnum) {
		return ResultGenerator.genSuccessResult();
	}
	
}
