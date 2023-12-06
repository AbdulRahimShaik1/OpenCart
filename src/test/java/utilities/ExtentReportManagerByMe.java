package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClass.BaseClass;

public class ExtentReportManagerByMe implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	 public void onStart(ITestContext testContext) 
	 {
		 
		 /*
		 SimpleDateFormat df= new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");
		 Date dt=new Date();
		 String currentdatetimestamp=df.format(dt);
		 */
		 
		 String timeStamp=new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		 
		 repName="Test-Report_"+timeStamp+".html";
		
		 sparkReporter = new ExtentSparkReporter(".\\reports\\" +repName);
		 
		 sparkReporter.config().setDocumentTitle("Tutorials Ninja Automation Report");
		 sparkReporter.config().setReportName("Tutorials Ninja Functional Testing");
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Application", "Tutorials Ninja");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("User Name",System.getProperty("user.name"));
		 extent.setSystemInfo("Environment", "QA");
		 
		String os= testContext.getCurrentXmlTest().getParameter("os");
		 
		 extent.setSystemInfo("Operating System", os);
		 
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		
		extent.setSystemInfo("Browser", browser);
		 
		List<String> includeGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups",includeGroups.toString());
		}
	}
	
	
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ "- is Passed");

	}
	
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+" - is Failed");
		test.log(Status.FAIL,"The reason test got FAILED is: "+ result.getThrowable().getMessage());
		
		try 
		{    
			BaseClass bc=new BaseClass();                                   //This is used to capture Screenshot of failed test and add it to the ExtentReports
			String imgPath=bc.captureScreen(result.getName());
		
			test.addScreenCaptureFromPath(imgPath);                                                            
		
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
	   
	}
	
	public void onTestSkipped(ITestResult result) 
	{
	    test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.SKIP, result.getName()+" - is Skipped");
	    test.log(Status.SKIP,"The reason test got SKIPPED is: "+ result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) 
	{
	    extent.flush();
	    
	    //below code is used to open extent report automatically when execution of test cases is completed
	    
	   String ExtentReportPath= System.getProperty("user.dir")+"\\reports\\"+repName;
	   
	   File extentReport = new File(ExtentReportPath);
	   
	   try {
		   
		   Desktop.getDesktop().browse(extentReport.toURI());
		   
	   }catch(IOException e1)
	   {
		   e1.printStackTrace();
	   }
	   
	  
	}
	
	
	
	
	
	
	

}
