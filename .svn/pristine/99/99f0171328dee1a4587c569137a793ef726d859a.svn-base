package com.ymy.xxb.test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import org.junit.Test;
import com.ymy.xxb.security.coder.SHACoder;

public class SHACoderTest {

	@Test
	public void test() throws UnsupportedEncodingException, Exception {
		byte[] result = SHACoder.encodeSHA512("2019010100007148".getBytes("UTF-8"));
		Encoder encoder = Base64.getEncoder();
		String secretKey = encoder.encodeToString(result);
		System.out.println(secretKey);
	}
}
