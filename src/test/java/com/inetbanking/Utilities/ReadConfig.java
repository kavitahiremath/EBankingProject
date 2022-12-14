package com.inetbanking.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	
	public ReadConfig() {
		
		File file=new File("./Configuration/config.properties");
		try
		{
		prop=new Properties();
		FileInputStream fis=new FileInputStream(file);
		prop.load(fis);
		}
		catch(Exception e)
		{
		System.out.println("Exception is :"+e.getMessage());
		}
	}
	
	
	public String getAppURL()
	{
		String url=prop.getProperty("URL");
		return url;
		}
	

	public String getUsername()
	{
		String username=prop.getProperty("username");
		return username;
	}

	public String getPassword()
	{
		String password=prop.getProperty("password");
		return password;	
	}

	public String getBrowser()
	{
		String browser=prop.getProperty("browser");
		return browser;	
	}
}
