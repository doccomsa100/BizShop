package com.docmall.basic.kakaoLogin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/oauth2")
public class KakaoLoginController {
	
	@Value("${kakao.client.id}")
	private String clientId;
	
	@Value("${kakao.redirect.uri}")
	private String redirectUri;
	
	@Value("${kakao.client.secret}")
	private String clientsecret;
	

	// Step1. 카카오 로그인 API Server 에게 인가코드를 요청하는 작업
	// 헤더는 없고, 요청파라미터가 있는 경우
	@GetMapping("/kakaologin")
	public String connect() {
		
		// API작업할떄 사용하기 좋은 클래스임
		// 헤더가 없으면 문자열로 만들면 됨.
		// StringBuffer: 문자열을 연결할때 사용하는 클래스
		StringBuffer url = new StringBuffer();
		url.append("https://kauth.kakao.com/oauth/authorize");
		url.append("response_type=code");
		url.append("&client_id=" + clientId);
		url.append("&redirect_uri=" + redirectUri);
		
		log.info("인가코드: " + url.toString());
		
		return "redirect:" + url.toString();
	}
}
