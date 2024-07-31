package com.docmall.basic.orderinquiry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orderinquiry/*")
@RequiredArgsConstructor
public class OrderInquiryController {

	private final OrderInquiryService orderInquiryService;
}
