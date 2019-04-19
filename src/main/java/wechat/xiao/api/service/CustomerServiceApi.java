package com.chehaha.api.wechat.xiao.api.service;

import java.util.List;

import com.chehaha.api.wechat.xiao.api.WechatApiResult;
import com.chehaha.api.wechat.xiao.util.RestUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.chehaha.common.exception.FrameworkException;
import com.chehaha.api.wechat.xiao.api.pojo.custserv.CustomerServiceAccount;
import com.chehaha.api.wechat.xiao.api.pojo.custserv.CustomerServiceForm;
import com.chehaha.api.wechat.xiao.api.pojo.custserv.CustomerServiceUser;
import com.chehaha.api.wechat.xiao.custserv.pojo.CsvInfoList;
import com.chehaha.api.wechat.xiao.custserv.pojo.CsvRecordResult;
import com.chehaha.api.wechat.xiao.custserv.pojo.OnlineList;
import com.chehaha.api.wechat.xiao.custserv.pojo.RequireOnLineCustomerService;
import com.chehaha.api.wechat.xiao.custserv.pojo.RequireWeChatRecords;
import com.chehaha.api.wechat.xiao.custserv.pojo.regist.CsvRemoveResult;
import com.chehaha.api.wechat.xiao.api.WechatConfig;

//客服Api
public class CustomerServiceApi {
	
	private final static Class<?> clazz = CustomerServiceApi.class;
	
	//获取客服列表
	public static CsvInfoList acquireCsvInformation(){
		String api="https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token={:token}";
		CsvInfoList csvInfoList = RestUtil.getForEntity(api, CsvInfoList.class, WechatConfig.getAccessToken());
		if(csvInfoList.isSuccess()) {
			return csvInfoList;
		}
		throw  csvInfoList.getException(clazz);
	}
	
	//获取在线客服列表
	public static List<RequireOnLineCustomerService> getCustomerServiceList() throws FrameworkException {
		String api = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token= {:token}";
		OnlineList onlineList = RestUtil.getForObject(api, OnlineList.class, WechatConfig.getAccessToken());
		if(onlineList.isSuccess()) {
			return onlineList.getKf_online_list();
		}
		throw  onlineList.getException(clazz);
	}
	
	
	public static boolean addCustomerService(CustomerServiceForm info) throws FrameworkException {
		final String api = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token={:token}";

		WechatApiResult result = RestUtil.postJsonForEntity(api, info, WechatApiResult.class,WechatConfig.getAccessToken());
		if (result.isSuccess()) {
			//如果有账号
			if(info.hasWechatAccount()) {
				inviteCustomerService(info);
			}
			//如果有图片
			if(info.hasAvatar()) {
				setCustomerServiceAvatar(info);
			}			
			return true;
		}
		throw result.getException(clazz);
	}

	public static boolean inviteCustomerService(CustomerServiceUser user) throws FrameworkException {
		final String api = "https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token={:token}";
		WechatApiResult result = RestUtil.postJsonForEntity(api, user, WechatApiResult.class,
				WechatConfig.getAccessToken());
		if (result.isSuccess()) {
			return true;
		}
		throw result.getException(clazz);
	}

	public static boolean setCustomerServiceInfo(CustomerServiceForm info) throws FrameworkException{
		final String api = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token={:token}";
		WechatApiResult result = RestUtil.postJsonForEntity(api, info, WechatApiResult.class,
				WechatConfig.getAccessToken());
		if (result.isSuccess()) {
			//如果有图片
			if(info.hasAvatar()) {
				setCustomerServiceAvatar(info);
			}
			return true;
		}
		throw result.getException(clazz);
	}

	public static boolean setCustomerServiceAvatar(CustomerServiceForm info) throws FrameworkException{
		final String api = "https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg";  //?access_token={:token}&kf_account= {:account}
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		form.add("access_token", WechatConfig.getAccessToken());
		form.add("kf_account", info.getUsername());
		form.add("media", new FileSystemResource(info.getAvatar()));
		WechatApiResult result = RestUtil.postFormForEntity(api, form, WechatApiResult.class);
		if (result.isSuccess()) {
			return true;
		}
		throw result.getException(clazz);
		
	}
	
	public static void removeCustomerService(CustomerServiceAccount account) throws FrameworkException{
		final String api = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token={:token}&kf_account= {:account}";
		WechatApiResult result = RestUtil.postJsonForEntity(api, account, WechatApiResult.class,
				WechatConfig.getAccessToken(),account.getUsername());
		if (result.isSuccess()) {
			return;
		}
		throw result.getException(clazz);
	}
	
	//获取聊天记录
	public static CsvRecordResult getChartRecords(RequireWeChatRecords rwcr) {
		String api = "https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token= {:token}";
		CsvRecordResult crr = RestUtil.postJsonForEntity(api, rwcr, CsvRecordResult.class,WechatConfig.getAccessToken());
		if (crr.isSuccess()) {
			return crr ;
		}
		throw crr.getException(clazz);
		//映射结果集到类中
	}
	
	//删除客服账号
	public static CsvRemoveResult deleteCsvAccount(String account) {
		String token=WechatConfig.getAccessToken();
		account = account+"@"+WechatConfig.getCustomService().getSuffix();
		String url="https://api.weixin.qq.com/customservice/kfaccount/del?access_token={:token}&kf_account={:account}";
		CsvRemoveResult crr = RestUtil.getForEntity(url,CsvRemoveResult.class,token,account);
		return crr;
	}
	
}
