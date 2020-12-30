package com.w2a.base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.w2a.pages.actions.TopNavigation;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;

public class Page {
	
	/*
	 * WebDriver
	 * 
	 * ExcelReader
	 * Logs
	 * WebDriverWait
	 * ExtentReports
	 * 
	 * 
	 * 
	 */
	public static WebDriver driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;	
	public static String browser;
	public static TopNavigation topNav;
	public ExtentManager rep;
	public static ExtentTest test;
	
	public static void initConfiguration(){
		
		if(Constants.browser.equals("firefox")){
			
			driver = new FirefoxDriver();
			log.debug("Launching Firefox");
		}else if(Constants.browser.equals("chrome")){
			
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver(options);
			log.debug("Launching Chrome");
		}else if(Constants.browser.equals("ie")){
			
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			
			driver = new InternetExplorerDriver();
			log.debug("Launching IE");
		}
		
		driver.get(Constants.testsiteurl);
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    wait = new WebDriverWait(driver, 30);
	    ExtentManager rep = new ExtentManager();
		ExtentTest test;
		topNav = new TopNavigation(driver);
	
	}
	
	
	public static void click(WebElement element) {
		
		element.click();
		
		log.debug("Clicking on an Element : "+element);
		test.log(Status.INFO, "Clicking on : " + element);
	}
	
	
	public static void type(WebElement element, String value) {
		
		element.sendKeys(value);

		log.debug("Typing in an Element : "+element+" entered value as : "+value);
		
		test.log(Status.INFO, "Typing in : " + element + " entered value as " + value);

	}
	
	public static WebElement waitForElement(int timeOut,By element) {
//		wait = new WebDriverWait(driver,10);
	
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
		log.debug("Waiting for in an Element : "+element);
		
		test.log(Status.INFO, "Waiting for in an Element : " + element );
//		return wait.until(ExpectedConditions.presenceOfElementLocated(element));
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class)
				.pollingEvery(Duration.ofSeconds(5))
				.withTimeout(Duration.ofSeconds(timeOut));
		WebElement foo = fwait.until(new Function<WebDriver,WebElement>(){

			@Override
			public WebElement apply(WebDriver arg0) {
				// TODO Auto-generated method stub
				System.out.println("polling");
				return driver.findElement(element);
			}
			
			
		});
		return foo;
				               
	
	}
	
	
	
	public static void quitBrowser(){
		
		driver.quit();
		
	}


	
	

}
