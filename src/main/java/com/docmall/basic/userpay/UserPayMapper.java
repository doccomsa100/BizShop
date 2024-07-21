package com.docmall.basic.userpay;

public interface UserPayMapper {
	
	// 결제내용 삽입
	void userpay_insert(UserPayVO vo);
	
	// 결제정보 보기
	UserPayVO order_pay_info(Long order_num);

}
