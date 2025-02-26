package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage
{
	@FindBy(xpath="//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchtxtedt;
	
	@FindBy(name="seach")
	private WebElement ele2;

	public WebElement getCreateProductImgBtn() {
		return createProductImgBtn;
		
		
	}

	public WebElement getSearchtxtedt() {
		return searchtxtedt;
	}
	
}
