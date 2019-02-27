package com.conpany.project;

import com.alibaba.fastjson.JSON;
import com.company.project.model.Light;

public class JsonTest {
	public static void main(String[] args) {
		Light light = new Light();
		
		light.setAttrNum("123");
		light.setHeartfrequency(30);
		
		System.out.println(light);
		
		Object json = JSON.toJSON(light);
		System.out.println(json);
	}
}
