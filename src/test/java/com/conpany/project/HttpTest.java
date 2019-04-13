package com.conpany.project;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.company.project.core.Result;
import com.company.project.utils.HttpUtils;

public class HttpTest {
	public static void main(String[] args) throws IOException, URISyntaxException {
		testPost();
	}
	
	public static void testGet() throws IOException, URISyntaxException {
		String url = "http://127.0.0.1:9091/api/v1/orders/1";
		Map<String, Object> params = new HashMap<>();
		Result doGet = HttpUtils.doGet(url, params);
		System.out.println(doGet);
	}
	
	public static void testPost () throws IOException {
		String url = "http://127.0.0.1:9091/api/v1/costomer/signup";
		Map<String, String> params = new HashMap<>();
		params.put("openid", "askldfjlasslafdkajfoiasf");
		params.put("shopid", "2");
		
//		Result doPost = HttpUtils.doPost(url, params);
//		System.out.println(doPost);
	}
}
