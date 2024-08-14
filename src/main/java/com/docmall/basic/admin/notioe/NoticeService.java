package com.docmall.basic.admin.notioe;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.basic.common.dto.Criteria;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;
	
	// 페이징 & 검색조건이 추가된 리스트
	public List<NoticeVO> NoticeList(Criteria cri) {
		return noticeMapper.NoticeList(cri);
	}
		
		// 데이터 총개수
	public int getNoticeCount(Criteria cri) {
		return noticeMapper.getNoticeCount(cri);
	}
}
