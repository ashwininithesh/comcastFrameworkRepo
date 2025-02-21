package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CreateOrganizationWithIndustriesTestPom 
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
				
				//Step1: login to app
				
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
				
				cnop.createOrg(orgname,industry,type);
				
				
				//verify the industries and type info
				
				//verify industry information
				
				OrganizationInfoPage oip=new OrganizationInfoPage(driver);
				
				String actIndustry=oip.getIndustryDd().getText();
				
				if(actIndustry.contains(industry))
				{
					System.out.println(industry + " information is verified ==pass ");
					
				}
				else 
				{
					System.out.println(industry + " iformation is not verified ==fail ");
				}
				
				//verify type  information
				
				
               String actType=oip.getTypeDd().getText();
				
				if(actType.contains(type))
				{
					System.out.println(type + " information is verified ==pass ");
					
				}
				else 
				{
					System.out.println(type + " information is not verified ==fail ");
				}
				
				
				//logout
				
			  driver.quit();
			  }

}
