package com.ymy.xxb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.google.common.base.Objects;
@Component
@PropertySource("classpath:/production/filter.properties")
@ConfigurationProperties(prefix = "filter")
public class AbstractFilterBean implements Serializable{

	private static final long serialVersionUID = -6943471715276892933L;
	
	private String chartSetFilterIgnoreUrl;
	
	private String sqlInjectFilterIgnoreUrl;
	
	private String accessTokenFilterIgnoreUrl;
	
	private String secrityValidateFilterIgnoreUrl;

	public String getChartSetFilterIgnoreUrl() {
		return chartSetFilterIgnoreUrl;
	}

	public void setChartSetFilterIgnoreUrl(String chartSetFilterIgnoreUrl) {
		this.chartSetFilterIgnoreUrl = chartSetFilterIgnoreUrl;
	}
	
	public String getSqlInjectFilterIgnoreUrl() {
		return sqlInjectFilterIgnoreUrl;
	}

	public void setSqlInjectFilterIgnoreUrl(String sqlInjectFilterIgnoreUrl) {
		this.sqlInjectFilterIgnoreUrl = sqlInjectFilterIgnoreUrl;
	}

	public String getAccessTokenFilterIgnoreUrl() {
		return accessTokenFilterIgnoreUrl;
	}

	public void setAccessTokenFilterIgnoreUrl(String accessTokenFilterIgnoreUrl) {
		this.accessTokenFilterIgnoreUrl = accessTokenFilterIgnoreUrl;
	}
	
	public String getSecrityValidateFilterIgnoreUrl() {
		return secrityValidateFilterIgnoreUrl;
	}

	public void setSecrityValidateFilterIgnoreUrl(String secrityValidateFilterIgnoreUrl) {
		this.secrityValidateFilterIgnoreUrl = secrityValidateFilterIgnoreUrl;
	}

	public static List<String> stringToLists(String url) {
		List<String> list = new ArrayList<String>();
		if (url != null && !Objects.equal("", url) ) {
			String[] urls = url.split(",");
			list = Arrays.asList(urls);
		} 
		return list;
	}
}
