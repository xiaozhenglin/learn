package com.chehaha.api.wechat.xiao.api;

public interface ThirdpartyApiError{
    String NOT_ALIPAY_CALLBACK_INFO="F7010101";//不是支付宝的回调信息
    String ALIPAY_PAY_FAIL="F7010102";//支付宝支付失败
    String ORDER_INFO_ERROR = "F7010103"; //订单信息错误
    String ALIPAY_REFUND_FAIL ="F7010104"; //支付宝退款失败
    String SMS_SEND_ERROR="F7010105"; //短信发送失败
    String GAODE_ERROR = "F7010106"; //高德api调用异常
    String JUHE_NETWORK_ERROR="F7010107"; //网络错误,请重试!
    String JUHE_VEHICLE_ERROR="B40101114"; //车辆信息错误
    String JUHE_NOT_SUPPORT_AREA="F7010109"; //不支持城市
    String JUHE_OTHER_ERROR="F7010110"; //聚合其他未知错误
    String JUHE_SYNCHRONIZE_AREA_ERROR= "F7010111";//聚合同步城市失败
    String SEND_SIMPLE_MAIL_ERROR="F7010112";//发送简单邮件时发生异常
    String SEND_HTML_MAIL_ERROR="F7010113"; // 发送HTML邮件时发生异常
    String ALIPAY_CALLBACK_AMOUNT_DIFFERENT="F7010114";//支付宝回调订单付款金额不一致
    String WECHAT_CALLBACK_AMOUNT_DIFFERENT="F7010115";//微信回调订单付款金额不一致
    String WECHAT_REFUND_FAIL ="F7010116"; //微信退款失败
    String WECHAT_RETURN_INFO_READ_FAIL ="F7010117";//微信返回信息读取失败
    String WECHAT_CREATE_ORDER_FAIL="F7010118"; //微信创建订单失败
    String WECHAT_CALLBACK_SIGN_ERROR="F7010119";//微信回调SIGN校验失败
    
    
    String ALIDAYU_SIGN_NOT_EXIST = "F7030101";//阿里大于短信接口，不存在这个签名
    String ALIDAYU_TEMPLATE_NOT_EXIST = "F7030102";//阿里大于短信接口，不存在这个模板
    String ALIDAYU_API_SERVER_FAILED = "F7030201";//阿里大于短信接口调用失败,服务端异常
    String ALIDAYU_API_CLIENT_FAILED = "F7030202";//阿里大于短信接口调用失败，客户端异常
    
    String WECHAT_API_FAILED = "F7020101";//微信接口错误
}
