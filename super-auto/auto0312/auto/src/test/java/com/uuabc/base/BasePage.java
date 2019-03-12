package com.uuabc.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.JavascriptExecutor;




public class BasePage {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	
	public BasePage(WebDriver dirver) {
		wait =new WebDriverWait(driver, 60);
	}
	
	
	/** 增加红色元素边框 */
	public static void JQueryAddCss(WebElement wbElm) {
		((JavascriptExecutor) driver).executeScript(
				"$(atguments[0].css('border','solid 2px red'))", wbElm);
	}
}



