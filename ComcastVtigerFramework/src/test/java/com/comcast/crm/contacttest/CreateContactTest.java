package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactTest {

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
		
		String lastname=elib.getDataFromExcel("contact",1,2) + jlib.getRandomNumber();
		
		
		
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
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//verify Header msg Expected Result
		String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actLastName.equals(lastname))
		{
			System.out.println(lastname + " information is verified==pass");
		}
		else
		{
			System.out.println(lastname + " information is not verified==Fail");
		}
		
		//logout
		
	  driver.quit();
		

	}

}
