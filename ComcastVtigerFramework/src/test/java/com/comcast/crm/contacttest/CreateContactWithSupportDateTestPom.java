package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDateTestPom 
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
		
		
        wlib.maximizeWindow(driver);
		
		wlib.waitForPageToLoad(driver);
		
		driver.get(URL);
		
		//step1: login to application
		
        LoginPage lp=new LoginPage(driver);
		
		lp.loginToapp(USERNAME,PASSWORD);
		
		
		//step2 : navigate to Contact module
		
		HomePage op=new HomePage(driver);
		op.getContactLink().click();
		
		
		//step3:click on "Contact" button 
		
		ContactPage cnp=new ContactPage(driver);
		cnp.getCreateNewContactBtn().click();
		
		//step4: Enter all the details and create new organization
		
		//current date  
		
		String startDate=jlib.getSystemDateYYYYMMDD();
		
		System.out.println(startDate);
		
		// Required Date
		
		String endDate=jlib.getRequiredDateYYYYMMDD(30);
		System.out.println(endDate);
		
		CreateNewContactPage cnop=new CreateNewContactPage(driver);
		
		cnop.createContact(lastname,startDate,endDate);
		
	    //verify Header msg Expected Result
		
        ContactInfoPage cip=new ContactInfoPage(driver);
		
		String actStartDate=cip.getSupportStartDateEdt().getText();
		System.out.println(actStartDate);
		
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate + " information is verified==pass");
		}
		else
		{
			System.out.println(startDate + " information is not verified==Fail");
		}
		
		String actEndDate=cip.getSupportEndDateEdt().getText();
		
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
	  
	  
