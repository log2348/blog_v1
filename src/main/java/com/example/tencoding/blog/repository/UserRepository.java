package com.example.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tencoding.blog.model.User;

// DAO
// Bean으로 등록될 수 있나요? --> 스프링에서 Ioc에서 객체를 가지고 있나요?
public interface UserRepository extends JpaRepository<User, Integer>{
	

}
