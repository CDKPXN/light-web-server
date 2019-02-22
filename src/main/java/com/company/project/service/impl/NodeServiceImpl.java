package com.company.project.service.impl;

import com.company.project.dao.NodeMapper;
import com.company.project.model.Node;
import com.company.project.service.LightService;
import com.company.project.service.NodeService;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.company.project.core.AbstractService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class NodeServiceImpl extends AbstractService<Node> implements NodeService {
    @Resource
    private NodeMapper nodeMapper;

    
    private static final Logger LOG = LoggerFactory.getLogger(NodeServiceImpl.class);
    
	/**
	 * 获取权限内的所有节点，并且返回树形结构
	 */
	public List<Node> getNodeswithAuth() {
		List<Node> nodes = new ArrayList<>();
		
		
		return null;
	}

	/**
	 * 通过id删除节点，并且删除该节点上的灯具
	 */
	public void deleteNodeById(Integer id) {
		
		
	}

	/**
	 * 查询该节点下的子节点
	 */
	public List<Node> getChildrenNodes(Integer id) {
		Condition condition = new Condition(Node.class);
		Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("fid", id);
		List<Node> nodes = nodeMapper.selectByCondition(condition);
		return nodes;
	}

	/**
	 * 添加节点
	 * 需要判断是否已存在该节点
	 */
	public Integer addNode(Node node) {
		boolean hasExistedNode = hasExistedNode(node);
		
		if (hasExistedNode) {
			// 已存在该节点
			LOG.info("已存在该节点");
			return -1;
		}
		
		try {
			save(node);
		} catch (Exception e) {
			LOG.error("添加节点发生异常={}",e.getMessage());
		}
		
		return 0;
	}
	
	/**
	 * 判断是否存在该节点
	 * @param node
	 * @return
	 */
	private boolean hasExistedNode(Node node) {
		Condition condition = new Condition(Node.class);
		Criteria criteria = condition.createCriteria();
		
		Integer fid = node.getFid();
		String nodename = node.getNodename();
		criteria.andEqualTo("fid", fid);
		criteria.andEqualTo("nodename", nodename);
		
		List<Node> nodes = nodeMapper.selectByCondition(condition);
		
		if (null == nodes || nodes.size() == 0) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 根据当前节点id获取本节点及其所有子节点的id
	 * @param nodeid
	 * @return
	 */
	public List<Integer> getChildNodeids(List<Integer> nodeids,Integer nodeid) {
		nodeids.add(nodeid);
		List<Integer> childnodeids = nodeMapper.selectChildNodeids(nodeid);
		if (!childnodeids.isEmpty()) {
			childnodeids.forEach(childnodeid -> {
				getChildNodeids(nodeids, childnodeid);
			});
		}
		return nodeids;
	}
}
