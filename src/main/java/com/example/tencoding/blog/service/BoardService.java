package com.example.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tencoding.blog.model.Board;
import com.example.tencoding.blog.model.User;
import com.example.tencoding.blog.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void write(Board board, User user) { // title, content
		board.setCount(0);
		board.setUserId(user);
		boardRepository.save(board);
	}
	
	@Transactional
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public Board boardDetail(int boardId) {
		return boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글은 찾을 수 없습니다.");
		});
	}
	
	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}

}
