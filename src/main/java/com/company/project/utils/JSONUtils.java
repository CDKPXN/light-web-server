package com.company.project.utils;

import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 将JSON中的Key,value值进行替换
 * @author xcj
 * @date 2019年4月1日 下午1:48:36
 * @todo TODO
 *
 */
public class JSONUtils {
	
	public static JSONObject changeJsonObj(JSONObject jsonObj,Map<String, String> keyMap) {
        JSONObject resJson = new JSONObject();
        Set<String> keySet = jsonObj.keySet();
        for (String key : keySet) {
            String resKey = keyMap.get(key) == null ? key : keyMap.get(key);
            try {
                JSONObject jsonobj1 = jsonObj.getJSONObject(key);
                resJson.put(resKey, changeJsonObj(jsonobj1, keyMap));
            } catch (Exception e) {
                try {
                    JSONArray jsonArr = jsonObj.getJSONArray(key);
                    resJson.put(resKey, changeJsonArr(jsonArr, keyMap));
                } catch (Exception x) {
                    resJson.put(resKey, jsonObj.get(key));
                }
            }
        }
        return resJson;
    }
    
    public static JSONArray changeJsonArr(JSONArray jsonArr,Map<String, String> keyMap) {
        JSONArray resJson = new JSONArray();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            resJson.add(changeJsonObj(jsonObj, keyMap));
        }
        return resJson;
    }
    
    public static void  changeValue (JSONObject jsonObj) {
    	// 故障指示
    	Integer faultIndicate = jsonObj.getInteger("faultIndicate");
    	
    	if(faultIndicate != null) {
    		switch (faultIndicate) {
    		case 1:
    			jsonObj.put("faultIndicate", "正常");
    			break;
    		case 2:
    			jsonObj.put("faultIndicate", "主通道故障");
    			break;
    		case 3:
    			jsonObj.put("faultIndicate", "主副通道均故障");
    			break;
    		default:
    			jsonObj.put("faultIndicate", "上报值错误");
    			break;
    		}
    	}
    	
    	// 白天夜间指示
    	Integer dayIndicate = jsonObj.getInteger("dayIndicate");
    	
    	if (dayIndicate != null) {
    		switch (dayIndicate) {
    		case 0:
    			jsonObj.put("dayIndicate", "白天");
    			break;
    		case 1:
    			jsonObj.put("dayIndicate", "夜间");
    			break;
    		default:
    			jsonObj.put("dayIndicate", "上报值错误");
    			break;
    		}
    	}
    	
    	// 白天灯具状态
    	Integer lampDayState = jsonObj.getInteger("lampDayState");
    	changeLampState(jsonObj, "lampDayState", lampDayState);
    	
    	// 夜间灯具状态
    	Integer lampNightState = jsonObj.getInteger("lampNightState");
    	changeLampState(jsonObj, "lampNightState", lampNightState);
    	
    	// 白天灯具状态设定值
    	Integer setDayState = jsonObj.getInteger("setDayState");
    	changeLampState(jsonObj, "setDayState", setDayState);
    	
    	// 夜间灯具状态设定值
    	Integer setNightState = jsonObj.getInteger("setNightState");
    	changeLampState(jsonObj, "setNightState", setNightState);
    	
    	// 白天蜂鸣器状态
    	Integer lampBuzzerDay = jsonObj.getInteger("lampBuzzerDay");
    	changeBuzzerState(jsonObj, "lampBuzzerDay", lampBuzzerDay);
    	
    	// 夜间蜂鸣器状态
    	Integer lampBuzzerNight = jsonObj.getInteger("lampBuzzerNight");
    	changeBuzzerState(jsonObj, "lampBuzzerNight", lampBuzzerNight);
    	
    	// 白天蜂鸣器状态设定值
    	Integer setDayBuzzer = jsonObj.getInteger("setDayBuzzer");
    	changeBuzzerState(jsonObj, "setDayBuzzer", setDayBuzzer);
    	
    	// 夜间蜂鸣器状态设定值
    	Integer setNightBuzzer = jsonObj.getInteger("setNightBuzzer");
    	changeBuzzerState(jsonObj, "setNightBuzzer", setNightBuzzer);
    	
    }
    
    private static void changeBuzzerState(JSONObject jsonObj, String key, Integer value) {
    	if (value != null) {
    		switch (value) {
			case 0:
				jsonObj.put(key, "长鸣");
				break;
			case 1:
				jsonObj.put(key, "长静");
				break;
			default:
				jsonObj.put(key, "值错误");
				break;
			}
    	}
    }
    
    /**
     * 改变灯具状态值
     * @param jsonObj
     * @param key
     * @param value
     */
    private static void changeLampState(JSONObject jsonObj, String key, Integer value) {
    	if (value != null) {
    		switch (value) {
    		case 0:
    			jsonObj.put(key, "长亮");
    			break;
    		case 1:
    			jsonObj.put(key, "长灭");
    			break;
			case 2:
				jsonObj.put(key, "同步闪烁");
    			break;
			case 3:
				jsonObj.put(key, "自主闪烁");
    			break;
			case 4:
				jsonObj.put(key, "整体断电");
    			break;
    		default:
    			jsonObj.put(key, "上报值错误");
    			break;
    		}
    	}
    }
}
