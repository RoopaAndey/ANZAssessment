package com.anz.testcases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.anz.pages.UserDetailsPage;
import com.anz.utilities.CommonUtil;
import com.anz.utilities.Util;
import com.anz.utilities.ExcelRead;

public class UserInfo {
	
	WebDriver driver;
	String browser,url,path;
	
	@BeforeClass
	
	public void openANZpage() {
	
		try {
	//launch Browser
   	this.browser=CommonUtil.getProperty("config", "browser");
   	this.url=CommonUtil.getProperty("config", "url");
   	
	
	 System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.31.0-win64\\geckodriver.exe");
	 driver=new FirefoxDriver();
	 System.out.println("Firefox opened");
	 Util.sleepTime(6000);
	 
	 //Open URL
	 driver.get(url);
	 driver.manage().window().maximize();
	 Util.sleepTime(3000);
	 	 			
	}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	@Test
	
	public void getAmountwithProperDetails() throws IOException, Exception {
		this.path=CommonUtil.getProperty("config", "path");
		 UserDetailsPage up=PageFactory.initElements(driver, UserDetailsPage.class);
		 
		 String sheet="CreateUser";
			
			for(int exclrow=1;exclrow<=1;exclrow++) {
			
			String income=ExcelRead.getCellData(path, sheet, exclrow, 0);
			String otherincome=ExcelRead.getCellData(path, sheet, exclrow, 1);
			String livexp=ExcelRead.getCellData(path, sheet, exclrow, 2);
			String loanrepay=ExcelRead.getCellData(path, sheet, exclrow, 3);
			String limit=ExcelRead.getCellData(path, sheet, exclrow, 4);
			
			up.entrAnnualIncome(income);
			System.out.println("Entered Annual Income: "+income);
			Util.sleepTime(2000);
			
			up.entrOtherIncome(otherincome);
			System.out.println("Entered Other Annual Income: "+otherincome);
			Util.sleepTime(2000);
			
			up.entrLiveExpenses(livexp);
			System.out.println("Entered living expenses: "+livexp);
			Util.sleepTime(2000);
			
			up.entrLoanRepay(loanrepay);
			System.out.println("Entered Loan repayment: "+loanrepay);
			Util.sleepTime(2000);
			
			up.entrCClimit(limit);
			System.out.println("Entered Credit card limit: "+limit);
			Util.sleepTime(2000);
			
			up.clkBorrowBtn();
			System.out.println("Clicked on Borrow button with given details");
			Util.sleepTime(2000);
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,600)");
					
		 }
			
			
	}
	
	@Test
	
	public void startOverthePage() throws IOException{
		 UserDetailsPage up=PageFactory.initElements(driver, UserDetailsPage.class);
		 
		 up.clkStartOver();
		 System.out.println("Clicked on start over and refreshed the page");
		 Util.sleepTime(2000);
		 
}
	
	@Test
	public void getErrorMsg() throws IOException, Exception {
		this.path=CommonUtil.getProperty("config", "path");
		 UserDetailsPage up=PageFactory.initElements(driver, UserDetailsPage.class);
		 
		 	String sheet="ErrorMessage";
					
			
			for(int exclrow=1;exclrow<=1;exclrow++) {
						
			String liveexpen=ExcelRead.getCellData(path, sheet, exclrow, 0);	
			
			up.entrLiveExpenses(liveexpen);
			System.out.println("Entered living expenses: "+liveexpen);
			Util.sleepTime(2000);
			
			up.clkBorrowBtn();
			System.out.println("Clicked on borrow button by giving only living expenses");
			Util.sleepTime(2000);
			
			String errormsg=up.getErrormsg();
			System.out.println("Error message displayed as: "+errormsg);
			Util.sleepTime(2000);
			
			if(errormsg.contains("unable to give you an estimate ")) {
				ExcelRead.setCellData(path, sheet, exclrow, 1, errormsg);
				Util.sleepTime(1000);
			}
			
					
			}
	}
	@AfterClass
	public void closePage() {
		driver.close();
		System.out.println("Browser closed successfully");
		Util.sleepTime(1000);
	}
	
	

}
