package com.company.project.log.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.company.project.dao.LightMapper;
import com.company.project.dao.LogEquipmentMapper;
import com.company.project.dao.LogSysMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Light;
import com.company.project.model.Log;
import com.company.project.model.LogEquipment;
import com.company.project.model.LogSys;
import com.company.project.model.User;
import com.company.project.utils.LogUtils;
import com.company.project.utils.RequestUtils;
import com.company.project.utils.TokenUtils;
import com.company.project.vo.HeartReport;

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
	private LogEquipmentMapper logEquipmentMapper;
	
	@Pointcut("execution(public * com.company.project.web.*.*(..))")
	public void controllerAspect() {}
	
	@Pointcut("execution(public * com.company.project.web.AuthController.login(*))")
	public void beforeLogin() {}
	
	
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
		boolean equipmentLog = uri.contains("cmd"); // 判断是设备日志还是系统日志，true：设备日志，false：系统日志
		
		if (equipmentLog) {
			// 设备日志
			LOG.info("--- 设备日志 ---");
			LogEquipment logEquipment = getEquipmentLog(joinPoint, uri, method);
			
			setResult(result, logEquipment);
			logEquipmentMapper.insertSelective(logEquipment);
		} else {
			// 系统日志
			LOG.info("--- 系统日志 ---");
			
			LogSys sysLog = getSysLog(uri, method, token, request);
			
			setResult(result, sysLog);
			logSysMapper.insertSelective(sysLog);
		}
	}
	
	/**
	 * 获取设备日志
	 * @param joinPoint
	 * @param uri
	 * @param method
	 * @return
	 */
	private LogEquipment getEquipmentLog(JoinPoint joinPoint, String uri, String method) {
		Object[] args = joinPoint.getArgs();
		Object object = args[0];
		String lightnum = null;
		Light light2 = null;
		if (object instanceof String) {
			lightnum = (String) object;
		} else if (object instanceof Light) {
			light2 = (Light) object;
			LOG.info("light2={}",light2);
			lightnum = light2.getAttrNum();
		}
		LOG.info("lightnum={}",lightnum);
		Light light = lightMapper.selectLightByAttrNum(lightnum);
		
		if (light == null) {
			light = light2;
			lightMapper.insert(light);
		}
		
		String attrNum = light.getAttrNum(); // 编号
		String attrBelonging = light.getAttrName();
		String lightName = attrBelonging; //　灯具名称
		String equipmentLogNum = LogUtils.getEquipmentLogNum(); // 设备日志编号
		
		LogEquipment logEquipment = new LogEquipment();
		String logEquipmentEnum = LogUtils.getLogEquipmentEnum(uri, method);
		logEquipment.setContent(logEquipmentEnum);
		logEquipment.setLampname(lightName);
		logEquipment.setLampnum(attrNum);
		logEquipment.setNum(equipmentLogNum);
		
		return logEquipment;
	}
	
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
}
