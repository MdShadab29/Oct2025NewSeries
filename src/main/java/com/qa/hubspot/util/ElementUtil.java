package com.qa.hubspot.util;

import java.time.Duration;
import java.util.Properties;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.hubspot.Base.BasePage;


public class ElementUtil extends BasePage {
	
	WebDriver driver;
	Properties prop;
	

	public  ElementUtil(WebDriver driver){
		this.driver = driver;
	}
	
	public WebElement getElement(By locator){
		waitForElementPresent(locator);
		
		WebElement element = null;
	try {	
	 element = driver.findElement(locator);
	 if(flash.equalsIgnoreCase("yes")) {	 
	 JavaScriptUtil.flash(element, driver);
	 }
	}catch(Exception e) {
		System.out.println("Some exception occured while creating WebElement " + locator);
	}
	return element;
 }
	
	public void waitForElementPresent(By locator){
		
		WebDriverWait wait = new WebDriverWait(driver,java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));		
	}

	public void doClick(By locator){
		try {
		getElement(locator).click();
		}catch(Exception e) {
		System.out.println("Some exception occured while clicking on WebElement " + locator);
		}
	}
	
	public void doSendKeys(By locator, String value){
		try {
		getElement(locator).clear();

		getElement(locator).sendKeys(value);
		}catch(Exception e) {
		System.out.println("Some exception occured while sending to WebElement " + locator);
		}
	}
	
	public String doGetText(By locator){
		String text = null;
		try {
		text = getElement(locator).getText();
		}catch(Exception e) {
		System.out.println("Some exception occured while sending to WebElement " + locator);
		}
		return text;
	}
	
	public  String waitForPageTitle(String title){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
		
	}
	public  boolean isElementDisplayed(By locator){
		try {
		return getElement(locator).isDisplayed();
		}catch(Exception e) {
			System.out.println("Some exception occured while checking  WebElement displayed" + locator);
			return false;	
		}	
	}
	public WebElement waitForElementToBeVisible(By locator, int timeoutSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForFrameAndSwitchToIt(String frameNameOrId, int timeoutSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
	}

	public void clickWhenReady(By locator, int timeoutSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	    WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
	    el.click();
	}

	
	
	
	
	
}
