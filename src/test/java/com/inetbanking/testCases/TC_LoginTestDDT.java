package com.inetbanking.testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.PageObjects.BaseClass;
import com.inetbanking.PageObjects.LoginPage;
import com.inetbanking.Utilities.XLUtils;

public class TC_LoginTestDDT extends BaseClass
{
	Logger logger=Logger.getLogger(TC_LoginTest.class);
	LoginPage lp;
    @Test(dataProvider = "logindata")
	public void loginDDT(String username,String password){

      lp=new LoginPage();
      lp.Login(username, password);
      logger.info("entered username and password");

      if(alertisPresent()==true) {
    	  driver.switchTo().alert().accept();
    	  driver.switchTo().defaultContent();
    	  Assert.assertTrue(false);
    	  logger.warn("login failed");
    	 
      }else {
    	  logger.info("login passed");
    	  Assert.assertTrue(true);
    	  lp.Logout();
    	  driver.switchTo().alert().accept();
    	  driver.switchTo().defaultContent();
      }

	}

    public boolean alertisPresent() {
    	try {
    		driver.switchTo().alert();
    		return true;
    	}catch(NoAlertPresentException e){
    		return false;
    	}
    }
	@DataProvider(name = "logindata")
	String[][] getData() throws Throwable
	{
//		String path = "C:\\Users\\SPURGE\\Documents\\inetBankingV1\\src\\test\\java\\com\\inetbanking\\TestDa ta\\LoginDataXL.xlsx";

		int rownum= XLUtils.getRowCount( "LoginDetails");
		int colcount = XLUtils.getCellCount( "LoginDetails", 1);

		String[][] logindata = new String[rownum][colcount];
		for(int i =1;i<=rownum;i++ )
		{
			for(int j =0; j<colcount;j++)
			{
				logindata[i-1][j] = XLUtils.getCellData( "LoginDetails", i , j);	
			}
		}
		return logindata; 
	}

}
