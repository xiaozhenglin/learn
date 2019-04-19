package com.chehaha.api.wechat.xiao.custserv.service;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chehaha.common.exception.FrameworkException;
import com.chehaha.api.wechat.xiao.custserv.entity.CsvRecord;
import com.chehaha.api.wechat.xiao.custserv.pojo.chartadmin.CsvChartList;
import com.chehaha.api.wechat.xiao.custserv.pojo.chartadmin.CsvChartListQuery;

public interface ICsvRecordService {
	
	//获取公众号客服聊天记录
	void collectChartRecords(Date day) throws FrameworkException;
	
	// 1. 客服记录列表接口
	Page<CsvChartList> getCsvCharList(CsvChartListQuery query, Pageable page);
	
	// 2：客服记录删除接口
	String removeChart(String id);
		
}
