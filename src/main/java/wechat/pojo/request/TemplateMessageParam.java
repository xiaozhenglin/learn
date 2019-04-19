package com.chehaha.api.wechat.pojo.request;

import com.chehaha.api.wechat.Const;
import com.chehaha.common.util.string.StringUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DK
 * @date 2018/7/25 10:42
 */
public class TemplateMessageParam implements Serializable {
    private String touser; //用户OpenID
    private String template_id; //模板消息ID
    private String url; //URL置空，在发送后，点模板消息进入一个空白页面（ios），或无法点击（android）。
    private Map<String, TemplateData> data; //模板详细信息

    public boolean checkIsError() {
        return StringUtil.isBlank(touser) || StringUtil.isBlank(template_id);
    }

    public TemplateMessageParam() {

    }

    public TemplateMessageParam(String touser, String template_id, String url,  Map<String, TemplateData> data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;

        this.data = data;
    }

    public static TemplateMessageParam instance(String openId, String templateId, String url, Map<String, TemplateData> data) {
        return new TemplateMessageParam(openId, templateId, url, data);
    }

    //    订单通知
//    	"{{first.DATA}}
//    预约人：{{keyword1.DATA}}
//    手机号：{{keyword2.DATA}}
//    {{remark.DATA}}"


//    礼品领取成功通知
//{{first.DATA}}
//礼品名称：{{keyword1.DATA}}
//礼品数量：{{keyword2.DATA}}
//领取时间：{{keyword3.DATA}}
//{{remark.DATA}}


    //到期提醒
    //{{first.DATA}}
    //名称：{{keyword1.DATA}}
    //类型：{{keyword2.DATA}}
    //设定时间：{{keyword3.DATA}}
    //{{remark.DATA}}

    //邀请会员成功通知
    //{{first.DATA}}
    //奖励金额：{{keyword1.DATA}}
    //会员姓名：{{keyword2.DATA}}
    //加入时间：{{keyword3.DATA}}
    //{{remark.DATA}}


    //客服跟进提醒
    //{{first.DATA}}
    //客户称谓：{{keyword1.DATA}}
    //咨询时间：{{keyword2.DATA}}
    //{{remark.DATA}}

//    public static TemplateMessageParam instance(String openId, String templateId, String url, String pagePath, String... values) {
//        Map<String, TemplateData> datas = new HashMap<>();
//        TemplateData data;
//        switch (values.length) {  //TODO DK 故意不写break  利用case进入后 后续case全部都会执行的特性(如果不break)  满足3 则必须满足1.2  暂时最多支持5个key
//            case 5:
//                data = new TemplateData(values[5-1],null);
//                datas.put(Const.TEMPLATE_KEY_5,data);
//            case 4:
//                data = new TemplateData(values[4-1],null);
//                datas.put(Const.TEMPLATE_KEY_4,data);
//            case 3:
//                data = new TemplateData(values[3-1],null);
//                datas.put(Const.TEMPLATE_KEY_3,data);
//            case 2:
//                data = new TemplateData(values[2-1],null);
//                datas.put(Const.TEMPLATE_KEY_2,data);
//            case 1:
//                data = new TemplateData(values[1-1],null);
//                datas.put(Const.TEMPLATE_KEY_1,data);
//            default:
//                break;
//        }
//        return instance(openId, templateId, url, pagePath, datas);
//    }






    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }



}
