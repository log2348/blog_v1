package com.example.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tencoding.blog.model.User;
import com.example.tencoding.blog.repository.UserRepository;

@Service // IoC 등록
public class UserService {
	
	/**
	 * 
	 * 서비스가 필요한 이유
	 * 
	 * 현실 모델링 : 레스토랑 주문 -> 서버(추가적인 요청) -> 주방장 음식 조리
	 * 
	 * 트랜잭션 관리
	 * 송금 : 홍길동, 이순신
	 * 홍길동(10), 이순신(0) --> 홍길동 (select) --> 이순신(5) (insert)
	 * 여러 가지 트랜잭션들이 모여 하나의 서비스를 만든다.
	 * 
	 * 하나의 기능 + 하나의 기능을 묶어서 단위의 기능을 만들어내기 위해 필요하다
	 * 하나의 기능 (서비스가 될 수 있다.)
	 * 
	 */

	// DI 의존 주입
	@Autowired // 초기화 처리까지 되는 것
	private UserRepository userRepository;
	
	@Transactional
	public int saveUser(User user) {
		// insert
		// select
		// update
		// delete
		// 이런 것들을 묶는 하나의 기능을 만들 때
		try {
			userRepository.save(user); // Service -> Repository
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	@Transactional(readOnly = true)
	public User login(User user) {
		// repository select 요청
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
