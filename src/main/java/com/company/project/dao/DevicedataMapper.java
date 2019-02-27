package com.company.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Devicedata;

public interface DevicedataMapper extends Mapper<Devicedata> {

	List<Devicedata> findAllData(@Param("dataType")Integer dataType, @Param("startTime")Date startTime,@Param("endTime") Date endTime, @Param("sourceOrTarger")String sourceOrTarger);
}