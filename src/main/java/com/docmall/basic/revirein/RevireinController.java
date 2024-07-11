package com.docmall.basic.revirein;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/revirein/*")
@RequiredArgsConstructor
public class RevireinController {

	private final RevireinService revireinService;
	
	@GetMapping("rev_info")
	public void rev_info () {
		
	}
}
