package com.codon.VueHMS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Services {

	
	WebDriver driver;
	
	public Services(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//div[text()='Administration']")
	private WebElement administration;
	public void clickOnAdministrationTab() {
		
		administration.click();
	}
	
	@FindBy(xpath="//div[@class='v-list-item__title'][text()='Services Offered']")
	private WebElement servicesoffered;
	public void clickOnDServicesOffered() {
		
		servicesoffered.click();
	}
	
	
	
	
	//============================================================================================================================================
	
	
	
	@FindBy(xpath="//span[text()=' Service Category ']")
	private WebElement servicecatgbtn;
	public void clickOnServiceCategoryBtn() {
		
		servicecatgbtn.click();
	}
	
	@FindBy(xpath="//div[@class='v-dialog v-dialog--active']//div//span[text()=' New ']")
	private WebElement catgnewbtn;
	public void clickOnCatgNewBtn() {
		
		catgnewbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Service Category Name']/following-sibling::input")
	private WebElement servicecatgnametxtfld;
	public void enterServiceCatgName(String name) {
		
		servicecatgnametxtfld.sendKeys(name); 
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
	
	
	
	
	//==================================================================================================================================================
	
	
	
	// Edit Category details
	
	
	
	private WebElement selectcatgrecord(String catg) {
		
		return driver.findElement(By.xpath("//td[text()='"+catg+"']//preceding-sibling::td//i[contains(@class,'mdi mdi-checkbox')]"));
	}
	public void selectCategoryRecord(String catg) {
		
		selectcatgrecord(catg).click();
	}
	
	@FindBy(xpath="//span[text()=' Edit ']")
	private WebElement catgeditbtn;
	public void clickOnCatgEditBtn() {
		
		catgeditbtn.click();
	}
	
	@FindBy(xpath="//label[text()='Description']//following-sibling::textarea")
	private WebElement desctxtfld;
	public void enterDescription(String desc) {
		
		desctxtfld.sendKeys(desc);
	}
	
	@FindBy(xpath="//div[@class='v-dialog v-dialog--active']//div[text()='Rows per page:']//i[contains(@class,'mdi mdi-menu-down')]")
	private WebElement rowsperpagedropdown;
	public void clickOnCatgRowsPerPageDropdown() {
		
		rowsperpagedropdown.click();
	}
	
	@FindBy(xpath="//div[contains(@class,'menuable__content__active')]//div[@class='v-list-item__title'][text()='All']")
	private WebElement selectrowsperpage;
	public void selectAllRowsPerPage(){
		
		selectrowsperpage.click();
	}
	
	@FindBy(xpath="//span[text()=' Cancel ']")
	private WebElement cancelbtn2;
	public void clickOnCancelBtn2(){
		
		cancelbtn2.click();
	}
	
	

	//======================================================================================================================================
	
	
	// Delete Service category
	
	
	@FindBy(xpath="//span[text()=' Delete ']")
	private WebElement deletebtn;
	public void clickOnCatgDeleteBtn() {
		
		deletebtn.click();
	}
	
	@FindBy(xpath="//span[text()=' Ok ']")
	private WebElement okbtn;
	public void clickOnOkbtn() {
		
		okbtn.click();
	}
	
	
	//================================================================================================================================================
	
	// Services Creation 
	
	
	
	@FindBy(xpath="//section[@id='services']//i[contains(@class,'mdi mdi-menu-down')]")
	private WebElement rowsperpage;
	public void clickOnRowsPerPageDropdown() {
		
		rowsperpage.click();
	}
	
	@FindBy(xpath="//section[@id='services']//span[text()=' New ']")
	private WebElement newbtn;
	public void clickOnNewBtn() {
		
		newbtn.click();
	}
	
	@FindBy(xpath="//label[text()='ServiceName']/following-sibling::input")
	private WebElement servicenametxtfld;
	public void enterServiceName(String name) {
		
		servicenametxtfld.sendKeys(name);
	}
	
	@FindBy(xpath="//label[text()='Service Code']/following-sibling::input")
	private WebElement servicecodetxtfld;
	public void enterServiceCode(String code) {
		
		servicecodetxtfld.sendKeys(code);
	}
	
	@FindBy(xpath="//label[text()='Category']/following-sibling::input")
	private WebElement catgdropdown;
	public void clickOnCatgDropdown() {
		
		catgdropdown.click();
	}
	
	private WebElement selectcategory(String catg) {
		
		return driver.findElement(By.xpath("//div[contains(@class,'menuable__content__active')]//div[text()='"+catg+"']"));
	}
	public void selectCategory(String catg) {
		
		selectcategory(catg).click();
	}
	
	@FindBy(xpath="//label[text()='Price(Rupees)']/following-sibling::input")
	private WebElement pricetxtfld;
	public void enterPrice(String price) {
		
		pricetxtfld.sendKeys(price);
	}
	public void clearPrice() {
		
		pricetxtfld.sendKeys(Keys.CONTROL+"a");
		pricetxtfld.sendKeys(Keys.BACK_SPACE);
	}
	
	@FindBy(xpath="//label[text()='Tax%']/following-sibling::input")
	private WebElement taxfld;
	public void enterTax(String tax) {
		
		taxfld.sendKeys(tax);
	}
	public void clearTax() {
		
		taxfld.sendKeys(Keys.CONTROL+"a");
		taxfld.sendKeys(Keys.BACK_SPACE);
	}
	
	
	//===========================================================================================================================================
	
	// Edit Service
	
	
	private WebElement clickoneditbutton(String service) {
		
		return driver.findElement(By.xpath("//section[@id='services']//td[text()='"+service+"']/following-sibling::td//button[contains(@class,'mdi mdi-pencil')]"));
	}
	public void clickOnEditButton(String service) {
		
		clickoneditbutton(service).click();
	}
	
	
	
	
	//==========================================================================================================================================
	
	// Delete Service


	private WebElement clickondeletebutton(String service) {

		return driver.findElement(By.xpath("//section[@id='services']//td[text()='"+service+"']/following-sibling::td//button[contains(@class,'mdi mdi-delete')]"));
	}
	public void clickOnDeleteButton(String service) {

		clickondeletebutton(service).click();
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
