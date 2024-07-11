package com.docmall.basic.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/review/*")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	
	@GetMapping("/revlist/{pro_num}/{page}")
	public ResponseEntity<Map<String, Object>> revlist(@PathVariable("pro_num") int pro_num,@PathVariable("page") int page) {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();
		
		// Criteria 객체를 생성합니다. 이 객체는 페이지 번호와 페이지당 항목 수를 관리합니다.
		Criteria cri = new Criteria();
		cri.setAmount(2);
		cri.setPageNum(page);
		
		// 1) 후기목록
		List<ReviewVO> revlist = reviewService.rev_list(pro_num, cri);
		
		// 2) 페이징정보
		int revcount = reviewService.getCountReviewBypro_num(pro_num);
		PageDTO pageDTO = new PageDTO(cri, revcount);
		
		map.put("revlist", revlist);
		map.put("pageMaker", pageDTO);
		
		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		return entity;
	}
	
	// 상품후기 삭제
	@DeleteMapping("/review_delete/{rev_code}")
	public ResponseEntity<String> review_delete(@PathVariable("rev_code") Integer rev_code) throws Exception{
		ResponseEntity<String> entity = null;
		
		log.info("상품리뷰코드: " + rev_code);
		
		reviewService.review_delete(rev_code);
		entity = new ResponseEntity<String>("sueccss", HttpStatus.OK);
		
		
		return entity;
	}
	
	
}
