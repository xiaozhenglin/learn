package com.chehaha.api.wechat.xiao.custserv.pojo;

import java.io.Serializable;
import java.util.List;

import com.chehaha.api.wechat.xiao.api.WechatApiResult;
import com.chehaha.api.wechat.xiao.custserv.entity.CsvRecord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//聊天记录结果集
public class CsvRecordResult extends WechatApiResult implements Serializable{
	
	private static final long serialVersionUID = 8103639798079512130L;
	
	private List<CsvRecord> recordlist;

	public List<CsvRecord> getRecordlist() {
		return recordlist;
	}

	public void setRecordlist(List<CsvRecord> recordlist) {
		this.recordlist = recordlist;
	}

	public CsvRecordResult(List<CsvRecord> recordlist) {
		super();
		this.recordlist = recordlist;
	}

	public CsvRecordResult() {
		super();
	}

	@Override
	public boolean isSuccess() {
		return errorCode == null;
	}

	

}
