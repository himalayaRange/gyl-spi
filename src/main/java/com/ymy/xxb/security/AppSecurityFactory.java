package com.ymy.xxb.security;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import com.ymy.xxb.security.coder.SHACoder;

public class AppSecurityFactory {
	/**
	 * 生成AppID
	 * @return
	 */
	public String createAppId() {
		// TODO
		return null;
	}
	
	/**
	 * 生产SecretKey，采用SHA-512加密编码
	 * @param appId
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public String createAppSecretKey(String appId) throws UnsupportedEncodingException, Exception {
		byte[] result = SHACoder.encodeSHA512(appId.getBytes("UTF-8"));
		Encoder encoder = Base64.getEncoder();
		String secretKey = encoder.encodeToString(result);
		return secretKey;
	}
}
