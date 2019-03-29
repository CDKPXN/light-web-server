package com.company.project.log.aspect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.company.project.dao.DevicedataMapper;
import com.company.project.dao.LightMapper;
import com.company.project.dao.LogEquipmentMapper;
import com.company.project.dao.LogSysMapper;
import com.company.project.dao.UserMapper;
import com.company.project.device.dto.DataDto;
import com.company.project.device.dto.QueryDto;
import com.company.project.device.dto.ResultDto;
import com.company.project.device.dto.SettingDto;
import com.company.project.model.Devicedata;
import com.company.project.model.Light;
import com.company.project.model.Log;
import com.company.project.model.LogEquipment;
import com.company.project.model.LogSys;
import com.company.project.model.User;
import com.company.project.utils.Base64Utils;
import com.company.project.utils.LogUtils;
import com.company.project.utils.RequestUtils;
import com.company.project.utils.TokenUtils;

@Aspect
@Component
public class LOGAspect
{
	private static final Logger LOG = LoggerFactory.getLogger(LOGAspect.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private LogSysMapper logSysMapper;
	
	@Autowired
	private LightMapper lightMapper;
	
	@Autowired
	private DevicedataMapper devicedataMapper;
	
	@Autowired
	private LogEquipmentMapper logEquipmentMapper;
	
	@Pointcut("execution(public * com.company.project.web.*.*(..))")
	public void controllerAspect() {}
	
	@Pointcut("execution(public * com.company.project.web.AuthController.login(*))")
	public void beforeLogin() {}
	
	// 设备数据
	@Pointcut("execution(public * com.company.project.device.controller.DeviceController.*(..))")
	public void deviceData() {}
	
	
	// 设备日志  + 数据处理
	@AfterReturning(returning = "result", pointcut = "deviceData()")
	public void AfterDeviceController(JoinPoint joinPoint,Object result) {
		HttpServletRequest request = getRequest();
		String uri = RequestUtils.getURI(request);
		
		// 日志处理
		handleEquipmentLog(uri,joinPoint,result);
		
		// 数据处理
		handleDeviceDate(uri,joinPoint,result);
		
	}
	
	/**
	 * 数据处理
	 * @param uri
	 * @param joinPoint
	 * @param result
	 */
	private void handleDeviceDate(String uri, JoinPoint joinPoint, Object result) {
		
		LOG.info("---数据处理---插入数据---");
		List<Devicedata> devicedatas = new ArrayList<>();
		Devicedata devicedata = new Devicedata();
		
		if (uri.contains("query")) {
			Integer dataType = 1;
			String content = "无数据";
			
			List<ResultDto> resultDtos = decodeResult(result);
			LOG.info("返回数据={}",resultDtos);
			getDevicedatas(resultDtos, content, devicedata, dataType, devicedatas);
		} else if (uri.contains("setting")) {
			Integer dataType = 2;
			Object[] args = joinPoint.getArgs();
			SettingDto settingDto = (SettingDto)args[0];
			Light light = settingDto.getData();
			String content = JSON.toJSONString(light);
			
			List<ResultDto> resultDtos = decodeResult(result);
			getDevicedatas(resultDtos, content, devicedata, dataType, devicedatas);
			
		} else if (uri.contains("data")) {
			Integer dataType = 3;
			
			// 获取content
			Object[] args = joinPoint.getArgs();
			DataDto dataDto = (DataDto) args[0];
			String data = dataDto.getData();
			Light light = JSON.parseObject(data, Light.class);
			String attrNum = light.getAttrNum();
			light.setAttrNum(null);
			String content = JSON.toJSONString(light);
			
			
			String resultStr = getResultStrWithData(result);
			
			// 构建devicedata 对象
			devicedata.setContent(content);
			devicedata.setCtime(new Date());
			devicedata.setDataType(dataType);
			devicedata.setIsDel((byte)0);
			devicedata.setResult(resultStr);
			devicedata.setSource(attrNum);
			devicedata.setTarget("服务器");
			
			// 添加到list中
			devicedatas.add(devicedata);
		}
		
		LOG.info("要插入的数据={}",devicedatas);
		devicedataMapper.insertList(devicedatas);
	}


	/**
	 * 处理设备日志
	 * 根据uri 不同进行处理
	 * @param uri
	 * @param joinPoint
	 * @param result
	 */
	private void handleEquipmentLog(String uri, JoinPoint joinPoint, Object result) {
		LOG.info("----插入设备日志----");
		// 获取 type，FaultIndicate
		Object[] args = joinPoint.getArgs();
		List<LogEquipment> logEquipments = new ArrayList<>();
		LogEquipment logEquipment = new LogEquipment();
		
		if (uri.contains("query")) {
			QueryDto queryDto = (QueryDto) args[0];
			String type = queryDto.getType();
			
			String content = LogUtils.getLogEquipmentEnum(uri, type, null);
			LOG.info("设备日志内容={}",content);
			
			List<ResultDto> resultDtos = decodeResult(result);
			LOG.info("调用物联网接口返回结果={}",resultDtos);
			
			if (!resultDtos.isEmpty()) {
				getEquipmentLog(resultDtos, content,logEquipment,logEquipments);
			}
		} else if (uri.contains("setting")) {
			SettingDto settingDto = (SettingDto) args[0];
			String type = settingDto.getType();
			
			String content = LogUtils.getLogEquipmentEnum(uri, type, null);
			LOG.info("设备日志内容={}",content);
			
			List<ResultDto> resultDtos = decodeResult(result);
			LOG.info("调用物联网接口返回结果={}",resultDtos);
			
			if (!resultDtos.isEmpty()) {
				getEquipmentLog(resultDtos, content,logEquipment,logEquipments);
			}
			
		} else if (uri.contains("data")) {
			DataDto dataDto = (DataDto) args[0];
			String type = dataDto.getType();
			String data = dataDto.getData();
			
			Light light = JSON.parseObject(data, Light.class);
			LOG.info("接收数据转换成light={}",light);
			
			String attrNum = light.getAttrNum();
			Integer faultIndicate = light.getFaultIndicate();
			String content = LogUtils.getLogEquipmentEnum(uri, type, faultIndicate);
			LOG.info("设备日志内容={}",content);
			
			Light light2 = lightMapper.selectLightByAttrNum(attrNum);
			LOG.info("通过灯具编号查询到的light={}",light2);
			
			String lampname = light2.getAttrName();
			
			if (StringUtils.isBlank(lampname)) {
				lampname = "未命名设备";
			}
			
			String num = LogUtils.getEquipmentLogNum();
			
			String resultStr = getResultStrWithData(result);
			
			logEquipment.setContent(content);
			logEquipment.setCtime(new Date());
			logEquipment.setLampname(lampname);
			logEquipment.setLampnum(attrNum);
			logEquipment.setNum(num);
			logEquipment.setResult(resultStr);
			
			logEquipments.add(logEquipment);
		}
		
		LOG.info("插入的设备日志={}",logEquipments);
		if (!logEquipments.isEmpty()) {
			logEquipmentMapper.insertList(logEquipments);
		}
	}


	/**
	 * 通过返回结果的code 得到操作时成功 还是 失败
	 * @param result
	 * @return
	 */
	private String getResultStrWithData(Object result) {
		JSONObject resultJson = JSON.parseObject(result.toString());
		Integer code = resultJson.getInteger("code");
		
		// 判断操作是否成功
		String resultStr = null;
		if (code == 200) {
			resultStr = "成功";
		} else {
			resultStr = "失败";
		}
		
		return resultStr;
	}

	/**
	 * 遍历出需要插入的日志
	 * 插入到设备日志list中
	 * @param resultDtos
	 * @param content
	 * @param logEquipment
	 * @param logEquipments
	 */
	private void getEquipmentLog(List<ResultDto> resultDtos, String content, LogEquipment logEquipment, List<LogEquipment> logEquipments) {
		
		resultDtos.forEach(resultDto -> {
			String attrNum = resultDto.getAttrNum();
			Integer code = resultDto.getCode();
			
			Light light = lightMapper.selectLightByAttrNum(attrNum);
			String attrName = light.getAttrName();
			
			if (StringUtils.isBlank(attrName)) {
				attrName = "未命名设备";
			}
			
			String equipmentLogNum = LogUtils.getEquipmentLogNum();
			
			logEquipment.setContent(content);
			logEquipment.setCtime(new Date());
			logEquipment.setLampname(attrName);
			logEquipment.setLampnum(attrNum);
			logEquipment.setNum(equipmentLogNum);
			
			String result = null;
			if (code == 200) {
				result = "成功";
			} else {
				result = "失败";
			}
			logEquipment.setResult(result);
			LOG.info("插入一条设备日志={}",logEquipment);
			logEquipments.add(logEquipment);
		});
		
	}

	// 系统日志
	@AfterReturning(returning = "result", pointcut = "controllerAspect()")
	public void afterController(JoinPoint joinPoint,Object result) {
		
		HttpServletRequest request = getRequest();
		String uri = RequestUtils.getURI(request);
		String method = RequestUtils.getMethod(request);
		
		// 登录时没有Token，所以登录的日志单独做，这里排除登录
		if (uri.contains("auth") && "post".equals(method.toLowerCase())) {
			return ;
		}
		
		String token = request.getHeader("token");
		// 设备日志
		LOG.info("--- 系统日志 ---");
		LogSys sysLog = getSysLog(uri, method, token, request);
		setResult(result, sysLog);
		logSysMapper.insertSelective(sysLog);
	}
	
	/**
	 * 获取设备日志
	 * @param joinPoint
	 * @param uri
	 * @param method
	 * @return
	 */
//	private LogEquipment getEquipmentLog(JoinPoint joinPoint, String uri, String method) {
//		Object[] args = joinPoint.getArgs();
//		Object object = args[0];
//		String lightnum = null;
//		Light light2 = null;
//		if (object instanceof String) {
//			lightnum = (String) object;
//		} else if (object instanceof Light) {
//			light2 = (Light) object;
//			LOG.info("light2={}",light2);
//			lightnum = light2.getAttrNum();
//		}
//		LOG.info("lightnum={}",lightnum);
//		Light light = lightMapper.selectLightByAttrNum(lightnum);
//		
//		if (light == null) {
//			light = light2;
//			lightMapper.insert(light);
//		}
//		
//		String attrNum = light.getAttrNum(); // 编号
//		String attrBelonging = light.getAttrName();
//		String lightName = attrBelonging; //　灯具名称
//		String equipmentLogNum = LogUtils.getEquipmentLogNum(); // 设备日志编号
//		
//		LogEquipment logEquipment = new LogEquipment();
//		String logEquipmentEnum = LogUtils.getLogEquipmentEnum(uri, method);
//		logEquipment.setContent(logEquipmentEnum);
//		logEquipment.setLampname(lightName);
//		logEquipment.setLampnum(attrNum);
//		logEquipment.setNum(equipmentLogNum);
//		
//		return logEquipment;
//	}
	
	/**
	 * 获取系统日志
	 * @param uri
	 * @param method
	 * @param token
	 * @param request
	 * @return
	 */
	private LogSys getSysLog(String uri, String method, String token, HttpServletRequest request) {
		String sysLogEnum = LogUtils.getSysLogEnumString(uri, method);
		Map<String, Claim> claims = TokenUtils.verifyToken(token);
		String uid = TokenUtils.getInfo(claims, "uid");
		Integer id = Integer.parseInt(uid);
		
		User user = userMapper.selectByPrimaryKey(id);
		String operator = user.getUsername();
		String sysLogNum = LogUtils.getSysLogNum();
		String ipAddress = RequestUtils.getIpAddress(request);
		
		LogSys logSys = new LogSys();
		logSys.setContent(sysLogEnum);
		logSys.setIpaddress(ipAddress);
		logSys.setNum(sysLogNum);
		logSys.setOperator(operator);
		
		return logSys;
	}
	
	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	private HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}
	
