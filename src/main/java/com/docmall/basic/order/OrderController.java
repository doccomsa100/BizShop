package com.docmall.basic.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.docmall.basic.cart.CartProductVO;
import com.docmall.basic.cart.CartService;
import com.docmall.basic.cart.CartVO;
import com.docmall.basic.user.UserService;
import com.docmall.basic.user.UserVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/order/*")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	private final CartService cartService;
	
	private final UserService userService;
	
	
	@GetMapping("/productorder")
	public String productorder(@RequestParam(value = "type", defaultValue = "direct") String type, CartVO vo, Model model, HttpSession session) throws Exception {
		
		String user_id = ((UserVo) session.getAttribute("login_status")).getUser_id();
		vo.setUser_id(user_id);
		
		if(!type.equals("cartoder")) {
			
			cartService.cart_add(vo);
		}
		
		List<CartProductVO> cart_list = cartService.cart_list(user_id);
		int pro_total = 0;
		cart_list.forEach(d_vo -> {
			d_vo.setPro_up_folder(d_vo.getPro_up_folder().replace("\\", "/"));
		}
	);
		
	for(int i=0; i< cart_list.size(); i++) {
		pro_total += (cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount());
	}
		
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("pro_total", pro_total);
		return "/order/productorder";
	
	}
	
	@GetMapping("/orderagree")
	public ResponseEntity<UserVo> orderagree(HttpSession session) throws Exception {
		ResponseEntity<UserVo> entity = null;
		
		String user_id = ((UserVo) session.getAttribute("login_status")).getUser_id();
		
		entity = new ResponseEntity<UserVo>(userService.login(user_id), HttpStatus.OK);
		
		return entity;
	}
}
