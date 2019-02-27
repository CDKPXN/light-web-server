package com.company.project.service.impl;

import com.company.project.dao.AuthorityMapper;
import com.company.project.dao.NodeUserMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.vo.UserVo;
import com.company.project.core.AbstractService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	 * 根据id查询用户详情  没写完
	 */
	public UserVo getUserById(Integer id) {
		LOG.info("根据id查询用户详情 id={}",id);
		UserVo user = userMapper.findByUserId(id);
		
		List<Integer> nodeIds = userMapper.findNode(id);

		return user;
	}
	
	

	@Override
	public void delete(Integer id) {
	    LOG.info("根据id删除用户 id={}",id);
	    
	    userMapper.delete(id);
	    
	    authorityMapper.deleteByUserId(id);
	    
	    nodeUserMapper.deleteByUserId(id);
	    
		
	}

	@Override
	public void add(UserVo user) {
		LOG.info("添加新用户 id={}",user);
		userMapper.add(user);
		
		
		
	}
	

}
