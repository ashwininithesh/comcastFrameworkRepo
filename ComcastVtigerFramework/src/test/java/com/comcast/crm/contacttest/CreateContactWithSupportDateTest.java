package com.comcast.crm.contacttest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithSupportDateTest
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		/*create object*/
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		//read common data from properties file
		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		
		//read testScript data from Excel File
		
		String lastname=elib.getDataFromExcel("contact",4,2) + jlib.getRandomNumber();
		
		WebDriver driver=null;
		
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
		
		//step1: login to application
        wlib.maximizeWindow(driver);
		
		wlib.waitForPageToLoad(driver);
		
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		
		//step2 : navigate to Contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		
		//step3:click on "Contact" button 
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step4: Enter all the details and create new organization
		
		//current date  
		
		String startDate=jlib.getSystemDateYYYYMMDD();
		
		// Required Date
		
		String endDate=jlib.getRequiredDateYYYYMMDD(30);
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//verify Header msg Expected Result
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate + " information is verified==pass");
		}
		else
		{
			System.out.println(startDate + " information is not verified==Fail");
		}
		
		String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate + " information is verified==pass");
		}
		else
		{
			System.out.println(endDate + " information is not verified==Fail");
		}
		
		Thread.sleep(2000);
		
		
		//logout
		
	  driver.quit();
	}
		

}
