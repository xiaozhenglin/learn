package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.wechat.Const;
import com.chehaha.common.util.string.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 *  * 关闭订单返回对象
 * <P>参考自</P>
 * <URL>https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_3&index=3</URL>
 * @author DK
 * @date 2018/3/3 17:11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatCloseOrderResponse implements Serializable {
    String return_code;//SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
    String return_msg;//返回信息，如非空，为错误原因
    String appid;// 微信开放平台审核通过的应用APPID
    String mch_id;//微信支付分配的商户
    String nonce_str;//随机字符串，不长于32位
    String sign;//签名
    String result_code;//业务结果	 SUCCESS/FAIL
    String result_msg;// 业务结果描述
    String err_code;//错误返回的信息描述
    String err_code_des;//错误代码

    @JsonIgnore
    public boolean isSuccess(){
        return  StringUtil.equals(Const.SUCCESS,this.return_code) && StringUtil.equals(Const.SUCCESS,this.result_code);
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }
}
