package com.inetbanking.PageObjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.inetbanking.Utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports  report;
	public static ExtentTest test;
	
	ReadConfig readconfig=new ReadConfig();
	public static Properties prop;
	public static WebDriver driver;	
	Logger logger=Logger.getLogger(BaseClass.class);
	//public static Logger logger;
	
	//public String URL=https://demo.guru99.com/v4/index.php;
	public String URL=readconfig.getAppURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public String browserName=readconfig.getBrowser();

//	public BaseClass()
//	{
//		File file=new File("./Configuration/config.properties");
//		try
//		{
//		prop=new Properties();
//		FileInputStream fis=new FileInputStream(file);
//		prop.load(fis);
//		}
//		catch(Exception e)
//		{
//		System.out.println("Exception is :"+e.getMessage());
//		}
//	}
	
	//@Parameters("browser")
	@BeforeSuite
	public void beforeSuite()
	{
		String datetimestamp =new SimpleDateFormat("yyyyMMddHHmmss" ).format(new Date());
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Test-Output"+datetimestamp+".html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
	}
	@BeforeClass
	public void setup()
	{		
		
		//String browserName=prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}
		
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		else if(browserName.equals("edge"))
		{
			WebDriverManager.iedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("Incorrect Browser");	
		}
		
		
		logger.info("Browser Launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(URL);
		logger.info("URL is opened");
		//logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		test=report.createTest("login");
	}
	@AfterClass
	public void tearDown()
	{
		logger.info("Closing the browser");
		driver.quit();
		
	}
	public void captureScreenShot(WebDriver driver, String tname) throws Throwable
	{
	TakesScreenshot ts =  (TakesScreenshot)driver;	
	File source = ts.getScreenshotAs(OutputType.FILE);
	File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
	FileUtils.copyFile(source, target);
	System.out.println("ScreenShot Captured");
	}

	@AfterTest
	public void afterTest()
	{
		report.flush();
	}
}
