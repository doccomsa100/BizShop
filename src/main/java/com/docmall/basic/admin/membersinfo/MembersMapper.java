package com.docmall.basic.admin.membersinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.user.UserVo;

public interface MembersMapper {

	// 페이징 & 검색조건이 추가된 리스트
	List<UserVo> member_list(Criteria cri);
	
	// 데이터 총개수
	int getTotalCount(Criteria cri);
	
	// 회원(강제)탈퇴
	void member_delete(String user_id);
	
	// 메일정보 DB저장하기
	void mem_mail_save(MembersVO vo);
	
	// 메일발송리스트
	List<MembersVO> mem_mail_list(@Param("cri") Criteria cri,@Param("mtitle") String mtitle);
	
	// 메일발송리스트 개수
	int getMailListCount(String mtitle);
	
	
	
}
