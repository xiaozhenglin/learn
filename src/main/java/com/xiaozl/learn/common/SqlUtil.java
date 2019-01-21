package com.xiaozl.learn.common;

public class SqlUtil {
	public static String addRowId(String sql) {
		return "SELECT (@i\\:=@i+1) AS ID,T.* FROM ("+sql+") T,(SELECT @i\\:=0) AS it";
	}
	
}
