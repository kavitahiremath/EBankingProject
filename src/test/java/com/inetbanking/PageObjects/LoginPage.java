package com.inetbanking.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
	
	
	private @FindBy(name="uid")
	WebElement txtUsername;
	
	private @FindBy(name="password")
	WebElement txtPassword;
	
	private @FindBy(name="btnLogin")
	WebElement LoginBtn;
	
	private @FindBy(xpath="//a[text()='Log out']")
	WebElement LogoutBtn;
	//private By text=By.xpath("//td/marquee[@class='heading3']");
	
	
	public LoginPage()
	{		
		PageFactory.initElements(driver,this);
	}
	

	
	public void Login(String Uname,String Pword)
	{
		txtUsername.sendKeys(Uname);
		txtPassword.sendKeys(Pword);
		LoginBtn.click();
	}	
	
	public void Logout() {
		LogoutBtn.click();
	}
}
