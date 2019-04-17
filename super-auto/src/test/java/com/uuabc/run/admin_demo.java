package com.uuabc.run;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
//import org.testng.annotations.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.uuabc.base.BasePage;
import com.uuabc.page.PageBo;
import com.uuabc.sql.ActionCodesMapper;
import com.uuabc.util.SystemUtil;
import com.uuabc.util.info;

public class admin_demo {
	private static WebDriver driver;
	private String url_admin;
	private String Add = null;
	
	@Before
	public void initial() {
		try {
			Properties props =new Properties();
			InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream("system.properties");
			props.load(in);
			System.setProperty(props.getProperty("driver"),props.getProperty("Add"));
			driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(props.getProperty("url_admin"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void run001() {
		info info = new info();
		List<String> action = new ArrayList<String>();
		List<String> code = new ArrayList<String>();
		try {
			/**
			 * 
			 * Type1：添加老师
			 * */
//			//后台登录
//			action.add("AdminLogin");
//			//进入老师列表
//			action.add("ToTeacherList");
//			//添加老师
//			action.add("AddTeacherNew");
//			//给老师签约
//			action.add("AddTeacherContract");
			
			/**
			 * 
			 * Type2：添加试听学生
			 * */
			//后台登录
			action.add("AdminLogin");
			//进入学生列表
			action.add("ToStudent2List");
			//添加学生
//			action.add("AddStudent2New");
			//约老班课
			action.add("OldClass");
			
			
			/**
			 * Type3: 添加车次
			 * 
			 * */
//			//后台登录
//			action.add("AdminLogin");
//			//进入车次管理
//			action.add("ToTrainList");
//			//添加车次
//			action.add("AddTrain");
			
			
			
			
			for(int a = 0 ;a<action.size();a++) {	//获取action集合中的codes值，将codes值通过逗号进行截取，存放到 codes数组中
				String aaa= action.get(a);
				info = ActionCodesMapper.getActionCodes(aaa);
				String [] codes = info.getType().split(",");
				for(int nu = 0;nu<codes.length;nu++) {	//遍历codes数组将codes数组的值存入到code集合中
					code.add(codes[nu]);
				}
			}
				for(int i=0;i<code.size();i++) {	//遍历code集合
					login(code.get(i));
				}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@After
	public void destory() {
		System.out.println("执行完成");
//		driver.quit();
	}
	
	
	
	public String login(String code) {
		return PageFactory.initElements(driver, PageBo.class).specialCreate(code);
	}

}
