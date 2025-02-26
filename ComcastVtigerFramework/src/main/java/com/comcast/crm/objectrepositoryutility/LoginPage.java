package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * author Ashwini
 * contains login page elements and business libraries like login
 */

public class LoginPage extends WebDriverUtility
{

	
	public LoginPage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
	}
	
	//Rule2: object creation
	///ranjini
	
	
	@FindBy(name="user_name")
	 private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	//rule3:Object Initialization
	   
	//rule 4: object encapsulation
		

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//provide action
	
	/**
	 * login to application based on username, password arguments
	 * @param username
	 * @param password
	 */
	public void loginToapp(String username,String password)
	{
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
	

}
