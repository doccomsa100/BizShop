package com.docmall.basic.admin.StaticAnalysis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaticAnalysisService {

	private final StaticAnalysisMapper staticAnalysisMapper;
	
	// 카테고리별월별매출현황
	public List<Map<String, Object>> Monthlysalesstatus(String ordDate) {
		return staticAnalysisMapper.Monthlysalesstatus(ordDate);
	}
}
