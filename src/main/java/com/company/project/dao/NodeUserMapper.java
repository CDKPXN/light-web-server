package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Light;
import com.company.project.model.NodeUser;
import com.company.project.vo.LightAndUsersVo;

public interface NodeUserMapper extends Mapper<NodeUser> {
	
	/**
	 * 通过灯具编号查询灯具和用户的Vo
	 * @param num
	 * @return
	 */
	public LightAndUsersVo selectLightAndUsersByNum(@Param("num")String num);

	public void deleteByUserId(@Param("uid")Integer id);
	
	/**
	 * 根据nodeids 删除用户和节点的中间表记录
	 * @param nodeids
	 */
	public void deleteByNodeIds(@Param("nodeids")List<Integer> nodeids);
}