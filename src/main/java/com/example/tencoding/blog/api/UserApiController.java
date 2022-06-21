package com.example.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tencoding.blog.dto.ResponseDto;
import com.example.tencoding.blog.model.RoleType;
import com.example.tencoding.blog.model.User;
import com.example.tencoding.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		// DB (Validation)
		System.out.println("UserApiController 호출됨 !!!");
		user.setRole(RoleType.USER);		
		int result = userService.saveUser(user); // 클라이언트 -> Controller -> Service
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}

}
