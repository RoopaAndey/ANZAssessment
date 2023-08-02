package com.codon.VueHMS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Locations {

	
	WebDriver driver;
	
	public Locations(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, Locations.this);
	}
	
	
	@FindBy(xpath="//div[text()='Administration']")
	private WebElement administration;
	public void clickOnAdministrationTab() {
		
		administration.click();
	}
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='Locations']")
	private WebElement locations;
	public void clickOnLocations() {
		
		locations.click();
	}
	
	
	
	//================================================================================================================================================
	
	// Location Creation
	
	
	
	@FindBy(xpath="//span[text()=' New ']")
	private WebElement newbtn;
	public void clickOnNewBtn() {
		
		newbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Name']/following-sibling::input")
	private WebElement nametxtfld;
	public void enterName(String name) {
		
		nametxtfld.sendKeys(name);
	}
	
	@FindBy(xpath="//label[text()='Location Type']/following-sibling::input[@type='text']")
	private WebElement locationdropdown;
	public void clickOnLocationTypeDropdown() {
		
		locationdropdown.click();
	}
	
	private WebElement selectlocationtype(String loctype) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'active')]/div//div[text()='"+loctype+"']"));
	}
	public void selectLocationType(String loctype) {
		
		selectlocationtype(loctype).click();
	}
	
	@FindBy(xpath="//label[text()='City']/following-sibling::input[@type='text']")
	private WebElement citytxtfld;
	public void enterCity(String city) {
		
		citytxtfld.sendKeys(city);
	}
	
	@FindBy(xpath="//label[text()='State']/following-sibling::input[@type='text']")
	private WebElement statetxtfld;
	public void enterState(String state) {
		
		statetxtfld.sendKeys(state);
	}
	
	@FindBy(xpath="//label[text()='Country']/following-sibling::input[@type='text']")
	private WebElement countrytxtfld;
	public void enterCountry(String country) {
		
		countrytxtfld.sendKeys(country);
	}
	
	@FindBy(xpath="//label[text()='PhoneNo']/following-sibling::input[@type='text']")
	private WebElement phonenotxtfld;
	public void enterPhoneno(String phoneno) {
		
		phonenotxtfld.sendKeys(phoneno);
	}
	
	public void clearPhoneno() {
		
		phonenotxtfld.sendKeys(Keys.CONTROL+"a");
		phonenotxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//label[text()='Pincode']/following-sibling::input[@type='text']")
	private WebElement pincodetxtfld;
	public void enterPincode(String pincode) {
		
		pincodetxtfld.sendKeys(pincode);
	}
	
	@FindBy(xpath="//label[text()='Address']//following-sibling::textarea")
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
	
	@FindBy(xpath="//div[contains(@class,'v-dialog v-dialog--active')]//span[text()=' Cancel ']")
	private WebElement cancelbtn;
	public void clickOnCancelBtn() {
		
		cancelbtn.click();
	}
	
	@FindBy(xpath="//div[@class='v-snack__content']//span")
	private WebElement status;
	public String getAlert() {
		
		return status.getText();	
	}
	
	
	
	//=================================================================================================================================================
	
	
	// Edit Location
	
	

	@FindBy(xpath="//div[text()='Rows per page:']//i[contains(@class,' mdi mdi-menu-down')]")
	private WebElement rowsperpagedropdown;
	public void clickOnRowsPerPageDropdown() {
		
		rowsperpagedropdown.click();
	}
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='All']")
	private WebElement selectrowsperpage;
	public void selecAlltRowsPerPage(){
		
		selectrowsperpage.click();
	}
	
	private WebElement clickoneditbtn(String locname,String city,String state) {
		
		return driver.findElement(By.xpath("//td[text()='"+locname+"']/following-sibling::td[text()='"+city+"']/following-sibling::td[text()='"+state+"']/following-sibling::td//button[contains(@class,' mdi mdi-pencil')]"));
	}
	public void clickOnEditButton(String locrecord) {

		String [] data=locrecord.split(",");
		
		String name =data[0];
		String city=data[1];
		String state=data[2];
		
		clickoneditbtn(name,city,state).click();
	}
	
	
	
	//========================================================================================================================================


	// Delete Location


	private WebElement clickondeletebtn(String locname,String city,String state) {

		return driver.findElement(By.xpath("//td[text()='"+locname+"']/following-sibling::td[text()='"+city+"']/following-sibling::td[text()='"+state+"']/following-sibling::td//button[contains(@class,' mdi mdi-delete')]"));
	}
	public void clickOnDeleteButton(String locrecord) {

		String [] data=locrecord.split(",");

		String name =data[0];
		String city=data[1];
		String state=data[2];

		clickondeletebtn(name,city,state).click();
	}
	
	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOKBtn() {
		
		okbtn.click();
	}
	
	
	
	//========================================================================================================================================
	
	
	// Create Room Details
	
	
	
	private WebElement expandlocation(String loc) {
		
		return driver.findElement(By.xpath("//td[text()='"+loc+"']/preceding-sibling::td//button[contains(@class,'v-data-table__expand-icon')]"));
	}
	public void expandLocation(String loc) {
		
		expandlocation(loc).click();
	}
	
	@FindBy(xpath="//span[text()=' Add ']")
	private WebElement addbtn;
	public void clickOnAddBtn() {
		
		addbtn.click();
	}

    @FindBy(xpath="//label[text()='Name']/following-sibling::input")
    private WebElement roomnametxtfld;
    public void enterRoomName(String name) {
    	
    	roomnametxtfld.sendKeys(name);
    }
    
    @FindBy(xpath="//label[text()='Description']/following-sibling::textarea")
    private WebElement desctxtfld;
    public void enterDescription(String desc) {
    	
    	desctxtfld.sendKeys(desc);
    }
    
    
    
    //==================================================================================================================================================
    
    
    // Edit Room Details
    
    
    
    private WebElement clickonroomeditbtn(String loc) {

		return driver.findElement(By.xpath("//a[text()='"+loc+"']/parent::span/parent::td/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnRoomEditButton(String loc) {

		clickonroomeditbtn(loc).click();
	}
	
	
	
	//============================================================================================================================================

	
	// Assign Cost


	private WebElement clickonassigncostbtn(String room) {

		return driver.findElement(By.xpath("//a[text()='"+room+"']/parent::span/parent::td/following-sibling::td//button[contains(@class,'mdi mdi-currency-usd')]"));
	}
	public void clickOnAssignCostButton(String room) {

		clickonassigncostbtn(room).click();
	}
	
	@FindBy(xpath="//label[text()='Service Codes']//following-sibling::div[@class='v-input__append-inner']")
	private WebElement sercodedropdown;
	public void clickOnServiceCodeDropdown() {
		
		sercodedropdown.click();
	}
	
	private WebElement selectservicecode(String servicecode) {

		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+servicecode+"']"));
	}
	public void selectServiceCode(String servicecode) {

		selectservicecode(servicecode).click();
	}
	

	//==================================================================================================================================================


	// Delete Room 



	private WebElement clickonroomdeletebtn(String room) {

		return driver.findElement(By.xpath("//a[text()='"+room+"']/parent::span/parent::td/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnRoomDeleteButton(String room) {

		clickonroomdeletebtn(room).click();
	}



	//==============================================================================================================================================
	
	
	// Create Bed
	
	
	
	private WebElement clickonroom(String room) {
		
		return driver.findElement(By.xpath("//a[text()='"+room+"']/parent::span"));
	}
	public void clickOnRoom(String room) {
		
		clickonroom(room).click();
	}

	@FindBy(xpath="//label[text()='Name']/following-sibling::input")
	private WebElement bednametxtfld;;
	public void enterBedName(String name) {

		bednametxtfld.sendKeys(name);
	}


	//==================================================================================================================================================


	// Edit Bed Details



	private WebElement clickonbededitbtn(String bed) {

		return driver.findElement(By.xpath("//span[text()='"+bed+"']/parent::td/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnBedEditButton(String bed) {

		clickonbededitbtn(bed).click();
	}


	//==================================================================================================================================================


	// Delete Bed 



	private WebElement clickonbeddeletebtn(String bed) {

		return driver.findElement(By.xpath("//span[text()='"+bed+"']/parent::td/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnBedDeleteButton(String bed) {

		clickonbeddeletebtn(bed).click();
	}

	
	
		
}
