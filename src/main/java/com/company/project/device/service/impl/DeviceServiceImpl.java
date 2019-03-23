package com.company.project.device.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.DevicedataMapper;
import com.company.project.dao.LightMapper;
import com.company.project.dao.NodeUserMapper;
import com.company.project.device.dto.DataDto;
import com.company.project.device.dto.IoTDto;
import com.company.project.device.dto.QueryDto;
import com.company.project.device.dto.ResultDto;
import com.company.project.device.dto.SettingDto;
import com.company.project.device.service.DeviceService;
import com.company.project.model.Devicedata;
import com.company.project.model.Light;
import com.company.project.utils.Base64Utils;
import com.company.project.utils.HttpUtils;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional
public class DeviceServiceImpl extends AbstractService<Devicedata> implements DeviceService {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	private static final String URL = "http://127.0.0.1:8089/api/cmd";
	
	@Autowired
	private HttpServletRequest request;
	
	
	@Autowired
	private LightMapper lightMapper;
	
	@Override
	public Light setHeart(String attrNum, Integer heartfrequency) {
		
		
		String jsonparam = getParamJsonString();
		
		// 伪装的json串
//		String jsonparam = "{\"openid\":\"lslsfjals\",\"shopid\":2}";
		
		// 插入数据表
		Devicedata deviceData1 = getDeviceData(jsonparam, 5,"服务器",attrNum);
		// 调用协议接口
		String url = "http://127.0.0.1:8080/api/protocol";
		Result result = null;
		try {
			result = HttpUtils.doPost(url, jsonparam);
		} catch (IOException e) {
			LOG.info("请求协议接口发生异常");
			// 设置result 的值，使其不为null
			result = new Result<>();
			result.setCode(ResultCode.FAIL);
		}
		LOG.info("result={}",result);
		
		if (result.getCode() == 200) {
			deviceData1.setResult("成功");
		} else {
			deviceData1.setResult("失败");
		}
		save(deviceData1);
		
		// 将返回数据插入数据表
		Light light3 = getLightByAttrNum(attrNum);
		light3.setSetHeartfrequency(heartfrequency);
		
		Light light2 = handleProtocolData(result, attrNum, 1);
		
		if (light2 != null) {
			Integer heartfrequency2 = light2.getHeartfrequency();
			// 设置心跳实际返回值字段
			light3.setHeartfrequency(heartfrequency2);
			light3.setHeartupdatetime(new Date());
		}
		lightMapper.updateByPrimaryKeySelective(light3);
		
		return light2;
	}

	@Override
	public Light refresh4G(String attrNum) {
		String param = getParamJsonString();
		
		Devicedata deviceData1 = getDeviceData(param, 4,"服务器",attrNum);
		
		String url = "http://127.0.0.1:8080/api/protocol";
		Result result = null;
		try {
			result = HttpUtils.doPost(url, param);
		} catch (IOException e) {
			LOG.info("请求协议接口发生异常");
			// 设置result 的值，使其不为null
			result = new Result<>();
			result.setCode(ResultCode.FAIL);
		}
		LOG.info("result={}",result);
		
		if (result.getCode() == 200) {
			deviceData1.setResult("成功");
		} else {
			deviceData1.setResult("失败");
		}
		
		save(deviceData1);
		
		Light light = handleProtocolData(result, attrNum, 2);
		if (light != null) {
			Light light2 = getLightByAttrNum(attrNum);
			String fgSignal = light.getFgSignal();
			String fgIccid = light.getFgIccid();
			light2.setFgIccid(fgIccid);
			light2.setFgSignal(fgSignal);
			light2.setHeartupdatetime(new Date());
			lightMapper.updateByPrimaryKeySelective(light2);
		}
		
		return light;
	}

