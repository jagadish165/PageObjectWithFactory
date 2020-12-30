package com.w2a.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.w2a.base.Page;



public class ExtentManager {
	
	public static ExtentReports extent;
	
	
	  public ExtentManager (){
		
		if(extent==null){
			 extent = new ExtentReports();
			ExtentSparkReporter html = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html");
			// ExtentXReporter extentx = new ExtentXReporter(ystem.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml");
			 
			
			 extent.attachReporter(html);
			 Page.test = extent.createTest("Test Start");
			
			//extent = new ExtentReports();
			//extent.loadConfig(new File(S));
			
		}
		
		
		
	}

}
