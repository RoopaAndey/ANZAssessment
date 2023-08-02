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

public class Consultation {


	WebDriver driver;

	public Consultation(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	//=====================================================================================================================================================

	//Patient INFO Tab


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

	private WebElement clickonappointmentsbtn(String patient) {

		return driver.findElement(By.xpath("//td[text()='"+patient+"']/following-sibling::td//button[contains(@class,'mdi-plus-circle')]"));
	}
	public void clickOnAppointmentsBtn(String patient) {

		clickonappointmentsbtn(patient).click();
	}

	private WebElement clickonappconsultationbtn(String doctor,String date,String type) {

		return driver.findElement(By.xpath("//td[text()='"+doctor+"']/following-sibling::td[contains(text(),'"+date+"')]/following-sibling::td[text()='"+type+"']/following-sibling::td//button[contains(@class,'mdi mdi-card-text')]"));
	}
	public void clickOnAppConsultationBtn(String appointment) {

		String[] data=appointment.split(",");

		String doctor=data[0];
		String date=data[1];
		String type=data[2];

		clickonappconsultationbtn(doctor,date,type).click();
	}


	@FindBy(xpath="//label[text()='Contact Phone No']/following-sibling::input")
	private WebElement contactphoneno;
	public void enterContactPhoneNo(String phoneno) {

		contactphoneno.sendKeys(phoneno);
	}
	public void clearContactPhoneNo() {

		contactphoneno.sendKeys(Keys.CONTROL+"a");
		contactphoneno.sendKeys(Keys.BACK_SPACE);
	}

	@FindBy(xpath="//label[text()='Age']/following-sibling::input[@type='text']")
	private WebElement agetxtfld;
	public void enterAge(String age) {

		agetxtfld.sendKeys(age);
	}
	public void clearAge() {

		agetxtfld.sendKeys(Keys.CONTROL+"a");
		agetxtfld.sendKeys(Keys.BACK_SPACE);
	}

	@FindBy(xpath="//div[text()='Reasons']/parent::div//span[text()=' Update ']")
	private WebElement updatebtn;
	public void clickOnUpdateBtn() {

		updatebtn.click();
	}

	@FindBy(xpath="//div[text()='Reasons']/parent::div//span[text()=' Update ']/parent::button")
	private WebElement updatebtnstaus;
	public String getUpdateBtnStatus() {

		return updatebtnstaus.getAttribute("class");
	}

	@FindBy(xpath="//div[contains(@class,'v-dialog--active')]//span[text()=' Close ']")
	private WebElement closebtn;
	public void clickOnCloseBtn() {

		closebtn.click();
	}


	//==========================================================================================================================================


	// Medical History


	@FindBy(xpath="//div[@id='#medicalhistory']")
	private WebElement medicalhistory;
	public void clickOnMedicalHistoryTab() {

		medicalhistory.click();
	}

	private WebElement expandsystemname(String diseasename) {

		return driver.findElement(By.xpath("//td[text()='"+diseasename+"']/preceding-sibling::td//button[contains(@class,'v-data-table__expand')]"));
	}
	public void expandSystemName(String diseasename) {

		expandsystemname(diseasename).click();
	}

	private WebElement clickondiseaseditbtn(String symptom) {

		return driver.findElement(By.xpath("//td[contains(text(),'"+symptom+"')]/following-sibling::td//button[contains(@class,'mdi mdi-pencil ')]"));
	}
	public void clickOnDiseaseEditBtn(String symptom) {

		clickondiseaseditbtn(symptom).click();
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

	@FindBy(xpath="//div[contains(@class,'v-dialog--active')]//span[text()=' Cancel ']")
	private WebElement cancelbtn1;
	public void clickOnCancelBtn1() {

		cancelbtn1.click();
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

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+name+"']"));
	}
	public void selectTestName(String name) {

		Actions actions = new Actions(driver);

		List<WebElement> dropdownlist=driver.findElements(By.xpath("//div[contains(@class,'v-list v-select-list')]//div[@tabindex='0']"));

		int list=dropdownlist.size();

		for(int i=0;i<=list;i++) {

			actions.sendKeys(Keys.DOWN).build().perform();

		}
		selecttestname(name).click();
	}

	@FindBy(xpath="//label[text()='Scheduled Date']/following-sibling::input")
	private WebElement scheduleddatepicker;
	public void clickOnScheduledDatePicker() {

		scheduleddatepicker.click();
	}

	@FindBy(xpath="//div[contains(@class,'menuable__content__active')]//div[contains(@class,'v-date-picker-header__value')]//button")
	private WebElement month;
	public void selectMonth(String mon) {

		String currentmonth=month.getText();
		System.out.println("currentmonth  :"+currentmonth);

		while(!currentmonth.contains(mon)) {

			clickOnRightArrowBtn();
			Util.sleepTime(1000);
			currentmonth=driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[contains(@class,'v-date-picker-header__value')]//button")).getText();

		}		
	}

	@FindBy(xpath="//div[contains(@class,'menuable__content__active')]//i[contains(@class,'mdi mdi-chevron-right ')]")
	private WebElement rightarrowbtn;
	public void clickOnRightArrowBtn() {

		rightarrowbtn.click();
	}

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


