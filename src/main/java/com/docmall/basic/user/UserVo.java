package com.docmall.basic.user;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 성능관련
   - 회원테이블(로그인 포함)
   - 회원테이블 + 로그인테이블
 총 컬럼수 : 14
mbsp_id, mbsp_name, mbsp_email, mbsp_password, mbsp_zipcode, mbsp_addr, mbsp_deaddr, 
mbsp_phone, mbsp_nick, mbsp_receive, mbsp_point, mbsp_lastlogin, mbsp_datesub, mbsp_updatedate
*/

@Getter
@Setter
@ToString
public class UserVo {

	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_email;
	private String user_zipcode;
	private String user_addr;
	private String user_deaddr;
	private String user_phone;
	private Date   user_datesub;
	private Date   user_update;
}
