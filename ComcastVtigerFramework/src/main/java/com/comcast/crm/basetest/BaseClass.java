package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass
{
	/*create object*/
	
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public DataBaseUtility dblib=new DataBaseUtility();
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	public ExtentSparkReporter spark;
	public ExtentReports report;
	
	@BeforeSuite   //(groups= {"smokeTest", "regressionTest"})
	public void configBS() throws SQLException
	{
        System.out.println("===connect to Db and Report config===");
		dblib.getDbconnection();
	}
	
	//@Parameters("Browser")
	@BeforeClass  // (groups= {"smokeTest", "regressionTest"})
	public void configBC() throws IOException
	{
		System.out.println("===launch the browser===");
		
		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		
		
		if(BROWSER.equals("chrome"))
		{
		   driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		
		UtilityClassObject.setDriver(driver);
		
		
		wlib.waitForPageToLoad(driver);
		
		wlib.maximizeWindow(driver);
		
        String Url=flib.getDataFromPropertiesFile("url");
		
		driver.get(Url);
	}
	
	@BeforeMethod //(groups= {"smokeTest", "regressionTest"})
	public void configBM() throws IOException
	{
		System.out.println("===login===");
		String username=flib.getDataFromPropertiesFile("username");
		String password=flib.getDataFromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(username, password);	
	}
	
	@AfterMethod  //(groups= {"smokeTest", "regressionTest"})
	public void configAM()
	{
		System.out.println("===logout===");
		HomePage hp=new HomePage(driver);
		hp.logout(driver);
		
	}
	
	@AfterClass  //  (groups= {"smokeTest", "regressionTest"})
	public void configAC()
	{
		System.out.println("close the browser");
		driver.quit();
	}
	
	@AfterSuite  //(groups= {"smokeTest", "regressionTest"})
	public void configAS()
	{
		System.out.println("===close DB and report backup");
		dblib.closeDbConnection();
	
	}
	

}
