package com.company.project.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Light;

public interface LightMapper extends Mapper<Light> {
	
	/**
	 * 根据编号查询单个灯具
	 * @param num
	 * @return
	 */
	public Light selectLightByAttrNum(@Param("num") String num);
	
	/**
	 * 查询所有灯具（权限内、带条件、搜索不以“-”开头）
	 * @param nodeids
	 * @param searchcontent
	 * @return
	 */
	public List<Light> selectAllLights(@Param("nodeids")Set<Integer> nodeids,@Param("searchcontent")String searchcontent);
	
	/**
	 * 通过nodeid 查询灯具
	 * @param nodeids
	 * @return
	 */
	public List<Light> selectLightsByNodeids(@Param("nodeids") List<Integer> nodeids);
	
	/**
	 * 搜索以“-”开头
	 * @param nodeids
	 * @param searchcontent
	 * @return
	 */
	public List<Light> selectLightsByNodeidAndsearch(@Param("nodeids")Set<Integer> nodeids,@Param("searchcontent")String searchcontent);
}