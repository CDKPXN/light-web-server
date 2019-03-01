package com.company.project.service;
import com.company.project.model.Node;
import com.company.project.vo.NodeVo;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
public interface NodeService extends Service<Node> {

	/**
	 * 获取所有节点、并且返回树形结构
	 * @return
	 */
	List<NodeVo> getAllNodes();

	/**
	 * 通过id 删除节点信息，并且删除该节点上的灯具
	 * @param id
	 */
	Integer deleteNodeById(Integer id);

	/**
	 * 查询该节点下的子节点
	 * @param id
	 * @return
	 */
	List<Node> getChildrenNodes(Integer id);

	/**
	 * 添加节点
	 * @param node
	 */
	Integer addNode(Node node);
	
	
	/**
	 * 获取该节点下的子节点
	 * @param nodeids
	 * @param nodeid
	 * @return
	 */
	List<Integer> getChildNodeids(List<Integer> nodeids,Integer nodeid);

	/**
	 * APP端-返回所有省级节点
	 * @return
	 */
	List<Node> getAPPProviceNodeList();

}
