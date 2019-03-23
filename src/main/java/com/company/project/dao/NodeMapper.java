package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Node;
import com.company.project.vo.NodeFVo;
import com.company.project.vo.NodeVo;

public interface NodeMapper extends Mapper<Node> {
	
	/**
	 * 查询子节点ids
	 * @param fid
	 * @return
	 */
	public List<Integer> selectChildNodeids (@Param("fid")Integer fid);
	
	/**
	 * 通过节点ids删除节点
	 * @param nodeids
	 */
	public void deleteNodeByids(@Param("ids")List<Integer> nodeids);

	/**
	 * 获取所有的节点信息，并返回树形结构
	 * @return
	 */
	public List<NodeVo> getAllNodesList();
	
	/**
	 * 获取全国选项的id
	 * @return
	 */
	public Integer selectQuanGuoId();

	/**
	 * 获取父节点直到最高节点
	 * @param id
	 * @return
	 */
//	public NodeFVo selectNodeFatherTree(@Param("id")Integer id);

	public void updateById(@Param("node")Node node);
	
}