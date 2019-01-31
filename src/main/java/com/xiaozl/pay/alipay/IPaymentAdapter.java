package com.xiaozl.pay.alipay;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.chehaha.api.payment.pojo.IPayParam;
import com.chehaha.api.payment.pojo.ITransferParam;
import com.chehaha.api.payment.pojo.PayApiType;
import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.order.entity.OrderInfo;

/**   
* @Title: IPaymentAdapter.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author James.Wong
* @date 2018年2月13日 
* @version V1.0   
*/
public interface IPaymentAdapter {
	/**
	 * 支付
	 * @return
	 * @throws BizException
	 * @throws FrameworkException
	 */
//	Serializable payOrder(OrderInfo order, PayApiType apiType, Object... extentions) throws BizException,FrameworkException;
	Serializable payOrder(IPayParam payParam) throws Exception;


	/**
	 * 退款
	 * @param order
	 * @param response
	 * @param reason
	 * @return
	 * @throws BizException
	 * @throws FrameworkException
	 */
	Serializable refund(OrderInfo order,Serializable response,String reason) throws Exception;

	/**
	 * 回调
	 * @param data
	 * @param request
	 * @throws FrameworkException
	 */
	void payCallback(Serializable  data, HttpServletRequest request) throws Exception;

	/**
	 * 查询订单
	 * @param tradeNo
	 * @return
	 */
	Serializable payQuery(String tradeNo);

	/**
	 * 关闭订单
	 * @param tradeNo
	 * @return
	 */
	Serializable closePay(String tradeNo);

	/**
	 * 转账
	 */
	Serializable transfer(ITransferParam transferParam) throws Exception;
}
