package com.chehaha.api.wechat.xiao.api.pojo.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WechatMenuButton implements Serializable {

	private static final long serialVersionUID = 5940515182617854151L;

	private String name;
	@JsonIgnore
	private WechatButtonType buttonType;
	private String type;
	private String key;
	@JsonProperty("sub_button")
	private List<WechatMenuButton> subButtons = new ArrayList<>();
	private String url;
	@JsonProperty("media_id")
	private String mediaId;
	@JsonProperty("appid")
	private String appId;
	@JsonProperty("pagepath")
	private String pagePath;

	public void addSubButton(WechatMenuButton button) {
		subButtons.add(button);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WechatButtonType getButtonType() {
		return buttonType;
	}

	public void setButtonType(WechatButtonType buttonType) {
		this.buttonType = buttonType;
		this.type = buttonType.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<WechatMenuButton> getSubButtons() {
		return subButtons;
	}

	public void setSubButtons(List<WechatMenuButton> subButtons) {
		this.subButtons = subButtons;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

}
