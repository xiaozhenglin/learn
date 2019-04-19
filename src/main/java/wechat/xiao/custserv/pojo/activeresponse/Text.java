package com.chehaha.api.wechat.xiao.custserv.pojo.activeresponse;

//主动回复用的Text转pojo，然后提交
public class Text {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Text(String content) {
		this.content = content;
	}

	public Text() {
		super();
	}	
	
}
