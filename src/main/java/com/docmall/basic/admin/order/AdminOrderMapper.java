package com.docmall.basic.admin.order;

import java.util.List;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.order.OrderVO;

public interface AdminOrderMapper {
	
	// 주문관리 리스트
	List<OrderVO> order_list(Criteria cri);
	
	// 리스트 총새수
	int getTotalCount(Criteria cri);
	
	// 주문자(수령인)정보
	OrderVO order_info(Long order_num);
	
	// 주문상품정보
	List<AdminOrderVO> order_admin_info(Long order_num);

}
