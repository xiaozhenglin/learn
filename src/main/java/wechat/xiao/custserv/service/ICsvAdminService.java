package com.chehaha.api.wechat.xiao.custserv.service;

import com.chehaha.api.wechat.xiao.custserv.entity.CsvSeat;
import com.chehaha.api.wechat.xiao.custserv.pojo.CsvInfoList;
import com.chehaha.api.wechat.xiao.custserv.pojo.regist.CsvRemoveResult;

public interface ICsvAdminService {
	
	//2：坐席添加接口
	boolean seatCreate(String nickname, String account, String wxcode);

	//1.获取客服列表信息
	CsvInfoList acquireCsvInformation();

	//3：坐席删除接口
	String removeCsv(String account);

	
	
//	String csvSeatAudit(int id, int active, String account);


}
