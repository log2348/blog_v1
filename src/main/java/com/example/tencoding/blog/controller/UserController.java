package com.example.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tencoding.blog.dto.ResponseDto;
import com.example.tencoding.blog.model.User;
import com.example.tencoding.blog.service.UserService;

// 페이지 리턴
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;

	// .../blog/user/login_form
	@GetMapping("/auth/login_form")
	public String loginForm() {		
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}	
	
	@GetMapping("/logout")
	public String logout() {
		// 세션 정보를 제거 (로그아웃 처리)
		// redirect -> 새롭게 요청
		httpSession.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/user/update_form")
	public String updateForm() {
		return "user/update_form";
	}
	
	// 스프링 부트 기본 데이터 파싱 전략 key=value
	@PostMapping("/auth/joinProc")
	public String save(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}
}
