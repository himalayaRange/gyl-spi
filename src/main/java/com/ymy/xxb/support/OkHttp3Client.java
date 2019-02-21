package com.ymy.xxb.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import okhttp3.OkHttpClient;

public class OkHttp3Client {

	public static String post(String url, Map<String, String> params) {
		okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
		if (params == null) {
			params = new HashMap<String, String>();
		}
		if (params != null) {
			Set<Map.Entry<String, String>> entries = params.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				builder.add(entry.getKey(), entry.getValue());
			}
		}
		okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(builder.build()).build();
		return exec(request);
	}

	public static String post(String url, Map<String, String> params, String accessToken) {
		okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
		if (params == null) {
			params = new HashMap<String, String>();
		}
		if (params != null) {
			Set<Map.Entry<String, String>> entries = params.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				if(entry.getKey() == null || entry.getValue() == null) {
					continue;
				}
				builder.add(entry.getKey(), entry.getValue());
			}
		}
		if (StringUtils.isEmpty(accessToken)) {
			okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(builder.build()).build();
			return exec(request);
		} else {
			okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(builder.build()).addHeader("Access-User-Token", accessToken).build();
			return exec(request);
		}
	}

	private static String exec(okhttp3.Request request) {
		try {
			 OkHttpClient okHttpClient = new OkHttpClient.Builder()
				        .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
				        .readTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
				        .writeTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
				        .build();
			okhttp3.Response response = okHttpClient.newCall(request).execute();

			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);

			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
