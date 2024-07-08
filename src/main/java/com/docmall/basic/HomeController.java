package com.docmall.basic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.docmall.basic.admincategory.AdminCategoryService;
import com.docmall.basic.admincategory.AdminCategoryVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final AdminCategoryService categoryService;

//	@ResponseBody  // 이 어노테이션이 사용이 안되면, return "index" 는 데이터가 아니라, 타임리프 파일명으로 인식.
	@GetMapping("/")
	public String index(Model model) {
		log.info("기본 주소.");
		
		List<AdminCategoryVO> cate_list = categoryService.getFirstCategoryList();
		model.addAttribute("user_cate_list", cate_list);
		return "index";
	}
}
