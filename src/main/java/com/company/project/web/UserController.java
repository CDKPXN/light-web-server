package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2018/12/29.
*/
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;
    
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public Result add(@RequestBody UserVo user) {
    	LOG.info("添加新用户",user);
        userService.add(user);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        LOG.info("删除id={}的用户",id);
        userService.delete(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody UserVo user) {
    	LOG.info("修改用户，user={}",user);
    	if (user == null && user.getId() == null) {
    		return ResultGenerator.genFailResult("参数错误");
    	}
    	
    	Date now = new Date();
    	user.setUpdatetime(now);
        userService.update(user);
        LOG.info("修改过后user={}",user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
    	LOG.info("查询id={}的用户",id);
        UserVo user = userService.getUserById(id);
        LOG.info("返回={}",user);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size,
    		String filter) {
    	LOG.info("查询所有用户,page={},size={},filter={}",page,size,filter);
    	PageHelper.startPage(page, size);
        List<User> list = userService.findByFilter(filter);
        PageInfo pageInfo = new PageInfo(list);
        LOG.info("返回={}",pageInfo);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
