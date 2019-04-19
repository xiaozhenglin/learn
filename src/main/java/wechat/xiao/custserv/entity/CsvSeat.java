package com.chehaha.api.wechat.xiao.custserv.entity;

import java.awt.SecondaryLoop;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chehaha.api.wechat.xiao.custserv.pojo.CsvStatus;

@Entity
@Table(name="T_CSV_SEAT")
public class CsvSeat implements Serializable{    //客服人员信息
	private static final long serialVersionUID = 6504054271425361171L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ACCOUNT")
	private String kfAccount;
	
	@Column(name="NAME")
	private String nickName;
	
	@Column(name="OPEN_ID")
	private String openId;
	
	@Column(name="STATUS")
	@Enumerated
	private CsvStatus status;

	@Column(name="WECHAT_CODE")
	private String code;
	
	@Column(name="REG_TIME")
	private Date regTime;
	
	@Column(name="AUDIT_TIME")
	private Date AuditTime;
	
	public String getKfAccount() {
		return kfAccount;
	}

	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public CsvStatus getStatus() {
		return status;
	}

	public void setStatus(CsvStatus status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getAuditTime() {
		return AuditTime;
	}

	public void setAuditTime(Date auditTime) {
		AuditTime = auditTime;
	}
	
	
}
