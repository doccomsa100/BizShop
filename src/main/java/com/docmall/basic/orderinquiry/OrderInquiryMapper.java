package com.docmall.basic.orderinquiry;

import java.util.List;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.order.OrderVO;

public interface OrderInquiryMapper {

	// 주문조회리스트
	List<OrderVO> inquiry_list(Criteria cri);
}
