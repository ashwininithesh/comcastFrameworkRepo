package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage
{
	

	public CreateNewContactPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name= "support_end_date")
	private WebElement endDateEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling :: img")
	private WebElement createimg;
	
	
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	

	public WebElement getStartDateEdt() {
		return startDateEdt;
	}

	public WebElement getEndDateEdt() {
		return endDateEdt;
	}

	public void setLastNameEdt(WebElement lastNameEdt) {
		this.lastNameEdt = lastNameEdt;
	}
	
	

	public WebElement getCreateimg() {
		return createimg;
	}

	public void createContact(String lastname)
	{
		lastNameEdt.sendKeys(lastname);
		saveBtn.click();
		
	}
	
	public void createContact(String lastname,String startDate, String endDate)
	{
		lastNameEdt.sendKeys(lastname);
		startDateEdt.sendKeys(startDate);
		endDateEdt.sendKeys(endDate);
		saveBtn.click();
		
	}

}