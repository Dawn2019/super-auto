package com.uuabc.page;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

import com.uuabc.run.frist;
import com.uuabc.base.BasePage;
import com.uuabc.sql.EventMapper;
import com.uuabc.util.info;

public class PageBo extends BasePage {
	
	public PageBo(WebDriver driver) {
		super(driver);
	}

	public String specialCreate(String code , int...values ) {
		info info = new info();
		try {
			if(code.indexOf("moveToElement") != -1) {	//是否为鼠标悬停事件
				info = EventMapper.getTypeByCode(code);
				String Type = info.getType();
				String value = info.getValue();
				BasePage.moveToElement(Type,value);
			}else if(code.indexOf("frame") != -1) {		//是否为iframe浮窗切换事件
				info = EventMapper.getTypeByCode(code);
				String value =info.getValue();
				BasePage.frame(value);
			}
//			else if(code.indexOf("db") != -1) {		//进行写入操作时是否需要使用数据库
//				info = EventMapper.getTypeByCode(code);
//				String event = info.getEvent();
//				String value =info.getValue();
//				BasePage.handles(event,value);
//			}
			else if(code.indexOf("handles") != -1) {	//是否要切换句柄
				info = EventMapper.getTypeByCode(code);
				String event = info.getEvent();
				String value =info.getValue();
				BasePage.handles(event,value);
			}else {
				info = EventMapper.getTypeByCode(code);
				String Webelement = info.getWebelement();
				String Type = info.getType();
				String event = info.getEvent();
				String value = info.getValue();
				BasePage.Event(event, value, Type, Webelement);
			}
		}catch (Exception e) {
			log.error("出现错误——————————"+e);
			File files = null;
			try {
//				fail();
				files = frist.jpg();
				log.error("截图成功"+e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("出现异常");
		}
		return null;
	}
}