	@Override
	public Light refreshGPS(String attrNum) {
		String param = getParamJsonString();
		
		Devicedata deviceData1 = getDeviceData(param, 4,"服务器",attrNum);
		
		String url = "http://127.0.0.1:8080/api/protocol";
		Result result = null;
		try {
			result = HttpUtils.doPost(url, param);
		} catch (IOException e) {
			LOG.info("请求协议接口发生异常");
			// 设置result 的值，使其不为null
			result = new Result<>();
			result.setCode(ResultCode.FAIL);
		}
		LOG.info("result={}",result);
		
		if (result.getCode() == 200) {
			deviceData1.setResult("成功");
		} else {
			deviceData1.setResult("失败");
		}
		
		save(deviceData1);
		
		Light light = handleProtocolData(result, attrNum, 2);
		if (light != null) {
			Light light2 = getLightByAttrNum(attrNum);
			Integer gpsElevation = light.getGpsElevation();
			String gpsLatitude = light.getGpsLatitude();
			Byte gpsLock = light.getGpsLock();
			String gpsLongitude = light.getGpsLongitude();
			
			light2.setGpsElevation(gpsElevation);
			light2.setGpsLatitude(gpsLatitude);
			light2.setGpsLongitude(gpsLongitude);
			light2.setGpsLock(gpsLock);
			
			lightMapper.updateByPrimaryKeySelective(light2);
		}
		
		return light;
	}

	@Override
	public Light setBuzzer(String attrNum, Integer lampBuzzerDay, Integer lampBuzzerNight) {
		String param = getParamJsonString();
		
		Devicedata deviceData1 = getDeviceData(param, 5,"服务器",attrNum);
		
		String url = "http://127.0.0.1:8080/api/protocol";
		Result result = null;
		try {
			result = HttpUtils.doPost(url, param);
		} catch (IOException e) {
			LOG.info("请求协议接口发生异常");
			// 设置result 的值，使其不为null
			result = new Result<>();
			result.setCode(ResultCode.FAIL);
		}
		LOG.info("result={}",result);
		
		if (result.getCode() == 200) {
			deviceData1.setResult("成功");
		} else {
			deviceData1.setResult("失败");
		}
		
		save(deviceData1);
		
		Light light = handleProtocolData(result, attrNum, 2);
		Light light2 = getLightByAttrNum(attrNum);
		light2.setSetDayBuzzer(lampBuzzerDay);
		light2.setSetNightBuzzer(lampBuzzerNight);
		if (light != null) {
			Integer lampBuzzerDay2 = light.getLampBuzzerDay();
			Integer lampBuzzerNight2 = light.getLampBuzzerNight();
			
			light2.setLampBuzzerDay(lampBuzzerDay2);
			light2.setLampBuzzerNight(lampBuzzerNight2);
			light2.setHeartupdatetime(new Date());
			lightMapper.updateByPrimaryKeySelective(light2);
		}
		
		return light;
	}

	@Override
	public Light setLight(String attrNum, Integer lampDayFrequency, Integer lampNightFrequency, Integer lampDayState,
			Integer lampNightState) {
		
		String param = getParamJsonString();
		Devicedata deviceData1 = getDeviceData(param, 5, "服务器", attrNum);
		
		String url = "http://127.0.0.1:8080/api/protocol";
		
		Result result = null;
		try {
			result = HttpUtils.doPost(url, param);
		} catch (Exception e) {
			LOG.info("请求协议接口发生异常");
			// 设置result 的值，使其不为null
			result = new Result<>();
			result.setCode(ResultCode.FAIL);
		}
		
		LOG.info("result={}",result);
		
		if (result.getCode() == 200) {
			deviceData1.setResult("成功");
		} else {
			deviceData1.setResult("失败");
		}
		
		save(deviceData1);
		
		Light light = handleProtocolData(result, attrNum, 2);
		
		Light light2 = getLightByAttrNum(attrNum);
		light2.setSetDayFrequency(lampDayFrequency);
		light2.setSetNightFrequency(lampNightFrequency);
		light2.setSetDayState(lampDayState);
		light2.setSetNightState(lampNightState);
		
		if (light != null) {
			Integer lampDayFrequency2 = light.getLampDayFrequency();
			Integer lampDayState2 = light.getLampDayState();
			Integer lampNightFrequency2 = light.getLampNightFrequency();
			Integer lampNightState2 = light.getLampNightState();
			
			light2.setLampDayFrequency(lampDayFrequency2);
			light2.setLampDayState(lampDayState2);
			light2.setLampNightState(lampNightState2);
			light2.setLampNightFrequency(lampNightFrequency2);
			lightMapper.updateByPrimaryKeySelective(light2);
		}
		
		return light;
	}

