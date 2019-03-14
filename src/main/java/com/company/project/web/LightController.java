package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Light;
import com.company.project.service.LightService;
import com.company.project.vo.LightAndUsersVo;

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
@RequestMapping("/api/light")
public class LightController {
	
	private static final Logger LOG = LoggerFactory.getLogger(LightController.class);
	
    @Resource
    private LightService lightService;

    
    @PostMapping
    public Result add(@RequestBody Light light) {
    	LOG.info("添加灯具，灯具={}",light);
    	if (null == light) {
    		LOG.info("参数错误：light==null");
    		return ResultGenerator.genFailResult("参数错误");
    	}
    	
    	String attrName = light.getAttrName();
    	Date attrDeadtime = light.getAttrDeadtime();
    	Double attrLatitude = light.getAttrLatitude();
    	Double attrLongitude = light.getAttrLongitude();
    	Integer attrNodeid = light.getAttrNodeid();
    	String attrNum = light.getAttrNum();
    	String attrPhone = light.getAttrPhone();
    	
    	if (StringUtils.isAnyBlank(attrName, attrNum, attrPhone) || attrDeadtime == null || attrLatitude == null || attrLongitude == null || attrNodeid == null) {
    		LOG.info("参数错误：有参数为null");
    		return ResultGenerator.genFailResult("参数错误");
    	}
    	
        lightService.save(light);
    	
        LOG.info("添加灯具成功");
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
    	LOG.info("删除灯具，id={}",id);
        lightService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Light light) {
    	LOG.info("修改灯具（与协议无关），light={}",light);
    	
    	if (null == light || null == light.getId()) {
    		return ResultGenerator.genFailResult("参数错误");
    	}
        lightService.update(light);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{num}")
    public Result detail(@PathVariable String num) {
        LOG.info("根据编号查询单个灯具，编号={}",num);
         
        if (StringUtils.isBlank(num)) {
        	return ResultGenerator.genFailResult("参数错误");
        }
        
        LightAndUsersVo lightAndUserVo = lightService.findByAttrNum(num);
        
        if (lightAndUserVo == null) {
        	return ResultGenerator.genFailResult("该编号不存在");
        }
        
        LOG.info("返回单个灯具和用户信息={}",lightAndUserVo);
        return ResultGenerator.genSuccessResult(lightAndUserVo);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
    		@RequestParam(defaultValue = "20") Integer pagesize, 
    		@RequestParam String searchcontent, @RequestParam Integer nodeid) {
    	
    	LOG.info("查询权限内的所有灯具，条件={},节点id={}",searchcontent,nodeid);
    	List<Light> lights = lightService.getAllLights(searchcontent, nodeid, page, pagesize);
    	
        return ResultGenerator.genSuccessResult(lights);
    }
    
//    @PutMapping("/cmd")
//    public Result lightSet(@RequestBody Light light) {
//    	LOG.info("设置灯具light={}",light);
//    	return ResultGenerator.genSuccessResult();
//    }
//    
//    @GetMapping("/cmd")
//    public Result lightFresh(@RequestParam String type) {
//    	LOG.info("刷新灯具");
//    	return ResultGenerator.genSuccessResult();
//    }
}
