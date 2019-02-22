package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Node;

public interface NodeMapper extends Mapper<Node> {
	
	/**
	 * 查询子节点ids
	 * @param fid
	 * @return
	 */
	public List<Integer> selectChildNodeids (@Param("fid")Integer fid);
}