	@Override
	public void heartAutoReport(Light light) {
		String lightJson = JSON.toJSONString(light);
		String attrNum = light.getAttrNum();
		
		if (StringUtils.isBlank(attrNum)) {
			return ;
		}
		
		// 获取devicedata，并存入数据库
		Devicedata deviceData1 = getDeviceData(lightJson, 1, attrNum, "服务器");
		deviceData1.setResult("成功");
		save(deviceData1);
		
		Light light2 = null;
		try {
			light2 = lightMapper.selectLightByAttrNum(attrNum);
		} catch (Exception e) {
			LOG.error("根据编号查询到多个灯具设备");
			return;
		}
		
		if (light2 == null) {
			int insertres = 0;
			try {
				insertres = lightMapper.insertSelective(light);
			} catch (Exception e) {
				LOG.error("插入灯具发生异常");
			}
			if (insertres != 1) {
				return ;
			}
		} else {
			Integer id = light2.getId();
			light.setId(id);
			lightMapper.updateByPrimaryKeySelective(light);
		}
	}

	@Override
	public void lightOn(String attrNum) {
		Light light = lightMapper.selectLightByAttrNum(attrNum);
		Integer faultIndicate = light.getFaultIndicate();
		LOG.info("原本故障指示={}",faultIndicate);
		if (faultIndicate < 4) {
			return ;
		}
		light.setFaultIndicate(faultIndicate - 3);
		lightMapper.updateByPrimaryKeySelective(light);
	}

	/**
	 * 超过24消失才算离线
	 * 正常：1，主通道：2，主副通道：3
	 * 正常/离线：4，主通道/离线：5，主副通道/离线：6
	 */
	public void lightOff(String attrNum) {
		Light light = lightMapper.selectLightByAttrNum(attrNum);
		Integer faultIndicate = light.getFaultIndicate();
		LOG.info("原本故障指示={}",faultIndicate);
		if (faultIndicate > 3) {
			return ;
		}
		light.setFaultIndicate(faultIndicate + 3);
		lightMapper.updateByPrimaryKeySelective(light);
	}
	
	
	/**
	 * 发送查询指令
	 */
	public Result query(QueryDto queryDto) {
		
		// 判断参数是否是否正确
		List<String> attrNums = queryDto.getAttrNum();
		String type = queryDto.getType();
		
		if (attrNums.isEmpty() || StringUtils.isBlank(type)) {
			LOG.debug("参数错误");
			return ResultGenerator.genFailResult("参数不正确");
		}
		
		// 循环调用物联网提供的接口
		List<ResultDto> resultDtos = queryCMD(queryDto);
		
		return ResultGenerator.genSuccessResult(resultDtos);
	}
	
	/**
	 * 发送控制指令
	 */
	public Result setting(SettingDto settingDto) {
		
		Integer validataParam = validataParam(settingDto);
		
		if (validataParam != 0) {
			LOG.debug("参数不正确");
			return ResultGenerator.genFailResult("参数不正确");
		}
		
		List<ResultDto> list = controlCMD(settingDto);
		
		return ResultGenerator.genSuccessResult(list);
	}
	
	/**
	 * 处理设备回复的数据
	 */
	public Result handleData(DataDto dataDto) {
		LOG.info("开始校验light数据");
		Integer res = validataData(dataDto);
		
		if (res != 0) {
			LOG.info("数据错误");
			return ResultGenerator.genFailResult("参数错误");
		}
		
		Result result = handleDataByType(dataDto);
		
		return result;
	}
	
