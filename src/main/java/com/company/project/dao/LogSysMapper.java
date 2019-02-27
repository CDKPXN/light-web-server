package com.company.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.LogSys;

public interface LogSysMapper extends Mapper<LogSys> {

	List<LogSys> findByFilter(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("searchContent")String searchContent);
}