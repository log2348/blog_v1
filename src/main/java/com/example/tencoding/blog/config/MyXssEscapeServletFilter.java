package com.example.tencoding.blog.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

public class MyXssEscapeServletFilter extends XssEscapeServletFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("데이터 확인해보기 : " + request.getRemoteAddr());
 		System.out.println("데이터 확인해보기 : " + request.getContentType());
		super.doFilter(request, response, chain);
	}
}
