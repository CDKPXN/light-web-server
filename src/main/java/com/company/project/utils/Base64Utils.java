package com.company.project.utils;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Utils {
	
	private static final Logger LOG = LoggerFactory.getLogger(Base64Utils.class);
	
	public static String decode(String encodeString) {
		if (encodeString == null) {
			return null;
		}
		
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(encodeString);
		
		String decodeString = null;
		try {
			decodeString = new String(decode,"UTF-8");
		} catch (Exception e) {
			LOG.error("解码后生成字符串发生异常");
		}
		
		return decodeString;
	}
}
