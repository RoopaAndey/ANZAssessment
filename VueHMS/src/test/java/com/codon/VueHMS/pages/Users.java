package com.codon.VueHMS.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codon.VueHMS.utilities.Util;

public class Users {

	
	WebDriver driver;
	
	public Users(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[text()='Administration']")
	private WebElement administration;
	public void clickOnAdministrationTab() {
		
		administration.click();
	}
	
	@FindBy(xpath="//div[text()='Users']")
	private WebElement users;
	public void clickOnUsers() {
		
		users.click();
	}
	
	
	
	//=============================================================================================================================================
	
	// User Creation
	
	
	@FindBy(xpath="//span[text()=' New ']")
	private WebElement newbtn;
	public void clickOnNewBtn() {
		
		newbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Username']//following-sibling::input")
	private WebElement untxtfld;
	public void enterUserName(String un) {
		
		untxtfld.sendKeys(un);
	}
	
	@FindBy(xpath="//label[text()='FirstName']//following-sibling::input")
	private WebElement firstnametxtfld;
	public void enterFirstName(String fname) {
		
		firstnametxtfld.sendKeys(fname);
	}
	
	@FindBy(xpath="//label[text()='LastName']//following-sibling::input")
	private WebElement lastnametxtfld;
	public void enterLastName(String lname) {
		
		lastnametxtfld.sendKeys(lname);
	}
	
	@FindBy(xpath="//label[text()='Email']//following-sibling::input")
	private WebElement emailtxtfld;
	public void enterEmail(String emailid) {
		
		emailtxtfld.sendKeys(emailid);
	}
	
	@FindBy(xpath="//label[text()='Password']//following-sibling::input")
	private WebElement passwordtxtfld;
	public void enterPassword(String pwd) {
		
		passwordtxtfld.sendKeys(pwd);
	}
	
	@FindBy(xpath="//label[text()='Confirm Password']//following-sibling::input")
	private WebElement confirmpwdtxtfld;
	public void enterConfirmPassword(String confirmpwd) {
		
		confirmpwdtxtfld.sendKeys(confirmpwd);
	}
	
	@FindBy(xpath="//label[text()='Active User']/preceding-sibling::div//div")
	private WebElement activeusercheckbox;
	public void selectActiveUserCheckBox() {
		
		activeusercheckbox.click();
	}
	
	@FindBy(xpath="//span[text()=' Continue ']/parent::button")
	private WebElement continuebtnstatus;
	public String getContinueButtonStatus() {
		
		return continuebtnstatus.getAttribute("Class");
	}
	
	@FindBy(xpath="//span[text()=' Continue ']")
	private WebElement continuebtn;
	public void clickOnContinueBtn() {
		
		continuebtn.click();
	}
	
	@FindBy(xpath="//label[text()='State']/following-sibling::input")
	private WebElement statetxtfld;
	public void enterState(String state) {
		
		statetxtfld.sendKeys(state);
	}
	
	@FindBy(xpath="//label[text()='City']/following-sibling::input")
	private WebElement citytxtfld;
	public void enterCity(String city) {
		
		citytxtfld.sendKeys(city);
	}
	
	@FindBy(xpath="//label[text()='Country']/following-sibling::input")
	private WebElement countrytxtfld;
	public void enterCountry(String country) {
		
		countrytxtfld.sendKeys(country);
	}
	
	@FindBy(xpath="//label[text()='ZipCode']/following-sibling::input")
	private WebElement zipcodetxtfld;
	public void enterZipcode(String zipcode) {
		
		zipcodetxtfld.sendKeys(zipcode);
	}
	
	@FindBy(xpath="//label[text()='Phone Number']/following-sibling::input")
	private WebElement phonenotxtfld;
	public void enterPhoneNumber(String phoneno) {
		
		phonenotxtfld.sendKeys(phoneno);
	}
	
	public void clearPhoneno() {
		
		phonenotxtfld.sendKeys(Keys.CONTROL+"a");
		phonenotxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//label[text()='Fax']/following-sibling::input")
	private WebElement faxtxtfld;
	public void enterFax(String fax) {
		
		faxtxtfld.sendKeys(fax);
	}
	
	@FindBy(xpath="//label[text()='Location']/following-sibling::input[@type='text']")
	private WebElement locationdropdown;
	public void clickOnLocationDropdown() {
		
		locationdropdown.click();
	}
	
	private WebElement selectlocation(String loc) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+loc+"']"));
	}
	public void selectLocation(String location) {
		
		Actions actions = new Actions(driver);
		

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {
			

			actions.sendKeys(Keys.DOWN).build().perform();
			Util.sleepTime(100);
		}

			selectlocation(location).click();
	}
	
	
	@FindBy(xpath="//label[text()='Roles']/following-sibling::input[@type='text']")
	private WebElement roledropdown;
	public void clickOnRoleDropdown() {
		
		roledropdown.click();
	}
	
