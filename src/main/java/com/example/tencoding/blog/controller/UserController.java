package com.example.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.tencoding.blog.dto.KakaoProfile;
import com.example.tencoding.blog.dto.OAuthToken;
import com.example.tencoding.blog.model.User;
import com.example.tencoding.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 페이지 리턴
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;

	// .../blog/user/login_form
	@GetMapping("/auth/login_form")
	public String loginForm() {		
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}	
	
	@GetMapping("/logout")
	public String logout() {
		// 세션 정보를 제거 (로그아웃 처리)
		// redirect -> 새롭게 요청
		httpSession.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/user/update_form")
	public String updateForm() {
		return "user/update_form";
	}
	
	// 스프링 부트 기본 데이터 파싱 전략 key=value
	@PostMapping("/auth/joinProc")
	public String save(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}
	
	@GetMapping("/auth/kakao/callback")
	@ResponseBody
	public String kakaoCallback(@RequestParam String code) {
		// POST -->
		// 통신 -- 인증서버
		// Retrofit2
		// OkHttp
		// HttpsURLConnect
		// RestTemplete
		RestTemplate rt = new RestTemplate();
		
		// http 메시지 -> POST
		// 시작줄
		// http header
		// http body

		// 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		// 값 넣기
		params.add("grant_type", "authorization_code");
		params.add("client_id", "ffbe3230182358295ada174604373316");
		params.add("redirect_uri", "http://localhost:8081/auth/kakao/callback");
		params.add("code", code);
		
		// 헤더와 바디를 하나의 오브젝트로 담아야 한다.
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers); // body, 헤더
		
		// Http 요청 - Post 방식 - 응답
		ResponseEntity<String> response =
				rt.exchange("https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		// response -> object 타입으로 변환 (Gson, Json Simple ..)
		// 파싱처리
		OAuthToken authToken = null;
		ObjectMapper objectMapper = new ObjectMapper();
		// String --> Object (클래스 생성)
		try {
			authToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(authToken);
		
		// 엑세스 토큰 사용
		
		RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		// 주의 Bearer 다음에 무조건 한 칸 띄우기
		headers2.add("Authorization", "Bearer " + authToken.getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디 없이 헤더만 넣기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);
				
		ResponseEntity<String> response2 =
				rt2.exchange("https://kapi.kakao.com/v2/user/me",
						HttpMethod.POST,
						kakaoProfileRequest,
						String.class);
		
		System.out.println(response2);
		
		// JSON --> Object 파싱하기 (KakaoProfile.class)
		KakaoProfile kakaoProfile = null;
		ObjectMapper objectMapper2 = new ObjectMapper();
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(kakaoProfile);

		return "카카오 프로필 정보 요청: " + response2;
	}
}
