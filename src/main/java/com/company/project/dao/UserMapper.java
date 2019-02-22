package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.User;

public interface UserMapper extends Mapper<User> {
	
	/**
	 * 查询所有用户
	 * @param filter
	 * @return
	 */
	public List<User> getAll(@Param("filter")String filter);
}