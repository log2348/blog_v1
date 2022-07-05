package com.example.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration // IoC
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		// file:///C:/springImageDir/tencoblog/upload/51e9788f-6229-4bc9-936c-253714ca68c1_cat.jpg
		System.out.println("file:///" + uploadFolder);
		
		registry.addResourceHandler("/upload/**") // URL 패턴 /upload/ -> 낚아챔
		.addResourceLocations("file:///" + uploadFolder) // 실제 물리적인 경로
		.setCachePeriod(60 * 10 * 6) // 캐시 지속 시간 설정(초)
		.resourceChain(true) // 리소스 찾는 것을 최적화하기 위해서
		.addResolver(new PathResourceResolver());
	}

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
