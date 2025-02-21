package com.comcast.crm.orgtest;

import java.io.IOException;
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
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithPhoneNumberTestPom 
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
				
				String orgname=elib.getDataFromExcel("org",7,2) + jlib.getRandomNumber();
				String phone=elib.getDataFromExcel("org",7,3);
				
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
				
				lp.loginToapp("admin","admin");
				
				
				//step2 : navigate to organization module
				
                HomePage op=new HomePage(driver);
				
				op.getOrgLink().click();
				
				
				//step3:click on "create organization" button
				
                OrganizationsPage cnp=new OrganizationsPage(driver);
				
				cnp.getCreateNewOrgBtn().click();
				
				//step4: Enter all the details and create new organization
				
                CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
				
				cnop.createOrg(orgname,phone);
				
				//verify the phone number info Expected Result
				
                OrganizationInfoPage oip=new OrganizationInfoPage(driver);
				
				String actPhoneNumber=oip.getPhoneEdt().getText();
				
				if(actPhoneNumber.contains(phone))
				{
					System.out.println(phone + " information is verified ==pass ");
					
				}
				else 
				{
					System.out.println(phone + " information is not verified ==fail ");
				}
				
				// close the application
				
				driver.close();
				

}
}
