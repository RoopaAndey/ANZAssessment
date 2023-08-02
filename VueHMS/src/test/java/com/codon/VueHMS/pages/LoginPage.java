package com.codon.VueHMS.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//==========================================================================================================================================
	
	// Login 
	
	
	
	@FindBy(xpath="//div//label[text()='Username or Email']/following-sibling::input")
	private WebElement untxtfld;
	public void enterUsernameOrEmail(String un) {
		
		untxtfld.sendKeys(un);
	}
	
	@FindBy(xpath="//div//label[text()='Password']/following-sibling::input")
	private WebElement pwdtxtfld;
	public void enterPassword(String pwd) {
		
		pwdtxtfld.sendKeys(pwd);
	}
	
	@FindBy(xpath="//span[text()=' Login ']")
	private WebElement loginbtn;
	public void clickOnLoginBtn() {
		
		loginbtn.click();
	}
	
	public boolean isLoginBtnEnabled() {
		
		boolean value=loginbtn.isEnabled();
		
		return value;
		
	}
	
	
	@FindBy(xpath="//div//button/following-sibling::button/preceding::button")
	private WebElement loginbtnstatus;
	public String getLoginBtnStatus() {
		
		return loginbtnstatus.getAttribute("class");
	}
	
	@FindBy(xpath="//div[@class='v-snack__content']//span")
	private WebElement status;
	public String getAlert() {
		
		return status.getText();	
	}
	
	@FindBy(xpath="//span[text()=' Reset ']")
	private WebElement resetbtn;
	public void clickOnResetBtn() {
		
		resetbtn.click();
	}
	
	
	
	
	//Logout
	
	
	@FindBy(xpath="//i[contains(@class,'v-icon notranslate mdi mdi-power')]")
	private WebElement logoutbtn;
	public void clickOnLogoutBtn() {
		
		logoutbtn.click();
	}
	

	
	
}
