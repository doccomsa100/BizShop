package com.docmall.basic.qnaboard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.basic.common.dto.Criteria;

public interface QnaBoardMapper {
	
	// QnA리스트
	List<QnaBoardVO> qnalist(@Param("pro_num") Integer pro_num, @Param("cri") Criteria cri);

	// QnA리스트 총개수
	int getCountQnaBypro_num(Integer pro_num);

}
