package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.device.controller.DeviceController;
import com.company.project.model.Devicedata;
import com.company.project.service.DevicedataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
    public Result list(@RequestParam Integer dataType,@RequestParam(defaultValue = "1970-1-1") Date startTime,
    		           @RequestParam(defaultValue = "2099-12-31") Date endTime,@RequestParam String sourceOrTarger,
    		           @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        
        LOG.info("查询上报下发数据，dateType={}，startTime={}，endTime={}，sourceOrTarger={}",dataType,startTime,endTime,sourceOrTarger);
        PageInfo list = devicedataService.findAllData(dataType,startTime,endTime,sourceOrTarger,page,size);
        LOG.info("返回={}",list);      
        return ResultGenerator.genSuccessResult(list);       
      
    
    }
}
