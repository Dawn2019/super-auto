package com.uuabc.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserBase {
	
	
	//生成随机五位字符串
	public String numberString() {
		String numberString = Integer.toString((int)((Math.random()*9+1)*10000));
		return numberString;
	}
	
	//生成随机邮箱
	public String rdEmail() {
		String rdEmail = "9992019"+numberString()+"@test.com";
		return rdEmail;
	}
	
	//生成随机手机号
	public String rdPhone() {
		String rdPhone = "1201900"+numberString();
		return rdPhone;
	}
	
	//当前时间
	public static String NowStringTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String NowString = df.format(new Date());// new Date()为获取当前系统时间
		return NowString;
	}
	
	//当前时间戳，10位
	public static String StringTenTime(){
		String StringTenTime = String.valueOf(new Date().getTime()).substring(0,10);
		return StringTenTime;
	}
}
