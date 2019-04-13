package com.company.project.web;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.device.controller.DeviceController;
import com.company.project.model.Devicedata;
import com.company.project.service.DevicedataService;
import com.company.project.utils.ListToStringUtils;
import com.company.project.vo.DeviceDataVo;
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
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2018/12/29.
*/
@RestController
@RequestMapping("/api/devicedata")
public class DevicedataController {
    @Resource
    private DevicedataService devicedataService;

    private static final Logger LOG = LoggerFactory.getLogger(DeviceController.class);
    
    
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
    public Result add(@RequestBody Devicedata devicedata) {
        devicedataService.save(devicedata);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        devicedataService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Devicedata devicedata) {
        devicedataService.update(devicedata);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Devicedata devicedata = devicedataService.findById(id);
        return ResultGenerator.genSuccessResult(devicedata);
    }

    @GetMapping
    public Result list(@RequestParam(required = false) Integer dataType,@RequestParam(defaultValue = "1970-1-1") Date startTime,
    		           @RequestParam(defaultValue = "2099-12-31") Date endTime,@RequestParam(required = false) String sourceOrTarger,
    		           @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "0") Integer pageSize) {
        
        LOG.info("查询上报下发数据，dateType={}，startTime={}，endTime={}，sourceOrTarger={}",dataType,startTime,endTime,sourceOrTarger);
        PageInfo list = devicedataService.findAllData(dataType,startTime,endTime,sourceOrTarger, pageNo, pageSize);
        LOG.info("返回={}",list);      
        return ResultGenerator.genSuccessResult(list);       
      
    
    }
    
    /**
     * 批量删除数据
     */
    @DeleteMapping
    public Result delMore (@RequestBody DeviceDataVo deviceDataVo) {
    	LOG.info("批量删除={}",deviceDataVo);

    	if (deviceDataVo == null) {
    		LOG.info("参数不正确");
    		return ResultGenerator.genFailResult("参数不正确");
    	}
    	
    	List<Integer> ids = deviceDataVo.getIds();
    	
    	if (ids == null || ids.isEmpty()) {
    		LOG.info("参数不正确");
    		return ResultGenerator.genFailResult("参数不正确");
    	}
    	
    	String idsStr = ListToStringUtils.ListToString(ids);
    	LOG.info("ids={}",idsStr);
    	if (!StringUtils.isBlank(idsStr)) {
    		devicedataService.deleteByIds(idsStr);
    	}
    	LOG.info("批量删除成功");
    	
    	return ResultGenerator.genSuccessResult();
    }
}
