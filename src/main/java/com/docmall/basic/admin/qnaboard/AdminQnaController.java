package com.docmall.basic.admin.qnaboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;
import com.docmall.basic.common.util.FileManagerUtils;
import com.docmall.basic.qnaboard.QnaBoardVO;
import com.docmall.basic.review.ReviewVO;
import com.docmall.basic.user.UserVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/qnaboard")
@RequiredArgsConstructor
public class AdminQnaController {
	
	private final AdminQnaService adminQnaService;
	
	//상품이미지 업로드 경로
	@Value("${file.product.image.user}")
	private String uploadPath;
	
	@GetMapping("/qnalist")
	public void qnalist(Criteria cri, Model model) {
	
		cri.setAmount(2);
		
		List<QnaBoardVO> adminqna_list = adminQnaService.adminqnalist(cri);
		adminqna_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		int amdinqnacount = adminQnaService.getAdminQnaCount(cri);
		PageDTO pageDTO = new PageDTO(cri, amdinqnacount);
		
		model.addAttribute("adminqna_list", adminqna_list);
		model.addAttribute("pageMaker", pageDTO);
	}

	
	// Qna리스트에서 사용할 이미지보여주기
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		// 로그 출력
	    log.info("File Path: " + fileName);
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	// 답변하기 폼작업
	@GetMapping("/qna_modify/{qna_idx}")
	public ResponseEntity<QnaBoardVO> qna_modify(@PathVariable("qna_idx") Long qna_idx) throws Exception {
		ResponseEntity<QnaBoardVO> entity = null;
		
		QnaBoardVO qnaBoard = adminQnaService.qna_modify(qna_idx);
		
		// 파일 경로에 있는 역슬래시(\)를 슬래시(/)로 변경
		if(qnaBoard.getPro_up_folder() != null) {
			qnaBoard.setPro_up_folder(qnaBoard.getPro_up_folder().replace("\\", "/"));
		}
	
		 // 이미지 파일 이름에 대한 경로도 변경
		if(qnaBoard.getPro_img() != null) {
			qnaBoard.setPro_img(qnaBoard.getPro_img().replace("\\", "/"));
		}
		
		return new ResponseEntity<>(qnaBoard, HttpStatus.OK);
	}
	
	
}
