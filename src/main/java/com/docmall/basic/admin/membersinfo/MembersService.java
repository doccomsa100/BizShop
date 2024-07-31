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
	
	// 수신등록
	public void save_recipients(ReceiverVO r_vo) {
		membersMapper.save_recipients(r_vo);
	}
	
	// 등록된수신자목록
	public List<ReceiverVO> getReceiverList(String email) {
		return membersMapper.getReceiverList(email);
	}
	
	// 메일발송리스트
	public List<MembersVO> mem_mail_list(Criteria cri,String mtitle) {
		return membersMapper.mem_mail_list(cri, mtitle);
	}
		
	// 메일발송리스트 개수
	public int getMailListCount(Criteria cri,String mtitle) {
		return membersMapper.getMailListCount(cri,mtitle);
	}
	
	// 회원테이블에서 전체회원 메일정보를 읽어오는 작업
	public String[] getALLMemberMail() {
		return membersMapper.getALLMemberMail();
	}
	
	// 메일발송 횟수증가
	public void mailSendCountUpdate(int idx) {
		membersMapper.mailSendCountUpdate(idx);
	}
	
	// 메일목록에서 메일발송
	public MembersVO getMailSendinfo(int idx) {
		return membersMapper.getMailSendinfo(idx);
	}
	
	// 메일수정
	public void mailedit(MembersVO vo) {
		membersMapper.mailedit(vo);
	}
	
	// 메일삭제
	public void maildelete(int idx) {
		membersMapper.maildelete(idx);
	}
	
}
