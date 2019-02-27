package com.company.project.service.impl;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;

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
import com.company.project.dao.DevicedataMapper;
import com.company.project.dao.LightMapper;
import com.company.project.dao.NodeUserMapper;
import com.company.project.model.Devicedata;
import com.company.project.model.Light;
import com.company.project.service.DeviceService;
import com.company.project.utils.Base64Utils;
import com.company.project.utils.HttpUtils;
import com.company.project.vo.LightAndUsersVo;

@Service
@Transactional
public class DeviceServiceImpl extends AbstractService<Devicedata> implements DeviceService {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceServiceImpl.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DevicedataMapper deviceDataMapper;
	
	@Autowired
	private NodeUserMapper nodeUserMapper;
	
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
		String url = "http://127.0.0.1:9091/api/v1/costomer/signup";
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
		}
		lightMapper.updateByPrimaryKeySelective(light3);
		
		return light2;
	}

	@Override
	public Light refresh4G(String attrNum) {
		String param = getParamJsonString();
		
		Devicedata deviceData1 = getDeviceData(param, 4,"服务器",attrNum);
		
		String url = "";
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
			String fgSignal = light.get4gSignal();
			String fgIccid = light.get4gIccid();
			light2.set4gIccid(fgIccid);
			light2.set4gSignal(fgSignal);
			lightMapper.updateByPrimaryKeySelective(light2);
		}
		
		return light;
	}

	@Override
	public Light refreshGPS(String attrNum) {
		String param = getParamJsonString();
		
		Devicedata deviceData1 = getDeviceData(param, 4,"服务器",attrNum);
		
		String url = "";
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
			Double gpsLatitude = light.getGpsLatitude();
			Byte gpsLock = light.getGpsLock();
			Double gpsLongitude = light.getGpsLongitude();
			
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
		
		String url = "";
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
			lightMapper.updateByPrimaryKeySelective(light2);
		}
		
		return light;
	}

	@Override
	public Light setLight(String attrNum, Integer lampDayFrequency, Integer lampNightFrequency, Integer lampDayState,
			Integer lampNightState) {
		
		String param = getParamJsonString();
		Devicedata deviceData1 = getDeviceData(param, 5, "服务器", attrNum);
		
		String url = "";
		
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
		}
		// 获取devicedata，并存入数据库
		Devicedata deviceData1 = getDeviceData(lightJson, 1, attrNum, "服务器");
		deviceData1.setResult("成功");
		save(deviceData1);
		
		Integer id = light2.getId();
		light.setId(id);
		
		lightMapper.updateByPrimaryKeySelective(light);
		
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
		String data = result.getData().toString();
		
		String decodeData = Base64Utils.decode(data);
		LOG.info("解码后的内容={}",decodeData);
		
		Object parseData = JSON.parse(decodeData);
		JSONObject jsonData = JSON.parseObject(parseData.toString());
		
		LOG.info("jsonResult={}",jsonData);
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
//		LightAndUsersVo lightAndUsersVo = nodeUserMapper.selectLightAndUsersByNum(attrNum);
//		// 设置 心跳设定值字段
//		Light light = new Light();
//		try {
//			BeanUtils.copyProperties(light, lightAndUsersVo);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			LOG.error("copyProperties 发生异常");
//		}
		Light light = lightMapper.selectLightByAttrNum(attrNum);
		return light;
	}
}
