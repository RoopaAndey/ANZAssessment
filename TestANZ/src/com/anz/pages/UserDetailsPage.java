package com.anz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetailsPage {
	
WebDriver driver;
	
	public UserDetailsPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//enter annual income
	@FindBy(xpath="//label[contains(text(),'Your annual income (before tax)')]/following-sibling::div/input")
	private WebElement anlinc;
	public void entrAnnualIncome(String income) {
		anlinc.sendKeys(income);
		
	}
	//Enter other income
	@FindBy(xpath="//label[contains(text(),'Your annual other income (optional)')]/following-sibling::div/input")
	private WebElement othrinc;
	public void entrOtherIncome(String othrin) {
		othrinc.sendKeys(othrin);
		
	}
	//Enter living expenses
	//Enter other income
	@FindBy(xpath="//span[@id='q3q1i1']/following-sibling::input")
	private WebElement livexp;
	public void entrLiveExpenses(String live) {
		livexp.sendKeys(live);
	}
	
	//Enter other loan repayments
	@FindBy(xpath="//input[@aria-labelledby='q3q3']")
	private WebElement repay;
	public void entrLoanRepay(String loan) {
		repay.sendKeys(loan);
	}
	//Enter credit card limit
	@FindBy(xpath="//input[@aria-labelledby='q3q5']")
	private WebElement cc;
	public void entrCClimit(String limit) {
		cc.sendKeys(limit);
	}
	
	//Click on borrow button
	@FindBy(xpath="//button[@id='btnBorrowCalculater']")
	private WebElement borrow;
	public void clkBorrowBtn() {
		borrow.click();
	}
	//Get Borrow result
	@FindBy(xpath="//span[@id='borrowResultTextAmount']")
	private WebElement result;
	public String getResult() {
		return result.getText();
	}
	
	//click on start over 
	//@FindBy(xpath="//div[@class='borrow__scenario__container']//button[contains(text(),'Start')]")
	@FindBy(xpath="//div[@class='borrow__result__action']//div/button[@aria-label='Start over']")
	private WebElement start;
	public void clkStartOver() {
		start.click();
	}
	//get the error message
	@FindBy(xpath="//div[@class='borrow__scenario__container']//div[contains(text(),'unable to give')]")
	private WebElement error;
	public String getErrormsg() {
		return error.getText();
	}
}
