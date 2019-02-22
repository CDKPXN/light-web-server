package com.company.project.service.impl;

import com.company.project.dao.LogSysMapper;
import com.company.project.model.LogSys;
import com.company.project.service.LogSysService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class LogSysServiceImpl extends AbstractService<LogSys> implements LogSysService {
    @Resource
    private LogSysMapper logSysMapper;

}
