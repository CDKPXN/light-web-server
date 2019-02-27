package com.company.project.service.impl;

import com.company.project.dao.DevicedataMapper;
import com.company.project.model.Devicedata;
import com.company.project.service.DevicedataService;
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
public class DevicedataServiceImpl extends AbstractService<Devicedata> implements DevicedataService {
    @Resource
    private DevicedataMapper devicedataMapper;
    
    private static final Logger LOG = LoggerFactory.getLogger(DevicedataServiceImpl.class);
    
    /**
     *条件查询数据
     * 
     */  
	@Override
	public PageInfo findAllData(Integer dataType, Date startTime, Date endTime, String sourceOrTarger, Integer page,
			Integer size) {
		LOG.info("分页参数，page={},pagesize={}",page, size);
		PageHelper.startPage(page, size);
		List<Devicedata> deviceDatas = devicedataMapper.findAllData(dataType,startTime, endTime, sourceOrTarger);
        PageInfo pageInfo = new PageInfo(deviceDatas);
		return pageInfo;
		
	}
    
   
	

}
