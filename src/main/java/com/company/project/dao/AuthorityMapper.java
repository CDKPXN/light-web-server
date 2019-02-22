package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Authority;

public interface AuthorityMapper extends Mapper<Authority> {
	
	/**
	 * 通过用户id查询
	 * @param uid
	 * @return
	 */
	List<Authority> selectAuthorityByUid(@Param("uid") Integer uid);
	
	/**
	 * 通过用户id查询所管理的节点ids
	 * @param uid
	 * @return
	 */
	List<Integer> selectNodeidsByUid(@Param("uid") Integer uid);
}