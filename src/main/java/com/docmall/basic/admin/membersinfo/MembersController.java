package com.docmall.basic.admin.membersinfo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;
import com.docmall.basic.user.UserService;
import com.docmall.basic.user.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/members/*")
public class MembersController {

	private final MembersService membersService;
	
	private final UserService userService;
	
	private final PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/member_list")
	public void member_list(Criteria cri, Model model) {
		
		cri.setAmount(20);
		
		List<UserVo> list = membersService.member_list(cri);
		log.info("회원목록 데이터: " + list);
		
		// 게시물 목록 20건
		model.addAttribute("member_list", list);
		
		// 전체데이터 개수
		int total = membersService.getTotalCount(cri);
		PageDTO pageDTO = new PageDTO(cri, total);
		
		log.info("페이징 기능데이터: " + pageDTO);
		model.addAttribute("pageMaker", pageDTO);
	}
	
	@PostMapping("/member_delete")
	public String member_delete(Criteria cri, String user_id) {
		membersService.member_delete(user_id);
		
		return "redirect:/admin/members/member_list" + cri.getListLink();
	}
	
	@GetMapping("/member_insert")
	public void member_insert() {
		
	}
	
	// 아이디중복체크
	@GetMapping("idCheck")
	public ResponseEntity<String> idCheck(String user_id) throws Exception {
		ResponseEntity<String> entity = null;
		
		String idUse = "";
		// 사용자가 입력한 아이디를 비교해서 있으면 null이 아니여서 true 사용불가
		// 사용자가 입력한 아이디를 비교해서 없으면 null이기 떄문에 false 사용가능
		if(userService.idCheck(user_id) != null) {
			idUse = "no";  // 사용불가능
		}else {
			idUse = "yes"; // 사용가능
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}
	
	// 회원가입 등록
	@PostMapping("member_join")
	public String member_joinOk(UserVo vo) throws Exception {
		
		vo.setUser_pwd(passwordEncoder.encode(vo.getUser_pwd()));
		
		log.info("회원정보: " + vo);
		
		userService.join(vo);
		
		return "redirect:/admin/members/member_list";
	}
	
	
}
