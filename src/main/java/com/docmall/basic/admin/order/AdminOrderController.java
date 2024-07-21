package com.docmall.basic.admin.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;
import com.docmall.basic.common.util.FileManagerUtils;
import com.docmall.basic.order.OrderVO;
import com.docmall.basic.userpay.UserPayService;
import com.docmall.basic.userpay.UserPayVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/order/*")
@RequiredArgsConstructor
public class AdminOrderController {

	private final AdminOrderService adminOrderService;
	
	private final UserPayService userPayService;
	
	//상품이미지 업로드 경로
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	// 주문리스트 
	@GetMapping("/order_list")
	public void order_list(Criteria cri, Model model) throws Exception {
		
		log.info("Criteria" +cri);
		
		// 주문리스트
		List<OrderVO> order_list = adminOrderService.order_list(cri);
		
		log.info("order_list" + order_list);
		
		// 주문리스트 총새수
		int total = adminOrderService.getTotalCount(cri);
		
		log.info("pagedto" + new PageDTO(cri, total));
		
		model.addAttribute("order_list", order_list);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	// 주문상세정보에서 주문상품 이미지출력 1)<img src="매핑주소"> 2) <img src="test.gif">
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	// 주문상세정보
	@GetMapping("/order_detail_info")
	public ResponseEntity<Map<String, Object>> order_detail_info(Long order_num) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		// 주문자(수령인)정보
		OrderVO vo = adminOrderService.order_info(order_num);
		map.put("order_basic", vo);
		
		// 주문상품정보
		List<AdminOrderVO> order_product_list = adminOrderService.order_admin_info(order_num);
		
		// 클라이언트에 \를 /로 변환하여, model작업전에 처리함.  2024\07\01 -> 2024/07/01
		order_product_list.forEach(ord_pro -> {
			ord_pro.setPro_up_folder(ord_pro.getPro_up_folder().replace("\\", "/"));
		});
		
		map.put("order_pro_list", order_product_list);
		
		// 결제정보
		UserPayVO u_po = userPayService.order_pay_info(order_num);
		map.put("payinfo", u_po);
		
		entity = new ResponseEntity<Map<String,Object>> (map,HttpStatus.OK);
		
		return entity;
		
	}
	
	
	
	
	
	
	
	
	
}
