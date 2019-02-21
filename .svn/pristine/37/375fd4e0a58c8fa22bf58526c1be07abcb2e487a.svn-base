package com.ymy.xxb.adapter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.jfinal.template.ext.spring.JFinalViewResolver;

public class JFinalViewResolverAdapter extends JFinalViewResolver implements ApplicationListener<ContextRefreshedEvent>{

	/**
	 * 添加默认路径
	 * @param contextRefreshedEvent
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		getEngine().addSharedObject("ctx", getServletContext().getContextPath());
	}

}
