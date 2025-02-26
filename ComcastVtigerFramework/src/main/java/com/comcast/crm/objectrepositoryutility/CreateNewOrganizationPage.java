package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage
{

	
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDd;
	
	@FindBy(name="accounttype")
	private WebElement typeDd;
	
	@FindBy(name="phone")
	private WebElement phoneEdt;
	
	@FindBy(name="website")
	private WebElement websiteEdt;
	
	
	
	public WebElement getIndustryDd() {
		return industryDd;
	}

	public WebElement getTypeDd() {
		return typeDd;
	}

	public WebElement getWebsiteEdt() {
		return websiteEdt;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	public void setPhoneEdt(WebElement phoneEdt) {
		this.phoneEdt = phoneEdt;
	}

	public void createOrg(String orgname)
	{
		orgNameEdt.sendKeys(orgname);
		saveBtn.click();
		
	}
	
	public void createOrg(String orgname,String phone)
	{
		orgNameEdt.sendKeys(orgname);
		phoneEdt.sendKeys(phone);
		saveBtn.click();
		
	}
	
	public void createOrgin(String orgname,String pindustry)
	{
		orgNameEdt.sendKeys(orgname);
		Select sel=new Select(industryDd);
		sel.selectByVisibleText(pindustry);
		saveBtn.click();
		
	}
	
	public void createOrg(String orgname,String pindustry,String type)
	{
		orgNameEdt.sendKeys(orgname);
		Select selindustry=new Select(industryDd);
		selindustry.selectByVisibleText(pindustry);
		
		Select seltype=new Select(typeDd);
		seltype.selectByVisibleText(type);
		saveBtn.click();
		
	}
	
	

}
