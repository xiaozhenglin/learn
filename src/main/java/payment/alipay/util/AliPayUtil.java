package com.chehaha.api.payment.alipay.util;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.chehaha.api.payment.alipay.pojo.AliPayCallBackResponse;
import com.chehaha.common.util.DateUtil;
import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.common.util.string.DecimalUtil;

import java.util.Arrays;

/**
 * @author DK
 * @date 2018/3/5 19:23
 */
public class AliPayUtil {
    private final static String QUERY_SUCCESS = "TRADE_SUCCESS";

    public static boolean checkPayIsSuccess(AlipayTradeQueryResponse alipayTradeQueryResponse) {
        if (alipayTradeQueryResponse == null) {
            return false;
        }
        return QUERY_SUCCESS.equalsIgnoreCase(alipayTradeQueryResponse.getTradeStatus());
    }

    public static AliPayCallBackResponse alipayTradeQueryResponseToAliPayCallBackResponse(AlipayTradeQueryResponse query, String rivalAccount, String appId) {
        AliPayCallBackResponse callBack = new AliPayCallBackResponse();
        callBack.setApp_id(appId);
        callBack.setNotify_time(DateUtil.formatDate());
        callBack.setNotify_type("主动获取");
        callBack.setTrade_no(query.getTradeNo());
        callBack.setOut_trade_no(query.getOutTradeNo());
        callBack.setBuyer_logon_id(query.getBuyerLogonId());
        callBack.setBuyer_id(query.getBuyerUserId());
        callBack.setSeller_id(rivalAccount);
        callBack.setTrade_status(query.getTradeStatus());
        callBack.setTotal_amount(DecimalUtil.doubleValue(DecimalUtil.percentValue(Double.parseDouble(query.getTotalAmount()))));
        //TODO DK 所有金额等于订单金额 查询返回只有订单总金额
        callBack.setReceipt_amount(DecimalUtil.doubleValue(DecimalUtil.percentValue(Double.parseDouble(query.getTotalAmount()))));
        callBack.setInvoice_amount(DecimalUtil.doubleValue(DecimalUtil.percentValue(Double.parseDouble(query.getTotalAmount()))));
        callBack.setBuyer_pay_amount(DecimalUtil.doubleValue(DecimalUtil.percentValue(Double.parseDouble(query.getTotalAmount()))));
        callBack.setPoint_amount(DecimalUtil.doubleValue(DecimalUtil.percentValue(Double.parseDouble(query.getPointAmount()))));
        callBack.setFund_bill_list(query.getFundBillList() == null ? "" : FastjsonUtil.parse(query.getFundBillList()));
        callBack.setVoucher_detail_list(query.getVoucherDetailList() == null ? "" : FastjsonUtil.parse(query.getVoucherDetailList()));
        return callBack;
    }
}
