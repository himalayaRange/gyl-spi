package com.ymy.xxb.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.ymy.xxb.vo.ext.SecurityConfigMechantBean;
@Component
@PropertySource("classpath:/production/security.properties")
@ConfigurationProperties(prefix = "security")
public class SecurityConfigBean extends SecurityConfigMechantBean implements Serializable{

	private static final long serialVersionUID = -6474077990563871477L;
	// 商户ID
	private String appId;
	// 商户秘钥
	private String secretKey;
	// 加密解密公钥
	private String publicKey;
	// 加密解密私钥
	private String privateKey;
	// 编码
	private String charSet;
	// 支持格式
	private String format;
	// 签名类型
	private String signType;
	// 签名字符串
	private String sign;
	// 时间戳，用于bizContent验签 yyyy-MM-dd HH:mm:ss
	private String timestamp;
	// 盐值，4位的随机数，也进行验签
	private String salt;
	// 版本号
	private String version;
	// 交易内容
	private String bizContent;
	// 初始化当前平台所有的商户信息
	public Map<String, Map<String,String>> getAppIdAndSecretKeyMaps() {
		Map<String,Map<String,String>> appIdAndSecretKeyMaps = new HashMap<String, Map<String, String>>();
		String appIdAndSecretKey = this.getAppIdAndSecretKey();
		if( !StringUtils.isEmpty(appIdAndSecretKey) ) {
			String[] appIdAndSecretKeyArray = appIdAndSecretKey.split(",");
			for(int i = 0 ; i < appIdAndSecretKeyArray.length ; i ++ ) {
				String  appIdAndSecretKeyArrayItem = appIdAndSecretKeyArray[i];
				String[] appIdAndSecretKeyArrayItemArray = appIdAndSecretKeyArrayItem.split("@");
				if(appIdAndSecretKeyArrayItemArray.length == 4) {
					Map<String,String> secrityDetail = new HashMap<String,String>();
					secrityDetail.put("secretKey", appIdAndSecretKeyArrayItemArray[1]);
					secrityDetail.put("publicKey", appIdAndSecretKeyArrayItemArray[2]);
					secrityDetail.put("privateKey", appIdAndSecretKeyArrayItemArray[3]);
					appIdAndSecretKeyMaps.put(appIdAndSecretKeyArrayItemArray[0], secrityDetail);
				}
			}
		}
		return appIdAndSecretKeyMaps;
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBizContent() {
		return bizContent;
	}
	public void setBizContent(String bizContent) {
		this.bizContent = bizContent;
	}

	@Override
	public String toString() {
		return "SecurityConfigBean [appId=" + appId + ", secretKey=" + secretKey + ", publicKey=" + publicKey
				+ ", privateKey=" + privateKey + ", charSet=" + charSet + ", format=" + format + ", signType="
				+ signType + ", sign=" + sign + ", timestamp=" + timestamp + ", salt=" + salt + ", version=" + version
				+ ", bizContent=" + bizContent + "]";
	}
	
	
}
