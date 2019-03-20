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

public class frist {
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
			
			//后台登录
			action.add("AdminLogin");
			//进入老师列表
			action.add("ToTeacherList");
			//添加老师
			action.add("AddTeacherNew");
			//给老师签约
			action.add("AddTeacherContract");
			
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
	public void destory() {
		System.out.println("执行完成");
//		driver.quit();
	}
	
	
	
	public String login(String code) {
		return PageFactory.initElements(driver, PageBo.class).specialCreate(code);
	}

	public static File jpg() throws IOException {
		String dataString = getDateFormat();
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("D:\\ui_png\\ "+dataString+"s.png"));
			return srcFile;
	}
	
	/**  */
	private static String getDateFormat(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String dataString = sdf.format(date);
		return dataString;
	}
}
