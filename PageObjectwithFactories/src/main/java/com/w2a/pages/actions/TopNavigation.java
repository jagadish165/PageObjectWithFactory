package com.w2a.pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.w2a.base.Page;
import com.w2a.pages.locators.TopNavigationLocators;

public class TopNavigation {
	
	public TopNavigationLocators topNavigation;

	public TopNavigation(WebDriver driver){
		
		this.topNavigation = new TopNavigationLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory, this.topNavigation);

		
	}
	
	public SigninPage gotoSignIn(){
		
	//	Page.click(topNavigation.account);
	Page.waitForElement( 20, By.cssSelector("#gc-custom-header-nav-bar-acct-menu > button > div"));
        Page.click(topNavigation.signinBtn);
        Page.click(topNavigation.signInButtonBig);
		
		return new SigninPage();
		
	}

	public void gotoCreateAccount(){
		
		
		
	}
	
	public void gotoList(){
		
		
	}
	
	
	public void gotoSupport(){
		
		
	}
	
	
	public void gotoHome(){
		
		
	}
	
	
	public void gotoFlights(){
		
		
	}
}
