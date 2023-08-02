package com.codon.VueHMS.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Doctors {

	
	WebDriver driver;
	
	public Doctors(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//div[text()='Administration']")
	private WebElement administration;
	public void clickOnAdministrationTab() {
		
		administration.click();
	}
	
	@FindBy(xpath="//div[text()='Doctors']")
	private WebElement doctors;
	public void clickOnDoctors() {
		
		doctors.click();
	}
	
	
	
	//==================================================================================================================================
	
	
	// Departments 
	
	
	@FindBy(xpath="//span[text()=' Departments ']")
	private WebElement departmentsbtn;
	public void clickOnDepartmentsBtn() {
		
		departmentsbtn.click();
	}
	
	
	// Departments Creation
	
	
	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//span[text()=' New ']")
	private WebElement deptnewbtn;
	public void clickOnDepartmentsNewBtn() {
		
		deptnewbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Department Name']/following-sibling::input")
	private WebElement deptnametxtfld;
	public void enterDepartmentName(String name) {
		
		deptnametxtfld.sendKeys(name);
	}
	
	@FindBy(xpath="//label[text()='Description']/following-sibling::textarea")
	private WebElement deptdesctxtfld;
	public void enterDeptDescription(String desc) {
		
		deptdesctxtfld.sendKeys(desc);
	}
	
	public void clearDescription() {
		
		deptdesctxtfld.sendKeys(Keys.CONTROL+"a");
		deptdesctxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//span[text()=' Save ']")
	private WebElement savebtn;
	public void clickOnSaveBtn() {
		
		savebtn.click();
	}
	
	@FindBy(xpath="//span[text()=' Save ']/parent::button")
	private WebElement savebtnstaus;
	public String getSaveBtnStatus() {
		
		return savebtnstaus.getAttribute("class");
	}
	
	@FindBy(xpath="//div[@class='v-snack__content']//span")
	private WebElement status;
	public String getAlert() {
		
		return status.getText();	
	}
	
	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//following::div[contains(@class,'active')]//span[text()=' Cancel ']")
	private WebElement cancelbtn1;
	public void clickOnCancelBtn1() {
		
		cancelbtn1.click();
	}
	
	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//span[text()=' Cancel ']")
	private WebElement cancelbtn2;
	public void clickOnCancelBtn2() {
		
		cancelbtn2.click();
	}
	
	
	// Edit Department details
	
	
	@FindBy(xpath="//div[contains(@class,'v-input--is-label-active')]//i[contains(@class,'mdi mdi-menu-down')]")
	private WebElement rowsperpagedropdown;
	public void clickOnRowsPerPageDropdown() {
		
		rowsperpagedropdown.click();
	}
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='All']")
	private WebElement selectrowsperpage;
	public void selectAllRowsPerPage(){
		
		selectrowsperpage.click();
	}
	
	private WebElement selectdepartmentrecord(String department) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'v-dialog v-dialog--active ')]//th[@class='text-start']/parent::tr/following::tbody//td[text()='"+department+"']/preceding-sibling::td//i"));
	}
	public void selectDepartmentRecord(String dept) {
		
		selectdepartmentrecord(dept).click();
	}
	
	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//button//span[text()=' Edit ']")
	private WebElement depteditbtn;
	public void clickOnDeptEditBtn() {
		
		
		depteditbtn.click();
	}
	
	
	
	// Delete Department
	
	
	
	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//span[text()=' Delete ']")
	private WebElement deletebtn;
	public void clickOnDeptDeleteBtn() {
		
		deletebtn.click();
	}
	
	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOkBtn() {
		
		okbtn.click();
	}



	//=============================================================================================================================================
	
	
	// Skills
	
	
	@FindBy(xpath="//span[text()=' Skills ']")
	private WebElement skillsbtn;
	public void clickOnSkills() {
		
		skillsbtn.click();
	}
	
	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//span[text()=' New ']")
	private WebElement skillsnewbtn;
	public void clickOnSkillsNewBtn() {
		
		skillsnewbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Skill']/following-sibling::input")
	private WebElement skillnametxtfld;
	public void enterSkillName(String name) {
		
		skillnametxtfld.sendKeys(name);
	}
	
	@FindBy(xpath="//label[text()='Description']/following-sibling::textarea")
	private WebElement skilldesctxtfld;
	public void enterSkillDescription(String desc) {
		
		skilldesctxtfld.sendKeys(desc);
	}
	
	public void clearSkillDescription() {
		
		skilldesctxtfld.sendKeys(Keys.CONTROL+"a");
		skilldesctxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	
	
	// Edit Skill details
	
	

	private WebElement selectskillrecord(String skill) {

		return driver.findElement(By.xpath("//td[text()='"+skill+"']/preceding-sibling::td//i[contains(@class,'mdi mdi-checkbox')]"));
	}
	public void selectSkilltRecord(String skill) {

		selectskillrecord(skill).click();
	}

	@FindBy(xpath="//span[text()=' Edit ']")
	private WebElement skilleditbtn;
	public void clickOnSkillEditBtn() {

		skilleditbtn.click();
	}


	// Delete Skill


	@FindBy(xpath="//span[text()=' Delete ']")
	private WebElement skilldeletebtn;
	public void clickOnSkillDeleteBtn() {

		skilldeletebtn.click();
	}
	
	
	
	//==========================================================================================================================================



	//  Doctors Creation
	
	
	@FindBy(xpath="//div[@class='row row--dense']//span[text()=' New ']")
	private WebElement doctornewbtn;
	public void clickOnNewBtn() {
		
		doctornewbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Username/DisplayName']/following-sibling::input")
	private WebElement unametxtfld;
	public void enterUsernameOrDisplayname(String uname) {
		
		unametxtfld.sendKeys(uname);
	}
	
	@FindBy(xpath="//label[text()='FirstName']/following-sibling::input")
	private WebElement fnametxtfld;
	public void enterFirstName(String fname) {
		
		fnametxtfld.sendKeys(fname);
	}
	
	@FindBy(xpath="//label[text()='LastName']/following-sibling::input")
	private WebElement lastnametxtfld;
	public void enterLastname(String lname) {
		
		lastnametxtfld.sendKeys(lname);
	}
	
	@FindBy(xpath="//label[text()='Age']/following-sibling::input")
	private WebElement agetxtfld;
	public void enterAge(String age) {
		
		agetxtfld.sendKeys(age);
	}
	
	@FindBy(xpath="//label[text()='Email Id']/following-sibling::input")
	private WebElement emailidtxtfld;
	public void enterEmailID(String email) {
		
		emailidtxtfld.sendKeys(email);
	}
	
	@FindBy(xpath="//label[text()='Gender']/following-sibling::input")
	private WebElement genderdropdown;
	public void clickonGenderDropdown() {
		
		genderdropdown.click();
	}
	
	private WebElement selectgender(String gender) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'active')]//div[text()='"+gender+"']"));
	}
	public void selectGender(String gender) {
		
		selectgender(gender).click();
	}
	
	@FindBy(xpath="//label[text()='Departments']/following-sibling::input[@type='text']")
	private WebElement deptdropdown;
	public void clickonDepartmentsDropdown() {
		
		deptdropdown.click();
	}

	private WebElement selectdepartment(String department) {

		return driver.findElement(By.xpath("//div[contains(@class,'active')]//div[text()='"+department+"']"));
	}
	public void selectDepartment(String dept) {

		selectdepartment(dept).click();
	}
	
	@FindBy(xpath="//label[text()='Mobile No']/following-sibling::input[@type='text']")
	private WebElement mobilenotxtfld;
	public void enterMobileNo(String mobileno) {
		
		mobilenotxtfld.sendKeys(mobileno);
	}
	
	public void clearMobileNo() {
		
		mobilenotxtfld.sendKeys(Keys.CONTROL+"a");
		mobilenotxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-4 v-card')]/following-sibling::div//span[text()=' Continue ']/parent::button")
	private WebElement continuebtn1status;
	public String getContinueBtn1Status() {
		
		return continuebtn1status.getAttribute("class");
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-4 v-card')]/following-sibling::div//span[text()=' Continue ']")
	private WebElement continuebtn1;
	public void clickOnContinueBtn1() {
		
		continuebtn1.click();
	}
	
	@FindBy(xpath="//label[text()='LandLine']/following-sibling::input")
	private WebElement landlinetxtfld;
	public void enterLandLine(String landline) {
		
		landlinetxtfld.sendKeys(landline);
	}
	
	public void clearLandLine() {
		
		landlinetxtfld.sendKeys(Keys.CONTROL+"a");
		landlinetxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//label[text()='Fax']/following-sibling::input")
	private WebElement faxtxtfld;
	public void enterFax(String fax) {
		
		faxtxtfld.sendKeys(fax);
	}
	
	@FindBy(xpath="//label[text()='City']/following-sibling::input")
	private WebElement citytxtfld;
	public void enterCity(String city) {
		
		citytxtfld.sendKeys(city);
	}
	
	@FindBy(xpath="//label[text()='State']/following-sibling::input")
	private WebElement statetxtfld;
	public void enterState(String state) {
		
		statetxtfld.sendKeys(state);
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
	
	@FindBy(xpath="//label[text()='Address1']/following-sibling::textarea")
	private WebElement adress1txtfld;
	public void enterAddress1(String address) {
		
		adress1txtfld.sendKeys(address);
	}
	
	@FindBy(xpath="//label[text()='Location']/following-sibling::input[@type='text']")
	private WebElement locdropdown;;
	public void clickOnLocationDropdown() {
		
		locdropdown.click();
	}
	
	private WebElement selectlocation(String location) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+location+"']"));
	}
	public void selectLocation(String loc) {
		
		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {
			
			actions.sendKeys(Keys.DOWN).build().perform();
		}
		
		selectlocation(loc).click();;
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-4 pt-1')]/following-sibling::div//span[text()=' Continue ']/parent::button")
	private WebElement continuebtn2status;
	public String getContinueBtn2Status() {
		
		return continuebtn2status.getAttribute("class");
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-4 pt-1')]/following-sibling::div//span[text()=' Continue ']")
	private WebElement continuebtn2;
	public void clickOnContinueBtn2() {
		
		continuebtn2.click();
	}
	
	@FindBy(xpath="//label[text()='Service Codes']/following-sibling::div//i[contains(@class,'mdi mdi-menu-down')]")
	private WebElement servicecodedropdown;
	public void clickOnServiceCodesDropdown() {
		
		servicecodedropdown.click();
	}
	
	private WebElement selectservicecodes(String sc) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+sc+"']"));
	}
	public void selectServiceCodes(String sc) {
		
		String[] servicecodes=sc.split(",");
		
		for(String servicecode :servicecodes ) {
			
			selectservicecodes(servicecode).click();
		}	
	}
	
	@FindBy(xpath="//label[text()='Skills']/following-sibling::div//i[contains(@class,'mdi mdi-menu-down')]")
	private WebElement skillsdropdown;
	public void clickOnSkillsDropdown() {

		skillsdropdown.click();
	}

	private WebElement selectskills(String skill) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+skill+"']"));
	}
	public void selectSkills(String data) {

		String[] skills=data.split(",");

		for(String skill :skills ) {

			selectskills(skill).click();
		}	
	}
	
	@FindBy(xpath="//label[text()='Is User']//preceding-sibling::div//div[@class='v-input--selection-controls__ripple']")
	private WebElement isusercheckbox;
	public void selectIsUserCheckBox() {
		
		isusercheckbox.click();
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

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();
//			Util.sleepTime(100);
		}

		selectrole(role).click();;
	}
	
	@FindBy(xpath="//label[text()='Password']/following-sibling::input[@type='password']")
	private WebElement passtxtfld;
	public void enterPassword(String password) {
		
		passtxtfld.sendKeys(password);;
	}
	
	@FindBy(xpath="//label[text()='Confirm Password']/following-sibling::input[@type='password']")
	private WebElement confirmpasstxtfld;
	public void enterConfirmPasssword(String confirmpass) {
		
		confirmpasstxtfld.sendKeys(confirmpass);
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-4 pt-1')]/following-sibling::div//span[text()=' Cancel ']")
	private WebElement cancelbtn4;
	public void clickOnCancelBtn4() {
		
		cancelbtn4.click();
	}
	
	@FindBy(xpath="//div[@class='v-stepper__items']//span/following-sibling::span/preceding-sibling::span//div[contains(@class,'mb-4 v-card')]/following-sibling::div//span[text()=' Cancel ']")
	private WebElement cancelbtn3;
	public void clickOnCancelBtn3() {
		
		cancelbtn3.click();
	}

	@FindBy(xpath="//div[contains(@class,'active')]//div[@class='v-stepper__items']//span/following-sibling::span/following::span//span[text()=' Cancel ']")
	private WebElement cancelbtn5;
	public void clickOnCancelBtn5() {
		
		cancelbtn5.click();
	}
	
	
	
	// Edit Doctor Details
	
	
	private WebElement clickoneditbutton(String doctorrecord) {
		
		return driver.findElement(By.xpath("//td[text()='"+doctorrecord+"']/following-sibling::td//button[contains(@class,' mdi mdi-pencil')]"));
	}
	public void clickOnEditButton(String doctorname) {
		
		clickoneditbutton(doctorname).click();
	}
	
	
	
	// Delete Doctor
	
	
	private WebElement clickondeletebutton(String doctorname) {
		
		return driver.findElement(By.xpath("//td[text()='"+doctorname+"']/following-sibling::td//button[contains(@class,' mdi mdi-delete')]"));
	}
	public void clickOnDeleteButton(String doctorname) {
		
		clickondeletebutton(doctorname).click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
