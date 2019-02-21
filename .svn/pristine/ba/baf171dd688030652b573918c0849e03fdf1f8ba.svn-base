package com.ymy.xxb.test;

import java.util.Map;
import org.junit.Test;
import com.ymy.xxb.security.RSAUtils;

public class RSA2Test {

	@Test
	public void test() throws Exception {
		Map<String, String> keys = RSAUtils.getKeys();
		String publicKey = keys.get("publicKey");
		String privateKey = keys.get("privateKey");
		String content = "我是Javen SPI";
		String encrypt = RSAUtils.encryptByPublicKey(content, publicKey);
		String decrypt = RSAUtils.decryptByPrivateKey(encrypt, privateKey);
		System.out.println("加密之后：" + encrypt);
		System.out.println("解密之后：" + decrypt);

		System.out.println("==================");

		content = "我是Javen SPI";
		encrypt = RSAUtils.encryptByPublicKeyByWx(content, publicKey);
		decrypt = RSAUtils.decryptByPrivateKeyByWx(encrypt, privateKey);
		System.out.println("加密之后：" + encrypt);
		System.out.println("解密之后：" + decrypt);
	}
}
