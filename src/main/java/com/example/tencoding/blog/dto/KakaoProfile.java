package com.example.tencoding.blog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoProfile {
	
	private long id;
	private String connectedAt;
	private Property properties;
	private KakaoAccount kakaoAccount;
}
