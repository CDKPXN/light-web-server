package com.company.project.service.impl;

import com.company.project.dao.MessageMapper;
import com.company.project.model.Message;
import com.company.project.service.MessageService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class MessageServiceImpl extends AbstractService<Message> implements MessageService {
    @Resource
    private MessageMapper messageMapper;

}
