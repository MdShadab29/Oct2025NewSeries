package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.util.ElementUtilPF;

public class LoginPagePF extends BasePage {
	
	WebDriver driver;
	ElementUtilPF elementUtilPF;
	//PF pattern: this is called Page Factory pattern with the help of @FindBy
	@FindBy(name ="username")
	WebElement emailId;
	
	@FindBy(name ="password")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;
	
	public LoginPagePF(WebDriver driver) {
		this.driver = driver;
//		PageFactory.initElements(driver, LoginPagePF.class);
		PageFactory.initElements(driver, this);	
		elementUtilPF = new ElementUtilPF(driver);
	}
    public void doLogin(String username,String pwd) {
    	elementUtilPF.waitForElementVisible(emailId, 10);
    	emailId.sendKeys(username);
    	password.sendKeys(pwd);
    	loginButton.click();
    	
    	
    }
}
