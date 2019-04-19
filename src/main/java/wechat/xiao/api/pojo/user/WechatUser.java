package com.chehaha.api.wechat.xiao.api.pojo.user;

import java.util.List;

import com.chehaha.api.wechat.xiao.api.WechatApiResult;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WechatUser extends WechatApiResult {

	private static final long serialVersionUID = 3650015522154667894L;

	@JsonProperty("openid")
	private String openId;
	@JsonProperty("nickname")
	private String nickName;
	private int sex;
	private String country;
	private String province;
	private String city;
	@JsonProperty("headimgurl")
	private String avatar;
	private List<String> privilege;
	@JsonProperty("unionid")
	private String unionId;

	@Override
	public boolean isSuccess() {
		return errorCode == null;
	}
	
	public WechatUser(String openId, String nickName, int sex, String country, String province, String city,
			String avatar, List<String> privilege, String unionId) {
		super();
		this.openId = openId;
		this.nickName = nickName;
		this.sex = sex;
		this.country = country;
		this.province = province;
		this.city = city;
		this.avatar = avatar;
		this.privilege = privilege;
		this.unionId = unionId;
	}

	public WechatUser() {
		super();
	}


	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
