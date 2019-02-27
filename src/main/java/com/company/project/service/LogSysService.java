package com.company.project.service;
import com.company.project.model.LogSys;
import com.github.pagehelper.PageInfo;

import java.util.Date;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
public interface LogSysService extends Service<LogSys> {

	PageInfo findByFilter(Date startTime, Date endTime, String searchContent, Integer page, Integer size);

}
