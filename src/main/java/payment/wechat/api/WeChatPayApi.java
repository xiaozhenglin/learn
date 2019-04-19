package com.chehaha.api.payment.wechat.api;

import com.chehaha.api.payment.pojo.IPayParam;
import com.chehaha.api.payment.pojo.ITransferParam;
import com.chehaha.api.payment.pojo.PayApiType;
import com.chehaha.api.payment.wechat.Const;
import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.payment.wechat.WeChatPayConfig.*;
import com.chehaha.api.payment.wechat.pojo.*;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.exception.code.F7;
import com.chehaha.common.exception.code.F8;
import com.chehaha.common.type.ExpiredStrategy;
import com.chehaha.common.type.Section;
import com.chehaha.common.util.cache.RedisUtil;
import com.chehaha.common.util.crypt.MD5;
import com.chehaha.common.util.http.RestUtil;
import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.common.util.string.DecimalUtil;
import com.chehaha.common.util.string.EncryptionUtil;
import com.chehaha.common.util.string.StringUtil;
import com.chehaha.common.util.string.ValidatorUtil;
import com.chehaha.fee.pojo.PayPipe;
import com.chehaha.platform.util.PlatformAccountUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import scala.annotation.meta.param;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.*;
import java.util.*;

@Service
public class WeChatPayApi {

    @Autowired
    private WeChatPayConfig config;

    public String notifySuccessXml() {
        return "<xml>" +
                "<return_code><![CDATA[SUCCESS]]></return_code>" +
                "</xml>";
    }

    /**
     * 微信退单
     *
     * @param weChatCallBackResponse
     * @param refundReason
     * @return
     */
    public Map<String, String> wechatPayTradeRefund(WeChatCallBackResponse weChatCallBackResponse, String refundReason) {
        try {
            WeChatRefund instance = WeChatRefund.instance(weChatCallBackResponse, refundReason, config);
            Map<String, String> stringStringMap = cretRequest(config.getRefundApi(), instance.toXml());
            //修改金额 微信已分为单位
            PlatformAccountUtil.changeAmount(Float.parseFloat(instance.getRefund_fee()) / 100F, false, PayPipe.Alipay);
            return stringStringMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameworkException(F7.WECHAT_REFUND_FAIL, weChatCallBackResponse);
        }
    }


    public Map<String, String> wehcatTransfers(ITransferParam transferParam) {
        try {
            WeChatTransfersParam weChatTransfersParam = new WeChatTransfersParam(config, transferParam);
            return cretRequest(config.getTransfersApi(), weChatTransfersParam.toXml());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameworkException(F7.WECHAT_TRANSFER_ERROR, transferParam,e.getMessage());
        }
    }

    public boolean isSignatureValid(Map<String, String> data) throws Exception {

//		String signTypeInData = data.get(WeChatPayConfig.FIELD_SIGN_TYPE);
        String signTypeInData = data.get(Const.SIGN_TYPE_KEY);
        SignType signType;
        if (signTypeInData == null) {
            signType = SignType.MD5;
        } else {
            signTypeInData = signTypeInData.trim();
            if (signTypeInData.length() == 0) {
                signType = SignType.MD5;
            } else if (Const.MD5.equals(signTypeInData)) {
                signType = SignType.MD5;
            } else if (Const.SHA.equals(signTypeInData)) {
                signType = SignType.HMACSHA256;
            } else {
                throw new Exception(String.format("Unsupported sign_type: %s", signTypeInData));
            }
        }
        return isSignatureValid(data, config.getKey(), signType);
    }


    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data     Map类型数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    private boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey(Const.SIGN_KEY)) {
            return false;
        }
        String sign = data.get(Const.SIGN_KEY);
        return generateSignature(data, key, signType).equals(sign);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data     待签名数据
     * @param key      API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(Const.SIGN_KEY)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        } else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    private static String MD5(String data) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     * @return 加密结果
     * @throws Exception
     */
    private static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 创建统一下单的xml的java对象
     *
     * @return
     */
    public static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-", "");
    }