	/**
	 * 设置日志结果
	 * @param result
	 * @param log
	 */
	private void setResult(Object result, Object log) {
		JSONObject resultJson = JSONObject.parseObject(result.toString());
		String code = resultJson.getString("code");
		
		Log log2 = (Log) log;
		
		if ("200".equals(code)) {
			LOG.info("--日志记录结果：操作成功--");
			log2.setResult("成功");
		} else {
			LOG.info("--日志记录结果：操作失败--");
			log2.setResult("失败");
		}
	}
	
	/**
	 * 解码Result
	 * @param result
	 * @return
	 */
	private List<ResultDto> decodeResult(Object result) {
		JSONObject resultOjbect = JSON.parseObject(result.toString());
		Object object = resultOjbect.get("data");
		
		String data = null;
		if (object != null) {
			data = resultOjbect.get("data").toString();
		}
		
//		String decodeData = Base64Utils.decode(data);
//		LOG.info("解码后的内容={}",decodeData);
//		List<ResultDto> list = JSON.parseArray(decodeData, ResultDto.class);
		List<ResultDto> list = JSON.parseArray(data, ResultDto.class);
		return list;
	}
	
	/**
	 * 遍历resultDto，构造devicedata，并添加到list中
	 * @param resultDtos
	 * @param content
	 * @param devicedata
	 * @param dataType
	 * @param devicedatas
	 */
	private void getDevicedatas(List<ResultDto> resultDtos,String content,Devicedata devicedata,Integer dataType,List<Devicedata> devicedatas) {
		resultDtos.forEach(resultDto -> {
			devicedata.setContent(content);
			devicedata.setCtime(new Date());
			devicedata.setDataType(dataType);
			
			Integer code = resultDto.getCode();
			String attrNum = resultDto.getAttrNum();
			String resultStr = null;
			if (code == 200) {
				resultStr = "成功";
			} else {
				resultStr = "失败";
			}
			
			devicedata.setResult(resultStr);
			devicedata.setSource("服务器");
			devicedata.setTarget(attrNum);
			devicedata.setIsDel((byte)0);
			
			devicedatas.add(devicedata);
		});
	}
}
