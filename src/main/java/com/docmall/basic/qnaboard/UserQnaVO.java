package com.docmall.basic.qnaboard;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserQnaVO {

	
	// qna_idx, pro_num, user_id,que_title ,Que_Content,pro_up_folder, pro_img, question_date

	private Long qna_idx;
	private int pro_num;
	private String user_id;
	private String que_title;
	private String que_content;
	private String pro_up_folder;
	private String pro_img;
	private Date   question_date;

}
