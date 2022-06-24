package com.example.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tencoding.blog.dto.ResponseDto;
import com.example.tencoding.blog.model.User;
import com.example.tencoding.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	/*
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		// DB (Validation)
		System.out.println("UserApiController 호출됨 !!!");
		user.setRole(RoleType.USER);		
		int result = userService.saveUser(user); // 클라이언트 -> Controller -> Service
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user) {
		// Controller -> Service에게 요청
		System.out.println("login 호출됨");

		//principal - 접근 주체
		User principal = userService.login(user);
		// 접근 주체가 정상적으로 username과 password 확인 ! (세션이라는 거대한 메모리에 저장)
		if (principal != null) {
			httpSession.setAttribute("principal", principal);
			System.out.println("세션 정보가 저장 되었습니다.");
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	*/
	
	// @ResponseBody JSON 방식으로 데이터를 받겠다
	
	@PostMapping("/auth/joinProc")
	// 스프링 부트 기본 데이터 파싱 전략 key=value
	public ResponseDto<Integer> save(User user) {
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}


}
