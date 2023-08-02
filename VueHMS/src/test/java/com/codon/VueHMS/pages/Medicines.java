package com.codon.VueHMS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.codon.VueHMS.utilities.Util;

public class Medicines {


	WebDriver driver;

	public Medicines(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}




	@FindBy(xpath="//div[text()='Administration']")
	private WebElement administration;
	public void clickOnAdministrationTab() {

		administration.click();
	}

	@FindBy(xpath="//div[@class='v-list-item__title'][text()='Medicines']")
	private WebElement medicines;
	public void clickOnMedicines() {

		medicines.click();
	}



	//==============================================================================================================================================

	// Manufacturers 



	@FindBy(xpath="//span[text()=' Manufacturers ']")
	private WebElement manufacturersbtn;
	public void clickOnManufacturersBtn() {

		manufacturersbtn.click();
	}

	@FindBy(xpath="//div[@class='v-dialog v-dialog--active']//span[text()=' New ']")
	private WebElement manfnewbtn;
	public void clickOnManfNewBtn() {

		manfnewbtn.click();
	}

	@FindBy(xpath="//label[text()='Code']/following-sibling::input")
	private WebElement codetxtfld;
	public void enterCode(String code) {

		codetxtfld.sendKeys(code);
	}

	@FindBy(xpath="//label[text()='Name']/following-sibling::input")
	private WebElement nametxtfld;
	public void enterName(String name) {

		nametxtfld.sendKeys(name);
	}

