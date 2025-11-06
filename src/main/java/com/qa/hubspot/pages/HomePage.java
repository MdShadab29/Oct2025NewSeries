package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;
/**
 * 
 * @author Md Shadab
 *
 */

public class HomePage extends BasePage {
	
	
	WebDriver driver;
	ElementUtil elementUtil;
	By header = By.className("logo_text");
	By accountName = By.xpath("//td[contains(text(),'User:')]");;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}
	public String getHomePageTitle() {
		return elementUtil.waitForPageTitle(Constants.HOME_PAGE_TITLE);	
//		return driver.getTitle();	
		}
//	public String getHomePageHeaderValue() {
//		return driver.findElement(header).getText();	
//	}
	
	public String getHomePageHeaderValue() {
	    // switch to the correct frame
	    driver.switchTo().frame("mainpanel");
	    return elementUtil.doGetText(header);
//	    return driver.findElement(header).getText();
	}

	public boolean verifyLoggedInAccountName() {
		driver.switchTo().frame("mainpanel");
		return elementUtil.isElementDisplayed(accountName);
//		return elementUtil.getElement(accountName).isDisplayed();
//		return driver.findElement(accountName).isDisplayed();	
	}
	public String getLoggedInAccountName() {
		driver.switchTo().frame("mainpanel");
		return elementUtil.doGetText(accountName);
//		return driver.findElement(accountName).getText();	
	}
	
}
