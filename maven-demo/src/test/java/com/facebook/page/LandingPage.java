package com.facebook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageobjects.common.PageBase;

public class LandingPage extends PageBase {
	

	
	public LandingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="email")
	public WebElement user_name_txt;
	
	@FindBy(id="pass")
	public WebElement password_txt;
	
	@FindBy(css="#loginbutton #u_0_l")
	public WebElement login_btn;
	
	@FindBy(id = "u_0_1")
	public WebElement first_name_txt;
	
	@FindBy(id = "u_0_3")
	public WebElement sur_name_txt;
	
	@FindBy(id = "u_0_5")
	public WebElement email_txt;
	
	@FindBy(id = "u_0_8")
	public WebElement re_enter_email_txt;
	
	@FindBy(id = "u_0_a")
	public WebElement new_password_txt;
	
	@FindBy(id = "day")
	public WebElement birthday_day_ddl;
	
	@FindBy(id = "month")
	public WebElement birthday_month_ddl;
	
	@FindBy(id = "year")
	public WebElement birthday_year_ddl;
	
	@FindBy(id = "u_0_d")
	public WebElement female_gender_check;
	
	@FindBy(id = "u_0_e")
	public WebElement male_gender_check;
	
	@FindBy(id = "u_0_i")
	public WebElement sign_up_btn;
	
	Select dropdown;
	
	public void Login(String email, String password){
		user_name_txt.sendKeys(email);
		password_txt.sendKeys(password);
		login_btn.submit();
	}
	
	public void SignUp(String first_name, String sur_name, String email, String password,String day ,String month , String year ,int gender)
	{
		first_name_txt.sendKeys(first_name);
		sur_name_txt.sendKeys(sur_name);
		email_txt.sendKeys(email);
		re_enter_email_txt.sendKeys(email);
		new_password_txt.sendKeys(password);
		dropdown = new Select(birthday_day_ddl);
		dropdown.selectByValue(day);;
		dropdown = new Select(birthday_month_ddl);
		dropdown.selectByValue(month);
		dropdown = new Select(birthday_year_ddl);
		dropdown.selectByValue(year);
		if (gender == 1) //male
				{
			male_gender_check.click();
				}
		else{
			female_gender_check.click();
		}
		
		sign_up_btn.submit();
		
	}

}
