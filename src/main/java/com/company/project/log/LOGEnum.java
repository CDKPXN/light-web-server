package com.company.project.log;

public enum LOGEnum
{
	LIGHT_ON("灯具上线"),
	LIGHT_OFF("灯具下线"),
	HEAER_GET("刷新心跳"),
	HEART_PUT("设置心跳"),
	HEART_POST("心跳定时上报"),
	REFRESH_4G("刷新4G"),
	REFRESH_GPS("刷新GPS"),
	BUZZER_PUT("设置蜂鸣器"),
	AUTH_POST("登录"), // 登录
	AUTH_PUT("修改密码"), // 修改密码
	LIGHT_GET("查询灯具"), // 查询灯具
	LIGHT_PUT("修改灯具"), // 修改灯具，与协议无关
	LIGHT_CMD_PUT("设置灯具"), // 设置灯具，与协议相关
	LIGHT_POST("添加灯具"), // 添加灯具
	LIGHT_DELETE("删除灯具"), // 删除灯具
	LIGHT_CMD_GET("刷新灯具"), // 
	LIGHT_FAULT_A("主通道故障"),
	LIGHT_FAULT_B("主副通道均故障"),
	CONTACT_GET("查询联系人"), // 查询联系人
	CONTACT_PUT("修改联系人"), // 修改联系人
	CONTACT_DELETE("删除联系人"), // 删除联系人
	CONTACT_POST("添加联系人"), // 添加联系人
	NODE_GET("查询节点"), // 查询节点
	NODE_POST("添加节点"), // 添加节点
	NODE_PUT("修改节点"), // 修改节点
	NODE_DELETE("删除节点"), // 修改节点
	MSG_SEND("发送消息"),
	MSG_ACCEPT("接收消息"),
	USER_GET("查询用户"),
	USER_POST("添加用户"),
	USER_PUT("修改用户"),
	USER_DELETE("删除用户"),
	LOG_GET("查询日志"),
	DEVICEDATA_GET("查询数据"), // 查询数据
	STATISTICS_GET("查询统计"),
	ILLEGALOPERATION("非法操作");
	
	private String operation;
	
	private LOGEnum(String operation) { 
		this.operation = operation;
	}
	
	@Override
	public String toString() {
		return operation;
	}
}
