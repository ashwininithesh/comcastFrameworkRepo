package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


//@Listeners(com.comcast.crm.listenerutility.ListenerImplementationClass.class)
public class InvoiceTest extends BaseClass
{
	@Test//(retryAnalyzer=com.comcast.crm.listenerutility.RetryListenerImplimentation.class)
	public void
	activateSim(
			)
	{
		System.out.println("Execute createinvoiceTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		String var=driver.getTitle();
		System.out.println(var);
	}

	/*@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println("Execute createInvoiceWithContactTest");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}*/

}
