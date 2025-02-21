package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage
{

	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "dvHeaderText")
	private WebElement headermsg;
	
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement supportStartDateEdt;
	
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement supportEndDateEdt;
	
	

	public WebElement getHeadermsg() {
		return headermsg;
	}



	public WebElement getSupportStartDateEdt() {
		return supportStartDateEdt;
	}



	public WebElement getSupportEndDateEdt() {
		return supportEndDateEdt;
	}
	
	
	
	

}
