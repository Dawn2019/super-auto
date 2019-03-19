package com.uuabc.page;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.uuabc.base.BasePage2;
import com.uuabc.run.demo1;
import com.uuabc.sql.EventMapper;
//import com.uuabc.sql.returnValueMapper;
//import com.uuabc.sql.windowEventMapper;
import com.uuabc.util.JdbcUtil;
import com.uuabc.util.info;

public class PageBo1 extends BasePage2 {

	public PageBo1(WebDriver driver) {
		super(driver);
	}
	/**
	 * @author 王卫嘉
	 * @throws IOException 
	 * 
	 * 
	 */
	public String specialCreate(String code , int...values) {
//		log.info 
		info info =new info();
		String orderno="";
		try {
			//是否为鼠标悬停事件
		if(code.indexOf("moveToElement")!=-1){
//			 info = windowEventMapper.getEventtByCode(code);
				String Type = info.getType();
				String value = info.getValue();
			BasePage2.moveToElement(value, Type);
		}
		 else if (code.indexOf("frame")!=-1){
				//是否为frame事件
//			 info = windowEventMapper.getEventtByCode(code);
				String Type = info.getType();
				String event = info.getEvent();
				String value = info.getValue();
//				BasePage.createEvent(event, value, Type);
				}
		else {
			info = EventMapper.getTypeByCode(code);
				String Webelement = info.getWebelement();
				String Type = info.getType();
				String event = info.getEvent();
				String value = info.getValue();
				BasePage2.Event(event, value, Type, Webelement);
		}
		} catch (Exception e) {
//			log.error("注意错误————————————————" + e);
			System.out.println("出现错误...");
			File aaa= null;
			 try {
				 fail();
				aaa = demo1.jpg();
				System.out.println("错误截图成功");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 System.out.print(aaa);
		}
		return null;
	}
}