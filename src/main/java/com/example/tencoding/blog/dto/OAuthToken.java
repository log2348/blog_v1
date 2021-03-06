package com.example.tencoding.blog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthToken {
	
	private String accessToken;
	private String token_type;
	private String refreshToken;
	private int expiresIn;
	private String scope;
	private String refreshTokenExpiresIn;
}
