package com.uuabc.util;

public class info {
	private String type;
	private String webelement;
	private String event;
	private String value;
	private String orderno;
	private String parameterValue;
	@Override
	public String toString() {
		return "info [type=" + type + ",webelement=" + webelement +",event = "+ event +",value =" + value + ",parameterValue="+parameterValue+"]";
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWebelement() {
		return webelement;
	}
	public void setWebelement(String webelement) {
		this.webelement = webelement;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	
}
