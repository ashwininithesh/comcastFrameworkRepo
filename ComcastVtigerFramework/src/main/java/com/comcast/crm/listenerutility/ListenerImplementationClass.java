package com.comcast.crm.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementationClass implements ITestListener, ISuiteListener
{
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) 
	{
		System.out.println("Report Configuration");
		
		//spark report config
		
		        String time=new Date().toString().replace(" ","_").replace(":","_");
				spark=new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
				spark.config().setDocumentTitle("Crm test Suite Results");
				spark.config().setReportName("Crm Report");
				spark.config().setTheme(Theme.DARK);
				
				//add Env information and create test
				
			    report=new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "WINDOWS-10");
				report.setSystemInfo("BROWSER", "CHROME-100");
		
	}

	@Override
	public void onFinish(ISuite suite) 
	{
		System.out.println("Report backup");
		report.flush();	
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
	   System.out.println("=======>" +result.getMethod().getMethodName()+ "<=====START====");
	   test=report.createTest(result.getMethod().getMethodName());
	   UtilityClassObject.setTest(test);
	   UtilityClassObject.getTest().log(Status.INFO,result.getMethod().getMethodName()+"==>Started <===");
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		System.out.println("=======>" +result.getMethod().getMethodName()+ "<=====END====");
		UtilityClassObject.getTest().log(Status.PASS,result.getMethod().getMethodName()+ "<=====Completed====");
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String testName=result.getMethod().getMethodName();
		 TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
	     String filepath=ts.getScreenshotAs(OutputType.BASE64);
	     String time=new Date().toString().replace(" ","_").replace(":","_");
	     UtilityClassObject.getTest().addScreenCaptureFromBase64String(filepath, testName+"_"+time);
	     UtilityClassObject.getTest().log(Status.FAIL,result.getMethod().getMethodName()+ "<=====Failed====");
	     UtilityClassObject.getTest().info(MarkupHelper.createLabel("Fail", ExtentColor.RED));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
