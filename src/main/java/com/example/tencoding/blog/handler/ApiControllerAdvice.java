package com.example.tencoding.blog.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @ControllerAdvice
@RestControllerAdvice
public class ApiControllerAdvice {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
		System.out.println("ApiControllerAdvice 호출() : ");
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
