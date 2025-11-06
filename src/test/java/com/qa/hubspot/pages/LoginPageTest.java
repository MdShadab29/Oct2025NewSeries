package com.qa.hubspot.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

@Test
public class LoginPageTest {

    WebDriver driver;
    BasePage basePage;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setup() {
        basePage = new BasePage();
		Properties prop = basePage.initialize_properties();

        driver = basePage.initialize_driver(prop);
        loginPage = new LoginPage(driver);
    }

    public void verifyLoginPageTitleTest() {
        String title = loginPage.getLoginPageTitle();
        System.out.println("Login Page Title: " + title);
        Assert.assertEquals(title, "CRM Login", "Title mismatch!");
    }

    public void verifyDoLoginTest() {
        homePage = loginPage.doLogin("testuser@example.com", "Test@12345");
        Assert.assertNotNull(homePage, "Login failed — HomePage is null");
        String accountName = homePage.getLoggedInAccountName();
        Assert.assertEquals(accountName, "Test User", "User name mismatch!");
    }
}





//package com.qa.hubspot.pages;
//
//import java.util.Properties;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.qa.hubspot.Base.BasePage;
//
//public class LoginPageTest {
//    WebDriver driver;
//    Properties prop;
//    BasePage basePage;
//    LoginPage loginPage;
//    HomePage homePage;
//
//    @BeforeMethod
//    public void setup() {
//        basePage = new BasePage();
//        prop = basePage.initialize_properties();
//        driver = basePage.initialize_driver(prop);
//        loginPage = new LoginPage(driver);
//    
//    }
//    @Test
//    public void LoginPageTest() {
//        String title = loginPage.getLoginPageTitle();
//        System.out.println("Login Page Title: " + title);
//        Assert.assertEquals(title, "CRM Login", "Login Page title mismatch!");
//    }
//
//    @Test
//    public void doLoginTest() {
//        homePage = loginPage.doLogin("testuser@example.com", "Test@12345");
//        String accountName = homePage.getLoggedInAccountName();
//        Assert.assertEquals(accountName, "Test User", "Logged in username mismatch!");
//    }
//
//    @Test
//    public void getLoginPageTitleTest() {
//        String title = loginPage.getLoginPageTitle();
//        System.out.println("Login Page Title: " + title);
//        Assert.assertEquals(title, "HubSpot Login", "Login Page title mismatch!");
//    }
//}

