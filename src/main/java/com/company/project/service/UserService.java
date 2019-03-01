package com.company.project.service;
import com.company.project.model.User;
import com.company.project.vo.UserVo;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/12/29.
 */

public interface UserService extends Service<User> {

	/**
	 * 
	 * @param filter 搜索条件
	 * @return
	 */
	List<User> findByFilter(String filter);
	
	/**
	 * 根据id查询用户及其管理的线路
	 * @param id
	 * @return
	 */
	UserVo getUserById(Integer id);

	
	Integer delete(Integer id);

	Integer add(UserVo user);
	
	/**
	 * 修改用户
	 * @param userVo
	 * @return
	 */
	Integer updateUser(UserVo userVo);

}
