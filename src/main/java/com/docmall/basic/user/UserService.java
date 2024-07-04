package com.docmall.basic.user;

import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// 구현클래스 : 인터페이스를 상속받는 클래스
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserMapper userMapper;

	public void join(UserVo vo) {
		userMapper.join(vo);
		
	}

	public String idCheck(String user_id) {
		// TODO Auto-generated method stub
		return userMapper.idCheck(user_id);
	}
	
	// 로그인
	public UserVo login(String user_id) {
		return userMapper.login(user_id);
	}
	
	// 아이디찾기
	public String idfind(String user_name,String user_email) {
		return userMapper.idfind(user_name,user_email);
	}
	
	// 임시비밀번호
	public String pwfind(String user_id,String user_name,String user_email) {
		return userMapper.pwfind(user_id, user_name,user_email);
	}
	
	// 임시비밀번호업데이트
	public void tempPwUpdate(String user_id,String temp_enc_pw) {
		userMapper.tempPwUpdate(user_id, temp_enc_pw);
	}
	
	// 회원탈퇴
	public void delete(String user_id) {
		userMapper.delete(user_id);
	}
	
	// 내정보수정
	public void modify(UserVo vo) {
		userMapper.modify(vo);
	}
	
	// 비밀번호 변경
		void changePw(String user_id,String new_user_pwd) {
			userMapper.changePw(user_id, new_user_pwd);
		}
	
	
	
	// 임시비밀번호 발급
	// 임시 비밀번호 생성(UUID 이용)
	// UUID를 이용하여 임시 비밀번호를 생성하고, 하이픈(-)을 제거하고, 10자리까지 비밀번호 생성
	public String getTempPw() {
		
	return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
	}
}	
