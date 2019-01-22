package com.chehaha.api.wechat.xiao.custserv.pojo.activeresponse;

public class WechatMessage {
		//主动回复 转json后提交用
		private  String touser;
		private  String msgtype;
		private  Text text;
		
		public WechatMessage(String touser, String msgtype, Text text) {
			super();
			this.touser = touser;
			this.msgtype = msgtype;
			this.text = text;
		}

		public String gettouser() {
			return touser;
		}

		public void settouser(String touser) {
			this.touser = touser;
		}

		public String getMsgtype() {
			return msgtype;
		}

		public void setMsgtype(String msgtype) {
			this.msgtype = msgtype;
		}
		
		public Text getText()  {
			return text;
		}

		public void setText(Text text) {
			this.text = text;
		}
		public WechatMessage() {
			super();
		}
}
