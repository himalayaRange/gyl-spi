package com.ymy.xxb.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.jfinal.template.source.ClassPathSourceFactory;
/**
 * @author wangyi
 */
@Configuration
public class EnjoyConfig {
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
				container.addErrorPages(error401Page, error404Page, error500Page);
			}
		};
	}
	
	@Bean(value = "jfinalViewResolver")
	public JFinalViewResolver getJFinalViewResolver(){
		JFinalViewResolver jf = new JFinalViewResolver();
		// Jfinal视图解析适配器
		// JFinalViewResolver jf = new JFinalViewResolverAdapter();
		// 配置放在最前端，开启热加载
		jf.setDevMode(true);
		// 是否开启页面缓存
		jf.setCache(false);
		// 视图解析器加载顺序
		jf.setOrder(0);
		// 页面编码
		jf.setContentType("text/html;charset=UTF-8");
		// springboot将模板放在src/main/resources下，这个目录会被编译到class path下，如果直接放在webapp下，可以注释下面语句
		jf.setSourceFactory(new ClassPathSourceFactory());
		// classpath下views前缀
		jf.setPrefix("/templates/");
    	// 后缀
		jf.setSuffix(".html");
		// 支持#(session.value) 的方式访问
		jf.setSessionInView(true);
		return jf;
	}
	
}
