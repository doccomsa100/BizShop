package com.docmall.basic.cart;

import java.util.List;

public interface CartMapper {
	
	// 장바구니 추가
	void cart_add(CartVO vo);
	
	// 장바구니 목록
	List<CartProductVO> cart_list(String user_id);

}