	private WebElement selectrole(String role) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+role+"']"));
	}
	public void selectRole(String role) {
		
		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<list;i++) {
			
			actions.sendKeys(Keys.DOWN).build().perform();
		}
		
		selectrole(role).click();
	}
	

	@FindBy(xpath="//label[text()='Address1']/following-sibling::textarea")
	private WebElement addresstxtfld;
	public void enterAddress(String address) {
		
		addresstxtfld.sendKeys(address);
	}
	
	@FindBy(xpath="//span[text()=' Save ']/parent::button")
	private WebElement savebtnstaus;
	public String getSaveBtnStatus() {
		
		return savebtnstaus.getAttribute("class");
	}
	
	@FindBy(xpath="//span[text()=' Save ']")
	private WebElement savebtn;
	public void clickOnSaveBtn() {
		
		savebtn.click();
	}
	
	@FindBy(xpath="//div[@class='v-snack__content']//span")
	private WebElement status;
	public String getAlert() {
		
		return status.getText();	
	}
	
	@FindBy(xpath="//div[@class='row']//button//span[text()=' Cancel ']")
	private WebElement cancelbtn1;
	public void clickOnCancelBtn1() {
		
		cancelbtn1.click();
	}
	
	@FindBy(xpath="//div[@class='row mx-1']//button//span[text()=' Cancel ']")
	private WebElement cancelbtn;
	public void clickOnCancelBtn2() {
		
		cancelbtn.click();
	}
	
	
	
	//===========================================================================================================================================
	
	
	// Edit Users
	
	
	
	
	@FindBy(xpath="//div[text()='Rows per page:']//i[contains(@class,' mdi mdi-menu-down')]")
	private WebElement rowsperpagedropdown;
	public void clickOnRowsPerPageDropdown() {
		
		rowsperpagedropdown.click();
	}
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='All']")
	private WebElement selectrowsperpage;
	public void selectAllRowsPerPage(){
		
		selectrowsperpage.click();
	}
	
	private WebElement clickoneditntm(String user) {
		
		return driver.findElement(By.xpath("//td[text()='"+user+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnEditBtn(String user) {
		
		clickoneditntm(user).click();
	}
	
	
	
	//===========================================================================================================================================
	
	
	//Reset Password
	
	
	
	private WebElement clickonresetpasswprdbtn(String user) {
		
		return driver.findElement(By.xpath("//td[text()='"+user+"']/following-sibling::td//button[contains(@class,'mdi mdi-lock-reset')]"));
	}
	public void clickOnResetPasswordBtn(String user) {
		
		clickonresetpasswprdbtn(user).click();
	}
	
	@FindBy(xpath="//label[text()='New Password']/following-sibling::input")
	private WebElement newpasswordtctfld;
	public void enterNewPassword(String pwd) {
		
		newpasswordtctfld.sendKeys(pwd);
	}
	
	@FindBy(xpath="//label[text()='Confirm Password']/following-sibling::input")
	private WebElement confirmpasswordtxtfld;
	public void enterConfirmNewPassword(String confirmpwd) {
		
		confirmpasswordtxtfld.sendKeys(confirmpwd);
	}
	
	@FindBy(xpath="//span[text()=' Cancel ']")
	private WebElement cancelbtn3;
	public void clickOnCancelBtn3() {
		
		cancelbtn3.click();
	}
	
	
	
	//=====================================================================================================================================


	// Delete Users


	private WebElement clickondeletentm(String user) {

		return driver.findElement(By.xpath("//td[text()='"+user+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnDeleteBtn(String user) {

		clickondeletentm(user).click();
	}
	
	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOkBtn() {
		
		okbtn.click();
	}





	
	
	
	
	
	
	
	
	
	
	
//	List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));
//	
//	int list=dropdownlist.size();
//	JavascriptExecutor js=(JavascriptExecutor)driver;
//	js.executeScript("arguments[0].scrollIntoView();", list);
//
//

	
	
	
	
	
	
	
	
	
	
	
	
	
}
