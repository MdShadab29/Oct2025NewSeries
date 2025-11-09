
package com.qa.hubspot.pages;

import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.Base.BasePage;
	import com.qa.hubspot.util.ElementUtil;
/**
 * 
 * @author Md Shadab
 *
 */
	public class ContactsPage extends BasePage {

	    WebDriver driver;
	    ElementUtil elementUtil;
	    Properties prop;

	    // top menu links (inside mainpanel frame)
	    By contactsMenu = By.xpath("//a[text()='Contacts']");
	    By newContactLink = By.xpath("//a[text()='New Contact']");

	    // contact form fields
	    By title = By.name("title");                 // dropdown
	    By firstName = By.id("first_name");
	    By middleName = By.id("middle_initial");
	    By lastName = By.id("surname");
	    By nickname = By.name("nickname");
	    By company = By.name("client_lookup");             // sometimes 'client_lookup' depending on version
	    By position = By.name("position");
	    By department = By.name("department");
	    By phone = By.name("phone");
	    By mobile = By.name("mobile");
	    By email = By.name("email");
	    By category = By.name("category");
	    By saveBtn = By.xpath("//input[@value='Save']");

	    public ContactsPage(WebDriver driver) {
	        this.driver = driver;
	        this.elementUtil = new ElementUtil(driver);
	    }
	    public void goToNewContact() {
	        // 1️⃣ Switch to the correct frame
	        elementUtil.waitForFrameAndSwitchToIt("mainpanel", 10);

	        // 2️⃣ Define locators
	        By contactsMenu = By.xpath("//a[text()='Contacts']");
	        By newContactLink = By.xpath("//a[text()='New Contact']");

	        // 3️⃣ Hover on "Contacts" to show dropdown
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement contactsMenuElement = wait.until(ExpectedConditions.visibilityOfElementLocated(contactsMenu));

	        Actions action = new Actions(driver);
	        action.moveToElement(contactsMenuElement).perform();

	        // 4️⃣ Wait for "New Contact" link to appear and click it
	        WebElement newContactElement = wait.until(ExpectedConditions.elementToBeClickable(newContactLink));
	        newContactElement.click();

	        System.out.println("Navigated to New Contact page successfully!");
	    }
	    
	    /**
	     * Navigate to Contacts -> New Contact (switches to mainpanel frame)
	     */
//	    public void goToNewContact() {
//	        // Wait for frame and switch
//	        elementUtil.waitForFrameAndSwitchToIt("mainpanel", 20);
//
//	        // Click Contacts then New Contact
//	        elementUtil.clickWhenReady(contactsMenu, 20);
//	        elementUtil.clickWhenReady(newContactLink, 20);
//	        
//	        // Hover on "Contacts" first
//	        WebElement contactsMenuElement = driver.findElement(By.xpath("//a[text()='Contacts']"));
//	        Actions action = new Actions(driver);
//	        action.moveToElement(contactsMenuElement).build().perform();
//
//	        // Wait for "New Contact" to be clickable, then click
//	        elementUtil.clickWhenReady(By.xpath("//a[text()='New Contact']"), 20);
//	    }

	    /**
	     * Create new contact filling minimal required fields.
	     */
	    public void createNewContact(String titleVisibleText, String fName, String mName, String lName,
	                                 String comp, String emailAddr) {

	        // assume already switched to mainpanel & New Contact form is visible
	        // set title (dropdown)
	        if (titleVisibleText != null && !titleVisibleText.isEmpty()) {
	            elementUtil.waitForElementToBeVisible(title, 5);
	            Select t = new Select(driver.findElement(title));
	            t.selectByVisibleText(titleVisibleText);
	        }

	        elementUtil.waitForElementToBeVisible(firstName, 5).sendKeys(fName);
	        elementUtil.waitForElementToBeVisible(middleName, 5).sendKeys(mName);
	        elementUtil.waitForElementToBeVisible(lastName, 5).sendKeys(lName);

	        if (comp != null) {
	            elementUtil.waitForElementToBeVisible(company, 5).sendKeys(comp);
	        }
	        if (emailAddr != null) {
	            elementUtil.waitForElementToBeVisible(email, 5).sendKeys(emailAddr);
	        }

	        // click save
	        elementUtil.clickWhenReady(saveBtn, 20);

	        // go back to default content to avoid frame issues in following steps
	        driver.switchTo().defaultContent();
	    }
<<<<<<< HEAD
	    public String getContactsPageTitle() {
	        elementUtil.waitForFrameAndSwitchToIt("mainpanel", 10);
	        return driver.getTitle();
	    }

=======
>>>>>>> 1dfbd1a81516041eee93ffb8d6df81db4c3df121
	}

	
	
	
	
	
	
	
	
	
	
	
	