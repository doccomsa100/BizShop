package com.docmall.basic.orderinquiry;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.basic.admin.order.AdminOrderVO;
import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.order.OrderVO;

public interface OrderInquiryMapper {

	// 주문관리 리스트
	List<OrderVO> order_list(@Param("cri") Criteria cri,@Param("start_date") String start_date,@Param("end_date") String end_date );
		
	// 리스트 총새수
	int getTotalCount(@Param("cri") Criteria cri,@Param("start_date") String start_date,@Param("end_date") String end_date );

	// 주문자(수령인)정보
	OrderVO order_info(Long order_num);
	
	// 주문상품정보
	List<OrderInQuiryVO> order_user_info(Long order_num);

}
