package com.ymy.xxb.test.module.e3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.ymy.xxb.helper.JwtUtil;
import com.ymy.xxb.security.RSAUtils;
import com.ymy.xxb.support.OkHttp3Client;
import com.ymy.xxb.vo.AccessToken;

public class AccessTokenHttpClientTest {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Test
	public void accessToken() {
		String httpURL = "http://localhost:11000/spi/e3/completionOrder/push/getAccessToken";
		String appId = "2019010100007148";
		String secretKey = "w3T68hHvytgmu2qrwDdrpIHYvJKJwPMau81nc7wNEEZGnq7BwnbOYNZiH5OBdxHBUs461oGiLPbBijXFTBOP1w==";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCb/Yg7QpGFQYEHIg+CYs0LLh1tntcKOO0OGLukfnTmy9WOaZj0jXKSJN2yLMDgVJUefIWtisjU9g2SEIcglquA+XRfjaXNC3KzNOCl5W1LUaY+tQ8PG4F9uV/5KSOXLxPOJUbxuBGC7cWTO30sG3OJx+swyynkDBYmzys3wo3DwwIDAQAB";
		String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJv9iDtCkYVBgQciD4JizQsuHW2e1wo47Q4Yu6R+dObL1Y5pmPSNcpIk3bIswOBUlR58ha2KyNT2DZIQhyCWq4D5dF+Npc0LcrM04KXlbUtRpj61Dw8bgX25X/kpI5cvE84lRvG4EYLtxZM7fSwbc4nH6zDLKeQMFibPKzfCjcPDAgMBAAECgYBZpbtFU1yOWLbugoSnSmDMrBP9i+vJ1C1ojI5KWuKUQVDaJ5Xf9eqCUu2WGGJAiluoyaeJtNj2ivIBn74I3I5zCOzXDn6j9JwoakImxHMc+Y+iKOkvOtENBxqy9MpEr8bnKaPsZizsXpvzO73GcqKb4mbuMKqTNBR5yC9lQLl+AQJBAOYqFmVRBUmRlBn7lznWoSCVjv9UH09PNIWMZtvWgCL1oYnSgabV4lWHq4dFKR3+3noLdoo3114+EszMzMxDaDsCQQCtgAJIl22Uo18Bz2ir2P5HYdf/mpUScpz+fGS9HbgKFOIb7Flg4lNNlQCOQgB8alkN6L/3Qu8boWQSnn1XFmIZAkASi/esCGBVsk5t3ZEtDveC6apRNtUjDe3ciRjMxDclGgf/VCAwcnG/lKQkzVTn3t7MlNnjf2ZyaKwZJrOmDhR7AkAv6iE3UPKHURLk2hXLF7MpnYDcayFuJc7rjQj5HWL9DcVf9sa8NeTRkSvFHb8qnOgZKkURkR+dntWXOsPcsCbJAkAljcnX56IjtQyLAaAgtgDOOm20UX5R2HdaOMbQxtdlP3lZpcczM5zeltKiZSAIwG9mQY1DGjXcUUHfz88W44Qw";
		String charSet = "UTF-8";
		String format = "JSON";
		String signType = "RSA2";
		String timestamp = sdf.format(new Date());
		String salt = String.valueOf((int)(Math.random()*9+1)*1000);
		String version = "1.0";
		Map<String,String> param = new HashMap<String,String>();
		param.put("userName", "admin");
		param.put("passWord", "8888");
		String bizContent = JSON.toJSONString(param);
		String buildSignOriginal = buildSignOriginal(appId, salt, timestamp, bizContent);
		String sign = signOriginalEncrypt(buildSignOriginal, publicKey);
		// 生成AccessToken,此处测试直接获取，实际开发通过API获取AccessToken
		AccessToken accessToken = new AccessToken();
		accessToken.setTokenType("bearer");
		accessToken.setAudience("RESTAPI@supAdmin");
		accessToken.setIssuser("1610651717@qq.com");
		accessToken.setExpire(1800000);
		accessToken.setPrivateKey("HFGEPWE>FPEMQseLMNQNQJQK#Erow32~3493465fdfLWPWSFHEOPFWEBM<RPE");
		accessToken.setUserId("1");
		accessToken.setUserName("ymy.xxb.devloper");
		accessToken.setRoleId("1");
		accessToken.setRoleName("supAdmin");
		String token = JwtUtil.createJWT(accessToken);
		Map<String,String> params = new HashMap<String,String>();
		params.put("appId", appId);
		params.put("secretKey", secretKey);
		params.put("charSet", charSet);
		params.put("format", format);
		params.put("signType", signType);
		params.put("timestamp", timestamp);
		params.put("salt", salt);
		params.put("version", version);
		params.put("bizContent", bizContent); // bizContent不允许为null,否则会提示授权失败，因为这个参数统一通过JSON.toJSONString获取
		params.put("sign", sign);
		String exec = OkHttp3Client.post(httpURL, params, token);
		System.out.println(exec);
	}
	
	/**
	 * 签名原文组装
	 * @param appId APPID
	 * @param salt 盐值
	 * @param timestamp 时间戳
	 * @param biz 交易内容的JSON串
	 * @return
	 */
	public String buildSignOriginal(String appId , String salt , String timestamp , String biz) {
		StringBuffer signOriginal = new StringBuffer();
		signOriginal = signOriginal.append("appId=").append(appId)
				.append("&salt=").append(salt)
				.append("&timestamp=").append(timestamp)
				.append("&biz=").append(biz);
		
		return signOriginal.toString();
	}
	
	/**
	 * 签名加密
	 * @param original
	 * @param publicKey
	 * @return
	 */
	public String signOriginalEncrypt(String original , String publicKey) {
		try {
			String encryptByPublicKey = RSAUtils.encryptByPublicKey(original, publicKey);
			return encryptByPublicKey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 签名解密
	 * @param original
	 * @param privateKey
	 * @return
	 */
	public String signOriginalDecrypt(String original , String privateKey) {
		try {
			String decryptByPrivateKey = RSAUtils.decryptByPrivateKey(original, privateKey);
			return decryptByPrivateKey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
