package com.example.tencoding.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WebMvcConfigurer {
	
	// @RequiredArgsConstructor - final 선언시 초기화 처리해줌
	private final ObjectMapper objectMapper;
}
