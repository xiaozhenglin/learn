package com.chehaha.api.wechat.xiao.custserv.pojo;

import com.chehaha.api.wechat.xiao.api.WechatConfig;

public class CopyMessageToKefu {
		//复制消息给客服 ,生成xmL字符串后提交给服务器
		 private String ToUserName;   

		 private String FromUserName;  

		 private long CreateTime;  
		     
		 private String MsgType;
		 

		 public String getToUserName() {
			return ToUserName;
		}

			public void setToUserName(String toUserName) {
				ToUserName = toUserName;
			}

			public String getFromUserName() {
				return FromUserName;
			}

			public void setFromUserName(String fromUserName) {
				FromUserName = fromUserName;
			}

			public long getCreateTime() {
				return CreateTime;
			}

			public void setCreateTime(long createTime) {
				CreateTime = createTime;
			}

			public String getMsgType() {
				return MsgType;
			}

			public void setMsgType(String msgType) {
				MsgType = msgType;
			}

			public CopyMessageToKefu(String toUserName, String fromUserName, long createTime, String msgType) {
				super();
				ToUserName = toUserName;
				FromUserName = fromUserName;
				CreateTime = createTime;
				MsgType = msgType;
			}

			public CopyMessageToKefu() {
				super();
				// TODO Auto-generated constructor stub
			}

			@Override
			public String toString() {
				return "Kefu [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
						+ ", MsgType=" + MsgType + "]";
			}  
		    
			//消息转发给指定客服
			public String setXml(String uid,long time,String kefuAccount ) {
				String str=" <xml>  " + 
						"     <ToUserName>"+uid+"</ToUserName>  " + 
						"     <FromUserName>"+WechatConfig.getWechatId()+"</FromUserName>  " + 
						"     <CreateTime>"+time+"</CreateTime>  " + 
						"     <MsgType>transfer_customer_service</MsgType>  " + 
						"     <TransInfo>  " + 
						"         <KfAccount>"+kefuAccount+"</KfAccount>  " + 
						"     </TransInfo>  " + 
						" </xml>";
				return str;
			}
			
			//随机转给随机的客服
			public String setSecondXml(String uid,long time) {
				String str="<xml>  " + 
						"     <ToUserName>"+uid+"</ToUserName>  " + 
						"     <FromUserName>"+WechatConfig.getWechatId()+"</FromUserName>  " + 
						"     <CreateTime>"+time+"</CreateTime>  " + 
						"     <MsgType>transfer_customer_service</MsgType>  " + 
						" </xml>";	
				return str;		
			}
}
