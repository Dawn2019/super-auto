package com.uuabc.run;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.uuabc.util.SystemUtil;

public class admin_test {
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

//			Cookie SessionId = new Cookie("JSESSIONID","a313328cf0f69fb8");
//			Cookie PhpSessionId = new Cookie("PHPSESSID","1mf4d61dp3skbvp0pe8vi2r0n1");
//			Cookie ADMIN_ID = new Cookie("UUENGLISH[ADMIN_ID]","561");
//			Cookie ADMIN_PASSWORD = new Cookie("UUENGLISH[ADMIN_PASSWORD]","e10adc3949ba59abbe56e057f20f883e");
//			Cookie ADMIN_PWD = new Cookie("UUENGLISH[ADMIN_PWD]","943566d5a8f484d2235c6b7bd0d406f2");
//			driver.manage().addCookie(SessionId);
//			driver.manage().addCookie(PhpSessionId);
//			driver.manage().addCookie(ADMIN_ID);
//			driver.manage().addCookie(ADMIN_PASSWORD);
//			driver.manage().addCookie(ADMIN_PASSWORD);
//			Set<Cookie> coo = driver.manage().getCookies();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void run() throws InterruptedException {
		driver.findElement(By.name("user_name")).sendKeys("zhangchenglin");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.cssSelector("body > div > form > div.login-wrap > button")).click();
	}
	
	@After
	
	public void detory() {
		System.out.println("执行结束");
//		driver.quit();
	}
}
