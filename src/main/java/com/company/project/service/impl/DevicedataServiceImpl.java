package com.company.project.service.impl;

import com.company.project.dao.DevicedataMapper;
import com.company.project.model.Devicedata;
import com.company.project.service.DevicedataService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class DevicedataServiceImpl extends AbstractService<Devicedata> implements DevicedataService {
    @Resource
    private DevicedataMapper devicedataMapper;

}
