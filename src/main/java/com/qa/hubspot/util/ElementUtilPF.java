package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.Base.BasePage;

public class ElementUtilPF extends BasePage {
	
	WebDriver driver;
	public ElementUtilPF(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
//	public void waitForElementOfPresent(WebElement element) {
//		
//		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOf(element));
//			
//	}
	public void waitForElementVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
	public WebElement waitForElementVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
}
