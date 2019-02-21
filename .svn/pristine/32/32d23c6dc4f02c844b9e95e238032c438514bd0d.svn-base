package com.ymy.xxb.helper;

import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.ymy.xxb.vo.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	/**
	 * 不要将隐私信息放入，base64可以对称解密，相当于明文
	 * @param username
	 * @param userId
	 * @param role
	 * @param audience
	 * @param issuer
	 * @param TTLMillis
	 * @param base64Security
	 * @return
	 */
	public static String createJWT(AccessToken accessToken) { 
		try {
			SignatureAlgorithm signatureAlgorithm  = SignatureAlgorithm.HS256;
			long nowMillis  = System.currentTimeMillis();
			Date now = new Date(nowMillis);
			// 生成签名秘钥 ，一个base64加密后的字符串
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(accessToken.getPrivateKey());
			SecretKeySpec siginKey  = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userId", accessToken.getUserId());
			jsonObject.put("userName", accessToken.getUserName());
			jsonObject.put("roleId", accessToken.getRoleId());
			jsonObject.put("roleName", accessToken.getRoleName());
			jsonObject.put("attributes", accessToken.getAttributes());
			//-----------构建 JWT-----------------
			JwtBuilder builder = Jwts.builder()
			// Header
			.setHeaderParam("typ", "JWT")
			.claim("type", accessToken.getTokenType())
			// 创建时间
			.setIssuedAt(now)
			// 载体，用户的基本信息
			.setSubject(jsonObject.toString())
			// 发送给谁
			.setIssuer(accessToken.getIssuser())
			// 个人签名
			.setAudience(accessToken.getAudience())
			// 第三段秘钥签名
			.signWith(signatureAlgorithm, siginKey);
			// Token过期时间
			if (accessToken.getExpire() >= 0) {
				long expMillis  = nowMillis + accessToken.getExpire() ; 
				Date exp = new Date(expMillis);
				// 系统之前的时间都是不可被承认的
				builder.setExpiration(exp).setNotBefore(now);
			}
			
			// 生成JWT
			return builder.compact();
		} catch (Exception e) {
			logger.error("create jwt exception : " , e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * jwt方式解析token获取Claims
	 * @param jsonWebToken
	 * @param base64Security
	 * @return
	 */
	public static Claims parseJWT(String jsonWebToken , String base64Security) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
			.parseClaimsJws(jsonWebToken).getBody();
			return claims;
		} catch (SignatureException ex) {
			logger.error("秘钥不正确，伪造的JWT串 ，error : " , ex.getMessage());
			return null;
		} catch (ExpiredJwtException  ex) {
			logger.error("JWT串已经过期，本次请求失效 ，error " , ex.getMessage());
			return null;
		} catch (Exception  ex) {
			logger.error("解析token异常 ，error " , ex.getMessage());
			return null;
		} 
	}
	
}
