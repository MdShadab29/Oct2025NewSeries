package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

<<<<<<< HEAD
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

=======
>>>>>>> 1dfbd1a81516041eee93ffb8d6df81db4c3df121
public class LoginTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	
	
	@BeforeMethod
	
	public void setup() {
		
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage =new LoginPage(driver);
	}
	
	@Test(priority = 1, description="login test with correct username and correct password....")
<<<<<<< HEAD
	@Description("login test with correct username and correct password....")
	@Severity(SeverityLevel.BLOCKER)
=======
>>>>>>> 1dfbd1a81516041eee93ffb8d6df81db4c3df121
      public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 2 , description = "login page title test...")
<<<<<<< HEAD
	@Description("login page title test...")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
	
		String title = loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE,"login page title is correct");
=======
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE,"login page title is in correct");
>>>>>>> 1dfbd1a81516041eee93ffb8d6df81db4c3df121
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
		
	}
}
