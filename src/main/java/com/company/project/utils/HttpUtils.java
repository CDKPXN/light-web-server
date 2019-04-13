package com.company.project.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.log.LOGEnum;

public class HttpUtils {
	private static final Integer CONNECT_TIMEOUT = 6000;
	
	private static final Integer SOCKET_TIMEOUT = 6000;
	
	private static final String ENCODING = "UTF-8";
	
	private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
	
	public static Result doGet(String url, Map<String, Object> params) throws IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            Set<Entry<String, Object>> entrySet = params.entrySet();
            for (Entry<String, Object> entry : entrySet) {
            	String key = entry.getKey();
            	Object value = entry.getValue();
            	LOG.info("参数{}={}",key,value);
                uriBuilder.setParameter(key, value.toString());
            }
        }

        // 创建http对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        /**
         * setConnectTimeout：设置连接超时时间，单位毫秒。
         * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
         * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
         * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
         */
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpGet.setConfig(requestConfig);
        

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = null;

        try {
            // 执行请求并获得响应结果
            return getHttpClientResult(httpResponse, httpClient, httpGet);
        } finally {
            // 释放资源
			release(httpResponse, httpClient);
        }
	}
	
	/**
	 * 释放资源
	 * @param httpResponse
	 * @param httpClient
	 * @throws IOException 
	 */
	private static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
		// 释放资源
        if (httpResponse != null) {
        	LOG.info("关闭response");
            httpResponse.close();
        }
        if (httpClient != null) {
        	LOG.info("关闭client");
            httpClient.close();
        }
	}

	public static Result doPost(String url, String jsonString) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建http对象
        HttpPost httpPost = new HttpPost(url);
        /**
         * setConnectTimeout：设置连接超时时间，单位毫秒。
         * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
         * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
         * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
         */
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpPost.setConfig(requestConfig);
        
        
        // 封装请求参数
        packageParam(jsonString, httpPost);

        // 创建httpResponse对象
        CloseableHttpResponse httpResponse = null;

        try {
            // 执行请求并获得响应结果
            return getHttpClientResult(httpResponse, httpClient, httpPost);
        } finally {
            // 释放资源
            release(httpResponse, httpClient);
        }
	}

	/**
	 * 封装返回结果
	 * @param httpResponse
	 * @param httpClient
	 * @param httpMethod
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	private static Result getHttpClientResult(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient,
			HttpRequestBase httpMethod) throws ClientProtocolException, IOException {
		// 执行请求
        httpResponse = httpClient.execute(httpMethod);

        // 获取返回结果
        if (httpResponse != null && httpResponse.getStatusLine() != null) {
            String content = "";
            if (httpResponse.getEntity() != null) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
                LOG.info("结果：{}",content);
            }
            return ResultGenerator.genSuccessResult(content);
        }
        LOG.info("没有结果");
		return null;
	}

	/**
	 * 封装参数
	 * @param jsonString
	 * @param httpMethod
	 * @throws UnsupportedEncodingException
	 */
	private static void packageParam(String jsonString, HttpEntityEnclosingRequestBase httpMethod) throws UnsupportedEncodingException {
        // 设置到请求的http对象中
        StringEntity se = new StringEntity(jsonString);
        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");
        httpMethod.setEntity(se);
		
	}
	
	
	
}
