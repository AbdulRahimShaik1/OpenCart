package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccounRegistrationPage extends BasePage{

	public WebDriver driver;
	
	public AccounRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	

	@FindBy(xpath="//input[@id='input-firstname']") 
	WebElement txtfirstName;
	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement txtlastName;
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txtEMail;	
	@FindBy(xpath="//input[@id='input-telephone']") 
	WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") 
	WebElement chkdPolicy;
	@FindBy(xpath="//input[@value='Continue']") 
	WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") 
	WebElement msgConfirmation;

	//@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']") WebElement warningMsg;
	
	
	public void setFirstName(String fname)
	{
		txtfirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtlastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEMail.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	
	public void setPrivacyPolicy()
	{
		//solution 1
		chkdPolicy.click();
				
		//solution 2
		//chkdPolicy.submit();
				
		//solution 3
		//Actions act =new Actions(driver);
		//act.moveToElement(chkdPolicy).click().build().perform();
				
		//solution 4
		//JavascriptExecutor js1=(JavascriptExecutor)driver;
		//js1.executeScript("arguments[0].click();",chkdPolicy);
				
		//solution 5
		//chkdPolicy.sendKeys(Keys.RETURN);
				
		//solution 6
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(chkdPolicy)).click();
	}
	
	public void clickContinue()
	{
		//solution 1
		btnContinue.click();
		
		//solution 2
		//btnContinue.submit();
		
		//solution 3
		//Actions act =new Actions(driver);
		//act.moveToElement(btnContinue).click().build().perform();
		
		//solution 4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();",btnContinue);
		
		//solution 5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//solution 6
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	
	}
	
	public String getConfirmationMsg()
	{
		
		/*
		if(warningMsg.getText().equals("Warning: E-Mail Address is already registered!"))
		{
			return warningMsg.getText();
			
		}
		
		else
		{
		try 
		{
		return (msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
		}
		*/
		try 
		{
		return (msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
		
	}


}
