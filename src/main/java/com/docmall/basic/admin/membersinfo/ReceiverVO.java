package com.docmall.basic.admin.membersinfo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReceiverVO {

	private Long    email_id;
	private String email;
	private Date   created_at;
	
}
