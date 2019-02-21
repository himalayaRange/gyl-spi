package com.ymy.xxb.module.e3.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.ymy.xxb.module.e3.bean.ext.HttpUrlBean;
@Component
@PropertySource("classpath:/production/e3.properties")
@ConfigurationProperties(prefix = "e3")
public class E3ConfigurationBean extends HttpUrlBean{

	private static final long serialVersionUID = 5957824558771885695L;

	private String ak;
	
	private String pk;
	
	private String privateKey;
	
	private String username;
	
	private String password;

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "E3ConfigurationBean [ak=" + ak + ", pk=" + pk + ", privateKey=" + privateKey + ", username=" + username
				+ ", password=" + password + "]";
	}
	
}
