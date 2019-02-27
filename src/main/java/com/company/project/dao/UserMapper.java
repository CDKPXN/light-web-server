package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.User;
import com.company.project.vo.UserVo;

public interface UserMapper extends Mapper<User> {
	
	/**
	 * 查询所有用户
	 * @param filter
	 * @return
	 */
	public List<User> getAll(@Param("filter")String filter);

	public List<User> findByFilter(@Param("filter")String filter);

	public UserVo findByUserId(@Param("id")Integer id);

	public List<Integer> findNode(@Param("id")Integer id);

	public List<String> findFatherNode(@Param("fid")Integer nodeId);

	public void delete(@Param("id")Integer id);

	public void add(@Param("user")UserVo user);
}