package com.company.project.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.service.LightService;
import com.company.project.vo.LightStatisticsVo;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
	private static final Logger LOG = LoggerFactory.getLogger(StatisticsController.class);
	
	@Autowired
	private LightService lightService;
	
	@GetMapping
	public Result getStatistics() {
		LOG.info("统计灯具状态==");
		LightStatisticsVo lightStatisticsVo = lightService.getStatisticsInfo();
		LOG.info("统计信息={}",lightStatisticsVo);
		Result<LightStatisticsVo> result = ResultGenerator.genSuccessResult(lightStatisticsVo);
		LOG.info("result={}",result);
		return result;
	}
}
