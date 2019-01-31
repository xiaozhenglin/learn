package com.xiaozl.pay.alipay;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by DK on 2017/12/6.
 */
@Component
@ConfigurationProperties(prefix = "third-party.pay.ali")
public class AliPayConfig {
	
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @NotNull private String APP_ID;
    @NotNull private String account; //appid对应的账户
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    @NotNull private String notify_url;
    // 请求网关地址
    @NotNull private String ALIPAY_PUBLIC_KEY;
    @NotNull private String APP_PRIVATE_KEY;
//    private String URL = "https://openapidev.alipay.com/gateway.do";  //测试沙箱url
//    private String URL = "https://openapidev.alipay.com/gateway.do";  //正式url
    private String url;

    private String charset = "UTF-8";
    private String format = "json";
    private String SIGN_TYPE = "RSA2";
	public String getAPP_ID() {
		return APP_ID;
	}
	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getALIPAY_PUBLIC_KEY() {
		return ALIPAY_PUBLIC_KEY;
	}
	public void setALIPAY_PUBLIC_KEY(String aLIPAY_PUBLIC_KEY) {
		ALIPAY_PUBLIC_KEY = aLIPAY_PUBLIC_KEY;
	}
	public String getAPP_PRIVATE_KEY() {
		return APP_PRIVATE_KEY;
	}
	public void setAPP_PRIVATE_KEY(String aPP_PRIVATE_KEY) {
		APP_PRIVATE_KEY = aPP_PRIVATE_KEY;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getSIGN_TYPE() {
		return SIGN_TYPE;
	}
	public void setSIGN_TYPE(String sIGN_TYPE) {
		SIGN_TYPE = sIGN_TYPE;
	}
    
    
  
	//
//
//	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    String NOTIFY_URL = RedisUtil.get(Section.Config,"ali.notify.url");
//    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
////    String return_url = "https://api.chehaha.net";
//    // 请求网关地址
//    String URL = "https://openapi.alipay.com/gateway.do";
//    //    沙箱
//    //   String URL = "https://openapi.alipaydev.com/gateway.do";
//    String ALIPAY_PUBLIC_KEY =RedisUtil.get(Section.Config,"ali.public.key");
//            //"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgQzxR0o55ReRD4pSlj7xUBqATKCg2/UlVjsedO8XX+D2+GaOyZzhANMLalQQae8a9BkQwHHKC97vxUn9H+HwF7JEmQPLLIao9pOgxFDQ31BKWqfYHQtKyTyQ2hpULZie7cNVXcubWJkS5nMjCJtKffYpSZOVOHn0fSzi+Qf/lJU6CsKq+2/zTYIlVvlnzuHLwcpBB3qpHnQSA68Krq8A6MR41MPwAuTScsAQC4fWZ3PfLCm1eCSwnX5YrYxU9/m+9OeJUc4hEE8rHKFJsh6uC7lCm8KCPbdMlOMZKWj8uv9znKeL22U7dwi+qzr2IAZ11pgmpMOnyMe0hogNCQKZmwIDAQAB";
//    // 编码
//    String CHARSET = "UTF-8";
//    // 返回格式
//    String FORMAT = "json";
//    // RSA2
//    String SIGNTYPE = "RSA2";
//    // 商户appid
//    String APPID =RedisUtil.get(Section.Config,"ali.appid"); //"2016052601446882";
//    // 私钥 pkcs8格式的
//    String RSA_PRIVATE_KEY = RedisUtil.get(Section.Config,"ali.rsa.private.key");
//            //"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDu/E77IHLGgcSqXtQ9V40/MbUAM1HJ5cRqIripD2fHIhwjfCs7UDsBJipoM9aMv73DhhsDT6MmRIwjrcroya7MyKTATMfCLJyNyImk4lo/wAq/zl4VEqG4k/ppBKGwi8SUDUAUe5H3drYFG2BOnVIFhrX4u2qA4YFuNvl56ohdg4mNSijxArICefxTT3nGEvx4oO+HSbFfx+6m2LIUzEWjQwljdqjoHH0NHxt5Kody1e3GXNatKHTk13yImVvrk3p/fIrS1ZlHYqTyWtg7zLlVLCOP+x6QeU4gWXIq8e6JHTUIl7rJquUOls6WgqxoP+OPHVOeiC0kUef/AyOkUZk5AgMBAAECggEAdlaxtWCH9QvlGogCiK2F37j5a7P91//SlsvCrNRYCx80181witpbnWIMtbSVlPSNPvh0/9q7awOecTxpsYPM7tKb4i0i7V+x1fNrVR5Qmii6KehCZiTnt8JuyPnmJsnO+ZDmHj5iNSuvTjAULFlyeBIQoa4MOu1vbegC+P/Nrp+yyRWZ6T+uhvC9dSzASEqVLTznvmwAWe4Ab3QefPz/ubnOkcE+GBXl1rOHJ+lDc8gEtTJAXqKPhaW9h03+PRxG28BkuVnnb0y1hhoASws8LPP6etQtwX8UYerAyLaGP6PfpUIwRA6m5k0SiwxRz5zvDlyFiZBwmINTVtSXxa/u0QKBgQD7zWTIxlmRadsxKeO5Hiyzd7lqrFdTj1pjBPcrIs9HqJoYWDrtQCSMu+bf/oTIHvD3ewr3RYVfQ2yforGB2+5oAicgzmoCyZnI3kiBDk3EERYac/7fGOD624etAY2I/SMJ8ceRXE95uOB+mFnsZaRQbAoaNzTYTlPuRpnLUWDXVwKBgQDy+DelR5VYNKl8xA0UA5r0F6N668RmyrrFjfdAsljndZ1sA7F+rsjIDJweW+DywMLI8OzKutKWl1+KmKIKoVe7ntGTl5GaxfK5ycvsjCmcH6PAGiJcY6VnzYT6LVeH3Fxe9JgDw+M0pMmV4namnAjbl1sjJDGlWt4rfvu0cc2J7wKBgQDI76y/c1lCayDZaL4qmWO+i4nREFpXegv8/JdpOswCOQ9O4gmUDh2cmM2ybd7z+4z7UJr9m+LElH94HFJeeG0VXrJuwYljVBF+c7OUGSABmEj1Lx9yVN4kEU+Bxb12VdrCMsi4vNtHHTs4iscnmCy5aJ3fMbLf8hGUEY7k6rnG3QKBgAYVcwc/cJzhzLCkFBPBN1sPUHeeea99OgoCUQOcs58wEb4HpaUfaUWhEzOrZ0mMzBPN/I/i96zvo2PMCJqtiY3YHzVL4Uh31mkShXmp1Mzk+euwe9XGZPjha4f8JKnxOZrjEv9dI05JJgcLwZ7QX2RoOM77dAbI7LE4H8NX6iafAoGAPgwGmCsJBO6H4zzPLMq8vvaf/rtZye+outb+FJryncrm8s30eGLkCA+xqJgFTnU6u0bAIFyceYQhKuWralYJIVyjF8NzE5gAHp8gE1tCpmkaC+W7lhnGCxCbz4R6rQJymLgFbYz3hWSV1PT8rrYfM+3/s4/GXvnMtoAu3WSEOhg=";
//

}
