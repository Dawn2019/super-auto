package com.uuabc.run;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*; 
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import java.text.SimpleDateFormat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io .FileUtils;

import com.uuabc.base.BasePage2;
import com.uuabc.page.PageBo1;
import com.uuabc.sql.ActionCodesMapper;
//import com.uuabc.sql.ElementMapper;
import com.uuabc.util.SystemUtil;
import com.uuabc.util.info 

;

public class demo1 {
	private static WebDriver driver;
	private String url;
	private String Add = null;
	private String var = null;
//	private List  code= null;
	
	/** 初始化加载 */
	@Before
	public void initial() {
		try {
		Properties props = new Properties();
		InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream("system.properties");
		props.load(in);
		System.setProperty(props.getProperty("driver"),props.getProperty("Add"));
		driver = new ChromeDriver();
//		if (props.getProperty("driver").indexOf("chrome")!=-1){
//		driver = new ChromeDriver();
//		}else if(props.getProperty("driver").indexOf("firefox")!=-1){
//			driver = new GoogleChromeDriver();
//		}else {
//			driver = new IEDriver();
//		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(props.getProperty("url_admin"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 执行 */
	@Test
	public void  run() {
		info info =new info();
		List<String> action = new ArrayList<String>();
		List<String> code = new ArrayList<String>();
		 try {
			 //学生登录
			 action.add("AdminLogin");

		
			 for (int a = 0 ; a<action.size();a++) {
				 String aaa = action.get(a);
				 info = ActionCodesMapper.getActionCodes(aaa);
				 String [] codes = info.getType().split(",");
				 for (int nu = 0 ; nu<codes.length;nu++) {
					 code.add(codes[nu]);
				 }
			 }
				for(int i = 0 ; i<code.size();i++){
					login(code.get(i));
				}
		}
		 catch (Exception e) {
			// TODO: handle exception
		 }
	}
	
	@After
	public void destory() {
		System.out.println("执行结束");
//		driver.quit();
		
	}
	
	
	public String login(String code) {
		return PageFactory.initElements(driver, PageBo1.class).specialCreate(code);
	}
	
	public static File jpg () throws IOException{
		String dataString = getDateFormat();
		 File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("C:\\drivers\\ui_png\\"+dataString+"a.png"));
		return srcFile;
		
	}


	private static String getDateFormat() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateString = sdf.format(date);
		// TODO Auto-generated method stub
		return dateString;
	}

	
}
