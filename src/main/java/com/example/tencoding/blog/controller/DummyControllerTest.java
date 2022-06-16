package com.example.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tencoding.blog.model.RoleType;
import com.example.tencoding.blog.model.User;
import com.example.tencoding.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	// UserRepository 메모리에 올라가있는 상태
	// 그럼 어떻게 가져오나요? DI
	@Autowired
	private UserRepository userRepository;
	
	// REST POST
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		
		System.out.println("===============================");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println("===============================");
		
		System.out.println(user.getId());
		System.out.println(user.getCreateDate());
		System.out.println(user.getRole()); // null값 들어감 -> default 값 불가

		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입 완료 되었습니다.";
	}
}
