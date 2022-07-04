package com.example.tencoding.blog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration // IoC
public class WebConfig {

	/**
	 * 
	 * 메서드 수행시 힙 메모리에 올라가야 할 때 @Bean
	 * 미리 IoC에 떠있는 거 DI 할 때는 @Autowired
	 */

	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		// 필터를 추가하는 것
		// 레거시 버전 web.xml 파일에서 설정하던 것 자바코드로 설정한 것
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new XssEscapeServletFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*"); // 모든 url에 적용

		return filterRegistrationBean;
	}

}
