package com.example.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tencoding.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}
