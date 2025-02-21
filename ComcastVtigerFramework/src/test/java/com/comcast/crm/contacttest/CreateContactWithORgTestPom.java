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
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithORgTestPom 
{
public static void main(String[] args) throws IOException, InterruptedException {
		
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
		
	String orgname=elib.getDataFromExcel("contact",7,2) + jlib.getRandomNumber();
	String ContactLastName=elib.getDataFromExcel("contact",7,3);
	
	
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
	
	LoginPage lp=new LoginPage(driver);
	
	lp.loginToapp(USERNAME,PASSWORD);
	
	
	//step2 : navigate to organization module
	
	HomePage op=new HomePage(driver);
	op.getOrgLink().click();
	
	
	//step3:click on "create Organization" button
	
	OrganizationsPage cnp=new OrganizationsPage(driver);
	cnp.getCreateNewOrgBtn().click();
	
	//step4: Enter all the details and create new organization

	CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	cnop.createOrg(orgname);
	
	//verify Header msg Expected Result
	
	OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	
	String actorgname=oip.getHeadermsg().getText();
	
	if(actorgname.contains(orgname))
	{
		System.out.println(orgname + " name is verified ==pass ");
		
	}
	else 
	{
		System.out.println(orgname + " name is not verified ==fail ");
	}
	
	//step to navigate to contact module
	
	HomePage hp=new HomePage(driver);
	hp.getContactLink().click();
	
	
	//step3:click on "Contact" button 
	
	ContactPage cop=new ContactPage(driver);
	cop.getCreateNewContactBtn().click();
	
	//step4: Enter all the details and create new organization
	
	CreateNewContactPage ccp=new CreateNewContactPage(driver);
	ccp.createContact(ContactLastName);
	
	ccp.getCreateimg().click();
	
	//switch to child window
	
	wlib.switchToTabOnURl(driver, "module=Accounts");
	
	
	driver.findElement(By.name("search_text")).sendKeys(orgname);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
	
	//switch to parent window
	
	wlib.switchToTabOnURl(driver, "Contacts&action");
	
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//verify header msg
	
	String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(headerinfo.contains(orgname))
	{
		System.out.println(orgname + " header verified==pass");
	}
	else
	{
		System.out.println(orgname + " header is not verified==Fail");
	}
	
	//verify header orgname info expected result
	
	String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
	if(actOrgName.equals(orgname))
	{
		System.out.println(orgname + " information is created==pass");
	}
	else
	{
		System.out.println(orgname + " information is not created==Fail");
	}
	
	Thread.sleep(2000);
	
	//logout
	
  driver.quit();
	}

}
