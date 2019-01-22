package com.chehaha.api.wechat.xiao.api.pojo.menu;


public enum WechatButtonType {

	Click("click"),
	View("view"),
	ScanCodePush("scancode_push"),
	ScanCodeWaitMessage("scancode_waitmsg"),
	PicForCamera("pic_sysphoto"),
	PicForAlbum("pic_photo_or_album"),
	PicForWeiXin("pic_weixin"),
	Position("location_select"),
	Media("media_id"),
	ViewLimited("view_limited");
	
	
	private String value;
	
	WechatButtonType(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
