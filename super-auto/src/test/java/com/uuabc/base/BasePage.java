package com.uuabc.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	/**
	 * 设置最大等待时长30s
	 * */
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
//		JQueryAddCss(wbElm);
		return wbElm;
	}
	
	/** 等待元素出现,判断元素是否可见 */
	public static WebElement waitVisibilityAndMark(WebElement wbElm) {
		wait.until(ExpectedConditions.visibilityOf(wbElm));
		logOpertation(wbElm);
//		JQueryAddCss(wbElm);
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
					info info = new info();
					UserUtil ub =new UserUtil();
					String NewEmail = ub.rdEmail();
					String NewPhone = ub.rdPhone();
					if(value.equals("rdEmail")) {
						EventParamterMapper.updateEmail(NewEmail,UserUtil.NowStringTime());
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(NewEmail);
					}else if(value.equals("writeEmail")) {
						info = EventParamterMapper.getValueByType("TeacherEmail");
						String createdEmail = info.getParameterValue();
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(createdEmail);
					}else if(value.equals("rdPhone")) {
						EventParamterMapper.updatephone(NewPhone,UserUtil.NowStringTime());
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(NewPhone);
					}else if(value.equals("writePhone")){
						info = EventParamterMapper.getValueByType("StudentPhone");
						String StudentPhone = info.getParameterValue();
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(StudentPhone);
					}else{
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(value);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(event != null && event.equals("click")) {
				if(value.equals("dropList")) {
					 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
				}
				waitClickAndMark(driver.findElement(getBy(Type,webelement))).click();
			}
			
			if(event != null && event.equals("clear")) {
				waitClickAndMark(driver.findElement(getBy(Type,webelement))).clear();
			}
			//拓展:若生成/填写随机参数(phone，email等),使用该方式,正在开发中...
			if(event != null && event.equals("sendKeysParamter")) {
				waitVisibilityAndMark(driver.findElement
						(getBy(Type, webelement))).sendKeys(value);
			}
		return null;
	}
		
		
		/**
		 * 写入日志信息
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
		
}



