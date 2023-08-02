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

public class Patients {

	
	WebDriver driver;
	public Patients(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='Patients']")
	private WebElement patientsmodule;
	public void clickOnPatientsModule() {
		
		patientsmodule.click();
	}
	
	
	//==========================================================================================================================================
	
	
	// Patients Details Creation
	
	
	@FindBy(xpath="//label[text()='Locations']/following-sibling::input[@type='text']")
	private WebElement locationsdropdown;
	public void clickOnLocationsDropdown() {
		
		locationsdropdown.click();
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
		}

		selectlocation(location).click();
	}

	@FindBy(xpath="//span[text()=' New ']")
	private WebElement newbtn;
	public void clickOnNewBtn() {
		
		newbtn.click();
	}
	
	@FindBy(xpath="//span[text()=' New ']/parent::button")
	private WebElement newbtnstatus;
	public String getNewBtnStatus() {
		
		return newbtnstatus.getAttribute("class");
	}
	
	@FindBy(xpath="//label[text()='Patient Name']/following-sibling::input")
	private WebElement patientnametxtfld;
	public void enterPatientName(String name) {
		
		patientnametxtfld.sendKeys(name);
	}
	
	@FindBy(xpath="//label[text()='Gender']/following-sibling::input[@type='text']")
	private WebElement genderdropdown;
	public void clickOnGenderDropdown() {
		
		genderdropdown.click();
	}
	
	private WebElement selectgender(String gender) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+gender+"']"));
	}
	public void selectGender(String gender) {
		
		selectgender(gender).click();
	}
	
	@FindBy(xpath="//label[text()='Blood Group']/following-sibling::input[@type='text']")
	private WebElement bloodgroupdropdown;
	public void clickOnBloodGroupDropdown() {
		
		bloodgroupdropdown.click();
	}
	
	private WebElement selectbloodgroup(String bg) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+bg+"']"));
	}
	public void selectBloodGroup(String bg) {
		
		selectbloodgroup(bg).click();
	}
	
	@FindBy(xpath="//label[text()='Age']/following-sibling::input[@type='text']")
	private WebElement agetxtfld;
	public void enterAge(String age) {
		
		agetxtfld.sendKeys(age);
	}
	
	@FindBy(xpath="//label[text()='Email Id']/following-sibling::input")
	private WebElement emailidtxtfld;
	public void enterEmailID(String email) {
		
		emailidtxtfld.sendKeys(email);
	}
	
	@FindBy(xpath="//div[contains(@class,' v-dialog--active')]//label[text()='Phone Number']/following-sibling::input")
	private WebElement phonenotxtfld;
	public void enterPhoneNumber(String phoneno) {
		
		phonenotxtfld.sendKeys(phoneno);
	}
	public void clearPhoneNumber() {
		
		phonenotxtfld.sendKeys(Keys.CONTROL+"a");
		phonenotxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//label[text()='Emergency Contact Name']/following-sibling::input")
	private WebElement emergenctctnametxtfld;
	public void enterEmergencyContactName(String name) {
		
		emergenctctnametxtfld.sendKeys(name);
	}
	
	@FindBy(xpath="//label[text()='Emergency Mobile Number']/following-sibling::input")
	private WebElement emergenmobilenotxtfld;
	public void enterEmergencyMobileNo(String no) {
		
		emergenmobilenotxtfld.sendKeys(no);
	}
	
	@FindBy(xpath="//label[text()='Occupation']/following-sibling::input")
	private WebElement occupationtxtfld;
	public void enterOccupation(String occupation) {
		
		occupationtxtfld.sendKeys(occupation);
	}
	
	@FindBy(xpath="//label[text()='Nationality']/following-sibling::input")
	private WebElement nationalitytxtfld;
	public void enterNationality(String nationality) {
		
		nationalitytxtfld.sendKeys(nationality);
	}
	
	@FindBy(xpath="//label[text()='Address']/following-sibling::textarea")
	private WebElement addresstxtfld;
	public void enterAddress(String address) {
		
		addresstxtfld.sendKeys(address);
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

	@FindBy(xpath="//div[contains(@class,'v-dialog--active')]//span[text()=' Cancel ']")
	private WebElement cancelbtn1;
	public void clickOnCancelBtn1() {

		cancelbtn1.click();
	}
	
	
	//====================================================================================================================================================
	
	
	// Edit Patient Details
	
	
	private WebElement clickoneditbtn(String patient) {
		
		return driver.findElement(By.xpath("//td[text()='"+patient+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnEditBtn(String patient) {
		
		clickoneditbtn(patient).click();
	}
	
	@FindBy(xpath="//label[text()='BloodGroup']/following-sibling::input[@type='text']")
	private WebElement bloodgroup;
	public void clickOnBloodGpDropdwon() {
		
		bloodgroup.click();
	}
	
	@FindBy(xpath="//span[text()=' Update ']")
	private WebElement updatebtn;
	public void clickOnUpdateBtn() {

		updatebtn.click();
	}

	

	//====================================================================================================================================================


	// Delete Patients


	private WebElement clickondeletebtn(String patient) {

		return driver.findElement(By.xpath("//td[text()='"+patient+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnDeleteBtn(String patient) {

		clickondeletebtn(patient).click();
	}


	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOkBtn() {

		okbtn.click();
	}
	
	
	//=================================================================================================================================================
	
	
	// Appointments
	
	
	private WebElement clickonappointmentsbtn(String patient) {

		return driver.findElement(By.xpath("//td[text()='"+patient+"']/following-sibling::td//button[contains(@class,'mdi-plus-circle')]"));
	}
	public void clickOnAppointmentsBtn(String patient) {

		clickonappointmentsbtn(patient).click();
	}
	
	@FindBy(xpath="//div[@class='v-dialog v-dialog--active']//span[text()=' New ']")
	private WebElement appointmentnewbtn;
	public void clickOnAppointmentsNewBtn() {
		
		appointmentnewbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Doctor Name']/following-sibling::input[@type='text']")
	private WebElement doctornamedropdown;
	public void clickOnDoctorNameDropdown() {
		
		doctornamedropdown.click();
	}
	
	private WebElement selectdoctorname(String doctor) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[contains(text(),'"+doctor+"')]"));
	}
	public void selectDoctorName(String doctor) {
		
		selectdoctorname(doctor).click();
	}
	
	@FindBy(xpath="//label[text()='Skills']/following-sibling::input[@type='text']")
	private WebElement skillsdropdown;
	public void clickOnSkillsDropdown() {
		
		skillsdropdown.click();
	}
	
	private WebElement selectskills(String skill) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+skill+"']"));
	}
	public void selectSkills(String skill) {
		
		selectskills(skill).click();
	}
	
	@FindBy(xpath="//label[text()='Reason For Visit']/following-sibling::textarea")
	private WebElement reasonforvisittxtfld;
	public void enterReasonForVisit(String reason) {
		
		reasonforvisittxtfld.sendKeys(reason);
	}
	
	@FindBy(xpath="//label[text()='Appointment Date']/following-sibling::input")
	private WebElement appointmentdatepicker;
	public void clickOnAppointmentDatePicker() {
		
		appointmentdatepicker.click();
	}
	
	public String getAppointmentDate() {
		
		return appointmentdatepicker.getText();
	}
	
	@FindBy(xpath="//div[contains(@class,' menuable__content__active')]//div[contains(@class,' v-date-picker-title__year')]")
	private WebElement year;
	public void clickOnYear() {

		year.click();
	}

	private WebElement selectyear(String year) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//li[text()='"+year+"']"));
	}
	public void selectYear(String yr) {
		
		selectyear(yr).click();
	}

//	private WebElement selectmonth(String mon) {
//
//		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//button//div[text()='"+mon+"']"));
//	}
//	public void selectMonth(String month) {
//
//		String mon= driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[contains(@class,'v-date-picker-table')]//button//div[text()='"+month+"']/parent::button")).getAttribute("class");
//
//		if(!mon.contains("disabled")) {
//
//			selectmonth(month).click();
//		}else {
//
//			while(mon.contains("disabled")) {
//
//				clickOnRightArrowBtn();
//				Util.sleepTime(500);
//				break;
//			}
//			 
//			selectmonth(month).click();
//		}
//	}
	
	private WebElement selectdate(String date) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//tbody//td//div[text()='"+date+"']"));
	}
	public void selectDate(String date) {

		String dates = driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//tbody//td//div[text()='"+date+"']/parent::button")).getAttribute("class");

		if(!dates.contains("disabled")) {

			selectdate(date).click();

		}else {

			while(dates.contains("disabled")) {

				clickOnRightArrowBtn();
				Util.sleepTime(500);
				break;
			}
			selectdate(date).click();
		}
	}
	
	
	private WebElement selectmonth(String month) {
		
		return driver.findElement(By.xpath("//button[text()='"+month+"']"));
	}
	public void selectMonth(String month) {
		
		String currentmonth=driver.findElement(By.xpath("//div[contains(@class,'v-date-picker-header__value')]//button")).getText();
		
		while(!currentmonth.contains(month)) {

			clickOnRightArrowBtn();
			Util.sleepTime(500);
			currentmonth=driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[contains(@class,'v-date-picker-header__value')]//button")).getText();
			System.out.println("currentmonth  :"+currentmonth);
		}		
	}

	
	@FindBy(xpath="//div[contains(@class,'menuable__content__active')]//i[contains(@class,'mdi mdi-chevron-right ')]")
	private WebElement rightarrowbtn;
	public void clickOnRightArrowBtn() {

		rightarrowbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Appointment Time']/following-sibling::input[@type='text']")
	private WebElement appointmenttime;
	public void clickOnAppointmenttimeDropdown() {
		
		appointmenttime.click();
	}
	
	private WebElement selectappointmenttime(String appointmenttime) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+appointmenttime+"']"));
	}
	public void selectAppointmetTime(String appointmenttime) {
		
		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();
		}
		selectappointmenttime(appointmenttime).click();
	}
	
	
	@FindBy(xpath="//label[text()='Appointment Type']/following-sibling::input[@type='text']")
	private WebElement appointmenttype;
	public void clickOnAppointmentTypeDropdown() {
		
		appointmenttype.click();
	}
	
	private WebElement selectappointmenttype(String appointmenttype) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+appointmenttype+"']"));
	}
	public void selectAppointmetType(String appointmenttype) {
		
		selectappointmenttype(appointmenttype).click();
	}
	
	@FindBy(xpath="//div[contains(@class,'v-dialog--active')]//span[text()=' Close ']")
	private WebElement closebtn;
	public void clickOnCloseBtn() {

		closebtn.click();
	}

	@FindBy(xpath="//label[text()='Room']/following-sibling::input[@type='text']")
	private WebElement room;
	public void clickOnRoomDropdown() {
		
		room.click();
	}
	
	private WebElement selectroom(String room) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+room+"']"));
	}
	public void selectRoom(String room) {
		
		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();
		}
		selectroom(room).click();
	}
	
	@FindBy(xpath="//label[text()='Bed']/following-sibling::input[@type='text']")
	private WebElement bed;
	public void clickOnBedDropdown() {
		
		bed.click();
	}
	
	private WebElement selectbed(String bed) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+bed+"']"));
	}
	public void selectBed(String bed) {
		
		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();
		}
		selectbed(bed).click();
	}
	
	
	//===========================================================================================================================================
	
	
	// Edit Appointment
	
	
	private WebElement clickonappointmenteditbtn(String doctor,String date,String type) {
		
		return driver.findElement(By.xpath("//td[text()='"+doctor+"']/following-sibling::td[contains(text(),'"+date+"')]/following-sibling::td[text()='"+type+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnAppointmentEditBtn(String appointment) {
		
		String[] data=appointment.split(",");

		String doctor=data[0];
		String date=data[1];
		String type=data[2];

		clickonappointmenteditbtn(doctor,date,type).click();
	}
	
	@FindBy(xpath="//label[text()='Referred By']/following-sibling::input[@type='text']")
	private WebElement referedbydropdown;
	public void clickOnReferedByDropdown() {
		
		referedbydropdown.click();
	}

	private WebElement selectreferedby(String referedby) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+referedby+"']"));
	}
	public void selectReferedBy(String referedby) {

		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();

		}
		selectreferedby(referedby).click();
	}


	
	//===========================================================================================================================================


	// Delete Appointment


	private WebElement clickonappointmentedeletebtn(String doctor,String date,String type) {

		return driver.findElement(By.xpath("//td[text()='"+doctor+"']/following-sibling::td[contains(text(),'"+date+"')]/following-sibling::td[text()='"+type+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnAppointmentDeleteBtn(String appointment) {

		String[] data=appointment.split(",");

		String doctor=data[0];
		String date=data[1];
		String type=data[2];

		clickonappointmentedeletebtn(doctor,date,type).click();
	}
	
	
	
	//======================================================================================================================================================
	
	// Admit as IN Patient
	
	
	private WebElement selectappointmentrecord(String doctor,String date,String type) {
		
		return driver.findElement(By.xpath("//td[text()='manvi']/following-sibling::td[contains(text(),'4')]//following-sibling::td[text()='OUT']/preceding-sibling::td//i"));
	}
	public void selectAppointmentRecord(String data) {
		
		String [] appointmentdata=data.split(",");
		String doctor=appointmentdata[0];
		String date=appointmentdata[1];
		String type=appointmentdata[2];
		
		selectappointmentrecord(doctor,date,type).click();
	}
	
	@FindBy(xpath="//span[text()=' Admit as IN Patient ']")
	private WebElement admitasinpatientbtn;
	public void clickOnAdmitAsINPatientBtn() {
		
		admitasinpatientbtn.click();
	}
	
	@FindBy(xpath="//span[text()=' Change ']/parent::button")
	private WebElement changebtnstatus;
	public String getChangeBtnStatus() {
		
		return changebtnstatus.getAttribute("class");
	}
	
	@FindBy(xpath="//span[text()=' Change ']")
	private WebElement changebtn;
	public void clickOnChangeBtn() {
		
		changebtn.click();
	}

	
	//====================================================================================================================================================
	
	
	// Consultation
	
	
	
	//Patient INFO
	
	
	@FindBy(xpath="//label[text()='Contact Phone No']/following-sibling::input")
	private WebElement contactphoneno;
	public void enterContactPhoneNo(String phoneno) {
		
		contactphoneno.sendKeys(phoneno);
	}
	
	@FindBy(xpath="//label[text()='Landline Number']/following-sibling::input")
	private WebElement landlinenotxtfld;
	public void enterLandLineNo(String landline) {
		
		landlinenotxtfld.sendKeys(landline);
	}
	
	
	//==========================================================================================================================================
	
	
	// Medical History
	
	
	@FindBy(xpath="//div[@id='#medicalhistory']")
	private WebElement medicalhistory;
	public void clickOnMedicalHistoryTab() {
		
		medicalhistory.click();
	}
	
	private WebElement expanddisease(String diseasename) {
		
		return driver.findElement(By.xpath("//td[text()='"+diseasename+"']/preceding-sibling::td//button[contains(@class,'v-data-table__expand')]"));
	}
	public void expandDisease(String diseasename) {
		
		expanddisease(diseasename).click();
	}
	
	private WebElement clickonsymptomeditbtn(String symptom) {
		
		return driver.findElement(By.xpath("//td[contains(text(),'"+symptom+"')]/following-sibling::td//button[contains(@class,'mdi mdi-pencil ')]"));
	}
	public void clickOnSymptomEditBtn(String symptom) {
		
		clickonsymptomeditbtn(symptom).click();
	}
	
	@FindBy(xpath="//label[text()='Notes']/following-sibling::textarea")
	private WebElement notestxtfld;
	public void enterNotes(String note) {
		
		notestxtfld.sendKeys(note);
	}
	
	@FindBy(xpath="//div[contains(@class,'v-dialog--active')]//button[contains(@class,'mdi mdi-close-circle')]")
	private WebElement closeiconbtn;
	public void clickOnCloseIconBtn() {

		closeiconbtn.click();
	}


	//==========================================================================================================================================
	
	
	// Consultation Tab
	
	
	@FindBy(xpath="//div[@id='#consultation']")
	private WebElement consultation;
	public void clickOnConsultationTab() {
		
		consultation.click();
	}
	
	
	//Tests
	
	
	@FindBy(xpath="//div[contains(@class,'v-window-item--active')]//span[text()=' New ']")
	private WebElement testsnewbtn;
	public void clickOnTestsNewBtn() {
		
		testsnewbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Name']/following-sibling::input[@type='text']")
	private WebElement namedropdown;
	public void clickOnNameDropdown() {
		
		namedropdown.click();
	}

	private WebElement selecttestname(String name) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+name+"']]"));
	}
	public void selectTestName(String name) {

		selecttestname(name).click();
	}
	
	@FindBy(xpath="//label[text()='Scheduled Date']/following-sibling::input")
	private WebElement scheduleddatepicker;
	public void clickOnScheduledDatePicker() {
		
		scheduleddatepicker.click();
	}

	private WebElement selectsheduletime(String time) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+time+"']"));
	}
	public void selectscheduletTime(String time) {

		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();

		}
		selectsheduletime(time).click();
	}
	
	@FindBy(xpath="//div[text()='Prescribed Tests ']/parent::div//button[contains(@class,' mdi mdi-close')]")
	private WebElement testscloseiconbtn;
	public void clickOnTestsCloseIconBtn() {
		
		testscloseiconbtn.click();
	}
	
	
	
	//=========================================================================================================================================


	// Edit Test details


	private WebElement clickontesteditbtn(String name,String time) {

		return driver.findElement(By.xpath("//td[text()='"+name+"']/following-sibling::td[text()='"+time+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil ')]"));
	}
	public void clickOnTestEditBtn(String name,String time) {

		clickontesteditbtn(name,time).click();
	}


	//=========================================================================================================================================


	// Delete Tests


	private WebElement clickontestdeletebtn(String name,String time) {

		return driver.findElement(By.xpath("//td[text()='"+name+"']/following-sibling::td[text()='"+time+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete ')]"));
	}
	public void clickOnTestDeleteBtn(String name,String time) {

		clickontestdeletebtn(name,time).click();
	}
	
	
	// Test Complete


	private WebElement clickontestcompletebtn(String name,String time) {

		return driver.findElement(By.xpath("//td[text()='"+name+"']/following-sibling::td[text()='"+time+"']/following-sibling::td//button[contains(@class,'mdi mdi-check-circle')]"));
	}
	public void clickOnTestCompleteBtn(String name,String time) {

		clickontestcompletebtn(name,time).click();
	}
	
	
	//====================================================================================================================================================
	
	// Upload Tests and Reports
	
	
	
	private WebElement clickonuploadtestsbtn(String name,String time) {

		return driver.findElement(By.xpath("//td[text()='"+name+"']/following-sibling::td[text()='"+time+"']/following-sibling::td//button[contains(@class,'mdi mdi-upload')]"));
	}
	public void clickOnUploadTestsAndReportsBtn(String name,String time) {

		clickonuploadtestsbtn(name,time).click();
	}
	
	@FindBy(xpath="//label[text()='Upload Documents']//following-sibling::div")
	private WebElement filepath;
	public void enterFilePath(String path) {
		
		filepath.sendKeys(path);
	}
	
	@FindBy(xpath="//span[text()=' upload ']")
	private WebElement uploadbbtn;
	public void clickOnUploadBtn() {
		
		updatebtn.click();
	}
	
	@FindBy(xpath="//span[text()=' upload ']/parent::button")
	private WebElement uploadbtnstatus;
	public String getUploadBtnStatus() {
		
		return uploadbtnstatus.getAttribute("class");
	}
	
	private WebElement clickonfiledeletebtn(String filename) {

		return driver.findElement(By.xpath("//td[text()='"+filename+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnFileDeleteBtn(String filename) {

		clickonfiledeletebtn(filename).click();
	}

	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//button[contains(@class,' mdi mdi-pencil-')]")
	private WebElement commentseditbtn;
	public void clickOnCommentsEditBtn() {

		commentseditbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Comments']/following-sibling::textarea")
	private WebElement commentstxtfld;
	public void enterComments(String comments) {
		
		commentstxtfld.sendKeys(comments);
	}
	
	@FindBy(xpath="//div[contains(@class,'v-dialog--active')]//div[contains(@class,'row pa-1 row')]//span[text()=' Cancel ']")
	private WebElement cancelbtn2;
	public void clickOnCancelBtn2() {
		
		cancelbtn2.click();
	}


	//====================================================================================================================================================
	
	
	// Daily COmments
	
	
	@FindBy(xpath="//div[text()=' Daily Comments ']")
	private WebElement dailtcomments;
	public void clickOnDailyComments() {
		
		dailtcomments.click();
	}
	
    @FindBy(xpath="//span[text()=' New Comment ']")
    private WebElement newcommentbtn;
    public void clickOnNewCommentBtn() {
    	
    	newcommentbtn.click();
    }
    
    @FindBy(xpath="//label[text()='Time']/following-sibling::input[@type='text']")
    private WebElement timedropdown;
    public void clickOnTimeDropdown() {
    	
    	timedropdown.click();
    }
    
    private WebElement selecttime(String time) {
    	
    	return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+time+"']"));
    }
    public void selectTime(String time) {
    	
    	Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();

		}
		
    	selecttime(time).click();
    }
	
    @FindBy(xpath="//div[contains(@class,'v-input v-input--dense')]//label[text()='Date']/following-sibling::input[@type='text']")
    private WebElement datepicker;
    public void clickOnDatePicker() {
    	
    	datepicker.click();
    }
    
    
    //=======================================================================================================================================================
    
    
    // Services Offered Daily
    
    
    @FindBy(xpath="//div[text()=' Services Offered Daily ']")
    private WebElement servicesoffereddaily;
    public void clickOnServicesOfferedDaily() {
    	
    	servicesoffereddaily.click();
    }
    
    @FindBy(xpath="//div[contains(@class,' v-window-item--active')]//div[contains(@class,' v-window-item--active')]//span[text()=' New ']")
    private WebElement servicesnewbtn;
    public void clickOnServicesOfferedNewBtn() {
    	
    	servicesnewbtn.click();
    }
	
    @FindBy(xpath="//label[text()='Type']/following-sibling::input[@type='text']")
    private WebElement typedropdown;
    public void clickOnTypeDropdown() {

    	typedropdown.click();
    }

    private WebElement selecttype(String type) {

    	return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+type+"']"));
    }
    public void selectType(String type) {

    	selecttype(type).click();
    }
    
    @FindBy(xpath="//label[text()='Services Offered']/following-sibling::input[@type='text']")
    private WebElement servicesoffereddropdwon;
    public void clickOnServicesOfferedDropdown() {
    	
    	servicesoffereddropdwon.click();
    }

    private WebElement selectservice(String service) {

    	return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+service+"']"));
    }
    public void selectService(String service) {

    	Actions actions = new Actions(driver);

    	List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

    	int list=dropdownlist.size();

    	for(int i=0;i<=list;i++) {

    		actions.sendKeys(Keys.DOWN).build().perform();
    	}

    	selectservice(service).click();
    }
    
    @FindBy(xpath="//label[text()='Doctors Name']/following-sibling::input[@type='text']")
    private WebElement drnamedropdown;
    public void clickOnDoctorsNameDropdown() {
    	
    	drnamedropdown.click();
    }
    
    private WebElement clickonservicedeletebtn(String service) {

		return driver.findElement(By.xpath("//td[text()='"+service+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnServiceDeleteBtn(String service) {

		clickonservicedeletebtn(service).click();
	}
	
	
	//================================================================================================================================================
	
	
	// Medicines
	
	
	@FindBy(xpath="//div[text()=' Medicines ']")
    private WebElement medicinetab;
    public void clickOnMedicineTab() {
    	
    	medicinetab.click();
    }
    
    @FindBy(xpath="//span[text()=' New Prescription ']")
    private WebElement newprescription;
    public void clickOnNewPrescriptionBtn() {
    	
    	newprescription.click();
    }
    
    @FindBy(xpath="//label[text()='Prescribed Medicines']//following-sibling::div[@class='v-input__append-inner']")
    private WebElement medicnedropdown;
    public void clickOnPrescribedMedicineDropdown() {
    	
    	medicnedropdown.click();
    }
    
    private WebElement selectmedicine(String medicine) {

    	return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+medicine+"']"));
    }
    public void selectMedicine(String medicine) {

    	Actions actions = new Actions(driver);

    	List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

    	int list=dropdownlist.size();

    	for(int i=0;i<=list;i++) {

    		actions.sendKeys(Keys.DOWN).build().perform();
    	}

    	selectmedicine(medicine).click();
    }
    
    
    @FindBy(xpath="//label[text()='Description']//following-sibling::textarea")
    private WebElement descriptiontxtfld;
    public void enterDescription(String desc) {
    	
    	descriptiontxtfld.sendKeys(desc);
    }
    
    @FindBy(xpath="//label[text()='Dosage Instructions']//following-sibling::textarea")
    private WebElement dosageinstructions;
    public void enterDosageInstructions(String dosage) {
    	
    	dosageinstructions.sendKeys(dosage);
    }
    
    private WebElement clickonmedicineeditbtn(String medicine) {

		return driver.findElement(By.xpath("//td[text()='"+medicine+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnMedicineEditBtn(String medicine) {

		clickonmedicineeditbtn(medicine).click();
	}

	private WebElement clickonmedicinedeletebtn(String medicine) {

		return driver.findElement(By.xpath("//td[text()='"+medicine+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnMedicineDeleteBtn(String medicine) {

		clickonmedicinedeletebtn(medicine).click();
	}


	//================================================================================================================================================


	// Treatment Planning


	@FindBy(xpath="//div[text()=' Treatment Plannings ']")
	private WebElement treatmentplanningtab;
	public void clickOnTreatmentPlanningsTab() {

		treatmentplanningtab.click();
	}
	
	@FindBy(xpath="//div[text()='TreatmentPlan']/parent::form//button[contains(@class,'mdi mdi-pencil')]")
	private WebElement treatmentplaneditbtn;
	public void clickOnTreatmentPlanEditBtn() {
		
		treatmentplaneditbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Treatment Plan']/following-sibling::input")
	private WebElement treatmentplantxtfld;
	public void enterTreatmentPlan(String plan) {
		
		treatmentplantxtfld.sendKeys(plan);
	}
	
	
	//=================================================================================================================================================
	
	
	// Add References
	
	
	@FindBy(xpath="//span[text()=' Add ']")
	private WebElement addbtn;
	public void clickOnAddBtn() {
		
		addbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Doctor Name']/following-sibling::input")
	private WebElement doctornametxtfld;
	public void enterDocctorName(String name) {
		
		doctornametxtfld.sendKeys(name);
	}
	
	public void clearDoctorName() {
		
		doctornametxtfld.sendKeys(Keys.CONTROL+"a");
		doctornametxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//label[text()='Reason']/following-sibling::textarea")
	private WebElement reasontxtfld;
	public void enterReason(String reason) {
		
		reasontxtfld.sendKeys(reason);
	}

	private WebElement clickonreferenceeditbtn(String ref) {

		return driver.findElement(By.xpath("//td[text()='"+ref+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnReferenceEditBtn(String ref) {

		clickonreferenceeditbtn(ref).click();
	}

	private WebElement clickonreferencedeletebtn(String ref) {

		return driver.findElement(By.xpath("//td[text()='"+ref+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnReferenceDeleteBtn(String ref) {

		clickonreferencedeletebtn(ref).click();
	}



	
	
	
	
	
	
	
}
