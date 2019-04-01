package com.company.project.service.impl;

import com.company.project.dao.AuthorityMapper;
import com.company.project.dao.LightMapper;
import com.company.project.dao.NodeMapper;
import com.company.project.dao.NodeUserMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Authority;
import com.company.project.model.Light;
import com.company.project.model.Node;
import com.company.project.model.NodeUser;
import com.company.project.model.User;
import com.company.project.service.LightService;
import com.company.project.service.NodeService;
import com.company.project.utils.TokenUtils;
import com.company.project.vo.Buzzerstate;
import com.company.project.vo.Faultindication;
import com.company.project.vo.LightAndUsersVo;
import com.company.project.vo.LightState;
import com.company.project.vo.LightStatisticsVo;
import com.company.project.vo.Lightfrequency;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.auth0.jwt.interfaces.Claim;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class LightServiceImpl extends AbstractService<Light> implements LightService {

    private static final Logger LOG = LoggerFactory.getLogger(LightServiceImpl.class);
    
    @Resource
    private LightMapper lightMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private AuthorityMapper authorityMapper;
    
    @Autowired
    private NodeUserMapper nodeUserMapper;
    
    @Autowired
    private NodeService nodeService;
    
    @Autowired
    private NodeMapper nodeMapper;
    
    /**
	 * 根据编号查询灯具和相关联的用户信息
	 * 1.这个不考虑权限：因为如果已经安装上了灯具，
	 *   但是并没有将灯具挂在节点上，
	 *   如果设置权限就会查不到该灯具
	 * @param num
	 * @return
	 */
	public Result findByAttrNum(String num) {
		
		Light light = lightMapper.selectLightByAttrNum(num);
		
		if (light == null) {
			LOG.info("该灯具不存在");
			ResultGenerator.genFailResult("该灯具不存在");
		}
		
		LightAndUsersVo lightAndUsersVo = new LightAndUsersVo();
		BeanUtils.copyProperties(light, lightAndUsersVo);
		LOG.info("将light  copy  到lightAndUsersVo={}",lightAndUsersVo);
		
		Integer attrNodeid = light.getAttrNodeid();
		// 如果该灯具节点id == null，说明还没有添加该灯具，但是该灯具已经上报数据了
		if (attrNodeid == null) {
			LOG.info("该灯具已经安装上了并且已经开始上报数据，但是并没有在系统上添加该灯具！");
			return ResultGenerator.genSuccessResult(lightAndUsersVo);
		}
		
		/**
		 *  已经添加灯具
		 */
		Set<Integer> userids = new HashSet<>();
		getUserIds(attrNodeid, userids);
		
		LOG.info("查到的userids={}",userids);
		
		if (!userids.isEmpty()) {
			List<User> users = new ArrayList<>();
			userids.forEach(userid -> {
				User user = userMapper.selectByPrimaryKey(userid);
				users.add(user);
			});
			
			lightAndUsersVo.setUsers(users);
		}
		LOG.info("返回lightVo={}",lightAndUsersVo);
		return ResultGenerator.genSuccessResult(lightAndUsersVo);
	}



	/**
	 * 根据条件查询权限内的所有灯具信息，如果条件为空，则查询权限内的所有灯具
	 */
	public List<Light> getAllLights(String searchcontent, Integer nodeid, Integer page, Integer pagesize) {
		Map<String, Object> authMap = getAuthority();
		Integer auth = (Integer)authMap.get("auth");
		if (auth < 1) { // 没有查询权限
			return null;
		}
		
		List<Integer> authNodeids = new ArrayList<>();
		List<Integer> authNodeids2 = (List<Integer>)authMap.get("nodeids");
		
		LOG.info("权限内的ids={}",authNodeids2);
		
		authNodeids2.forEach(authNodeid -> {
			nodeService.getChildNodeids(authNodeids, authNodeid);
		});
		
		// authNodeids 可能存在重复，需要去重
		Set<Integer> nodeSet = new HashSet<>(authNodeids);
		LOG.info("权限内的nodeids（包含子节点）={}",nodeSet);
		
		if (null != nodeid) {
			List<Integer> nodeids = new ArrayList<>();
			nodeService.getChildNodeids(nodeids, nodeid);
			LOG.info("条件中的节点ids={}",nodeids);
			
			// 两个nodeids 集合求交集
			nodeSet.retainAll(nodeids);
			LOG.info("交集={}",nodeSet);
		}
		
		if (nodeSet.isEmpty()) {
			return null;
		}
		
		List<Light> lights = null;
		LOG.info("searchcontent={}",searchcontent);
		
		
		LOG.info("page==={}",page);
		LOG.info("pagesize=={}",pagesize);
//		PageHelper.startPage(page, pagesize);
		
		
		Integer pageNo = 0;
		if (page > 1) {
			pageNo = (page - 1) * pagesize;
		}
		
		lights = lightMapper.selectAllLights(nodeSet, searchcontent, pageNo, pagesize);
		LOG.info("lights={}",lights);
//		PageInfo pageInfo = new PageInfo(lights);
		return lights;
	}
	
	
	/**
	 * 统计权限内的所有灯具状态信息
	 */
	public Result getStatisticsInfo() {
		Map<String, Object> authMap = getAuthority();
		Integer auth = (Integer)authMap.get("auth");
		if (auth < 1) {
			return ResultGenerator.genFailResult("没有查看权限");
		}
		List<Integer> authNodeids  = (List<Integer>)authMap.get("nodeids");
		LOG.info("权限内的nodeids={}",authNodeids);
		
		if (authNodeids.isEmpty()) {
			return ResultGenerator.genSuccessResult();
		}
		
		List<Integer> nodeids = new ArrayList<>();
		authNodeids.forEach(nodeid->{
			nodeService.getChildNodeids(nodeids, nodeid);
		});
		authNodeids = new ArrayList<>(nodeids);
		LOG.info("查询权限内的节点的子节点--={}",authNodeids);
		
		LightStatisticsVo lightStatisticsVo = new LightStatisticsVo();
		
		List<Light> lights = lightMapper.selectLightsByNodeids(authNodeids);
		LOG.info("权限内的灯具={}",lights);
		// 权限内没有灯具
		if (lights.isEmpty()) {
			return ResultGenerator.genSuccessResult();
		}
		// 统计信息
		
		Faultindication faultindication_2 = new Faultindication(2,0); // 主通道故障
		Faultindication faultindication_3 = new Faultindication(3,0); // 主副均故障
		Faultindication faultindication_4 = new Faultindication(4,0); // 长时间离线
		
		Buzzerstate buzzerstate_day_0 = new Buzzerstate(0,0,0); // 白天长鸣
		Buzzerstate buzzerstate_day_1 = new Buzzerstate(0,1,0); // 白天常静
		Buzzerstate buzzerstate_night_0 = new Buzzerstate(1,0,0); // 夜间长鸣
		Buzzerstate buzzerstate_night_1 = new Buzzerstate(1,1,0); // 夜间常静
		
		Lightfrequency lightfrequency_day_20 = new Lightfrequency(0,20,0);
		Lightfrequency lightfrequency_day_30 = new Lightfrequency(0,30,0);
		Lightfrequency lightfrequency_day_40 = new Lightfrequency(0,40,0);
		Lightfrequency lightfrequency_night_20 = new Lightfrequency(1,20,0);
		Lightfrequency lightfrequency_night_30 = new Lightfrequency(1,30,0);
		Lightfrequency lightfrequency_night_40 = new Lightfrequency(1,40,0);
		
		LightState lightState_day_0 = new LightState(0,0,0); // 长亮
		LightState lightState_day_1 = new LightState(0,1,0); // 长灭
		LightState lightState_day_2 = new LightState(0,2,0); // 同步
		LightState lightState_day_3 = new LightState(0,3,0); // 自主
		LightState lightState_day_4 = new LightState(0,4,0); // 整体断电
		LightState lightState_night_0 = new LightState(1,0,0);
		LightState lightState_night_1 = new LightState(1,1,0);
		LightState lightState_night_2 = new LightState(1,2,0);
		LightState lightState_night_3 = new LightState(1,3,0);
		LightState lightState_night_4 = new LightState(1,4,0);
		
		lights.forEach(light -> {
			Integer faultIndicate = light.getFaultIndicate(); // 故障指示
			Integer lampDayFrequency = light.getLampDayFrequency(); // 灯具白天频率
			Integer lampNightFrequency = light.getLampNightFrequency(); // 灯具夜间频率
			Integer lampBuzzerDay = light.getLampBuzzerDay(); // 蜂鸣器白天状态
			Integer lampBuzzerNight = light.getLampBuzzerNight(); // 蜂鸣器夜间状态
			Integer lampDayState = light.getLampDayState(); // 灯具白天状态
			Integer lampNightState = light.getLampNightState(); // 灯具夜间状态
			
			if (faultIndicate != null) {
				if (faultIndicate == 2) {
					LOG.info("---故障统计：主通道故障+1---");
					Integer count = faultindication_2.getCount();
					count++;
					faultindication_2.setCount(count);
				} else if (faultIndicate == 3) {
					LOG.info("---故障统计：主副通道故障+1---");
					Integer count = faultindication_3.getCount();
					count++;
					faultindication_3.setCount(count);
				} else if (faultIndicate == 7 || faultIndicate == 8 || faultIndicate == 9) {
					LOG.info("---故障统计：长时间离线+1---");
					Integer count = faultindication_4.getCount();
					count++;
					faultindication_4.setCount(count);
				}
			}
			
			// 白天蜂鸣器状态
			if (lampBuzzerDay != null) {
				if (lampBuzzerDay == 0) {
					LOG.info("蜂鸣器统计：白天长鸣+1");
					Integer count = buzzerstate_day_0.getCount();
					count++;
					buzzerstate_day_0.setCount(count);
				} else if (lampBuzzerDay == 1){
					LOG.info("蜂鸣器统计：白天长静+1");
					Integer count = buzzerstate_day_1.getCount();
					count++;
					buzzerstate_day_1.setCount(count);
				}
			}
			// 夜间蜂鸣器状态
			if (lampBuzzerNight != null) {
				if (lampBuzzerNight == 0) {
					LOG.info("蜂鸣器统计：夜间长鸣+1");
					Integer count = buzzerstate_night_0.getCount();
					count++;
					buzzerstate_night_0.setCount(count);
				} else if (lampBuzzerNight == 1) {
					LOG.info("蜂鸣器统计：夜间长静+1");
					Integer count = buzzerstate_night_1.getCount();
					count++;
					buzzerstate_night_1.setCount(count);
				}
			}
			
			// 灯具白天状态
			if (lampDayState != null) {
				if (lampDayState == 0) {
					LOG.info("灯具状态统计：白天长亮+1");
					Integer count = lightState_day_0.getCount();
					count++;
					lightState_day_0.setCount(count);
				} else if (lampDayState == 1) {
					LOG.info("灯具状态统计：白天长灭+1");
					Integer count = lightState_day_1.getCount();
					count++;
					lightState_day_1.setCount(count);
				} else if (lampDayState == 2) {
					LOG.info("灯具状态统计：白天同步闪烁+1");
					Integer count = lightState_day_2.getCount();
					count++;
					lightState_day_2.setCount(count);
				} else if (lampDayState == 3) {
					LOG.info("灯具状态统计：白天自主闪烁+1");
					Integer count = lightState_day_3.getCount();
					count++;
					lightState_day_3.setCount(count);
				} else if (lampDayState == 4) {
					LOG.info("灯具状态统计：白天整体断电+1");
					Integer count = lightState_day_4.getCount();
					count++;
					lightState_day_4.setCount(count);
				}
			}
			
			// 灯具夜间状态
			if (lampNightState != null) {
				if (lampNightState == 0) {
					LOG.info("灯具状态统计：夜间夜间长亮+1");
					Integer count = lightState_night_0.getCount();
					count++;
					lightState_night_0.setCount(count);
				} else if (lampNightState == 1) {
					LOG.info("灯具状态统计：夜间长灭+1");
					Integer count = lightState_night_1.getCount();
					count++;
					lightState_night_1.setCount(count);
				} else if (lampNightState == 2) {
					LOG.info("灯具状态统计：夜间同步闪烁+1");
					Integer count = lightState_night_2.getCount();
					count++;
					lightState_night_2.setCount(count);
				} else if (lampNightState == 3) {
					LOG.info("灯具状态统计：夜间自主闪烁+1");
					Integer count = lightState_night_3.getCount();
					count++;
					lightState_night_3.setCount(count);
				} else if (lampNightState == 4) {
					LOG.info("灯具状态统计：夜间整体断电+1");
					Integer count = lightState_night_4.getCount();
					count++;
					lightState_night_4.setCount(count);
				}
			}
			
			// 灯具白天频率
			if (lampDayFrequency != null) {
				if (lampDayFrequency == 20) {
					Integer count = lightfrequency_day_20.getCount();
					count++;
					lightfrequency_day_20.setCount(count);
				} else if (lampDayFrequency == 30) {
					Integer count = lightfrequency_day_30.getCount();
					count++;
					lightfrequency_day_30.setCount(count);
				} else if (lampDayFrequency == 40) {
					Integer count = lightfrequency_day_40.getCount();
					count++;
					lightfrequency_day_40.setCount(count);
				}
			}
			
			// 灯具夜间频率
			if (lampNightFrequency != null) {
				if (lampNightFrequency == 20) {
					Integer count = lightfrequency_night_20.getCount();
					count++;
					lightfrequency_night_20.setCount(count);
				} else if (lampNightFrequency == 30) {
					Integer count = lightfrequency_night_30.getCount();
					count++;
					lightfrequency_night_30.setCount(count);
				} else if (lampNightFrequency == 40) {
					Integer count = lightfrequency_night_40.getCount();
					count++;
					lightfrequency_night_40.setCount(count);
				}
			}
			
		});
		
		List<Buzzerstate> buzzerstates = new ArrayList<>();
		buzzerstates.add(buzzerstate_night_1);
		buzzerstates.add(buzzerstate_night_0);
		buzzerstates.add(buzzerstate_day_0);
		buzzerstates.add(buzzerstate_day_1);
		
		List<Faultindication> faultindications = new ArrayList<>();
		faultindications.add(faultindication_2);
		faultindications.add(faultindication_3);
		faultindications.add(faultindication_4);
		
		List<Lightfrequency> lightfrequencies = new ArrayList<>();
		lightfrequencies.add(lightfrequency_night_40);
		lightfrequencies.add(lightfrequency_night_30);
		lightfrequencies.add(lightfrequency_night_20);
		lightfrequencies.add(lightfrequency_day_20);
		lightfrequencies.add(lightfrequency_day_30);
		lightfrequencies.add(lightfrequency_day_40);
		
		List<LightState> lightStates = new ArrayList<>();
		lightStates.add(lightState_night_4);
		lightStates.add(lightState_night_3);
		lightStates.add(lightState_night_2);
		lightStates.add(lightState_night_1);
		lightStates.add(lightState_night_0);
		lightStates.add(lightState_day_4);
		lightStates.add(lightState_day_3);
		lightStates.add(lightState_day_2);
		lightStates.add(lightState_day_1);
		lightStates.add(lightState_day_0);
		
		
		lightStatisticsVo.setBuzzerstates(buzzerstates);
		lightStatisticsVo.setFaultindications(faultindications);
		lightStatisticsVo.setLightfrequencies(lightfrequencies);
		lightStatisticsVo.setLightStates(lightStates);
		
		return ResultGenerator.genSuccessResult(lightStatisticsVo);
	}
	
	
	
	/**
	 * 返回权限Map（node：线路List；authority：权限）
	 * @return
	 */
	private Map<String, Object> getAuthority() {
		Map<String, Object> map = new HashMap<>();
		
		String token = request.getHeader("token");
		Map<String, Claim> claims = TokenUtils.verifyToken(token);
		String uid = TokenUtils.getInfo(claims, "uid");
		int id = Integer.parseInt(uid);
		
		map.put("uid", uid);
		
		List<Authority> authorities = authorityMapper.selectAuthorityByUid(id);
		
		if (!authorities.isEmpty()) {
			Authority authority = authorities.get(0);
			Integer auth = authority.getAuthority();
			map.put("auth", auth);
			
			List<Integer> nodeids = new ArrayList<>();
			
			authorities.forEach(a -> {
				Integer nodeid = a.getNodeid();
				nodeids.add(nodeid);
			});
			
			map.put("nodeids", nodeids);
		}
		
		return map;
	}
	
	/**
	 * 根据nodeid 得到父id
	 * @param attrNodeid
	 * @return
	 */
	private Integer getFid(Integer attrNodeid) {
		Node node = nodeMapper.selectByPrimaryKey(attrNodeid);
		Integer fid = node.getFid();
		
		return fid;
	}

	/**
	 * 递归得到一个节点的所有管理者
	 * @param nodeid
	 * @param nodeids
	 */
	private void getUserIds(Integer nodeid, Set<Integer> userids) {
		Set<Integer> userids1 = nodeUserMapper.selectUserIDByNodeid(nodeid);
		userids.addAll(userids1);
		
		Integer fid = getFid(nodeid);
		if (fid != -1) {
			getUserIds(fid, userids);
		}
	}
	
}
