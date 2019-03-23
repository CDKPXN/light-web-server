package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Node;
import com.company.project.service.NodeService;
import com.company.project.vo.NodeFVo;
import com.company.project.vo.NodeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2018/12/29.
*/
@RestController
@RequestMapping("/api/node")
public class NodeController {
    @Resource
    private NodeService nodeService;
    
    private static final Logger LOG = LoggerFactory.getLogger(NodeController.class);

    @PostMapping
    public Result add(@RequestBody Node node) {
    	LOG.info("添加节点，node={}",node);
    	
    	if (null == node || node.getFid() == null || node.getNodename() == null) {
    		LOG.error("参数不正确");
    		return ResultGenerator.genFailResult("参数不正确");
    	}
    	
        Integer res = nodeService.addNode(node);
        
        if (res == -1) {
        	return ResultGenerator.genFailResult("该节点已存在");
        }
        
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
    	LOG.info("删除id={}的节点",id);
        Integer res = nodeService.deleteNodeById(id);
        
        if (res == -1) {
        	return ResultGenerator.genFailResult("删除节点失败");
        } else if (res == -2) {
        	return ResultGenerator.genFailResult("删除失败-没有删除节点上灯具的权限");
        }
        
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Node node) {
    	LOG.info("修改节点node={}",node);
    	
    	if (node == null || node.getId() == null) {
    		return ResultGenerator.genFailResult("参数不正确");
    	}
        nodeService.update(node);
        LOG.info("修改之后的节点node={}",node);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
    	LOG.info("查询节点下的子节点，nodeid={}",id);
        List<Node> nodes = nodeService.getChildrenNodes(id);
        LOG.info("返回={}",nodes);
        return ResultGenerator.genSuccessResult(nodes);
    }

//    @GetMapping
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Node> list = nodeService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }
    
    @GetMapping
    public Result getNodes() {
    	LOG.info("查询所有节点--返回树形结构");
    	List<NodeVo> nodes = nodeService.getAllNodes();
    	LOG.info("返回={}",nodes);
    	return ResultGenerator.genSuccessResult(nodes);
    }
    
    @GetMapping("/app")
    public Result getNodeList() {
    	LOG.info("APP端获取省级节点列表");
    	List<Node> nodes = nodeService.getAPPProviceNodeList();
    	LOG.info("返回={}",nodes);
    	return ResultGenerator.genSuccessResult(nodes);
    }
    
    @GetMapping("/{id}/fathers")
    public Result getFatherNode(@PathVariable Integer id) {
    	LOG.info("根据节点={}查询该节点父节点直到最高节点",id);
    	
    	List<Node> fnodes = nodeService.getFathNodes(id);
    	LOG.info("返回={}",fnodes);
    	return ResultGenerator.genSuccessResult(fnodes);
    }
    
}
