package com.qa.hubspot.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.LoginPage;
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

    @Test
    public void createNewContactTest() {
        contactsPage.goToNewContact();
        contactsPage.createNewContact("Mr.", "shadab", "M", "ahmad", "TechCorp", "shadab,shadab11@gmail.com");
        System.out.println("New contact created successfully!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
