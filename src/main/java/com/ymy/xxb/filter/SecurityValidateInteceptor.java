package com.ymy.xxb.filter;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Objects;
import com.ymy.xxb.constant.ErrorEnum;
import com.ymy.xxb.security.RSAUtils;
import com.ymy.xxb.vo.AjaxUtils;
import com.ymy.xxb.vo.Result;
import com.ymy.xxb.vo.SecurityConfigBean;
public class SecurityValidateInteceptor implements HandlerInterceptor{
	private SecurityConfigBean securityConfigBean;

	public SecurityValidateInteceptor(SecurityConfigBean securityConfigBean) {
		this.securityConfigBean = securityConfigBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean passport = false;
		// 公共参数校验.
		passport = validateCommonsRequestParamter(request, response);
		if(passport) {
			// 商户信息校验.
			passport = validateMerchantInformation(request, response);
			if(passport) {
				// 签名信息校验.
				passport = validateBizContentSign(request, response);
			}
		}
		return passport;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	/**
	 * 公共参数校验
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean validateCommonsRequestParamter(HttpServletRequest request , HttpServletResponse response) {
		boolean passport = false;
		String charSet = request.getParameter("charSet");
		String format = request.getParameter("format");
		String signType = request.getParameter("signType");
		String version = request.getParameter("version");
		if(StringUtils.isEmpty(charSet)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_CHARSET.getErrCode()));
			passport = false;
		}
		if(StringUtils.isEmpty(format)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_FORMAT.getErrCode()));
			passport = false;
		}
		if(StringUtils.isEmpty(signType)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_SIGNTYPE.getErrCode()));
			passport = false;
		}
		if(StringUtils.isEmpty(version)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_VERSION.getErrCode()));
			passport = false;
		}
		if( ! Objects.equal(securityConfigBean.getCharSet(), charSet.toUpperCase())) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_CHARSET.getErrCode()));
			passport = false;
		} else if(! Objects.equal(securityConfigBean.getFormat(), format.toUpperCase())) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_FORMAT.getErrCode()));
			passport = false;
		} else if(! Objects.equal(securityConfigBean.getSignType(), signType.toUpperCase())) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_SIGNTYPE.getErrCode()));
			passport = false;
		} else if(! Objects.equal(securityConfigBean.getVersion(), version)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "公共参数校验授权失败，错误码：" + ErrorEnum.FAILED_VERSION.getErrCode()));
			passport = false;
		} else {
			passport = true;
		}
		
		return passport;
	}
	
	/**
	 * 商户授权信息校验
	 * <p>
	 * 商户信息从平台数据库中获取，当前商户数量少，暂配置在security.properties中
	 * </p>
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean validateMerchantInformation(HttpServletRequest request , HttpServletResponse response) {
		boolean passport = false;
		String appId = request.getParameter("appId");
		String secretKey = request.getParameter("secretKey");
		if(StringUtils.isEmpty(appId)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "商户授权失败，错误码：" + ErrorEnum.FAILED_MERCH_APPID.getErrCode()));
			passport = false;
		}
		if(StringUtils.isEmpty(secretKey)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "商户授权失败，错误码：" + ErrorEnum.FAILED_MARCH_SECRETKEY.getErrCode()));
			passport = false;
		}
		Map<String, Map<String,String>> appIdAndSecretKeyMaps = securityConfigBean.getAppIdAndSecretKeyMaps();
		if( ! appIdAndSecretKeyMaps.containsKey(appId) ) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "商户授权失败，错误码：" + ErrorEnum.FAILED_MERCH_APPID.getErrCode()));
			passport = false;
		} else {
			Map<String, String> secrityMap = appIdAndSecretKeyMaps.get(appId);
			String secretKeyResource = secrityMap.get("secretKey");
			if( !Objects.equal(secretKey, secretKeyResource) ) {
				AjaxUtils.renderJson(response, Result.resultPassport(null, "商户授权失败，错误码：" + ErrorEnum.FAILED_MARCH_SECRETKEY.getErrCode()));
				passport = false;
			} else {
				passport = true;
			}
		}
		return passport;
	}
	
	/**
	 * 核心交易内容签名校验，防止交易数据被恶意篡改
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean validateBizContentSign(HttpServletRequest request , HttpServletResponse response) {
		boolean passport = false;
		Map<String, Map<String, String>> appIdAndSecretKeyMaps = securityConfigBean.getAppIdAndSecretKeyMaps();
		String appId = request.getParameter("appId");
//		String publicKey = appIdAndSecretKeyMaps.get(appId).get("publicKey");
		String privateKey = appIdAndSecretKeyMaps.get(appId).get("privateKey");
		String timestamp =request.getParameter("timestamp");
		String salt = request.getParameter("salt");
		String bizContent = request.getParameter("bizContent");
		String sign = request.getParameter("sign");
		if(StringUtils.isEmpty(timestamp)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "交易授权失败，错误码：" + ErrorEnum.FAILED_TIMESTAMP.getErrCode()));
			passport = false;
		}
		if(!(StringUtils.isNotEmpty(salt) && salt.length() == 4)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "交易授权失败，错误码：" + ErrorEnum.FAILED_SALT.getErrCode()));
			passport = false;
		}
		if(StringUtils.isEmpty(bizContent)) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "交易授权失败，错误码：" + ErrorEnum.FAILED_BIZCONTENT.getErrCode()));
			passport = false;
		}
		// 构建签名原文
		String buildSignOriginal = buildSignOriginal(appId, salt, timestamp, bizContent);
		// 公钥加密
		//String rightSign = signOriginalEncrypt(buildSignOriginal, publicKey);
		// 私钥解密
		String result = signOriginalDecrypt(sign, privateKey);
		if(result == null) {
			AjaxUtils.renderJson(response, Result.resultPassport(null, "交易授权失败，错误码：" + ErrorEnum.FAILED_SIGN.getErrCode()));
			passport = false;
		}else {
			if( ! Objects.equal(result, buildSignOriginal)) {
				AjaxUtils.renderJson(response, Result.resultPassport(null, "交易授权失败，错误码：" + ErrorEnum.FAILED_SIGN.getErrCode()));
				passport = false;
			} else {
				passport = true;
			}
		}
		return passport;
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
