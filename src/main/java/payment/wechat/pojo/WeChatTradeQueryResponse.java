package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.wechat.Const;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 查询返回对象
 * <P>参考自</P>
 * <URL>https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4</URL>
 * @author DK
 * @date 2018/3/3 14:17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatTradeQueryResponse extends WeChatCallBackResponse{
    private  String trade_state; //SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付）USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
    private  String settlement_total_fee; //当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
    private  String trade_state_desc;//对当前查询订单状态的描述和下一步操作的指引

    @JsonIgnore
    public boolean isPaySuccess(){
        return isSuccess() && Const.WECHAT_PAY_SUCCESS.equalsIgnoreCase(trade_state);
    }

    @JsonIgnore
    public boolean isExist(){
        return  !Const.WECHAT_ORDER_NOT_EXIST.equalsIgnoreCase(err_code);
    }

    public  WeChatCallBackResponse toWeChatCallBackResponse(){
        return this;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }
}
