package com.qa.hubspot.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ExcelUtil;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.ContactsPage;

public class ContactsPageTest {

    WebDriver driver;
    BasePage basePage;
    Properties prop;
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;

    @BeforeMethod
    public void setUp() {
        basePage = new BasePage();
        prop = basePage.initialize_properties();
        driver = basePage.initialize_driver(prop);
        loginPage = new LoginPage(driver);
        homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        contactsPage = new ContactsPage(driver);
    }
    @Test(priority=1)
    public void VerifyContactsPageTitleTest() {
    	String title = contactsPage.getContactsPageTitle();
    	System.out.println("contact page title is" + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE,"login page title is correct"); 	
    }
    
    @DataProvider()
    public Object[][] getContactData() {	
    Object data[][]= ExcelUtil.getTestData("CreateContact");
    System.out.println("Total rows from Excel: " + data.length);
    return data;
    }
    
    @Test(priority=2, dataProvider="getContactData")
    public void createNewContactTest(String title,String firstName,String middleName,String lastName,String company,String emailId) {
        contactsPage.goToNewContact();
        contactsPage.createNewContact(title,firstName,middleName,lastName,company,emailId );
        System.out.println("New contact created successfully!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
