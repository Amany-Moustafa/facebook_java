package test.java;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.facebook.demo.Base;
import com.facebook.page.*;

public class SignInTest {
	LandingPage landing_pageObj;
	HomePage home_pageObj;
	
	private String email = "stayclassytest@gmail.com";
	private String pass = "classy1234";

	
	@BeforeClass
	public void init(){
		Base base_obj = new Base();
		WebDriver driver = base_obj.getDriver();
		landing_pageObj = new LandingPage(driver);
		home_pageObj = new HomePage(driver);
	}
	
	@Test(priority=1)
	public void Login_with_valid_credintials(){
		landing_pageObj.Login(email, pass);
		Assert.assertTrue(home_pageObj.home_btn.isDisplayed());	
		home_pageObj.logout();
	}
	
	@AfterClass
	public void stopDriver(){
		// driver.quit();
	}
}
