package com.example.tencoding.blog.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ReplyCountOfBoardDto {

	private int id;
	private String content;
	
	// count 값 들어올 때 mySql은 BigInteger로, mariaDb는 Integer로 들어옴
	private int replyCount;

	/**
	 * 
	 * DB에서 쓰는 데이터 타입과 Java에서 쓰는 데이터 타입이 다르기 때문에
	 * 맞춰주어야 함
	 */
	
	/**
	 * 
	 * JpaResultManager와 동일한 컬럼 수를 맞추고 동일한 데이터 타입을 선언한다면
	 * 알아서 매핑을 진행해주기 때문에 직접 커스텀 할 필요는 없다.
	 */
	
	public ReplyCountOfBoardDto(Object[] objs) {
		// 직접 커스텀 하기
		this.id = ((Integer) objs[0]).intValue();
		this.content = ((String) objs[1]);
		this.replyCount = ((BigInteger) objs[2]).intValue();
	}
	
	public ReplyCountOfBoardDto(Integer id, String content, BigInteger replyCount) {
		this.id = id.intValue();
		this.content = content;
		this.replyCount = replyCount.intValue();
	}
	
}
