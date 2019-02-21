package com.ymy.xxb.module.e3.controller.ext;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSON;
import com.ymy.xxb.vo.AjaxUtils;
import com.ymy.xxb.vo.Result;

public abstract class BasicController {
	
	public Map<String,Object> bizContentMap;
	
	@SuppressWarnings("unchecked")
	public  Map<String,Object> requestContext(HttpServletRequest request) {
		Map<String,Object> biz = new HashMap<String,Object>();	
		String bizContent = request.getParameter("bizContent");
		if(StringUtils.isNotBlank(bizContent) && !"null".equals(bizContent)) {
			biz = JSON.parseObject(bizContent , Map.class);
		}
		return biz;
	}
	
	/**
	 * 返回通用方法
	 * @param response
	 * @param isTrue：true表示成功；false表示失败
	 * @param message：消息提示
	 * @return 
	 */
	public <T> void doResult(HttpServletResponse response, boolean isTrue, T data,String message) {
		if(isTrue){
			AjaxUtils.renderJson(response, Result.resultSuccess(data, message));
		}
		else{
			AjaxUtils.renderJson(response, Result.resultError(message));
		}
	}
	
	/**
	 * 没有权限登录方法
	 * @param response
	 * @param isTrue
	 * @param data
	 * @param message
	 */
	public <T> void doPassort(HttpServletResponse response , boolean isTrue ,T data ,String message){
		AjaxUtils.renderJson(response, Result.resultPassport(data, message));
	}

}
