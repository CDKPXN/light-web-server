package com.company.project.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(TokenUtils.class);
	
	// 密钥
		public static final String SECRET = "companiontek.com";
		
		/**
		 * 获取TOKEN
		 * @param username
		 * @param userpass
		 * @return
		 */
		public static String getToken (String uid) {
			Date iatDate = new Date();
			
			// 过期时间
//			Calendar nowDate = Calendar.getInstance();
//			nowDate.add(Calendar.HOUR, 2);
//			Date expireTime = nowDate.getTime();
			
			// header
			Map<String, Object> headerMap = new HashMap<>();
			headerMap.put("alg", "HS256");
			headerMap.put("typ", "JWT");
			
			Builder tokenBuilder = JWT.create().withHeader(headerMap);
			tokenBuilder.withIssuer("Service");
			tokenBuilder.withExpiresAt(null);
			tokenBuilder.withIssuedAt(iatDate);
			tokenBuilder.withClaim("uid", uid);
			
			String token = tokenBuilder.sign(Algorithm.HMAC256(SECRET));
			
			return token;
		}
		
		/**
		 * 解密TOKEN--解析失败返回null
		 * @param token
		 * @return
		 */
		public static Map<String, Claim> verifyToken(String token) {
			DecodedJWT jwt = null;
			try {
				JWTVerifier verify = JWT.require(Algorithm.HMAC256(SECRET)).build();
				jwt = verify.verify(token);
			} catch (Exception e) {
				LOG.error("解析TOKEN失败");
				return null; // 
			}
			
			if (jwt == null) {
				LOG.error("解析Token失败");
				return null;
			}
			
			return jwt.getClaims();
		}
		
		/**
		 * 从TOKEN中获取数据
		 * @param token
		 * @param key
		 * @return
		 */
		public static String getInfo(Map<String, Claim> claims, String key) {
			Claim claim = claims.get(key);
			if (claim == null || StringUtils.isEmpty(claim.asString())) {
				LOG.error("Token 解析失败...");
				return null;
			}
			
			String info = claim.asString();
			return info;
		}
		
}
