package com.chehaha.api.wechat.xiao.custserv.service;

import java.util.List;

import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.api.wechat.xiao.custserv.pojo.RequireOnLineCustomerService;

//连接客服接口列表
public interface IConcatCustomerService {

	/**
	 * @param uid
	 * @return
	 * 将消息复制转发给指定客服
	 * 可用 xiao
	 */
	String copyToOneCustomerService(String uid) throws BizException,FrameworkException;
	
	//随机转接给某个客服
	String copyToMoreCustomerService(String uid)  throws BizException,FrameworkException;
	
	//获取在线客服列表
	List<RequireOnLineCustomerService> requiredOnLineCustomerService();
	
	
}
