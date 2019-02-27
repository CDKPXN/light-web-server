package com.company.project.service.impl;

import com.company.project.dao.LogSysMapper;
import com.company.project.model.LogSys;
import com.company.project.service.LogSysService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.company.project.core.AbstractService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class LogSysServiceImpl extends AbstractService<LogSys> implements LogSysService {
    @Resource
    private LogSysMapper logSysMapper;
    
    private static final Logger LOG = LoggerFactory.getLogger(LogSysServiceImpl.class);

	@Override
	public PageInfo findByFilter(Date startTime, Date endTime, String searchContent, Integer page, Integer size) {
		LOG.info("分页参数 page={},size={}",page,size);
		PageHelper.startPage(page, size);
		List<LogSys> logSystems = logSysMapper.findByFilter(startTime, endTime, searchContent);
		PageInfo pageInfo = new PageInfo(logSystems);
		return pageInfo;
	}

}
