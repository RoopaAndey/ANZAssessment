package com.codon.VueHMS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentTypes {


	WebDriver driver;
	public DocumentTypes(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//div[text()='Administration']")
	private WebElement administration;
	public void clickOnAdministrationTab() {

		administration.click();
	}

	@FindBy(xpath="//div[@class='v-list-item__title'][text()='Document Types']")
	private WebElement doctypes;
	public void clickOnDocumentTypes() {

		doctypes.click();
	}



	//==============================================================================================================================================

	// Document Types Creation


	@FindBy(xpath="//span[text()=' New ']")
	private WebElement newbtn;
	public void clickOnNewBtn() {

		newbtn.click();
	}

	@FindBy(xpath="//label[text()='Document Type']/following-sibling::input")
	private WebElement doctypetxtfld;
	public void enterDocumentType(String code) {

		doctypetxtfld.sendKeys(code);
	}

	@FindBy(xpath="//span[text()=' Add ']")
	private WebElement addbtn;
	public void clickOnAddBtn() {

		addbtn.click();
	}

	@FindBy(xpath="//label[text()='Field']/following-sibling::input")
	private WebElement fieldtxtfld;
	public void enterField(String field) {

		fieldtxtfld.sendKeys(field);
	}
	public void clearField() {
		
		fieldtxtfld.sendKeys(Keys.CONTROL+"a");
		fieldtxtfld.sendKeys(Keys.BACK_SPACE);
	}

	@FindBy(xpath="//label[text()='Field Description']/following-sibling::input")
	private WebElement fielddesctxtfld;
	public void enterFieldDescription(String field) {

		fielddesctxtfld.sendKeys(field);
	}

	@FindBy(xpath="//div[text()='Document Fields']/parent::div//span[text()=' Save ']/parent::button")
	private WebElement fieldssavebtn;
	public void clickOnFieldsaveBtn() {

		fieldssavebtn.click();
	}

	@FindBy(xpath="//div[text()='Document Fields']/parent::div//button//span[text()=' Cancel ']")
	private WebElement fieldscancelbtn;
	public void clickOnFieldsCancelBtn() {

		fieldscancelbtn.click();
	}
	
	@FindBy(xpath="//span[text()=' Save ']")
	private WebElement savebtn;
	public void clickOnSaveBtn() {

		savebtn.click();
	}
	
	@FindBy(xpath="//span[text()=' Save ']/parent::button")
	private WebElement savebtn1staus;
	public String getSaveBtn1Status() {

		return savebtn1staus.getAttribute("class");
	}

	@FindBy(xpath="//span[text()=' Save ']/parent::button")
	private WebElement savebtn2staus;
	public String getSaveBtnStatus() {

		return savebtn2staus.getAttribute("class");
	}

	@FindBy(xpath="//div[@class='v-snack__content']//span")
	private WebElement status;
	public String getAlert() {

		return status.getText();	
	}

	@FindBy(xpath="//div[contains(@class,'v-dialog--active')]//span[text()=' Cancel ']")
	private WebElement cancelbtn1;
	public void clickOnCancelBtn() {

		cancelbtn1.click();
	}



	//==========================================================================================================================================


	// Edit Document Types


	private WebElement clickoneditbtn(String doctype) {

		return driver.findElement(By.xpath("//td[text()='"+doctype+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnEditBtn(String doctype) {

		clickoneditbtn(doctype).click();
	}


	private WebElement clickonfieldeditbtn(String doctype) {

		return driver.findElement(By.xpath("//div[contains(@class,'v-dialog--persistent')]//td[text()='"+doctype+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnFieldEditBtn(String doctype) {

		clickonfieldeditbtn(doctype).click();
	}


    //=============================================================================================================================================
	
	// Delete Document Types
	
	
	private WebElement clickondeletebtn(String doctype) {

		return driver.findElement(By.xpath("//td[text()='"+doctype+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnDeleteBtn(String doctype) {

		clickondeletebtn(doctype).click();
	}

	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOkBtn() {

		okbtn.click();
	}










































}
