package com.company.project.core;


import java.util.Base64;

import com.alibaba.fastjson.JSON;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

//    private static final Base64 base64 = new Base64();
    
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
    	
    	String jsondata = JSON.toJSONString(data);
//    	String encodejsondata = Base64.encodeBase64String(jsondata.getBytes());
    	String encodeData = Base64.getEncoder().encodeToString(jsondata.getBytes());
    	
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
}
