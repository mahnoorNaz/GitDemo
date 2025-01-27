package FrameworkPractice.SeleniumFrameworkDesign.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import FrameworkPractice.SeleniumFrameworkDesign.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // thread safe so there is no overriding whike tests are running in parallel
	
	@Override
	public void onTestStart(ITestResult result)
	{
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // giving unique thread id
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable()); // throwable will print the error logs in report
		// now will run parallely
		
		// giving life to driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			//interview question
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		// take a screenshot
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		// attach it to the report 
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
		// very imp
	}


}
