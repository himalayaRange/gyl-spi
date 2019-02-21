package com.ymy.xxb.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.alibaba.fastjson.JSONObject;
import com.ymy.xxb.helper.JwtUtil;
import com.ymy.xxb.vo.AccessToken;
import io.jsonwebtoken.Claims;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringBootJwtTest {
	@Autowired
	private AccessToken accessToken;
	
	@Test
	public void jwt() {
		accessToken.setUserId("1");
		accessToken.setUserName("ymy.xxb.devloper");
		accessToken.setRoleId("1");
		accessToken.setRoleName("supAdmin");
		Map<String,Object> attributes = new ConcurrentHashMap<String,Object>();
		attributes.put("resource", "https://baidu.com");
		attributes.put("auth", "xxxx");
		accessToken.setAttributes(attributes);
		String token = JwtUtil.createJWT(accessToken);
		System.out.println(token);
		
		Claims claims = JwtUtil.parseJWT(token, accessToken.getPrivateKey()); 
		System.out.println(claims);
		System.out.println("JWT 类型 ：" + claims.get("type"));
		System.out.println(claims.getExpiration());
		System.out.println(claims.getSubject());
		JSONObject subject = JSONObject.parseObject(claims.getSubject());
		System.out.println("userName:" + subject.getString("userName") + "\nuserId:" + subject.getString("userId"));
	}
}
