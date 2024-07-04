package com.docmall.basic.admin.membersinfo;

import java.util.List;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.user.UserVo;

public interface MembersMapper {

	// 페이징 & 검색조건이 추가된 리스트
	List<UserVo> member_list(Criteria cri);
	
	// 데이터 총개수
	int getTotalCount(Criteria cri);
	
	// 회원(강제)탈퇴
	void member_delete(String user_id);
	
	
	
}
