package com.docmall.basic.userpay;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserPayService {

	private final UserPayMapper userPayMapper;
}
