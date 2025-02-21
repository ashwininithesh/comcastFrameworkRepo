package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfotest
{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName)
	{
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//capture product info
		String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility elib=new ExcelUtility();
		int rowcount=elib.getRowcount("product");
		
		Object[][] objarr=new Object[rowcount][2];
		
		for(int i=0;i<rowcount;i++)
		{
		objarr[0][0]=elib.getDataFromExcel("product", i+1, 0);
		objarr[0][1]=elib.getDataFromExcel("product", i+1, 1);
		}
		
		return objarr;

}
	

}
