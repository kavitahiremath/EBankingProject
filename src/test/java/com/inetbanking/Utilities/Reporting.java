package com.inetbanking.Utilities;
//Listener class used to generate extent reports


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		
		// initialize the HtmlReporter
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output//"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"\\extent-config.xml");
		
		//initialize ExtentReports and attach the HtmlReporter
		 extent = new ExtentReports();
	     extent.attachReporter(htmlReporter);
	     
	   //To add system or environment info by using the setSystemInfo method.
	     extent.setSystemInfo("HostName", "localhost");
	     extent.setSystemInfo("Environment", "QA");
	     extent.setSystemInfo("user", "Kavita");
	    
	   //configuration items to change the look and feel
	   //add content, manage tests etc
	     
	       // htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("InetBankig Test Project");//title
	        htmlReporter.config().setReportName("Functional Automation Test");//name of the report
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of chart
	        htmlReporter.config().setTheme(Theme.STANDARD);
	}
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information
	}
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the passed information
		
		String screenShotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		
		File file=new File(screenShotPath);
		if(file.exists())
		{
			try {
				//addScreenCapture of Extent Test class to fetch the screenshot and append it to the Extent Report
				logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenShotPath));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));//send the passed information
		
	}
	
	public void onFinish(ITestResult tr)
	{
		//Flush method is used to erase any previous data on the report and create a new report.
		extent.flush();
	}
}