	@FindBy(xpath="//label[text()='Address']/following-sibling::textarea")
	private WebElement addresstxtfld;
	public void enterAddress(String address) {

		addresstxtfld.sendKeys(address);
	}
	public void clearAddress() {

		addresstxtfld.sendKeys(Keys.CONTROL+"a");
		addresstxtfld.sendKeys(Keys.BACK_SPACE);
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

	@FindBy(xpath="//div[contains(@class,'v-dialog--active v-dialog--persistent')]//span[text()=' Cancel ']")
	private WebElement cancelbtn1;
	public void clickOnCancelBtn1() {

		cancelbtn1.click();
	}

	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//span[text()=' Cancel ']")
	private WebElement cancelbtn2;
	public void clickOnCancelBtn2() {

		cancelbtn2.click();
	}


	//======================================================================================================================================


	// Edit Manufacturers details



	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//i[contains(@class,'mdi mdi-menu-down')]")
	private WebElement rowsperpagedropdown;
	public void clickOnRowsPerPageDropdown() {

		rowsperpagedropdown.click();
	}

	@FindBy(xpath="//div[contains(@class,'menuable__content__active')]//div[@class='v-list-item__title'][text()='All']")
	private WebElement selectrowsperpage;
	public void selectAllRowsPerPage(){

		selectrowsperpage.click();
	}

	private WebElement clickonmanufreditbutton(String manf) {

		return driver.findElement(By.xpath("//td[text()='"+manf+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnManfrEditButton(String manf) {

		clickonmanufreditbutton(manf).click();
	}


	//======================================================================================================================================


	// Delete Manufacturers 


	private WebElement clickonmanufrdeletebutton(String manf) {

		return driver.findElement(By.xpath("//td[text()='"+manf+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnManfrDeleteButton(String manf) {

		clickonmanufrdeletebutton(manf).click();
	}


	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOkBtn() {

		okbtn.click();
	}


	//=======================================================================================================================================


	// Pack Types


	@FindBy(xpath="//button//span[text()=' Pack Types ']")
	private WebElement packtypesbtn;
	public void clickOnPackTypesBtn() {

		packtypesbtn.click();
	}

	@FindBy(xpath="//div[@class='v-dialog v-dialog--active']//span[text()=' New ']")
	private WebElement packtypenewbtn;
	public void clickOnPackTypeNewBtn() {

		packtypenewbtn.click();
	}

	@FindBy(xpath="//label[text()='Quantity']/following-sibling::input")
	private WebElement qtytxtfld;
	public void enterQuantity(String qty) {

		qtytxtfld.sendKeys(qty);
	}
	public void clearQuantity() {

		qtytxtfld.sendKeys(Keys.CONTROL+"a");
		qtytxtfld.sendKeys(Keys.BACK_SPACE);
	}

	@FindBy(xpath="//label[text()='Code']/following-sibling::input")
	private WebElement packcodetxtfld;
	public void enterPackTypeCode(String code) {

		packcodetxtfld.sendKeys(code);
	}

	@FindBy(xpath="//label[text()='Name']/following-sibling::input")
	private WebElement packnametxtfld;
	public void enterPackTypeName(String name) {

		packnametxtfld.sendKeys(name);
	}



	//========================================================================================================================================


	// Edit Pack Types


	private WebElement clickonpacktypeeditbutton(String pack) {

		return driver.findElement(By.xpath("//div[@class='v-dialog v-dialog--active']//tbody//tr//td[text()='"+pack+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnPackTypeEditButton(String pack) {

		clickonpacktypeeditbutton(pack).click();
	}


	//========================================================================================================================================

	// Delete Pack Types


	private WebElement clickonpacktypedeletebutton(String manf) {

		return driver.findElement(By.xpath("//td[text()='"+manf+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnPackTypeDeleteButton(String manf) {

		clickonpacktypedeletebutton(manf).click();
	}



	//==========================================================================================================================================

	// Tax Codes


	@FindBy(xpath="//button//span[text()=' Tax Codes ']")
	private WebElement taxcodesbtn;
	public void clickOnTaxCodesBtn() {

		taxcodesbtn.click();
	}

	@FindBy(xpath="//div[@class='v-dialog v-dialog--active']//span[text()=' New ']")
	private WebElement taxcodenewbtn;
	public void clickOnTaxCodesNewBtn() {

		taxcodenewbtn.click();
	}

	@FindBy(xpath="//label[text()='Code']/following-sibling::input")
	private WebElement taxcodetxtfld;
	public void enterTaxCode(String code) {

		taxcodetxtfld.sendKeys(code);
	}

	@FindBy(xpath="//label[text()='SGST']/following-sibling::input")
	private WebElement sgsttxtfld;
	public void enterSGST(String sgst) {

		sgsttxtfld.sendKeys(sgst);
	}
	public void clearSGST() {

		sgsttxtfld.sendKeys(Keys.CONTROL+"a");
		sgsttxtfld.sendKeys(Keys.BACK_SPACE);
	}


	@FindBy(xpath="//label[text()='CGST']/following-sibling::input")
	private WebElement cgsttxtfld;
	public void enterCGST(String cgst) {

		cgsttxtfld.sendKeys(cgst);
	}
	public void clearCGST() {

		cgsttxtfld.sendKeys(Keys.CONTROL+"a");
		cgsttxtfld.sendKeys(Keys.BACK_SPACE);
	}

	@FindBy(xpath="//label[text()='IGST']/following-sibling::input")
	private WebElement igsttxtfld;
	public void enterIGST(String igst) {

		igsttxtfld.sendKeys(igst);
	}
	public void clearIGST() {
		
		igsttxtfld.sendKeys(Keys.CONTROL+"a");
		igsttxtfld.sendKeys(Keys.BACK_SPACE);
	}


	//========================================================================================================================================


	// Edit Tax Codes

	private WebElement clickontaxcodebutton(String taxcode) {

		return driver.findElement(By.xpath("//div[@class='v-dialog v-dialog--active']//tbody//tr//td[text()='"+taxcode+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnTaxCodeEditButton(String taxcode) {

		clickontaxcodebutton(taxcode).click();
	}



	//========================================================================================================================================

	// Delete Tax Code


	private WebElement clickontaxcodedeletebutton(String taxcode) {

		return driver.findElement(By.xpath("//div[@class='v-dialog v-dialog--active']//tbody//tr//td[text()='"+taxcode+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnTaxCodeDeleteButton(String taxcode) {

		clickontaxcodedeletebutton(taxcode).click();
	}


	//======================================================================================================================================

	// Medicines Creation


	@FindBy(xpath="//section[@id='medicines']//span[text()=' New ']")
	private WebElement newbtn;
	public void clickOnNewBtn() {

		newbtn.click();
	}

	@FindBy(xpath="//label[text()='Product Code']/following-sibling::input")
	private WebElement productcodetxtfld;
	public void enterProductCode(String code) {

		productcodetxtfld.sendKeys(code);
	}

	@FindBy(xpath="//label[text()='Medicine Name']/following-sibling::input")
	private WebElement medicinenametxtfld;
	public void enterMedicineName(String name) {

		medicinenametxtfld.sendKeys(name);
	}

	@FindBy(xpath="//label[text()='HSN Name']/following-sibling::input")
	private WebElement hsnnametxtfld;
	public void enterHSNName(String name) {

		hsnnametxtfld.sendKeys(name);
	}
	public void clearHSNName() {

		hsnnametxtfld.sendKeys(Keys.CONTROL+"a");
		hsnnametxtfld.sendKeys(Keys.BACK_SPACE);
	}

	@FindBy(xpath="//label[text()='Tax Code']/following-sibling::input[@type='text']")
	private WebElement taxcodedropdown;
	public void clickOnTaxCodeDropdown() {

		taxcodedropdown.click();
	}

	private WebElement selecttaxcode(String taxcode) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+taxcode+"']"));
	}
	public void selectTaxCode(String taxcode) {

		selecttaxcode(taxcode).click();
	}



	//==============================================================================================================================================

	// Edit Medicine


	private WebElement clickoneditbutton(String medicine) {

		return driver.findElement(By.xpath("//td[text()='"+medicine+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnEditButton(String medicine) {

		clickoneditbutton(medicine).click();
	}


	//==============================================================================================================================================

	// Delete Medicine


	private WebElement clickondeletebutton(String medicine) {

		return driver.findElement(By.xpath("//td[text()='"+medicine+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnDeleteButton(String medicine) {

		clickondeletebutton(medicine).click();
	}


	///==========================================================================================================================================


	// Stock Details


	private WebElement clickonstockdetailsbtn(String medicine) {

		return driver.findElement(By.xpath("//td[text()='"+medicine+"']/following-sibling::td//button[contains(@class,' mdi mdi-view-module')]"));
	}
	public void clickOnStockDetailsBtn(String medicine) {

		clickonstockdetailsbtn(medicine).click();
	}

	@FindBy(xpath="//div[@class='v-dialog v-dialog--active']//span[text()=' New ']")
	private WebElement stocknewbtn;
	public void clickOnStockNewBtn() {

		stocknewbtn.click();
	}

	@FindBy(xpath="//label[text()='Batch Number']/following-sibling::input")
	private WebElement batchnumbertxtfld;
	public void enterBatchNumber(String batchnumber) {

		batchnumbertxtfld.sendKeys(batchnumber);
	}

	@FindBy(xpath="//label[text()='Manufacturer']/following-sibling::input[@type='text']")
	private WebElement manufacturerdropdown;
	public void clickOnManufacturerDropdown() {

		manufacturerdropdown.click();
	}

	private WebElement selectmanufacturer(String manufacturer) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+manufacturer+"']"));
	}
	public void selectManufacturer(String manufacturer) {

		selectmanufacturer(manufacturer).click();
	}

	@FindBy(xpath="//label[text()='Manufacture Date']/following-sibling::input[@type='text']")
	private WebElement manfdatepicker;
	public void clickOnManufactureDatePicker() {

		manfdatepicker.click();
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

	private WebElement selectmonthanddate(String date) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+date+"']"));
	}
	public void selectMonthAndDate(String date) {

		String[] month=date.split(",");

		for(String monthanddate:month) {

			selectmonthanddate(monthanddate).click();
		}
	}

	@FindBy(xpath="//label[text()='Expiry Date']/following-sibling::input[@type='text']")
	private WebElement expirydatepicker;
	public void clickOnExpiryDatePicker() {

		expirydatepicker.click();
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
				break;
			}
			Util.sleepTime(500);
			selectdate(date).click();
		}
	}

	@FindBy(xpath="//div[contains(@class,'menuable__content__active')]//i[contains(@class,'mdi mdi-chevron-right ')]")
	private WebElement rightarrowbtn;
	public void clickOnRightArrowBtn() {

		rightarrowbtn.click();
	}
	
	private WebElement selectmonth(String mon) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//button//div[text()='"+mon+"']"));
	}
	public void selectMonth(String month) {

		String mon= driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[contains(@class,'v-date-picker-table')]//button//div[text()='"+month+"']/parent::button")).getAttribute("class");

		if(!mon.contains("disabled")) {

			selectmonth(month).click();
		}else {

			while(mon.contains("disabled")) {

				clickOnRightArrowBtn();
				Util.sleepTime(1000);
				break;
			}
			 
			selectmonth(month).click();
		}
	}
	
	
	@FindBy(xpath="//label[text()='Available Quantity']/following-sibling::input")
	private WebElement availqtytxtfld;
	public void enterAvailableQuantity(String qty) {
		
		availqtytxtfld.sendKeys(qty);
	}
	
	@FindBy(xpath="//label[text()='Total Cost']/following-sibling::input")
	private WebElement totalcosttxtfld;
	public void enterTotalCost(String cost) {
		
		totalcosttxtfld.sendKeys(cost);
	}
	
	@FindBy(xpath="//label[text()='Pack Cost']/following-sibling::input")
	private WebElement packcosttxtfld;
	public void enterPackCost(String cost) {
		
		packcosttxtfld.sendKeys(cost);
	}
	public void clearPackCost() {
		
		packcosttxtfld.sendKeys(Keys.CONTROL+"a");
		packcosttxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//label[text()='Received Date']/following-sibling::input")
	private WebElement receiveddatepicker;
	public void clickOnReceivedDatePicker() {
		
		receiveddatepicker.click();
	}
	
	@FindBy(xpath="//label[text()='Pack Type']/following-sibling::input[@type='text']")
	private WebElement packtypedropdown;
	public void clickOnPackTypeDropdown() {
		
		packtypedropdown.click();
	}
	
	private WebElement selectpacktype(String packtype) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+packtype+"']"));
	}
	public void selectPackType(String packtype) {
		
		selectpacktype(packtype).click();
	}
	
	
	
	//======================================================================================================================================
	
	// Edit Stock Details
	
	
	private WebElement selectstockrecord(String batchno) {
		
		return driver.findElement(By.xpath("//td[text()='"+batchno+"']/preceding-sibling::td//div//i[contains(@class,'mdi mdi-checkbox')]"));
	}
	public void selectStockRecord(String batchno) {
		
		selectstockrecord(batchno).click();
	}
	
	@FindBy(xpath="//span[text()=' Edit ']")
	private WebElement stcokeditbtn;
	public void clickOnStockEditBtn() {
		
		stcokeditbtn.click();
	}
	
	@FindBy(xpath="//section[@id='medicines']//i[contains(@class,'mdi mdi-menu-down')]")
	private WebElement medicinesrowsperpagedropdown;
	public void clickOnMedicinesRowsPerPageDropdown() {

		medicinesrowsperpagedropdown.click();
	}
	
	
	


}
