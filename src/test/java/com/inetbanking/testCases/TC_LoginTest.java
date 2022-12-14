package com.inetbanking.testCases;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.PageObjects.BaseClass;
import com.inetbanking.PageObjects.LoginPage;

//Login test changed

public class TC_LoginTest extends BaseClass {
	
	Logger logger=Logger.getLogger(TC_LoginTest.class);
	LoginPage lp;

	@Test
	public void LoginTest() throws Throwable
	{
			
		
		
		lp=new LoginPage();
		lp.Login("mngr458559","emamEhy");
		logger.info("User Logged-in Successfully and entered into HomePage");
		
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("LoginTest Passed");
		}
		else
		{
			captureScreenShot(driver, "LoginTest" );
			Assert.assertTrue(false);
			logger.info("LoginTest Failed");
		}
		
	}

}
