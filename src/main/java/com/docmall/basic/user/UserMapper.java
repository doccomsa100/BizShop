package com.docmall.basic.user;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

	// 회원가입
	void join(UserVo vo);
	
	// 아이디 중복체크
	String idCheck(String user_id);
	
	// 로그인
	UserVo login(String user_id);
	
	// 아이디찾기
	String idfind(@Param("user_name") String user_name, @Param("user_email") String user_email);
	
	// 임시비밀번호
	String pwfind(@Param("user_id")String user_id,@Param("user_name") String user_name,@Param("user_email") String user_email);
	
	// 임시비밀번호업데이트
	void tempPwUpdate(@Param("user_id")String user_id,@Param("temp_enc_pw") String temp_enc_pw);

	// 회원탈퇴
	void delete(String user_id);
	
	// 내정보수정
	void modify(UserVo vo);
	
	// 비밀번호 변경
	void changePw(@Param("user_id") String user_id,@Param("new_user_pwd") String new_user_pwd);
	



}
