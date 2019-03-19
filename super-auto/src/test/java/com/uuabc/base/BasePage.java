package com.uuabc.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.uuabc.sql.UserNowInfo;
import com.uuabc.util.*;


public class BasePage {
	public static boolean flag =true;
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	
	public BasePage(WebDriver dirver) {
			this.driver = dirver;
		wait = new WebDriverWait(driver, 30);
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
//		DropList(wbElm);
		wait.until(ExpectedConditions.elementToBeClickable(wbElm));
		JQueryAddCss(wbElm);
		return wbElm;
	}
	
	/** 等待元素出现,判断元素是否可见 */
	public static WebElement waitVisibilityAndMark(WebElement wbElm) {
		wait.until(ExpectedConditions.visibilityOf(wbElm));
//		logOpertation(wbElm);
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
	
	
	/** frame切换事件  */
	public static WebElement frame(String event,String Type,String value) {
		if(event != null && event.equals("switchTo")) {
			if(Type != null && Type.equals("frame")) {
				driver.switchTo().frame(value);
			}else if(Type != null && Type.equals("defaultContent")) {
				driver.switchTo().defaultContent();
			}
		}else if(Type != null && value != null) {
			WebElement frame = driver.findElement(getBy(Type,value));
			driver.switchTo().frame(frame);
		}
		
		return null;
	}
	
	/** 跳转至当前句柄  */
	public static WebElement handles(String Type,String value,String event) {
        if(value != null  && event == "TheHandles") {
        	String handle = driver.getWindowHandle();
            // 获取所有页面的句柄，并循环判断不是当前的句柄 
            for (String handles : driver.getWindowHandles()) {  
                if (handles.equals(handle)) {
                driver.switchTo().window(handles);  
                }
            }
        }
        //根据value的值决定跳那个句柄
        if(value != null  && event == "OthersHandles") {
        	Set<String> windHandels = driver.getWindowHandles();
        	List<String> it =new ArrayList<String>(windHandels);
        	int wh = Integer.parseInt(value);
        	driver.switchTo().window(it.get(wh));
        }
		return null;
	}
	
	
	/** 封装用户基本事件操作*/
		public static WebElement Event(String event,String value,String Type,
				String webelement) throws InterruptedException {
			if(event != null && event.equals("sendKeys")) {
				try {
					Thread.currentThread().sleep(1000);
					UserBase ub =new UserBase();
					String paramter = ub.rdEmail();
					if(value.equals("rdEmail")) {
						UserNowInfo.updateEmail(paramter,UserBase.NowStringTime());
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(paramter);
					}else if(value.equals("writeEmail")) {
						info email = UserNowInfo.getValueByType("TeacherEmail");
						waitVisibilityAndMark(driver.findElement
								(getBy(Type, webelement))).sendKeys(value);
					}else {
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
		return null;
	}
		
}



