package com.docmall.basic.admin.StaticAnalysis;

import java.util.List;
import java.util.Map;

public interface StaticAnalysisMapper {

	// 카테고리별월별매출현황
	List<Map<String, Object>> Monthlysalesstatus(String ordDate);
	
}
