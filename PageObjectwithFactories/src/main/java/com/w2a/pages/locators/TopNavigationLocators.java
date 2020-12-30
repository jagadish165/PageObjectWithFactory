package com.w2a.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class TopNavigationLocators {

	
	
	@FindBy(css="div.heading-container>div>a")
	public WebElement signInButtonBig;
	@FindAll({
		@FindBy(css="#gc-custom-header-nav-bar-acct-menu > button > div"),
		
	})
	public WebElement signinBtn;
	
}
