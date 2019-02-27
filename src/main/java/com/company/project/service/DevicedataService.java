package com.company.project.service;
import com.company.project.model.Devicedata;
import com.github.pagehelper.PageInfo;

import java.util.Date;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
public interface DevicedataService extends Service<Devicedata> {

	PageInfo findAllData(Integer dataType, Date startTime, Date endTime, String sourceOrTarger, Integer page,
			Integer size);

}
