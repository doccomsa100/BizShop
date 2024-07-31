package com.docmall.basic.orderinquiry;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInquiryService {

	private final OrderInquiryMapper orderInquiryMapper;
}
