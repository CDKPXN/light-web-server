package com.company.project.service;
import com.company.project.model.Light;
import com.company.project.vo.LightAndUsersVo;
import com.company.project.vo.LightStatisticsVo;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
public interface LightService extends Service<Light> {

	/**
	 * 根据编号查询灯具和相关联的用户信息
	 * @param num
	 * @return
	 */
	LightAndUsersVo findByAttrNum(String num);

	/**
	 * 根据条件查询权限内的所有灯具信息，如果条件为空，则查询权限内的所有灯具
	 * @param searchcontent 搜索内容
	 * @param nodeid 节点id
	 * @param page 页码
	 * @param pagesize 每页的数据条数
	 * @return
	 */
	List<Light> getAllLights(String searchcontent, Integer nodeid, Integer page, Integer pagesize);

	/**
	 * 统计权限内的所有灯具状态信息
	 * @return
	 */
	LightStatisticsVo getStatisticsInfo();

}
