package com.company.project.service.impl;

import com.company.project.dao.AuthorityMapper;
import com.company.project.dao.NodeUserMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Authority;
import com.company.project.model.NodeUser;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.vo.UserVo;
import com.company.project.core.AbstractService;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private AuthorityMapper authorityMapper;
    
    @Resource
    private NodeUserMapper nodeUserMapper;
    
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 查询所有用户
     */
	public List<User> findByFilter(String filter) {
		List<User> users = userMapper.findByFilter(filter);
		return users;
	}

	/**
	 * 根据id查询用户详情  
	 */
	public UserVo getUserById(Integer id) {
		LOG.info("根据id查询用户详情 id={}",id);
		
		//根据id查user
		User user = userMapper.findByUserId(id);
		
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(user, userVo);
		
		userVo.setAuthority(authorityMapper.selectOnlyAuthorityByUid(id));
		
		//根据id查节点
		List<Integer> nodeIds = userMapper.findNode(id);

		userVo.setNodeids(nodeIds);
		return userVo;
	}
	
	

	@Override
	public Integer delete(Integer id) {
	    LOG.info("根据id删除用户 id={}",id);
	    
	    try {
	    	userMapper.deleteByPrimaryKey(id);
		    
		    authorityMapper.deleteByUserId(id);
		    
		    nodeUserMapper.deleteByUserId(id);
		} catch (Exception e) {
			LOG.error("删除用户发生异常={}",e.getMessage());
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -1;
		}
	    
		return 0;
	}

	@Override
	public Integer add(UserVo userVo) {

		Integer authority = userVo.getAuthority();
		List<Integer> nodeids = userVo.getNodeids();
		
		if (authority == null && nodeids.isEmpty()) {
			return -1;
		}
		
		try {
			
			User user = new User();
			BeanUtils.copyProperties(userVo, user);
			LOG.info("copyPropertiest之后user={}",user);
			save(user);
			
			Integer uid = user.getId();
			if (uid == null) {
				return -2;
			}
			
			addNodeUserAndAuthorityByNodeids(nodeids,authority,uid);
			
		} catch (Exception e) {
			LOG.error("添加用户时插入多张表发生异常={}",e.getMessage());
			// 回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -2;
		}
		
		return 0;
	}

	/**
	 * 修改用户
	 */
	public Integer updateUser(UserVo userVo) {
		
		User user = new User();
		BeanUtils.copyProperties(userVo, user);
		LOG.info("copyProperties后的user={}",user);
		
		try {
			userMapper.updateByPrimaryKeySelective(user);
			Integer uid = userVo.getId();
			List<Integer> authorityNodeids = authorityMapper.selectNodeidsByUid(uid);
			LOG.info("原本权限内的节点id={}",authorityNodeids);
			List<Integer> nodeids = userVo.getNodeids();
			LOG.info("修改过后的的节点ids={}",nodeids);
			List<Integer> list1 = getBesidesTheIntersectionList(authorityNodeids, nodeids);
			LOG.info("需要删除的的节点ids={}",list1);
			if (list1 != null && !list1.isEmpty()) {
				authorityMapper.deleteByNodeIds(list1);
				nodeUserMapper.deleteByNodeIds(list1);
			}
			
			List<Integer> list2 = getBesidesTheIntersectionList(nodeids, authorityNodeids);
			LOG.info("需要添加的节点ids={}",list2);
			Integer authority = userVo.getAuthority();
			addNodeUserAndAuthorityByNodeids(list2, authority, uid);
		} catch (Exception e) {
			LOG.error("修改用户时发生异常={}",e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚事务
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * 求list1 在 list2 中没有的集合
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<Integer> getBesidesTheIntersectionList(List<Integer> list1, List<Integer> list2) {
		ListIterator<Integer> iterator = list1.listIterator();
		while (iterator.hasNext()) {
			Integer nodeid = iterator.next();
			boolean contains = list2.contains(nodeid);
			if (contains) {
				list1.remove(nodeid);
			}
		}
		return list1;
	}
	
	/**
	 * 批量插入authority、nodeUser
	 * @param list
	 * @param authority
	 * @param uid
	 */
	@Transactional
	private void addNodeUserAndAuthorityByNodeids(List<Integer> list, Integer authority, Integer uid) {
		List<Authority> authorities = new ArrayList<>();
		List<NodeUser> nodeUsers = new ArrayList<>();
		
		list.forEach(nodeid -> {
			Authority authority2 = new Authority();
			authority2.setAuthority(authority);
			authority2.setNodeid(nodeid);
			authority2.setUid(uid);
			authority2.setCtime(new Date());
			authorities.add(authority2);
			
			NodeUser nodeUser = new NodeUser();
			nodeUser.setNodeid(nodeid);
			nodeUser.setUserid(uid);
			nodeUser.setCtime(new Date());
			nodeUsers.add(nodeUser);
		});
		
		authorityMapper.insertList(authorities);
		nodeUserMapper.insertList(nodeUsers);
	}

}