	/**
	 * 讨论type不同的情况
	 * 从而处理数据
	 * @param dataDto
	 * @return
	 */
	private Result handleDataByType(DataDto dataDto) {
		
		String type = dataDto.getType();
		String data = dataDto.getData();
		
		if ("4g".equals(type) || "gps".equals(type)) {
			LOG.info("处理4G/GPS回复信息");
			handleDataByFG(data);
		} else if ("autoH".equals(type) || "hReply".equals(type)) {
			LOG.info("处理心跳回复信息");
			handleDataByHeart(data);
		} else if ("off".equals(type)) {
			LOG.info("处理离线事件");
			handleOff(data, 3);
		} else if ("offL".equals(type)) {
			LOG.info("处理长离线事件");
			handleOff(data, 6);
		}
		
		return ResultGenerator.genSuccessResult();
	}
	
	/**
	 * 处理4G和GPS回复的数据
	 * @param data
	 */
	private void handleDataByFG(String data) {
		Light light = JSON.parseObject(data, Light.class);
		String attrNum = light.getAttrNum();
		LOG.info("更新4G或 GPS信息");
		updateLight(attrNum, light);
	}
	
	/**
	 * 处理心跳回复的数据
	 * @param data
	 */
	private void handleDataByHeart(String data) {
		Light light = JSON.parseObject(data, Light.class);
		String attrNum = light.getAttrNum();
		light.setHeartupdatetime(new Date());
		LOG.info("更新灯具编号心跳信息");
		updateLight(attrNum, light);
	}
	
	/**
	 * 处理离线或者长时间离线
	 * @param data
	 * @param indecate 离线或者 长时间离线的临界值
	 */
	private void handleOff(String data, Integer indecate) {
		Light light = JSON.parseObject(data, Light.class);
		String attrNum = light.getAttrNum();
		LOG.info("灯具={}，离线或长时间离线",attrNum);
		Light light2 = lightMapper.selectLightByAttrNum(attrNum);
		
		// 如果上传的设备编号不存在，则不处理
		if (light2 == null) {
			LOG.info("设备不存在，不做处理");
			return ;
		}
		
		Integer findicate = light2.getFaultIndicate();
		// 如果设备已经处于离线或者长时间离线 也不做处理
		if (findicate > indecate) {
			LOG.info("设备已经离线 或者 长时间离线，不做任何处理");
			return ;
		}
		
		LOG.info("处理前故障指示值={}",findicate);
		findicate += 3;
		
		light2.setFaultIndicate(findicate);
		LOG.info("处理后故障指示值={}",findicate);
		lightMapper.updateByPrimaryKeySelective(light2);
	}
	
	/**
	 * 根据attrNum更新灯具信息
	 * @param attrNum
	 * @param light
	 */
	private void updateLight(String attrNum, Light light) {
		
		Light light2 = lightMapper.selectLightByAttrNum(attrNum);
		
		if (light2 == null) {
			LOG.info("灯具在数据库中不存在");
			LOG.info("--开始创建灯具--");
			int insert = lightMapper.insert(light);
			
			if (insert == 1) {
				LOG.info("--创建成功--");
			}
		} else {
			LOG.info("灯具已经存在");
			Condition condition = new Condition(Light.class);
			Criteria criteria = condition.createCriteria();
			criteria.andEqualTo("attrNum", attrNum);
			
			LOG.info("更新attrNum={}灯具状态信息",attrNum);
			LOG.info("灯具信息={}",light);
			lightMapper.updateByConditionSelective(light, condition);
		}
	}

	/**
	 * 验证DdataDto参数是否正确
	 * @param dataDto
	 * @return
	 */
	private Integer validataData(DataDto dataDto) {
		String data = dataDto.getData();
		String type = dataDto.getType();
		
		if (StringUtils.isAnyBlank(data,type)) {
			LOG.debug("有参数为空");
			return -1;
		}
		
		Light light = null;
		try {
			light = JSON.parseObject(data,Light.class);
		} catch (Exception e) {
			LOG.error("data转换成light 发生异常={}",e.getMessage());
			return -2;
		}
		if (light == null) {
			LOG.debug("data 数据错误");
			return -2;
		}
		
		String attrNum = light.getAttrNum();
		
		if (StringUtils.isBlank(attrNum)) {
			LOG.debug("参数不正确：灯具编号为空");
			return -3;
		}
		return 0;
	}

