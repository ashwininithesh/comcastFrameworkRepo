package com.comcast.crm.contacttest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactDataProviderTest
{
	@Test(dataProvider = "getData")
	public void createContactTest(String firstname, String lastname,long phonenumber)
	{
		System.out.println("Firstname :" + firstname + ", Lastname : " + lastname + ", phone number : " +phonenumber);
		
	}
 
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objarr=new Object[3][3];
		objarr[0][0]="deepak";
		objarr[0][1]="hr";
		objarr[0][2]=7389400478l;
		
		objarr[1][0]="Sam";
		objarr[1][1]="sh";
		objarr[1][2]=7389400472l;
		
		objarr[2][0]="Jhon";
		objarr[2][1]="smith";
		objarr[2][2]=7389400476l;
		
		return objarr;
		
	}
}
