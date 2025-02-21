package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for contact module
 * @author Ashwini
 */

public class SearchContactTest extends BaseClass
{
	/**
	 * 
	 */
	
	@Test
	public void searchcontactTset()
	{
		/* Step 1: login to app*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("username", "password");
	}

}
