package com.inetbanking.testCases;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.PageObjects.AddCustomerPage;
import com.inetbanking.PageObjects.BaseClass;
import com.inetbanking.PageObjects.LoginPage;

public class TC_AddCustomerTest extends BaseClass{
	//Code Updated
	Logger logger=Logger.getLogger(TC_LoginTest.class);
	LoginPage lp;

	@Test
	public void addNewCustomer() throws Throwable
	{
		LoginPage lp=new LoginPage();
		lp.Login(username,password);
		logger.info("login Sucessfully");
		Thread.sleep(3000);

		AddCustomerPage addcust=new AddCustomerPage(driver);
		Thread.sleep(3000);
		addcust.clickAddNewCustomer();

		//			 Thread.sleep(5000);
		//			 driver.navigate().refresh();
		//			 Thread.sleep(5000);



//		Actions act=new Actions(driver);
//		act.moveByOffset(100, 250).perform();
//		act.click().perform();

				Robot r=new Robot();
				 r.mouseMove(100, 250);
				  r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				  r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


		logger.info("providing customer details....");


		addcust.custName("Jayesh");
		addcust.custgender("male");
		addcust.custdob("11","16","1996");
		Thread.sleep(5000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("987890091");

		String email=randomString()+"@gmail.com";

		addcust.custemailid(email);
		addcust.custpassword("abc123");
		addcust.custsubmit();

		Thread.sleep(3000);

		logger.info("validation started....");

		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");

		}
		else
		{
			logger.info("test case failed....");
			captureScreenShot(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}

	}

	public static String randomString() {
		String str=RandomStringUtils.randomAlphabetic(8);
		return str;
	}
	public static String randomNumber() {
		String str=RandomStringUtils.randomNumeric(4);
		return str;
	}
}