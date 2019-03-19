package com.uuabc.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
public class BasePage2 {
	
	public static boolean flag = true;
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	
	private static By getBy(String type, String webelement) {
		By by = null;
		if (type.equals("id")) {
			by = By.id(webelement);
		}
		if (type.equals("name")) {
			by = By.name(webelement);
		}
		if (type.equals("xpath")) {
			by = By.xpath(webelement);
		}
		if (type.equals("className")) {
			by = By.className(webelement);
		}
		if (type.equals("linkText")) {
			by = By.linkText(webelement);
		}
		return by;
	}
	
	/**
	 * 红色元素边框
	 * */
	public static void JQueryAddCss(WebElement wbElm) {
		((JavascriptExecutor) driver).executeScript(
				"$(arguments[0]).css('border','solid 2px red')", wbElm);
	}
	
	public static WebElement waitVisibilityAndMark(WebElement wbElm) {
		wait.until(ExpectedConditions.visibilityOf(wbElm));
//		logOpertation(wbElm);
		JQueryAddCss(wbElm);
		return wbElm;
	}

	public static WebElement waitClickableAndMark(WebElement wbElm) {
		wait.until(ExpectedConditions.elementToBeClickable(wbElm));
//		logOpertation(wbElm);
		JQueryAddCss(wbElm);
		return wbElm;
	}
	
	/** 鼠标悬停事件 */
	public static WebElement moveToElement(String value, String Type) {
		WebElement moveToElement = driver.findElement(getBy(Type, value));
		new Actions(driver).moveToElement(moveToElement).perform();
		return null;
	}
	
	/** 浮窗切换 */
	public static WebElement createEvent(String event, String value, String Type) {
		if (event != null && event.equals("switchTo")) {
			if (Type != null && value != null && Type.equals("frame")) {
				driver.switchTo().frame(value);
			} else if (Type != null && Type.equals("defaultContent")) {
				driver.switchTo().defaultContent();
			}
		} else if (Type != null && value != null) {
			WebElement frame = driver.findElement(getBy(Type, value));
			driver.switchTo().frame(frame);
		}
		return null;

	}

	
	public static WebElement Event(String event, String value, String Type,
			String webelement) throws InterruptedException {
		if (event != null && event.equals("sendKeys")) {
			try {
				Thread.currentThread().sleep(1000);
				waitVisibilityAndMark(driver.findElement
						(getBy(Type, webelement))).sendKeys(value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (event != null && event.equals("clear")) {
			waitClickableAndMark((WebElement)getBy(Type, webelement));
			driver.findElement(getBy(Type, webelement)).clear();
		}
		if (event != null && event.equals("click")) {
			Thread.currentThread().sleep(2000);
			waitClickableAndMark(driver.findElement(getBy(Type, webelement))).click();
			Thread.currentThread().sleep(2000);
		}
		if (event != null && event.equals("getAttribute")) {
			driver.findElement(getBy(Type, webelement)).getAttribute(
					value);
		}
		// webelement 的值为
		if (Type != null && Type.equals("JavascriptExecutor")) {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript(webelement);
			}

		}
		if (Type != null && Type.equals("JQuery")) {
			
			String js = "var sum=document.getElementById('loginname'); sum.value='" + value + "';";
			     ((JavascriptExecutor)driver).executeScript(js);
			
		}

		return null;

	}
	public BasePage2(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
	}
	@Deprecated
	public static void justWait(int seconds) throws InterruptedException {
		Thread.sleep(seconds);
	}
}
