package com.uuabc.page;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.uuabc.util.UserUtil;

import com.uuabc.base.BasePage;

public class ActivityPage extends BasePage{

	public ActivityPage(WebDriver driver) {
		super(driver);
	}

	/** 手机号 **/
	@FindBy(how = How.NAME, name = "telephone")
	protected WebElement phone;

	/** 图形验证码 **/
	@FindBy(how = How.NAME, name = "yan")
	protected WebElement image_code;

	/** 验证码 **/
	@FindBy(how = How.NAME, name = "yzm")
	protected WebElement verification_code;
	
	/** 推荐人 **/
	@FindBy(how = How.ID, id = "recommend")
	protected WebElement recommend_phone;
	
	/** 学生姓名 **/
	@FindBy(how = How.ID, id = "recommend")
	protected WebElement student_name;

	/** 家长姓名 **/
	@FindBy(how = How.ID, id = "parent_name")
	protected WebElement parent_name;

	/** 学生地址**/
	@FindBy(how = How.ID, id = "parent_name")
	protected WebElement address;

	/** 领取 **/
	@FindBy(how = How.XPATH, xpath = "/html/body/div/div[1]/button")
	protected WebElement get;
	
	
	/** 通过文本检查是否领取成功 **/
	@FindBy(how = How.XPATH, xpath = "/html/body/div/div[4]/section[2]/div/div/h1")
	protected WebElement success;
	
	public void waitUtilSucceed() {
		wait.until(ExpectedConditions.textToBePresentInElement(success, "领取成功"));
	}
	
	//活动模板
	/**
	 * Type 1: 手机号+图形验证码+验证码+推荐人手机号          
	 * 
	 */
	public String join_activity1() {
		log.info("**********************************");
		UserUtil uu = new UserUtil();
		String activity_phone =uu.rdPhone();
		
		//手机号
		waitVisibilityAndMark(phone).sendKeys(activity_phone);
		this.JQueryAddCss(phone);
		
		//图形验证码
		waitVisibilityAndMark(image_code).sendKeys("1234");
		this.JQueryAddCss(image_code);
		
		//验证码
		waitVisibilityAndMark(verification_code).sendKeys("201904");
		this.JQueryAddCss(verification_code);
		
		//推荐人手机号
//		waitVisibilityAndMark(recommend_phone).sendKeys("17320194131");
//		this.JQueryAddCss(recommend_phone);
		
		//领取
		waitVisibilityAndMark(get).click();
		this.JQueryAddCss(get);

		return null;
	}
}
