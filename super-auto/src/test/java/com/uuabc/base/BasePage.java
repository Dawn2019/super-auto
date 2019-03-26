package com.uuabc.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uuabc.sql.EventParamterMapper;
import com.uuabc.util.UserUtil;
import com.uuabc.util.info;


public class BasePage {
	protected static WebDriver driver;
	protected static WebDriverWait wait;

	public BasePage(WebDriver dirver) {
			this.driver = dirver;
		wait = new WebDriverWait(driver, 30);
	}
	
	/** 日志 */
	public static List<Error> errors = new ArrayList<Error>();
	public static Log log;
	static {
		Properties props = new Properties();
		try {
			props.load(BasePage.class.getResourceAsStream("/log4j.properties"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		log = LogFactory.getLog(BasePage.class);
	}
	
	/** 定义常用定位方式 */
	private static By getBy(String type,String webelement) {
		By by = null;
		if(type.equals("id")) {
			by = By.id(webelement);
		}
		if(type.equals("css")) {
			by = By.cssSelector(webelement);
		}
		if(type.equals("name")) {
			by = By.name(webelement);
		}
		if(type.equals("xpath")) {
			by = By.xpath(webelement);
		}
		if(type.equals("className")) {
			by = By.className(webelement);
		}
		if(type.equals("linktext")) {
			by = By.linkText(webelement);
		}
		return by;
	}
	
	/** 增加红色元素边框 */
	public static void JQueryAddCss(WebElement wbElm) {
		((JavascriptExecutor) driver).executeScript(
				"$(arguments[0]).css('border','solid 2px red')", wbElm);
	}
	
	/** 等待元素出现,判断是否可以点击  */
	public static WebElement waitClickAndMark(WebElement wbElm) {
		logOpertation(wbElm);
		wait.until(ExpectedConditions.elementToBeClickable(wbElm));
		JQueryAddCss(wbElm);
		return wbElm;
	}
	
	/** 等待元素出现,判断元素是否可见 */
	public static WebElement waitVisibilityAndMark(WebElement wbElm) {
		wait.until(ExpectedConditions.visibilityOf(wbElm));
		logOpertation(wbElm);
		JQueryAddCss(wbElm);
		return wbElm;
	}
	
	/** 鼠标移动事件 */
	public static WebElement moveToElement(String value,String Type) {
		WebElement moveToElement = driver.findElement(getBy(Type,value));
		new Actions(driver).moveToElement(moveToElement).perform();
		return null;
	}
	
	
	/** 下拉直到出现某元素 */
	public static void DropList(WebElement wbElm) {
		((JavascriptExecutor) driver).executeScript(
				"return arguments[0].scrollIntoView();", wbElm);
		
	}
	
	
	/** 
	 * frame切换事件
	 * @author Dawn
	 */
	public static WebElement frame(String value) {
		if(value != null && value.equals("defaultContent")) {
			driver.switchTo().defaultContent();
		}else{
			driver.switchTo().frame(Integer.parseInt(value));
		}
		return null;
	}
	
	
	/**
	 * 跳转至当前句柄 
	 * @author Dawn
	 */
	public static WebElement handles(String event,String value) {
		if(value != null) {
		Set<String> winHandels=driver.getWindowHandles();// 得到当前窗口的set集合
        List<String> it = new ArrayList<String>(winHandels); // 将set集合存入list对象
        driver.switchTo().window(it.get(Integer.parseInt(value)));// 切换到哪个窗口
		}
        return null;
        
	}
	
	
	/** 封装用户基本事件操作*/
		public static WebElement Event(String event,String value,String Type,
				String webelement) throws InterruptedException {
			if(event != null && event.equals("sendKeys")) {
				try {
					Thread.currentThread().sleep(1000);
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(event != null && event.equals("click")) {
				//需要下拉到底部进行点击
				if(value.equals("drop_list")) {
					 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				}
				waitClickAndMark(driver.findElement(getBy(Type,webelement))).click();
			}
			
			if(event != null && event.equals("clear")) {
				waitClickAndMark(driver.findElement(getBy(Type,webelement))).clear();
			}
			/**
			 * @author Dawn
			 *	拓展:若生成/填写随机参数(phone，email等),使用该方式,正在开发中...
			 * */
			
			if(event != null && event.equals("sendKeysParamter")) {
				info info = new info();
				UserUtil ub =new UserUtil();
				String var = value.substring(0,5);
				/*1.判断是否为创建事件
				 *2.判断创建事件的值是手机号、邮箱还是其他(value前缀：create_phone/create_email)
				 */
				if(var.equals("creat")) {
					if(value.indexOf("phone") != -1) {
						//生成随机手机号
						String rdPhone = ub.rdPhone();
						EventParamterMapper.updateParameter("write_"+value,rdPhone,UserUtil.NowStringTime());
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(rdPhone);
					}else if(value.indexOf("email") != -1) {
						//生成随机邮箱
						String rdEmail = ub.rdEmail();
						EventParamterMapper.updateParameter("write_"+value,rdEmail,UserUtil.NowStringTime());
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(rdEmail);
					}
				}
				
				//1.判断是否为写入事件,若是写入值(value前缀：write_)
				if(var.equals("write")) {
					info = EventParamterMapper.getValueByType(value);
					String ParameterValue = info.getParameterValue();
					waitVisibilityAndMark(driver.findElement
							(getBy(Type, webelement))).sendKeys(ParameterValue);
				}
			}
		return null;
	}
		
		
		/**
		 * 写入日志信息
		 * 
		 * */
		private static void logOpertation(WebElement wbElm) {
			String text = wbElm.getText();
			String placeholder = wbElm.getAttribute("placeholder");
			String title = wbElm.getAttribute("title");
			if (text != null && !text.isEmpty()) {
				log.info("操作：" + text + "");
			}else if (placeholder != null && !placeholder.isEmpty()){
				log.info("操作：" + placeholder + "");
			}else if (title != null && !title.isEmpty()){
				log.info("操作：" + title + "");
			}else {
				log.info("未找到该元素");
			}
			
		}
		
		/**
		 * 错误截图
		 * */
		public static File jpg() throws IOException {
			String dataString = getDateFormat();
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File("C:\\ui_png\\ "+dataString+"s.png"));
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



