package com.company.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.LogEquipment;

public interface LogEquipmentMapper extends Mapper<LogEquipment> {

	/**
	 * 根据条件查询所有设备日志
	 * @param startDate
	 * @param endDate
	 * @param searchcontent
	 * @return
	 */
	List<LogEquipment> selectAllByFilter(@Param("sDate")Date startDate, @Param("eDate")Date endDate, @Param("sc")String searchcontent);
}