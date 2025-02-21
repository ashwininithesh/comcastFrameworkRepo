package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	// Implicit Wait
	
	public void waitForPageToLoad(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	//Explicit Wait
	
	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// Switch to child window based on URL
	
	public void switchToTabOnURl(WebDriver driver, String partialUrl)
	{
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext())
		{
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actUrl=driver.getCurrentUrl();
			
			if(actUrl.contains(partialUrl))
			{
				break;
			}
		}
	}
	
	//Switch to parent Window based on title
	
	public void switchToTabOnTitle(WebDriver driver, String partialtitle)
	{
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext())
		{
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actUrl=driver.getCurrentUrl();
			
			if(actUrl.contains(partialtitle))
			{
				break;
			}
		}
	}
	
	//Switch to Frame
	
	public void switchToFrame(WebDriver driver, int Index)
	{
		driver.switchTo().frame(Index);
	}
	
	public void switchToFrame(WebDriver driver, String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	//Switch To Alert
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	//Select class for DropDowns
	
	public void select(WebElement element,String text)
	{
	    Select sel=new Select(element);
	    sel.selectByVisibleText(text);
	    
	}
	
	public void select(WebElement element,int index)
	{
	    Select sel=new Select(element);
	    sel.selectByIndex(index);
	    
	}
	
	//MouseOvering Actions
	
	public void mousemoveonElement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void doubleclick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	// Maximize the window size
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	

}
