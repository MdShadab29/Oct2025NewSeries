package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class HomePageTest {
	
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	
	@BeforeMethod
	
	public void setup() {
		
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage =new LoginPage(driver);
		homePage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1, description="login test with correct username and correct password....")
      public void verifyHomePageTitleTest() {
	   String title = homePage.getHomePageTitle();
	   System.out.println("home page title is: "+ title);
	   Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "home page title mismatch");
	   
	}
	
	@Test(priority = 2 )
	public void verifyHomePageHeaderTest() {
		String headerValue = homePage.getHomePageHeaderValue();
		System.out.println("home page header value: "+ headerValue);
		Assert.assertEquals(headerValue, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority = 3)
	public void verifyLoggedInAccountTest() {
		String accountName = homePage.getLoggedInAccountName();
		System.out.println("logged in account name is : "+ accountName);
		
		Assert.assertEquals(accountName.trim().equalsIgnoreCase(Constants.LOGIN_ACCOUNT_NAME), false);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
		
	}

}
