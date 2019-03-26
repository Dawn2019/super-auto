package com.uuabc.run;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.uuabc.page.PageBo;
import com.uuabc.sql.ActionCodesMapper;
import com.uuabc.util.SystemUtil;
import com.uuabc.util.info;

public class demo {
	private static WebDriver driver;
	protected static WebDriverWait wait;
	
	
	@Before
	public void inital() {
		try {
			Properties props = new Properties();
			InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream("system.properties");
			props.load(in);
			System.setProperty(props.getProperty("driver"),props.getProperty("Add"));
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(props.getProperty("url_admin"));
			wait =new WebDriverWait(driver,10);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			action.add("AdminLogin");
			//进入老师列表
			action.add("ToTeacherList");
			//添加老师
			action.add("AddTeacherNew");
			//给老师签约
			action.add("AddTeacherContract");
			/**
			 * 
			 * Type2：添加试听学生
			 * */
//			后台登录
//			action.add("AdminLogin");
			//进入学生列表
//			action.add("ToStudent2List");
			//添加学生
//			action.add("AddStudent2New");
			
			
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
			
			
			
			
			for(int a = 0 ;a<action.size();a++) {
				String aaa= action.get(a);
				info = ActionCodesMapper.getActionCodes(aaa);
				String [] codes = info.getType().split(",");
				for(int nu = 0;nu<codes.length;nu++) {
					code.add(codes[nu]);
				}
			}
				for(int i=0;i<code.size();i++) {
					login(code.get(i));
				}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	@After
	
	public void detory() {
		System.out.println("执行结束");
//		driver.quit();
	}
	
	public String login(String code) {
		return PageFactory.initElements(driver, PageBo.class).specialCreate(code);
	}
	
	
	
}
