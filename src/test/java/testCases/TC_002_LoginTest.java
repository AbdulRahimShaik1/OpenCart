package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002_LoginTest extends BaseClass {
	
	
	@Test(groups= {"regression","master"})
	public void verify_Login() 
	{
		logger.info("*****TC_002_LoginTest is Strating*****");
		logger.debug("Debugging is Starting ....");
		
		try {
			
			HomePage hp = new HomePage(driver);
			
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount");
			
			hp.clickLogin();
			logger.info("Clicked on Login in HomePage");
			
			LoginPage lp =new LoginPage(driver);
			logger.info("Login page is Opened");
			
			logger.info("Entering User Email ID and Password");
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			
			lp.clickLogin();
			logger.info("Clicked on Login in LoginPage");
			MyAccountPage macc=new MyAccountPage(driver);
			
			boolean targetPage =macc.isMyAccountPageExist();
			
			System.out.println("isMyAccountPageExist? "+targetPage);
			
			//Approach 1
			/*
			if(targetPage==true)
			{
				logger.info("Login Test is Passed");
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Login Test is Failed");
				Assert.fail();
			}
			
			*/
			
			//Approach 2
			
			Assert.assertEquals(targetPage, true,"Login Test is failed ");
			
			
		}catch(Exception e)
		{
			e.getCause();
			logger.info("Login Test is Failed");
			Assert.fail();
		}
		
		logger.info("*****TC_002_LoginTest is Finished*****");
		logger.debug("Debugging is Completed");
	}
	
	

}
