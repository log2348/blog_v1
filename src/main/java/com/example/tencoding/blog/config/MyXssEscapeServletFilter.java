package com.example.tencoding.blog.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

// 기존 클래스 기능 수정, 추가해서 사용하고 싶을 때 상속받아서 커스텀하기
public class MyXssEscapeServletFilter extends XssEscapeServletFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("데이터 확인해보기 : " + request.getRemoteAddr());
 		System.out.println("데이터 확인해보기 : " + request.getContentType());
		super.doFilter(request, response, chain);
	}
}
