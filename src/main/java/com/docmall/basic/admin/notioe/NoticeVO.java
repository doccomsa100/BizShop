package com.docmall.basic.admin.notioe;

import java.util.Date;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ToString
public class NoticeVO {

	// no_idx, no_title, no_content, no_writer, no_readcount,no_class ,no_regdate
	private Integer idx;
	private String title;
	private String content;
	private String writer;   // 관리자 ID
	private int    readcount;
	private String noticlass;
	private Date   regdate;
	
	// 리스트 조회시만 필요
	private String adminName; // 추가된 관리자 이름 필드
}
