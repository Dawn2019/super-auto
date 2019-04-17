package com.uuabc.run;

//import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.uuabc.base.BasePage;
import com.uuabc.page.ActivityPage;

public class Activity {
		private static WebDriver driver;
		private String url;
		
		/** 信息数据初始化 **/
		@Before
		public void initial() {
			System.setProperty("webdriver.chrome.driver",
					"C:/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			url = "https://qa-home.uuabc.com/activity/zhstd?activity_id=40";
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		}
		
		@Test
		public void run001() throws IOException {
			
			try {
				// 领取活动
				join_activity1();
				waitUtilSucceed();
			}catch (Exception e) {
				File files = null;
				files = BasePage.jpg();
				System.out.println("截图成功");
				System.out.println(e);
			}
		}
		
		@After
		public void destory() {
			System.out.println("退出关闭");
			driver.quit();
		}
		
		/**
		 * 
		 * 
		 * */
		public void join_activity1() {
			PageFactory.initElements(driver, ActivityPage.class).join_activity1();
		}
		
		
		public void waitUtilSucceed() {
			PageFactory.initElements(driver, ActivityPage.class).waitUtilSucceed();
		}

}
