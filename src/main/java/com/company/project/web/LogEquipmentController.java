package com.company.project.web;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.LogEquipment;
import com.company.project.service.LogEquipmentService;
import com.company.project.utils.ListToStringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2018/12/29.
*/
@RestController
@RequestMapping("/api/log/equipment")
public class LogEquipmentController {
    @Resource
    private LogEquipmentService logEquipmentService;

    private static final Logger LOG = LoggerFactory.getLogger(LogEquipmentController.class);
    
    /**
	 * 自定义 日期转换
	 * @param request
	 * @param binder
	 */
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    
    @PostMapping
    public Result add(@RequestBody LogEquipment logEquipment) {
        logEquipmentService.save(logEquipment);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        logEquipmentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody LogEquipment logEquipment) {
        logEquipmentService.update(logEquipment);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        LogEquipment logEquipment = logEquipmentService.findById(id);
        return ResultGenerator.genSuccessResult(logEquipment);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "0") Integer pageSize, 
    		@RequestParam(defaultValue = "1970-1-1") Date startTime, 
    		@RequestParam(defaultValue = "2099-12-31") Date endTime, 
    		@RequestParam(required = false) String searchContent) {
        
    	LOG.info("查询全部设备日志，startDate={},endDate={},searchContent={}",startTime, endTime, searchContent);
    	PageInfo info = logEquipmentService.getAllEquipmentLogs(startTime, endTime, searchContent, pageNo, pageSize);
    	LOG.info("返回={}",info);
    	return ResultGenerator.genSuccessResult(info);
    }
    
    // 批量删除设备日志接口
    @DeleteMapping
    public Result delMore (@RequestBody JSONObject jsonObject) {
    	LOG.info("批量删除设备日志={}",jsonObject);
    	if (jsonObject == null) {
    		return ResultGenerator.genFailResult("参数错误");
    	}
    	
    	List<Integer> ids = jsonObject.getObject("ids", List.class);
    	
    	if (ids == null || ids.isEmpty()) {
    		return ResultGenerator.genFailResult("参数不正确");
    	}
    	
    	String idsStr = ListToStringUtils.ListToString(ids);
    	LOG.info("ids={}",idsStr);
    	if (!StringUtils.isBlank(idsStr)) {
    		logEquipmentService.deleteByIds(idsStr);
    	}
    	
    	return ResultGenerator.genSuccessResult();
    }
}
