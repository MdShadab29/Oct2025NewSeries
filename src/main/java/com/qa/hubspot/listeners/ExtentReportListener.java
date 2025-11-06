package com.qa.hubspot.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.hubspot.Base.BasePage;

public class ExtentReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	private static ExtentReports extent = init();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	// ------------------ Initialize Extent Report ------------------
	private static ExtentReports init() {
		Path path = Paths.get(OUTPUT_FOLDER);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String reportPath = OUTPUT_FOLDER + FILE_NAME;
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("Oct2025 Test Execution Report");
		spark.config().setTheme(Theme.DARK);

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Project", "Oct2025 Selenium Series");
		extent.setSystemInfo("Tester", "Md Shadab");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", "Chrome");

		return extent;
	}

	// ------------------ onStart ------------------
	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("🚀 Test Suite Started: " + context.getName());
	}

	// ------------------ onTestStart ------------------
	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println("🧪 Starting Test: " + methodName);
		ExtentTest extentTest = extent.createTest(methodName, result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	// ------------------ onTestSuccess ------------------
	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "✅ Test Passed: " + result.getMethod().getMethodName());
		System.out.println("✅ Test Passed: " + result.getMethod().getMethodName());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	
	// ------------------ onTestFailure ------------------
	@Override
	public synchronized void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
		System.out.println("❌ Test Failed: " + result.getMethod().getMethodName());

		WebDriver driver = BasePage.getDriver();
		if (driver != null) {
			String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
//			try {
//				test.get().fail("Screenshot on Failure:",
//						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			System.out.println("⚠️ WebDriver is null, screenshot not captured!");
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	// ------------------ onTestSkipped ------------------
	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "⏭️ Test Skipped: " + result.getMethod().getMethodName());
		System.out.println("⏭️ Test Skipped: " + result.getMethod().getMethodName());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	// ------------------ onFinish ------------------
	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("📘 Test Suite Finished: " + context.getName());
		if (extent != null) {
			extent.flush();
		}
		test.remove();
		System.out.println("📄 Extent Report generated successfully at: " + OUTPUT_FOLDER + FILE_NAME);
	}

	// ------------------ Helper: Capture Screenshot ------------------
	public String captureScreenshot(WebDriver driver, String screenshotName) {
		String dateName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + dateName + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}

	// ------------------ Helper: Convert Time ------------------
	private Date getTime(long millis) {
		return new Date(millis);
	}
}
