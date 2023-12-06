package baseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

static public WebDriver driver;
public Logger logger;
public Properties p;
	
	@SuppressWarnings({ "deprecation"})
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String opr_sys,String br) throws IOException
	{
		FileReader file=new FileReader(".//src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
				{
					DesiredCapabilities capabilities = new DesiredCapabilities();
					
					//to set/select Operating System on remote systems
					if(opr_sys.equalsIgnoreCase("windows"))
					{
						capabilities.setPlatform(Platform.WIN11);
					}
					else if(opr_sys.equalsIgnoreCase("mac"))
					{
						capabilities.setPlatform(Platform.MAC);
					}
					else
					{
						System.out.println("invalid operating system");
						return;
					}
					
					//to set/select browsers on remote systems
					
					switch(br.toLowerCase())
					{
					case "chrome" : capabilities.setBrowserName("chrome");break;
					case "edge" : capabilities.setBrowserName("MicrosoftEdge");break;
					case "safari" :capabilities.setBrowserName("safari");break;
					case "firefox" :capabilities.setBrowserName("Firefox");break;
					default : System.out.println("Invalid browser");return;
					}
					
					String hub_url="http://192.168.1.7:4444";
					
					driver=new RemoteWebDriver(new URL(hub_url),capabilities );
				}
				else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
					{
						switch(br.toLowerCase())
						{
						case "chrome" : driver=new ChromeDriver();break;
						case "edge" : driver=new EdgeDriver();break;
						case "safari" :driver=new SafariDriver();break;
						case "firefox" :driver=new FirefoxDriver();break;
						default:System.out.println("Invalid Browser");return;
						}
					}
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();		
	}
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		return (str+"@"+num);
	}
	
	
	public String captureScreen(String testName)
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		Date dt=new Date();
		String timeStamp=df.format(dt);
		
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\"+"_"+testName+"_"+timeStamp+".png";
		
		File targetFile=new File (targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
