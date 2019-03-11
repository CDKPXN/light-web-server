package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Message;
import com.company.project.service.MessageService;
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
@RequestMapping("/api/message")
public class MessageController {
    @Resource
    private MessageService messageService;
    
    private final static Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @PostMapping
    public Result add(@RequestBody Message message) {
        messageService.save(message);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        messageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Message message) {
        messageService.update(message);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Message message = messageService.findById(id);
        return ResultGenerator.genSuccessResult(message);
    }

    @GetMapping
    public Result list(Integer fromid,Integer toid) {
        LOG.info("根据发送人id和接收人id查询聊天记录，fronid={}，toid={}",fromid,toid);
        List<Message> list = messageService.findHistory(fromid,toid);
        return ResultGenerator.genSuccessResult(list);
    }
}
