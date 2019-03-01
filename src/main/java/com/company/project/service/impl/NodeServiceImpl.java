package com.company.project.service.impl;

import com.company.project.dao.AuthorityMapper;
import com.company.project.dao.LightMapper;
import com.company.project.dao.NodeMapper;
import com.company.project.dao.NodeUserMapper;
import com.company.project.model.Node;
import com.company.project.service.LightService;
import com.company.project.service.NodeService;
import com.company.project.vo.NodeVo;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.company.project.core.AbstractService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Resource
    private LightMapper lightMapper;
    
    @Resource
    private NodeUserMapper nodeUserMapper;
    
    @Resource
    private AuthorityMapper authorityMapper;
    
    private static final Logger LOG = LoggerFactory.getLogger(NodeServiceImpl.class);
    
	/**
	 * 获取所有节点，并且返回树形结构
	 */
	public List<NodeVo> getAllNodes() {
		
		List<NodeVo> nodeVos = nodeMapper.getAllNodesList();
		
		LOG.info("返回树形结构的节点={}",nodeVos);
		return nodeVos;
	}

	/**
	 * 通过id删除节点，并且删除该节点上的灯具
	 */
	public Integer deleteNodeById(Integer id) {
		
		List<Integer> nodeids = new ArrayList<>();
		List<Integer> childNodeids = getChildNodeids(nodeids, id);
		
		LOG.info("要删除的节点以及所有子节点={}",childNodeids);
		
		if (childNodeids.isEmpty()) {
			return 0;
		}
		
		try {
			lightMapper.deleteByNodeids(childNodeids);
			nodeMapper.deleteNodeByids(childNodeids);
			nodeUserMapper.deleteByNodeIds(childNodeids);
			authorityMapper.deleteByNodeIds(childNodeids);
		} catch (Exception e) {
			LOG.error("删除节点时发生异常={}",e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -1;
		}
		
		return 0;
	}

	/**
	 * 查询该节点下的一级子节点
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

	/**
	 * APP端-返回所有的省级节点
	 */
	public List<Node> getAPPProviceNodeList() {
		Condition condition = new Condition(Node.class);
		Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("fid", -1);
		List<Node> nodes = nodeMapper.selectByCondition(condition);
		return nodes;
	}
	
}
