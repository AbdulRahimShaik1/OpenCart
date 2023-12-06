package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.AccounRegistrationPage;
import pageObjects.HomePage;

public class TC_001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups= {"sanity","master"})
	public void verify_account_registration() {
		logger.debug("detailed log of execution starts.....");
		logger.info("*****starting TC_001_AccountRegistrationTest*****");
		
		try 
		{
		
		HomePage hp = new HomePage(driver);
	
		hp.clickMyAccount();
		logger.info("Cliked on MyAccount link");
		hp.clickRegister();
		logger.info("Cliked on Registration link");
		AccounRegistrationPage regpage = new AccounRegistrationPage(driver);
		
		logger.info("Entering user details....");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		
		String newEmail=randomString();
		System.out.println("the newly created user e-mail id is: "+newEmail.concat("@gmail.com"));
		regpage.setEmail(newEmail+"@gmail.com");
		//regpage.setEmail("abdul123@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		System.out.println("the password is : "+password);
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		logger.info("Cliked on Privacy Policy button");
		
		regpage.clickContinue();
		logger.info("Cliked on continue button");
		
		String act_confm_msg=regpage.getConfirmationMsg();
		logger.info("Getting the actual confirmation message after clicking on continue button");
		
		logger.info("Validating expected message with actual message..");
		
		//Assert.assertEquals(act_confm_msg, "Your Account Has Been Created!!!");
		
		if(act_confm_msg.equals("Your Account Has Been Created!!"))
		{
			logger.info("test is Passed");
			Assert.assertTrue(true);
			
		}
		else
		{
			logger.error("test is failed..");
			Assert.fail();
		}
		
		}catch(Exception e)
		{
			logger.error(e.getMessage());
			logger.error("test is failed");
			Assert.fail();
			
		}
		
		logger.info("*****Execution of TC_001_AccountRegistrationTest is completed*****");
		logger.debug("detailed log of execution ends.....");
	}
	
	
	
	

}
