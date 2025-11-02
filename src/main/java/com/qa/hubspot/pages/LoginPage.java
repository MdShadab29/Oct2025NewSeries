package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

/**
 * 
 * @author Md Shadab
 *
 */

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
//define locators /OR- with By locator (NPF-non Page Factory pattern):

	By emailId = By.name("username");
	By password = By.name("password");
//	By loginButton = By.id("loginBtn");
	By loginButton = By.xpath("//input[@value='Login']");
	
	//constructor of page class
	public LoginPage(WebDriver driver) {	
		this.driver = driver;	
	elementUtil = new ElementUtil(driver);
	}
	// page actions:
	public String getLoginPageTitle() {	
		return elementUtil.waitForPageTitle(Constants.LOGIN_PAGE_TITLE);	
	}	
	public HomePage doLogin(String username, String pwd) {
		elementUtil.doSendKeys(emailId, username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);

//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		return new HomePage(driver);
		
	}

}