	private List<ResultDto> controlCMD(SettingDto settingDto) {
		String type = settingDto.getType();
		Light light = settingDto.getData();
		List<String> attrNums = settingDto.getAttrNums();
		
		IoTDto ioTDto = new IoTDto();
		ioTDto.setType(type);
		
		try {
			BeanUtils.copyProperties(ioTDto, light);
		} catch (Exception e) {
			LOG.error("BeanUtils 转换时发生异常={}",e.getMessage());
		}
		LOG.info("转换之后的ioTDto={}",ioTDto);
		List<ResultDto> resultDtos = new ArrayList<>();
		for (String attrNum : attrNums) {
			ResultDto resultDto = new ResultDto();
			ioTDto.setAttrNum(attrNum);
			Map params = packageParam(ioTDto);
			
			Condition condition = new Condition(Light.class);
			Criteria criteria = condition.createCriteria();
			criteria.andEqualTo("attrNum", attrNum);
			lightMapper.updateByConditionSelective(light, condition);
			
			Result result = null;
			try {
				result = HttpUtils.doGet(URL, params);
			} catch (Exception e) {
				LOG.error("请求物联网服务器接口发生异常={}",e.getMessage());
				resultDto.setAttrNum(attrNum);
				resultDto.setCode(400);
				resultDtos.add(resultDto);
				continue;
			}
			
			getResultDto(result, attrNum, resultDtos, resultDto);
			
		}
		
		return resultDtos;
	}
	
	
	/**
	 * 验证参数是否正确
	 * @param settingDto
	 */
	private Integer validataParam(SettingDto settingDto) {
		String type = settingDto.getType();
		
		Light light = settingDto.getData();
		if (light == null) {
			LOG.debug("参数不正确");
			return -1;
		}
		
		if ("heart".equals(type)) {
			Integer setHeartfrequency = light.getSetHeartfrequency();
			
			if (setHeartfrequency == null) {
				LOG.debug("参数不正确");
				return -1;
			}
		} else if ("lihgt".equals(type)) {
			Integer df = light.getSetDayFrequency();
			Integer ds = light.getSetDayState();
			Integer nf = light.getSetNightFrequency();
			Integer ns = light.getSetNightState();
			
			if (df == null && ds == null && nf == null && ns == null) {
				LOG.debug("参数不正确");
				return -1;
			}
		}else {
			 Integer db = light.getSetDayBuzzer();
			 Integer nb = light.getSetNightBuzzer();
			 
			 if (db == null && nb == null) {
				 LOG.debug("参数不正确");
				 return -1;
			 }
		}
		
		return 0;
	}

	/**
	 * 循环调用 物联网提供的 接口进行查询
	 * @param queryDto
	 * @return
	 */
	private List<ResultDto> queryCMD(QueryDto queryDto) {
		// 参数转换、循环调用、返回的参数添加到list中
		List<String> attrNums = queryDto.getAttrNum();
		String type = queryDto.getType();
		
		IoTDto ioTDto = new IoTDto();
		List<ResultDto> resultDtos = new ArrayList<>();
		
		for (String attrNum : attrNums) {
			ResultDto resultDto = new ResultDto();
			
			ioTDto.setAttrNum(attrNum);
			
			if ("heart".equals(type)) {
				Light light = lightMapper.selectLightByAttrNum(attrNum);
				Integer heartfrequency = light.getHeartfrequency();
				ioTDto.setSetHeartfrequency(heartfrequency);
			}
			
			ioTDto.setType("r" + type);
			Map paramMap = packageParam(ioTDto);
			Result result = null;
			try {
				result = HttpUtils.doGet(URL, paramMap);
			} catch (Exception e) {
				LOG.error("发送指令时发送异常={}",e.getMessage());
				resultDto.setAttrNum(attrNum);
				resultDto.setCode(400);
				resultDtos.add(resultDto);
				continue;
			}

			getResultDto(result,attrNum, resultDtos, resultDto);
		}

		return resultDtos;
	}
	
