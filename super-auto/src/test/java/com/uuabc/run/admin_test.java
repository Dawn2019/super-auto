package com.uuabc.run;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
		driver.findElement(By.xpath("/html/body/div/form/div[2]/button/i")).click();
		driver.findElement(By.cssSelector("#left-title > li:nth-child(7)")).click();
		driver.findElement(By.cssSelector("#left-title > li.menu-list.nav-active > ul > li:nth-child(1) > a")).click();
		
		//切换浮窗
//		driver.switchTo().frame(1);
		driver.switchTo().frame(0);
		
		
		//添加车次
		driver.findElement(By.cssSelector("#app > div > div:nth-child(1) > button")).click();
		driver.findElement(By.cssSelector("#app > div > div:nth-child(9) > div > div.el-dialog__body > form > div:nth-child(1) > div > div > input")).sendKeys("test");;
		
//		driver.findElement(By.cssSelector("#left-title > li:nth-child(4)")).click();
//		driver.findElement(By.linkText("老师列表2")).click();
		//更多操作
//		driver.findElement(By.cssSelector("body > section > div.main-content > div.wrapper > div > div > section > div > div > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(10) > div")).click();
//		//小幅度下拉
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
//				driver.findElement(By.cssSelector("body > section > div.main-content > div.wrapper > div > div > section > div > div > table > tbody > tr:nth-child(1) > td:nth-child(14) > div > ul > li:nth-child(4) > a")));
//		//签约
//		driver.findElement(By.cssSelector("body > section > div.main-content > div.wrapper > div > div > section > div > div > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(10) > div > ul > li:nth-child(4)")).click();
//		
//		新增签约
//		System.out.println("切换句柄前的url："+driver.getCurrentUrl());
//		Set<String> winHandels=driver.getWindowHandles();// 得到当前窗口的set集合
//        List<String> it = new ArrayList<String>(winHandels); // 将set集合存入list对象
//        driver.switchTo().window(it.get(1));// 切换到弹出的新窗口
//	
//		System.out.println("切换句柄后的url："+driver.getCurrentUrl());
//		System.out.println(driver.getWindowHandle());
//		driver.findElement(By.cssSelector("#info-2 > div > a")).click();
//        driver.switchTo().window(it.get(0));// 返回至原页面

	}
	
	@After
	
	public void detory() {
		System.out.println("执行结束");
//		driver.quit();
	}
}
