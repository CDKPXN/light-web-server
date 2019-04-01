package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.LogSys;
import com.company.project.service.LogSysService;
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
@RequestMapping("/api/log/sys")
public class LogSysController {
    @Resource
    private LogSysService logSysService;
    
    private static final Logger LOG = LoggerFactory.getLogger(LogSysController.class);
    
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
    public Result add(@RequestBody LogSys logSys) {
        logSysService.save(logSys);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        logSysService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody LogSys logSys) {
        logSysService.update(logSys);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        LogSys logSys = logSysService.findById(id);
        return ResultGenerator.genSuccessResult(logSys);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "0") Integer pageSize,
    		           @RequestParam(defaultValue = "1997-1-1") Date startTime,@RequestParam(defaultValue = "2099-12-31") Date endTime,
    		           @RequestParam(required = false) String searchContent) {
        LOG.info("按条件查询系统日志  startTime={},endTime={},searchContent", startTime,endTime,searchContent);
    	
    	PageInfo list = logSysService.findByFilter(startTime,endTime,searchContent,pageNo,pageSize);
        
        return ResultGenerator.genSuccessResult(list);
    }
}
