package com.docmall.basic.qnaboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/qnaboard")
@RequiredArgsConstructor
public class QnaBoardController {

	private final QnaBoardService qnaBoardService;
	
	//상품이미지 업로드 경로
	@Value("${file.product.image.user}")
	private String uploadPath;
	
	// Qna리스트
	@GetMapping("/qnalist/{pro_num}/{page}")
	public ResponseEntity<Map<String, Object>> qnalist(@PathParam("pro_num")int pro_num, @PathParam("page") int page) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		Criteria cri = new Criteria();
		cri.setAmount(2);
		cri.setPageNum(page);
		
		// QnA목록
		List<QnaBoardVO> qnalist = qnaBoardService.qnalist(pro_num, cri);
		
		// 페이징정보
		int Qnacount = qnaBoardService.getCountQnaBypro_num(pro_num);
		PageDTO pageDTO = new PageDTO(cri, Qnacount);
		
		map.put("qnalist", qnalist);
		map.put("pageqna", pageDTO);
		
		entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return entity;
	}
}
