package com.example.tencoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // User 클래스가 자동으로 MySQL에 테이블을 생성한다
public class User {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라가겠다
	private int id;
	@Column(nullable = false, length = 30)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 50)
	private String email;
	@CreationTimestamp // 시간이 자동으로 입력된다
	private Timestamp createDate;

	// Domain - 데이터의 범주화 (User, user 등 잘못입력 방지)
	@ColumnDefault("'user'")
	private String role; // Enum 타입 사용 권장 : admin, user, manager
}
