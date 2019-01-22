package com.chehaha.api.wechat.xiao.custserv.service.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.chehaha.api.wechat.xiao.api.service.CustomerServiceApi;
import com.chehaha.api.wechat.xiao.custserv.pojo.CopyMessageToKefu;
import com.chehaha.api.wechat.xiao.custserv.pojo.OnlineList;
import com.chehaha.api.wechat.xiao.custserv.pojo.RequireOnLineCustomerService;
import com.chehaha.api.wechat.xiao.custserv.service.IConcatCustomerService;
import com.chehaha.api.wechat.xiao.custserv.service.ICustomerReplyService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ConcatCustomerServiceImpl implements IConcatCustomerService{
	
	
	@Autowired
	private ICustomerReplyService customerReplyService;
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	/* 
	 * 连接其中一个指定客服
	 */
	@Override
	public String copyToOneCustomerService(String uid) throws BizException, FrameworkException {
		String token=WechatConfig.getAccessToken();
		//判断所有客服是否具有连接的能力
		boolean staffStatu=false;
		// 获取所有客服列表
		String supportStaffUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token="+token;
		JSONObject json = restTemplate.getForEntity(supportStaffUrl, JSONObject.class).getBody();
		//获取在线列表
		JSONArray kf_online_list = json.getJSONArray("kf_online_list");
		String message="";
		for (int i = 0; i < kf_online_list.size(); i++) {
			JSONObject kf =kf_online_list.getJSONObject(i);
			// 判断该客服是否可以连接
			if (kf != null && kf.get("status").toString().equals("1")
					&& !kf.get("accepted_case").toString().equals("5")) {
				staffStatu=true;
				//转发消息给客服
				CopyMessageToKefu mtkf = new CopyMessageToKefu();
				message=mtkf.setXml(uid,new Date().getTime(), kf.get("kf_account").toString());
			}
		}
		if(staffStatu) {
			return message;
		}else{
			customerReplyService.customerServiceReply(uid,"当前客服不在线,请稍后再试,或拨打电话0731-96699");
			return null;
		}
	}


	/*
	 * 连接多个客服中的一个
	 */
	@Override
	public String copyToMoreCustomerService(String uid) throws BizException, FrameworkException {
		//先判断是否所有客服都不在线
		 List<RequireOnLineCustomerService> list=requiredOnLineCustomerService();
		 if(list == null ||list.size() == 0) {
			 //客服都不在线
			return null;
		 }else if(list.size()>0){		
			 //客服在线
			CopyMessageToKefu mtkf = new CopyMessageToKefu();
			String message=mtkf.setSecondXml(uid,new Date().getTime());
			return message;
		 }else {
			 return null;
		 }
	}

	/*
	 * 获取在线客服列表
	 */
	@Override
	public List<RequireOnLineCustomerService> requiredOnLineCustomerService() {
		return CustomerServiceApi.getCustomerServiceList();
	}
	
}
