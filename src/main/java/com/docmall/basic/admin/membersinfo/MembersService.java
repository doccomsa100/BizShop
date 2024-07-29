package com.docmall.basic.admin.membersinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.user.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersService {

	private final MembersMapper membersMapper;
	
	// 페이징 & 검색조건이 추가된 리스트
	public List<UserVo> member_list(Criteria cri) {
		return membersMapper.member_list(cri);
	}
		
		// 데이터 총개수
	public int getTotalCount(Criteria cri) {
		return membersMapper.getTotalCount(cri);
	}
	
	// 회원(강제)탈퇴
	public void member_delete(String user_id) {
		membersMapper.member_delete(user_id);
	}
	
	// 메일정보 DB저장하기
	public void mem_mail_save(MembersVO vo) {
		membersMapper.mem_mail_save(vo);
	}
	
	// 메일발송리스트
	public List<MembersVO> mem_mail_list(Criteria cri,String mtitle) {
		return membersMapper.mem_mail_list(cri, mtitle);
	}
		
	// 메일발송리스트 개수
	public int getMailListCount(String mtitle) {
		return membersMapper.getMailListCount(mtitle);
	}

	
}
