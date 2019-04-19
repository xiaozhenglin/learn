package com.chehaha.api.wechat.xiao;

public interface OfficialConst {
	String bindWxCodeSendSuccess ="邀请绑定微信发送成功";
	String removeCsvSeatSuccess = "删除账号成功";
	String auditPass = "审核通过";
	String auditError =  "审核失败,账号或微信号错误,或者已经被使用";
	String changeCsvDeniedSuccess ="修改该客服为拒绝状态成功";
	String createMenuSuccess = "菜单创建成功" ;
	String addCodeSuccess = "添加指令成功";
	String deleteCodeSuccess = "删除指令成功";
	String updateCodeSuccess = "修改指令成功";
	String CSV_HEAD_IMAGE = "/static/image/csv_avatar.png"; // 客服注册头像
	String updateError = "修改失指令失败";
	String CSV_EVENT_CODE = "csv_event_code"; 
	String BUSINESS_CODE = "business"; 
	String ZX_INFOMATION = "点击按钮可进行相应的操作";
	String unKnownCommand = "unknown";
	String csvNotUnline ="客服不在线";
	String concatCsv ="正在连接客服";
	String welcome = "欢迎您关注车哈哈汽车服务公众号!\n" +
			"\n" +
			"访问平台 <a href=\"https://customer.chehaha.net/index.html\">【点这里】</a>\n" +
			"\n" +
			"注册享1年车管家服务 <a href=\"https://customer.chehaha.net/index.html#/html/login\">【点这里】</a>\n" +
			"\n" +
			"兑换码入口 <a href=\"https://customer.chehaha.net/index.html#/html/mine/redemption-code\">【点这里】</a>";
	String TS = "8";
	String BUSINESS_TX_REPLY = "业务投诉XXXXXXX";
	String DONE_BUSINESS ="17";
	String BUSINESS_DONE_REPLY = "业务办理XXXXXXX";
}
