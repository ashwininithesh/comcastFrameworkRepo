package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustriesTest 
{
	public static void main(String[] args) throws IOException 
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
				
				String orgname=elib.getDataFromExcel("org",4,2) + jlib.getRandomNumber();
				String industry=elib.getDataFromExcel("org",4,3);
				String type=elib.getDataFromExcel("org",4,4);
			
				
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
				
				
				//step2 : navigate to organization module
				driver.findElement(By.linkText("Organizations")).click();
				
				
				//step3:click on "create organization" button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//step4: Enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgname);
				
				WebElement ele1=driver.findElement(By.name("industry"));
				wlib.select(ele1,industry);
				
				WebElement ele2=driver.findElement(By.name("accounttype"));
				wlib.select(ele2,type);
				
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify the industries and type info
				String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
				if(actIndustries.equals(industry))
				{
					System.out.println(industry + " information is verified==PASS");
				}
				else
				{
					System.out.println(industry + " information is not verified==FAIL");
				}
				
				String acttype=driver.findElement(By.id("dtlview_Type")).getText();
				if(acttype.equals(type))
				{
					System.out.println(type + " information is verified==PASS");
				}
				else
				{
					System.out.println(type + " information is not verified==FAIL");
				}
				
				//logout
				
			  driver.quit();
			  }

}

