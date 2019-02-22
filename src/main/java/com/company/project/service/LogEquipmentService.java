package com.company.project.service;
import com.company.project.model.LogEquipment;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
public interface LogEquipmentService extends Service<LogEquipment> {

	/**
	 * 查询所有设备日志
	 * @param startDate
	 * @param endDate
	 * @param searchcontent
	 * @return
	 */
	PageInfo getAllEquipmentLogs(Date startDate, Date endDate, String searchcontent, Integer page, Integer pagesize);

}
