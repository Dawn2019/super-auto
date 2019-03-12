package com.uuabc.run;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.uuabc.util.SystemUtil;

public class home_test {
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
			driver.get(props.getProperty("url_home"));
			wait =new WebDriverWait(driver,10);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void run() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"page-header\"]/div[2]/a[1]")).click();
		driver.findElement(By.id("username")).sendKeys("12012000000");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id=\"btn-normal-login\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"topheader\"]/div[1]/div/div/button[1]")).click();
		driver.findElement(By.xpath("/html/body")).click();
		driver.findElement(By.cssSelector("#homepage > div.record > div.empty > div > div.ziXun > a")).click();
		
		//回到根节点
//		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.className("easemobim-chat-panel")));
//		driver.switchTo().frame(driver.findElement(By.id("cross-origin-iframe")));
		//输入111
		driver.findElement(By.xpath("//*[@id=\"em-kefu-webim-chat\"]/div[4]/textarea")).sendKeys("测试数据");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"em-kefu-webim-chat\"]/div[4]/span")).click();
		//最小化聊天框
		driver.findElement(By.xpath("//*[@id=\"em-kefu-webim-chat\"]/div[1]/i[1]")).click();
	}
	
	
	@After
	
	public void detory() {
		System.out.println("执行结束");
//		driver.quit();
	}
}
