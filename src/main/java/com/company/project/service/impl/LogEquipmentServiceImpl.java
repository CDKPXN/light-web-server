package com.company.project.service.impl;

import com.company.project.dao.LogEquipmentMapper;
import com.company.project.model.LogEquipment;
import com.company.project.service.LogEquipmentService;
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
public class LogEquipmentServiceImpl extends AbstractService<LogEquipment> implements LogEquipmentService {
    @Resource
    private LogEquipmentMapper logEquipmentMapper;

    private static final Logger LOG = LoggerFactory.getLogger(LogEquipmentServiceImpl.class);
	/**
	 * 查询所有设备日志
	 */
	public PageInfo getAllEquipmentLogs(Date startDate, Date endDate, String searchcontent, Integer page, Integer pagesize) {
		LOG.info("分页参数，page={},pagesize={}",page, pagesize);
		PageHelper.startPage(page, pagesize);
		List<LogEquipment> logEquipments = logEquipmentMapper.selectAllByFilter(startDate, endDate, searchcontent);
        PageInfo pageInfo = new PageInfo(logEquipments);
		return pageInfo;
	}

}
