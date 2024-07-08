package com.docmall.basic.cart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.basic.user.UserVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cart/*")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	
	//상품이미지 업로드 경로
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	// 장바구니 추가
	@GetMapping("cart_add")
	public ResponseEntity<String> cart_add(CartVO vo, HttpSession session) throws Exception {
		
		log.info("장바구니: " + vo);
		
		ResponseEntity<String> entity = null;
		
		String user_id = ((UserVo) session.getAttribute("login_status")).getUser_id();
		vo.setUser_id(user_id);
		
		cartService.cart_add(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// 장바구니 목록
	// 장바구니에 들어갈때 장바구니에서 수량을 늘리면 가격이 그만큼 곱해지는데 CartVO로는 처리가 안되서 클래스를 하나 추가했다.
	@GetMapping("cart_list") // 장바구니 목록을 보여주기위해 사용자아이디를 가지고옴
	public void cart_list(HttpSession session, Model model) {
		
		String user_id = ((UserVo) session.getAttribute("login_status")).getUser_id();
		
		
	}
}
