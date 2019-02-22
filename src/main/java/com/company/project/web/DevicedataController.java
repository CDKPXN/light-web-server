package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Devicedata;
import com.company.project.service.DevicedataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Devicedata> list = devicedataService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
