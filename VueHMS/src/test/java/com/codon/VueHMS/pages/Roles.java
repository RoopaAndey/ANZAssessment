package com.codon.VueHMS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Roles {

	
	
	WebDriver driver;
	
	public Roles(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//div[text()='Administration']")
	private WebElement administration;
	public void clickOnAdministrationTab() {
		
		administration.click();
	}
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='Roles']")
	private WebElement roles;
	public void clickOnRoles() {
		
		roles.click();
	}
	
	
	
	//==============================================================================================================================================
	
	// Role creation
	
	
	
	@FindBy(xpath="//span[text()=' New ']")
	private WebElement newbtn;
	public void clickOnNewBtn() {
		
		newbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Role Name']/following-sibling::input")
	private WebElement rolenametxtfld;
	public void enterRoleName(String role) {
		
		rolenametxtfld.sendKeys(role);
	}
	
	private WebElement selectuserpermissions(int i) {

		return driver.findElement(By.xpath("//td[text()='Users']/parent::tr//td["+i+"]//i"));
	}
	public void selectUserPermissions(int i) {

		selectuserpermissions(i).click();
	}

	private WebElement selectdocumenttypepermissions(int i) {

		return driver.findElement(By.xpath("//td[text()='Document Type']/parent::tr//td["+i+"]//i"));
	}
	public void selectDocumentTypePermissions(int i) {

		selectdocumenttypepermissions(i).click();
	}

	private WebElement selectdoctorpermissions(int i) {

		return driver.findElement(By.xpath("//td[text()='Doctors']/parent::tr//td["+i+"]//i"));
	}
	public void selectDoctorPermissions(int i) {

		selectdoctorpermissions(i).click();
	}
	
	private WebElement selectmedicinepermissions(int i) {

		return driver.findElement(By.xpath("//td[text()='Medicine']/parent::tr//td["+i+"]//i"));
	}
	public void selectMedicinePermissions(int i) {

		selectmedicinepermissions(i).click();
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
	
	@FindBy(xpath="//span[text()=' Cancel ']")
	private WebElement cancelbtn;
	public void clickOnCancelBtn() {
		
		cancelbtn.click();
	}
	
	@FindBy(xpath="//div[@class='v-snack__content']//span")
	private WebElement status;
	public String getAlert() {
		
		return status.getText();	
	}
	
	
	
	
	//======================================================================================================================================
	
	//Edit Role
	
	
	@FindBy(xpath="//div[text()='Rows per page:']//i[contains(@class,' mdi mdi-menu-down')]")
	private WebElement rowsperpagedropdown;
	public void clickOnRowsPerPageDropdown() {
		
		rowsperpagedropdown.click();
	}
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='All']")
	private WebElement selectrowsperpage;
	public void selectRowsPerPage(){
		
		selectrowsperpage.click();
	}
	
	private WebElement clickoneditbutton(String role) {
		
		return driver.findElement(By.xpath("//td[text()='"+role+"']//parent::tr//following-sibling::td//button[contains(@class,'mdi mdi-pencil ')]"));
	}
	public void clickOnEditButton(String role) {
		
		clickoneditbutton(role).click();
	}
	
	
	
	//==============================================================================================================================================
	
	
	
	//Delete Role
	
	
	private WebElement clickondeletebutton(String role) {
		
		return driver.findElement(By.xpath("//td[text()='"+role+"']//parent::tr//following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnDeleteButton(String role) {
		
		clickondeletebutton(role).click();
	}
	
	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOKBtn() {
		
		okbtn.click();
	}
	
	
	
}
