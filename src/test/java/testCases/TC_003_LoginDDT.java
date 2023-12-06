package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginTestData",dataProviderClass=DataProviders.class) //DataProvider 1 is used from DataProviders Class
	public void verify_LoginDDT(String email,String password, String exp )
	{
		logger.info("**** Starting TC_003_LoginDDT *****");
		try
		{
			
			HomePage hp=new HomePage(driver);
			
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			
			MyAccountPage macc=new MyAccountPage(driver);
			
			boolean targetPage=macc.isMyAccountPageExist();
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					macc.clickLogOut();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);;
				}
			}
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.clickLogOut();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);;
				}
			}
			
		
		}catch(Exception e)
		{
			logger.error("Test is failed");
			Assert.fail();
			
		}
	}
	
	

}
