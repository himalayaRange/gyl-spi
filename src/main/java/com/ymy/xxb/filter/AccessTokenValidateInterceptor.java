package com.ymy.xxb.filter;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.ymy.xxb.constant.ErrorEnum;
import com.ymy.xxb.helper.JwtUtil;
import com.ymy.xxb.vo.AccessToken;
import com.ymy.xxb.vo.AjaxUtils;
import com.ymy.xxb.vo.Result;
import io.jsonwebtoken.Claims;
public class AccessTokenValidateInterceptor implements HandlerInterceptor{
	private AccessToken accessToken;
	
	public AccessTokenValidateInterceptor(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isAllowed = false; 
		String token = request.getHeader("Access-User-Token");
		if(token != null){
			Claims claims = JwtUtil.parseJWT(token, accessToken.getPrivateKey());
			if(claims != null) {
				Object tokenType = claims.get("type");
				if(tokenType != null) {
					if(Objects.equals("bearer", (String)tokenType)){
						isAllowed = true;
					}else{
						AjaxUtils.renderJson(response, Result.resultPassport(null, "拒绝请求，错误码：" + ErrorEnum.FAILED_ACCESSTOKEN.getErrCode()));
						isAllowed = false;
					}
				}else{
					AjaxUtils.renderJson(response, Result.resultPassport(null, "拒绝请求，错误码：" + ErrorEnum.FAILED_ACCESSTOKEN.getErrCode()));
					isAllowed = false;
				}
			}else {
				AjaxUtils.renderJson(response, Result.resultPassport(null, "无效accessToken或请求失效，错误码：" + ErrorEnum.FAILED_ACCESSTOKEN.getErrCode()));
				isAllowed = false;
			}
			return isAllowed;
		}else{
			AjaxUtils.renderJson(response, Result.resultPassport(null, "拒绝请求，错误码：" + ErrorEnum.FAILED_ACCESSTOKEN.getErrCode()));
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
