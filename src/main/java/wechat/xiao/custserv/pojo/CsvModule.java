/*package com.chehaha.wechat.custserv.pojo;

import com.clic.system.pojo.Operation;

public enum CsvModule implements Operation{
	
	CsvAdmin("客服后台");
	public enum CsvAdminOperation implements Operation{
		RemoveRecord("删除聊天记录"), CreateCsv("添加客服坐席"), RemoveCsv("删除客服坐席");
		
		private String data; 
		private CsvAdminOperation(String data) {
			this.data = data;
		}
		@Override
		public String getTrailData() {
			return data;
		}
	}
	
	private String data;
	private CsvModule(String data) {
		this.data = data;
	}
	@Override
	public String getTrailData() {
		// TODO Auto-generated method stub
		return data;
	}

}
*/