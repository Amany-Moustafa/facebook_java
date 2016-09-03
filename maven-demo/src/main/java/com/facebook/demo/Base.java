package com.facebook.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	public WebDriver driver =null;
public WebDriver getDriver(){
		driver = new FirefoxDriver();
		driver.navigate().to("http://www.facebook.com");
		driver.manage().window().maximize();
		return driver;
	}
	
}
