package com.facebook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageobjects.common.PageBase;


public class HomePage extends PageBase
{	
	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(id = "pageLoginAnchor")
	public WebElement pageLoginAnchor;
	
	@FindBy(id = "show_me_how_logout_1")
	public WebElement logout_link;
	
	@FindBy(id = "u_0_3")
	public WebElement home_btn;
	
	@FindBy(css = "ul._54nf li:nth-child(11) a")
	public WebElement setting_btn;
	
	@FindBy(xpath = ".//*[@id='navItem_security']/a/div[2]/div")
	public WebElement security_lbl;
	
	@FindBy(xpath = ".//*[@id='js_g']/span[1]")
	public WebElement deactivate_fb_edit;
	
	@FindBy(css = ".ptm>a")
	public WebElement deactivate_link;
	
	@FindBy(css= ".data>input")
	public WebElement password_txt;
	
	@FindBy(id= "u_jsonp_15_0")
	public WebElement continue_btn;
	
	public void logout(){
		pageLoginAnchor.click();
		logout_link.click();
	}
	
	public void setting_button_click(){
		pageLoginAnchor.click();
		setting_btn.click();
	}
	
	public void deactivate_your_account(){
		security_lbl.click();
		deactivate_fb_edit.click();
		deactivate_link.click();
	}
	
	public void fill_confirm_password_for_deactivate(String password){
		password_txt.sendKeys(password);
		continue_btn.click();
	}
	
}
