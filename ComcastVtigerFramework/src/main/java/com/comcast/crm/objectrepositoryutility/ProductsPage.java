package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage
{
	@FindBy(xpath="//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchtxtedt;
	
	@FindBy(name="search")
	private WebElement ele2;
	
	@FindBy(name="submit")
	private WebElement searchNowBtn;

	public WebElement getEle3() {
		return searchNowBtn;
	}

	public WebElement getCreateProductImgBtn() {
		return createProductImgBtn;
		
		
	}

	public WebElement getSearchtxtedt() {
		return searchtxtedt;
	}

	public WebElement getEle2() {
		return ele2;
	}
	
	
	
}
