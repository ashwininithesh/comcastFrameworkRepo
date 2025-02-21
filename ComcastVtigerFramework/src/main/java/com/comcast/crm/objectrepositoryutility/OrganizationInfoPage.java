package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	public OrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "dvHeaderText")
	private WebElement headermsg;

	@FindBy(name = "industry")
	private WebElement industryDd;

	@FindBy(name = "accounttype")
	private WebElement typeDd;

	@FindBy(id = "dtlview_Phone")
	private WebElement phoneEdt;

	public WebElement getHeadermsg() {
		return headermsg;
	}

	public WebElement getIndustryDd() {
		return industryDd;
	}

	public WebElement getTypeDd() {
		return typeDd;
	}

	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	public void setPhoneEdt(WebElement phoneEdt) {
		this.phoneEdt = phoneEdt;
	}
	
	

}
