package com.comcast.crm.orgtest;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.crm.listenerutility.ListenerImplementationClass.class)

public class OrganizationModule extends BaseClass
{
	
	
	@Test(groups={"smokeTest","regressionTest"})
	
	public void createOrganization() throws EncryptedDocumentException, IOException 
	{
		UtilityClassObject.getTest().log(Status.INFO, "read testScript data from Excel File");
	
				//read testScript data from Excel File
				
				String orgname=elib.getDataFromExcel("org",1,2) + jlib.getRandomNumber();
				
				
				//step2 : navigate to organization module
				
				UtilityClassObject.getTest().log(Status.INFO, "navigate to organization module");
				
				HomePage op=new HomePage(driver);
				op.getOrgLink().click();
				
				
				//step3:click on "create organization" button
				
				UtilityClassObject.getTest().log(Status.INFO, "click on create organization button");
				
				OrganizationsPage cnp=new OrganizationsPage(driver);
				cnp.getCreateNewOrgBtn().click();
				
				
				//step4: Enter all the details and create new organization
				
				UtilityClassObject.getTest().log(Status.INFO, "Enter all the details and create new organization");
				
				CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
				cnop.createOrg(orgname);
				
				
				//verify Header msg Expected Result
				
				UtilityClassObject.getTest().log(Status.INFO,  orgname +"Create a new organization");
				
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
	}
				
				
				@Test(groups="regressionTest")
				public void CreateOrganizationWithIndustryAndType() throws EncryptedDocumentException, IOException
				{
					//read testScript data from Excel File
					
					String orgname=elib.getDataFromExcel("org",4,2) + jlib.getRandomNumber();
					String industry=elib.getDataFromExcel("org",4,3);
					String type=elib.getDataFromExcel("org",4,4);
				
					
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
						System.out.println(industry + " information is not verified ==fail ");
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
					
				}
				
		@Test(groups="regressionTest")
		public void createOrganizationWithPhoneNumber() throws Throwable, IOException
		{

			//read testScript data from Excel File
			
			String orgname=elib.getDataFromExcel("org",7,2) + jlib.getRandomNumber();
			String phone=elib.getDataFromExcel("org",7,3);
			
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
			
		}
	}