//	 private static WeChatPayInfo createPayInfo(String ip, String body, String oid, String money, String attach, String tradeType) {
//		WeChatPayInfo payInfo = new WeChatPayInfo();
//		payInfo.setAppid(WeChatPayConfig.APP_ID);//微信开放平台审核通过的应用APPID
//		payInfo.setDevice_info("WEB");//终端设备号(门店号或收银设备ID)，默认请传"WEB"
//		payInfo.setMch_id(WeChatPayConfig.MCH_ID);//微信支付分配的商户号
//		payInfo.setNonce_str(create_nonce_str());//随机字符串，不长于32位。推荐随机数生成算法
//		payInfo.setBody(body);//商品或支付单简要描述
//		payInfo.setAttach(attach);//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
//		payInfo.setOut_trade_no(oid);//商户系统内部的订单号,32个字符内、可包含字母,
//		payInfo.setTotal_fee(money);//订单总价
//		payInfo.setSpbill_create_ip(ip);//客户端ip
//		payInfo.setNotify_url(WeChatPayConfig.NOTIFY_URL);//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
//		payInfo.setTrade_type(tradeType);//支付类型
//		return payInfo;
//	}

    private WeChatPayInfo createPayInfo(IPayParam payParam) {
        WeChatPayUniqueParam param = (WeChatPayUniqueParam) payParam.getPayChannelUniqueParam();
        WeChatPayInfo payInfo = new WeChatPayInfo();
        payInfo.setAppid(config,payParam.getPayApiType());//微信开放平台审核通过的应用APPID
        payInfo.setDevice_info("WEB");//终端设备号(门店号或收银设备ID)，默认请传"WEB"
        payInfo.setMch_id(config.getMchid());//微信支付分配的商户号
        payInfo.setNonce_str(create_nonce_str());//随机字符串，不长于32位。推荐随机数生成算法
        payInfo.setBody(com.chehaha.api.payment.Const.COMPANY_NAME);//商品或支付单简要描述
        payInfo.setOut_trade_no(payParam.getCode());//商户系统内部的订单号,32个字符内、可包含字母,
        payInfo.setTotal_fee(DecimalUtil.doubleToInt(DecimalUtil.multiply(payParam.getAmount(), 100)) + "");//订单总价 微信以分为单位
        payInfo.setSpbill_create_ip(getIp(param));//客户端ip
        payInfo.setNotify_url(config.getNotifyUrl());//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        payInfo.setTrade_type(config.getTradeType(payParam.getPayApiType()));//支付类型
        if(payParam.getPayApiType()== PayApiType.JSAPI){
            payInfo.setOpenid(param.getOpenId());
        }
        payInfo.setAttach(FastjsonUtil.parse(payParam.getPayCallBackParam())); //回调参数
        return payInfo;
    }

    private String getIp(WeChatPayUniqueParam param) {

        if (StringUtil.isBlank(param.getIp())) {
            throw new FrameworkException(F8.IP_IS_NULL);
        }
        if (!ValidatorUtil.isIPAddr(param.getIp())) {
            throw new FrameworkException(F8.IP_FORMAT_ERROR, param.getIp());
        }
        return param.getIp();
    }

    /**
     * 获取签名
     *
     * @param payInfo
     * @return
     * @throws Exception
     */
    public String getSign(WeChatPayInfo payInfo) {
        StringBuilder sb =  new StringBuilder();
        sb.append("appid=" + payInfo.getAppid());
        sb.append("&attach=" + payInfo.getAttach());
        sb.append("&body=" + payInfo.getBody());
        sb.append("&device_info=" + payInfo.getDevice_info());
        sb.append("&mch_id=" + payInfo.getMch_id());
        sb.append("&nonce_str=" + payInfo.getNonce_str());
        sb.append("&notify_url=" + payInfo.getNotify_url());
        if(StringUtil.isNotBlank(payInfo.getOpenid())){
            sb.append("&openid=" + payInfo.getOpenid());
        }
        sb.append("&out_trade_no=" + payInfo.getOut_trade_no());
        sb.append("&spbill_create_ip=" + payInfo.getSpbill_create_ip());
        sb.append("&total_fee=" + payInfo.getTotal_fee());
        sb.append("&trade_type=" + payInfo.getTrade_type());
        sb.append("&key=" + config.getKey());
        String sign = string2MD5(sb.toString()).toUpperCase();
        return sign;
    }
    public static String string2MD5(String origin) {
      return   MD5.encrypt(origin);
//        String charsetname = "UTF-8";
//        String resultString = null;
//        try {
//            resultString = new String(origin);
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            if (charsetname == null || "".equals(charsetname))
//                resultString = byteArrayToHexString(md.digest(resultString
//                        .getBytes()));
//            else
//                resultString = byteArrayToHexString(md.digest(resultString
//                        .getBytes(charsetname)));
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        return resultString;
    }

    public static Map<String, String> parseXml(String xml) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        return map;
    }

    public static Map<String, String> httpsRequestToXML(String requestUrl, String requestMethod, String outputStr) {
        try {
            StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
            return parseXml(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
//			  System.out.println("连接超时："+ce.getMessage());
            throw new FrameworkException(F7.WECHAT_REQUEST_FAIL, ce.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
//			  System.out.println("https请求异常："+e.getMessage());
            throw new FrameworkException(F7.WECHAT_REQUEST_FAIL, e.getMessage());
        }
    }


    private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
            throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, IOException {
        URL url = new URL(requestUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        if (null != output) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes("UTF-8"));
            outputStream.close();
        }
        // 从输入流读取返回内容
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        connection.disconnect();
        return buffer;
    }


    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

//	 public static AppCallWechat payUnifiedorder(String ip, String body, String oid, String money, String attach, String tradeType) throws JsonProcessingException {
//		 WeChatPayInfo payInfo = createPayInfo(ip, body, oid, money, attach,tradeType);
//		 payInfo.setSign(getSign(payInfo));
//		 Map<String, String> map = httpsRequestToXML(WeChatPayConfig.PAY_UNIFIEDORDER,
//				 "POST",payInfo.transeXml());
//		 String s = FastjsonUtil.beanToJson(map);
//		 PrepayIdResponse prepayIdResponse = FastjsonUtil.toObject(s, PrepayIdResponse.class);
//		 AppCallWechat appCallWechat = new AppCallWechat(prepayIdResponse);
//		 return appCallWechat;
//	 }


    /**
     * 创建微信订单
     *
     * @return
     */
    public AppCallWechat payUnifiedorder(IPayParam payParam) {
        WeChatPayInfo payInfo = createPayInfo(payParam);
        payInfo.setSign(getSign(payInfo));
        Map<String, String> map = httpsRequestToXML(config.getUnifiedorderApi(),
                "POST", payInfo.transeXml());
        String s = null;
        try {
            s = FastjsonUtil.beanToJson(map);
        } catch (JsonProcessingException e) {
            throw new FrameworkException(F7.WECHAT_RETURN_INFO_READ_FAIL, map);
        }
        PrepayIdResponse prepayIdResponse = FastjsonUtil.toObject(s, PrepayIdResponse.class);
        if (!prepayIdResponse.getIsSuccess()) {
            throw new FrameworkException(F7.WECHAT_CREATE_ORDER_FAIL, payInfo, s);
        }
        return new AppCallWechat(prepayIdResponse, config.getKey());
    }


    private Map<String, String> cretRequest(String url, String data) throws Exception {
        /**
         * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
         */

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(config.getSslCert()));//P12文件目录
        try {
            keyStore.load(instream, config.getSslPassword().toCharArray());//这里写密码..默认是你的MCHID
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, config.getSslPassword().toCharArray())//这里也是写密码的
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();

                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return parseXml(jsonStr);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }




}
