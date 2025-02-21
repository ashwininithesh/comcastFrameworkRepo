
package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class ContactModule extends BaseClass
{
	    @Test(groups="smokeTest")
	    public void createContact() throws EncryptedDocumentException, IOException {
	    	
		String lastname=elib.getDataFromExcel("contact",1,2) + jlib.getRandomNumber();

		//step2 : navigate to Contact module
		
		HomePage op=new HomePage(driver);
		op.getContactLink().click();
		
		//step3:click on "Contact" button 
		
		ContactPage cnp=new ContactPage(driver);
		cnp.getCreateNewContactBtn().click();
		
		//step4: Enter all the details and create new organization
		
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		
		cncp.createContact(lastname);
		
		//verify Header msg Expected Result
		
		ContactInfoPage cip=new ContactInfoPage(driver);
		
		String actHeader=cip.getHeadermsg().getText();
		
		boolean status=actHeader.contains(lastname);
		
		Assert.assertEquals(status,true);
		
		
//		if(actLastName.contains(lastname))
//		{
//			System.out.println(lastname + " information is verified==pass");
//		}
//		else
//		{
//			System.out.println(lastname + " information is not verified==Fail");
//		}
	 }
	    
	  @Test(groups="regressionTest")
	  public void createContactWithSupportDate() throws EncryptedDocumentException, IOException
	  {
		//read testScript data from Excel File
			
			String lastname=elib.getDataFromExcel("contact",4,2) + jlib.getRandomNumber();
			
			
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
	  }
	    
	   
}
