package com.chehaha.api.wechat.xiao.custserv.exception;


/**
 * 起始编码为  BC
 * @author James.Wong
 *
 */
public interface CsvException {
	
	String NOT_EXITS_IN_CSVCEAT = "BC010101";   //客服坐席表中不存在此用户
	String CSV_ALREADY_APPROVE = "BC010102";   //已经支持
	String CSV_DENIED = "BC010103";   //审核被拒绝
	String WECHAT_WX_USERD = "BC010104"; //该微信号已经被绑定
	String CSV_EXITS_ACCOUNT="BC010105";   //账户已经存在
	String CSV_CHAT_DELETE_ERROR="BC010106"; //删除客服聊天记录失败
	String CSV_AUDIT_ERROR = "BC010107"; //修改该客服为拒绝状态失败
	String CSV_AUDIT_ACTIVE_ERROR="BC010108";//active属性错误
	String INVITE_BIND_WECHAT_WXCODE_ERROR ="BC010109"; //邀请绑定微信发送失败
	String HAVE_NOT_PARAMS ="BC010110"; //查询主键id参数为空
}
