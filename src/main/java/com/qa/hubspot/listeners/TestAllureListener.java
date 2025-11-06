package com.qa.hubspot.listeners;

import com.qa.hubspot.Base.BasePage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestAllureListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // =======================================
    // Screenshot attachments for Allure
    // =======================================
    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // =======================================
    // Text attachments for Allure
    // =======================================
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }
    
    // =======================================
    // HTML attachments for Allure
    // =======================================
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    // =======================================
    // When a test fails, capture screenshot
    // =======================================
    
 // ------------------ onStart ------------------
 	@Override
 	public  void onStart(ITestContext iTestContext) {
 		System.out.println("🚀 Test Suite Started: " + iTestContext.getName());
// 		iTestContext.setAttribute("WebDriver", BasePage.getDriver());
 	}
 

    @Override
    public void onTestStart(ITestResult iTestResult) {
 	System.out.println("I am in onTestStart method:" + getTestMethodName(iTestResult)+ "Start");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    System.out.println("I am in onTestSucess method:" + getTestMethodName(iTestResult)+ "succeed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    
    @Override
    public void onTestFailure(ITestResult iTestResult) {
     	System.out.println("I am in onTestFailure method:" + getTestMethodName(iTestResult)+ "failed");
        Object testClass = iTestResult.getInstance();
//        WebDriver driver = BasePage.getDriver();
//
//        if (driver != null) {
//            saveScreenshotPNG(driver);
//        }
        saveTextLog(getTestMethodName(iTestResult) + " test has failed.");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
 		System.out.println("🚀 Test Suite Ended: " + iTestContext.getName());

    }
    
}
