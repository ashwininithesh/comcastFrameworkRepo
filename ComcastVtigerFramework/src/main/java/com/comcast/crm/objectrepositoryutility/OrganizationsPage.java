package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	@FindBy(name="submit")
	private WebElement searchNowBtn;

	@FindBy(name="submit")
	private WebElement seacrchbtnInOrg;
	
	@FindBy(name="search_text")
	private WebElement search_textInOrg;
	
	
	
	public WebElement getSearch_textInOrg() {
		return search_textInOrg;
	}



	public WebElement getSeacrchbtnInOrg() {
		return seacrchbtnInOrg;
	}



	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}

	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	
	

}
	