	/**
	 * 解码Result
	 * 根据返回的result 封装 resultDto
	 * 将resultDto 添加到ResultDtoList 中
	 * @param result
	 * @param attrNum
	 * @param resultDtos
	 * @param resultDto
	 */
	private void getResultDto(Result result, String attrNum, List<ResultDto> resultDtos, ResultDto resultDto) {
		resultDto.setAttrNum(attrNum);
		JSONObject decodeResult = decodeResult(result);
		Integer code = (Integer)decodeResult.get("code");
		if (code == 200) {
			LOG.info("attrNum={}指令发送成功",attrNum);
			resultDto.setCode(200);
		} else {
			LOG.info("attrNum={}指令发送失败",attrNum);
			resultDto.setCode(400);
		}
		resultDtos.add(resultDto);
	}

	/**
	 * 将request中的参数自动封装成JavaBean
	 * @return
	 */
	private Light getLightByParam() {
		Light light = new Light();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			try {
				BeanUtils.setProperty(light, name, value);
			} catch (Exception e) {
				LOG.error("使用Beanutils发生异常");
			}
		}
		return light;
	}
	
	/**
	 * 获取DeviceData对象
	 * @param content 数据内容
	 * @param type 数据类型
	 * @param source 发送源
	 * @param target 接收方
	 * @return
	 */
	private Devicedata getDeviceData(String content, Integer type, String source, String target) {
		Devicedata devicedata = new Devicedata();
		devicedata.setContent(content);
		devicedata.setCtime(new Date());
		devicedata.setDataType(type);
		devicedata.setIsDel((byte)0);
		devicedata.setSource(source);
		devicedata.setTarget(target);
		return devicedata;
	}
	
	/**
	 * 将参数转换成JSON.toString
	 * @return
	 */
	private String getParamJsonString() {
		Light light = getLightByParam();
		LOG.info("light={}",light);
		// 转换为json串
		Object json = JSON.toJSON(light);
		String jsonparam = json.toString();
		LOG.info("json串={}",jsonparam);
		return jsonparam;
	}
	
	/**
	 * 处理协议返回的数据
	 * 将数据解密，将返回的数据解析后保存在数据库中
	 * 如果返回值为null，说明数据返回超时了
	 * @param result
	 * @param attrNum
	 * @return
	 */
	private Light handleProtocolData(Result result, String attrNum, Integer type) {
		JSONObject jsonData = decodeResult(result);
		Integer code = (Integer)jsonData.get("code");
		Object data2 = null;
		if (code == 200) {
			data2 = jsonData.get("data");
			LOG.info("接口协议返回的data={}",data2);
		} else {
			return null;
		}
		Devicedata devicedata = getDeviceData(data2.toString(), type, attrNum, "服务器");
		devicedata.setResult("成功");
		save(devicedata);
		Light light = JSON.parseObject(data2.toString(), Light.class);
		LOG.info("协议数据中返回的灯具状态信息={}",light);
		return light;
	}
	
	/**
	 * 根据编号去查询灯具
	 * @param attrNum
	 * @return
	 */
	private Light getLightByAttrNum(String attrNum) {
		// 通过编号查询灯具
		Light light = lightMapper.selectLightByAttrNum(attrNum);
		return light;
	}
	
	/**
	 * 将Result 解码得到JSONObject 的数据
	 * @param result
	 * @return
	 */
	private JSONObject decodeResult(Result result) {
		String data = result.getData().toString();
		
		String decodeData = Base64Utils.decode(data);
		LOG.info("解码后的内容={}",decodeData);
		
		Object parseData = JSON.parse(decodeData);
		JSONObject jsonData = JSON.parseObject(parseData.toString());
		
		LOG.info("jsonResult={}",jsonData);
		
		return jsonData;
	}

	/**
	 * 将参数转换为Map
	 * @param ioTDto
	 * @return
	 */
	private Map packageParam(Object ioTDto) {
		String jsonString = JSON.toJSONString(ioTDto);
		LOG.info("查询指令中的参数JSON={}",jsonString);
		Map paramMap = JSON.parseObject(jsonString, Map.class);
		LOG.info("查询指令中的参数Map={}",paramMap);
		return paramMap;
	}

}