	@FindBy(xpath="//label[text()='Scheduled Time']/following-sibling::input[@type='text']")
	private WebElement scheduletime;
	public void clickOnScheduledTimeDropdown() {

		scheduletime.click();
	}

	private WebElement selectsheduletime(String time) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+time+"']"));
	}
	public void selectScheduletTime(String time) {

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

	@FindBy(xpath="//span[text()=' Save ']")
	private WebElement savebtn;
	public void clickOnSaveBtn() {

		savebtn.click();
	}

	@FindBy(xpath="//span[text()=' Save ']/parent::button")
	private WebElement savebtnstatus;
	public String getSaveBtnStatus() {

		return savebtnstatus.getAttribute("class");
	}

	@FindBy(xpath="//div[@class='v-snack__content']//span")
	private WebElement status;
	public String getAlert() {

		return status.getText();	
	}


	//=========================================================================================================================================


	// Edit Test details


	private WebElement clickontesteditbtn(String date,String name) {

		return driver.findElement(By.xpath("//td[contains(text(),'"+date+"')]/parent::tr/following-sibling::tr//td[text()='"+name+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnTestEditBtn(String testdata) {

		String [] data=testdata.split(",");

		String date=data[0];
		String name=data[1];

		clickontesteditbtn(date,name).click();
	}


	//=========================================================================================================================================


	// Delete Tests


	private WebElement clickontestdeletebtn(String date,String name) {

		return driver.findElement(By.xpath("//td[contains(text(),'"+date+"')]/parent::tr/following-sibling::tr//td[text()='"+name+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnTestDeleteBtn(String testdata) {

		String [] data=testdata.split(",");

		String date=data[0];
		String name=data[1];
		clickontestdeletebtn(date,name).click();
	}

	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOkBtn() {

		okbtn.click();
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



	private WebElement clickonuploadtestsbtn(String date,String name) {

		return driver.findElement(By.xpath("//td[contains(text(),'"+date+"')]/parent::tr/following-sibling::tr//td[text()='"+name+"']/following-sibling::td//button[contains(@class,'mdi mdi-upload')]"));
	}
	public void clickOnUploadTestsAndReportsBtn(String testdata) {

		String [] data=testdata.split(",");

		String date=data[0];
		String name=data[1];

		clickonuploadtestsbtn(date,name).click();
	}

	@FindBy(xpath="//label[text()='Upload Documents']//following-sibling::div")
	private WebElement filepath;
	public void enterFilePath(String path) {

		filepath.sendKeys(path);
	}

	@FindBy(xpath="//span[text()=' upload ']")
	private WebElement uploadbbtn;
	public void clickOnUploadBtn() {

		uploadbbtn.click();
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
	public void clickOnMedicinesTab() {

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

	@FindBy(xpath="//div[text()='Prescribed Medicines']/parent::div//span[text()=' Save ']")
	private WebElement medicinesavebtn;
	public void clickOnMedicineSaveBtn() {

		medicinesavebtn.click();
	}

	@FindBy(xpath="//div[text()='Prescribed Medicines']/parent::div//span[text()=' Save ']/parent::button")
	private WebElement medicinesavebtnstatus;
	public String getMedicineSaveBtnStatus() {

		return medicinesavebtnstatus.getAttribute("class");
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
	
	@FindBy(xpath="//div[text()='TreatmentPlan']/parent::form//span[text()=' Save ']")
	private WebElement treatmentplansavebtn;
	public void clickOnTreatmentPlanSaveBtn() {
		
		treatmentplaneditbtn.click();
	}
	
	@FindBy(xpath="//div[text()='TreatmentPlan']/parent::form//span[text()=' Save ']/parent::button")
	private WebElement treatmntplnsavebtnstatus;
	public String getTreatmentPlanSaveBtnStatus() {

		return treatmntplnsavebtnstatus.getAttribute("class");
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
	public void clearReason() {
		
		reasontxtfld.sendKeys(Keys.CONTROL+"a");
		reasontxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//div[text()=' New Reference']/parent::div//span[text()=' Save ']")
	private WebElement refsavebtn;
	public void clickOnReferenceSaveBtn() {
		
		refsavebtn.click();
	}
	
	@FindBy(xpath="//div[text()=' New Reference']/parent::div//span[text()=' Save ']/parent::button")
	private WebElement referencesavebtn;
	public String getReferenceSaveBtnStatus() {

		return referencesavebtn.getAttribute("class");
	}
	
	
	//===============================================================================================================================================
	
	
	// Edit References
	


	private WebElement clickonreferenceeditbtn(String ref) {

		return driver.findElement(By.xpath("//td[text()='"+ref+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnReferenceEditBtn(String ref) {

		clickonreferenceeditbtn(ref).click();
	}
	
	
	
	//===============================================================================================================================================
	
	
    // Delete References

	private WebElement clickonreferencedeletebtn(String ref) {

		return driver.findElement(By.xpath("//td[text()='"+ref+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnReferenceDeleteBtn(String ref) {

		clickonreferencedeletebtn(ref).click();
	}




}
