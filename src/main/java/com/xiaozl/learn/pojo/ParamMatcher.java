package com.xiaozl.learn.pojo;

public class ParamMatcher {
	
    private  MatcheType  type;
    private String value;

	public ParamMatcher() {
		super();
	}

	public ParamMatcher(MatcheType type, String value) {
		super();
		this.type = type;
		this.value = value;
	}

	public MatcheType getType() {
		return type;
	}

	public void setType(MatcheType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
