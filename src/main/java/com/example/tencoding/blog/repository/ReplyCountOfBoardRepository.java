package com.example.tencoding.blog.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.example.tencoding.blog.dto.ReplyCountOfBoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 컴포넌트 스캔시 메모리 올려줌
@Repository
public class ReplyCountOfBoardRepository {
	
	private final EntityManager em;
	
	public List<ReplyCountOfBoardDto> getReplyCount() {
		
		List<ReplyCountOfBoardDto> list = new ArrayList<ReplyCountOfBoardDto>();
		
		/**
		 * 직접 쿼리문 만들기
		 * 반드시 끝에 한 칸 띄워주어야 함
		 */
		String queryStr = "SELECT A.id, A.content, (SELECT COUNT(boardId) "
				+ "			FROM reply AS B "
				+ "            WHERE B.boardId = A.id) as replyCount "
				+ "FROM board AS A ";
		
		Query nativeQuery = em.createNativeQuery(queryStr);
		
		/**
		 * 두 가지 방식
		 * 
		 * 1. 직접 문자열을 컨트롤러에서 object 맵핑하는 방식
		 * 2. QLRM 라이브러리를 사용해서 object 맵핑하는 방식
		 */
		
		/*
		 * 1. 직접 매핑하는 방식
			List<Object[]> resultList = nativeQuery.getResultList();
			System.out.println(resultList.toString());
			resultList.forEach(t -> {
				System.out.println(t.toString());
			});
		*/
		
		// 2. QLRM 라이브러리 사용
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, ReplyCountOfBoardDto.class);
		
		return list;
	}
}
