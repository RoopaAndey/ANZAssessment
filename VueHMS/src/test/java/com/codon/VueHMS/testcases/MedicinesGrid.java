package com.codon.VueHMS.testcases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codon.VueHMS.pages.LoginPage;
import com.codon.VueHMS.pages.Medicines;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class MedicinesGrid {



	WebDriver driver;
	String bn,user,url,pass,dbURL,path,dbusername,dbpassword;
	Connection con;
	Statement st;
	ResultSet resultset;
	Logger logger=Logger.getLogger("VueHMS");


	@BeforeClass
	public void loginIntoApplication() {

		try {

			this.dbURL=CommonUtil.getProperty("config", "dbURL");
			this.dbusername=CommonUtil.getProperty("config", "dbusername");
			this.dbpassword=CommonUtil.getProperty("config", "dbpassword");

			Class.forName("org.postgresql.Driver");
			con= DriverManager.getConnection(dbURL, dbusername, dbpassword);

			PropertyConfigurator.configure("Log4j.properties");
			this.bn=CommonUtil.getProperty("config", "browser");
			this.url=CommonUtil.getProperty("config", "url");
			this.user=CommonUtil.getProperty("config", "username");
			this.pass=CommonUtil.getProperty("config", "password");

			driver=BrowsePage.startBrowser(bn);
			logger.info("Launched Firefox browser");
			Util.sleepTime(1000);

			BrowsePage.openUrl(url);
			logger.info("Entered Url :" +url);
			Util.sleepTime(2000);	

			LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
			Medicines md=PageFactory.initElements(driver, Medicines.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			md.clickOnAdministrationTab();
			logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);

			md.clickOnMedicines();
			logger.info("Clicked On Medicines section");
			Util.sleepTime(1000);

			md.clickOnMedicinesRowsPerPageDropdown();
			logger.info("Clicked On Rows per page dropdown");
			Util.sleepTime(1000);

			md.selectAllRowsPerPage();
			logger.info("Selected All rows per page");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}




	@Test
	public void medicinesCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="MedicineCreation";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String productcode=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String medicinename=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String hsnnumber=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String taxcode=ExcelRead.getCellData(path, sheet, excelrow, 3);









			md.clickOnNewBtn();
			logger.info("Clicked On New Btn");
			Util.sleepTime(1500);
			
			if(productcode!="") {

				md.enterProductCode(productcode);
				logger.info("Entered Product Code  :"+productcode);
				Util.sleepTime(500);
			}

			if(medicinename!="") {

				md.enterMedicineName(medicinename);
				logger.info("Entered Medicine name  :"+medicinename);
				Util.sleepTime(1000);
			}
			
			if(hsnnumber!="") {

				md.enterHSNName(hsnnumber);
				logger.info("Entered HSN number  :"+hsnnumber);
				Util.sleepTime(1000);
			}

			if(taxcode!="") {

				md.clickOnTaxCodeDropdown();
				logger.info("Clicked On Tax Code Dropdown");
				Util.sleepTime(500);

				md.selectTaxCode(taxcode);
				logger.info("Selected tax Code  :"+taxcode);
				Util.sleepTime(500);
			}

			String savebtnstatus=md.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				md.clickOnSaveBtn();
				logger.info("Clicked On Save Btn");
				Util.sleepTime(2000);

				String flag=md.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select product_id,product_code,product_name from product where product_code='"+productcode+"'  ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String code=resultset.getString("product_code").concat("     ");
						String name=resultset.getString("product_name").concat("     ");
						String result=resultset.getString("product_id").concat("     ")+code+name;
						ExcelRead.setCellData(path, sheet, excelrow, 5, result);
					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
					Util.sleepTime(1000);

					md.clickOnCancelBtn2();
					logger.info("Clicked On Cancel Btn");
					Util.sleepTime(1000);
				}	
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 4, "Save button is disabled");
				Util.sleepTime(1000);

				md.clickOnCancelBtn2();
				logger.info("Clicked On Cancel Btn");
				Util.sleepTime(1000);
			}
		}
	}




	@Test
	public void editMedicineDetails() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="EditMedicineDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String medicinerecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String hsnname=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String taxcode=ExcelRead.getCellData(path, sheet, excelrow, 2);










			md.clickOnEditButton(medicinerecord);
			logger.info("Clicked On Edit Btn  :"+medicinerecord);
			Util.sleepTime(1000);

			md.clearHSNName();
			logger.info("Cleared HSN Name");
			Util.sleepTime(500);

			md.enterHSNName(hsnname);
			logger.info("Entered HSN name  : "+hsnname);
			Util.sleepTime(500);

			md.clickOnTaxCodeDropdown();
			logger.info("Clicked On Tax code dropdown");
			Util.sleepTime(1000);

			md.selectTaxCode(taxcode);
			logger.info("Selected Tax code  :"+taxcode);
			Util.sleepTime(1000);

			md.clickOnSaveBtn();
			logger.info("Clicked On Save Btn");
			Util.sleepTime(2000);

			String flag=md.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
			Util.sleepTime(1500);
		}
	}	




	@Test
	public void deleteMedicine() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="DeleteMediicnes";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String medicinerecord= ExcelRead.getCellData(path, sheet, excelrow, 0);







			md.clickOnDeleteButton(medicinerecord);
			logger.info("Clicked On Delete Btn  :"+medicinerecord);
			Util.sleepTime(1000);

			md.clickOnOkBtn();
			logger.info("Clicked On Ok Btn");
			Util.sleepTime(2500);

			String flag=md.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
			Util.sleepTime(1000);	
		}	
	}





	@Test
	public void stockDetailsCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="BatchDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {

			String medicine=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String batchnumber=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String manufacturer=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String manfyear=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String manfmonthandate=ExcelRead.getCellData(path, sheet, excelrow, 4);
			String expiryyear=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String expirymonth=ExcelRead.getCellData(path, sheet, excelrow, 6);
			String expirydate=ExcelRead.getCellData(path, sheet, excelrow, 7);
			String quantity=ExcelRead.getCellData(path, sheet, excelrow, 8);
			String AvailQty=ExcelRead.getCellData(path, sheet, excelrow, 9);
			String Totalcost=ExcelRead.getCellData(path, sheet, excelrow, 10);
			String packcost=ExcelRead.getCellData(path, sheet, excelrow, 11);
			String receivedyear=ExcelRead.getCellData(path, sheet, excelrow, 12);
			String receivedmonanddate=ExcelRead.getCellData(path, sheet, excelrow, 13);
			String packtype=ExcelRead.getCellData(path, sheet, excelrow, 14);













			md.clickOnStockDetailsBtn(medicine);
			logger.info("Clicked On Stock Details button  :"+medicine);
			Util.sleepTime(1000);

			md.clickOnStockNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1000);

			if(batchnumber!="") {

				md.enterBatchNumber(batchnumber);
				logger.info("Entered Batch Number  :"+batchnumber);
				Util.sleepTime(500);	
			}

			if(manufacturer!="") {

				md.clickOnManufacturerDropdown();
				logger.info("Clicked On Manufacturer dropdown");
				Util.sleepTime(500);

				md.selectManufacturer(manufacturer);
				logger.info("Selected Manufacturer  :"+manufacturer);
				Util.sleepTime(500);
			}

			if(manfmonthandate!="") {

				md.clickOnManufactureDatePicker();
				logger.info("Clicked On Manufacture Date picker");
				Util.sleepTime(1000);

				md.clickOnYear();
				logger.info("Clicked On Year");
				Util.sleepTime(1000);

				md.selectYear(manfyear);
				logger.info("Selected year  :"+manfyear);
				Util.sleepTime(1000);

				md.selectMonthAndDate(manfmonthandate);
				logger.info("Selected Month and date  :"+manfmonthandate);
				Util.sleepTime(1000);
			}

			if(expirydate!="") {

				md.clickOnExpiryDatePicker();
				logger.info("Clicked On Expiry Date picker");
				Util.sleepTime(1000);

				md.clickOnYear();
				logger.info("Clicked On Year");
				Util.sleepTime(1000);

				md.selectYear(expiryyear);
				logger.info("Selected year  :"+expiryyear);
				Util.sleepTime(1000);

				md.selectMonth(expirymonth);
				logger.info("Selected month  :"+expirymonth);
				Util.sleepTime(1000);

				md.selectDate(expirydate);
				logger.info("Selected Date  :"+expirydate);
				Util.sleepTime(1000);
			}

			if(quantity!="") {

				md.enterQuantity(quantity);
				logger.info("Entered quantity  :"+quantity);
				Util.sleepTime(500);
			}

			if(AvailQty!="") {

				md.enterAvailableQuantity(AvailQty);
				logger.info("Entered Available quantity  :"+AvailQty);
				Util.sleepTime(500);
			}

			if(Totalcost!="") {

				md.enterTotalCost(Totalcost);
				logger.info("Entered Total Cost :"+Totalcost);
				Util.sleepTime(500);
			}

			if(packcost!=""){

				md.enterPackCost(packcost);
				logger.info("Entered pack cose :"+packcost);
				Util.sleepTime(500);
			}

			if(receivedmonanddate!="") {

				md.clickOnReceivedDatePicker();
				logger.info("Clicked On Received Date picker");
				Util.sleepTime(1000);

				md.clickOnYear();
				logger.info("Clicked On year");
				Util.sleepTime(1000);

				md.selectYear(receivedyear);
				logger.info("Selected year  :"+receivedyear);
				Util.sleepTime(1000);

				md.selectMonthAndDate(receivedmonanddate);
				logger.info("Selected Month and date  :"+receivedmonanddate);
				Util.sleepTime(1000);	
			}

			if(packtype!="") {

				md.clickOnPackTypeDropdown();
				logger.info("Clicked On Pack Type dropdown");
				Util.sleepTime(1000);

				md.selectPackType(packtype);
				logger.info("Selected pack type  :"+packtype);
				Util.sleepTime(1000);
			}

			String savebtnstatus=md.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				md.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=md.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 15, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select batch_id,batch_no,product_code,product_name from batch_details where batch_no='"+batchnumber+"' ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String productname=resultset.getString("product_name").concat("    ");
						String productcode=resultset.getString("product_code").concat("    ");
						String batchno=resultset.getString("batch_no").concat("   ");
						String result=resultset.getString("batch_id").concat("     ")+batchno+productname+productcode;
						ExcelRead.setCellData(path, sheet, excelrow, 16, result);

					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 15, flag);
					Util.sleepTime(1000);

					md.clickOnCancelBtn1();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1500);
				}

			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 15, "Save button is disabled");
				Util.sleepTime(1000);

				md.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}	

			md.clickOnCancelBtn2();
			logger.info("Clicked On Cancel button");
			Util.sleepTime(1500);
		}
	}



	@Test
	public void editStcokDetails() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="EditStockDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {

			String medicine=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String batchnumber=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String manufacturer=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String packcost=ExcelRead.getCellData(path, sheet, excelrow, 3);
			






			
			md.clickOnStockDetailsBtn(medicine);
			logger.info("Clicked On Stock details button  :"+medicine);
			Util.sleepTime(1500);
			
			md.selectStockRecord(batchnumber);
			logger.info("Selected Stock record  :"+batchnumber);
			Util.sleepTime(1000);
			
			md.clickOnStockEditBtn();
			logger.info("Clicked On Edit button");
			Util.sleepTime(1500);
			
			md.clickOnManufacturerDropdown();
			logger.info("Clicked On Manufacturer dropdown");
			Util.sleepTime(1000);
			
			md.selectManufacturer(manufacturer);
			logger.info("Selected Manufacturer :"+manufacturer);
			Util.sleepTime(1000);
			
			md.clearPackCost();
			logger.info("Cleared Pack cost");
			Util.sleepTime(500);
			
			md.enterPackCost(packcost);
			logger.info("Entered Pack cost  :"+packcost);
			Util.sleepTime(500);
			
			md.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);
			
			String flag=md.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
			Util.sleepTime(1000);

			md.clickOnCancelBtn2();
			logger.info("Clicked On Cancel button");
			Util.sleepTime(1500);
		}
	}





	





	@AfterClass
	public void logoutFromApplication() {

		try {

			LoginPage lp=PageFactory.initElements(driver, LoginPage.class);

			lp.clickOnLogoutBtn();
			logger.info("Clicked On Logout button");
			Util.sleepTime(1500);

			con.close();
			driver.close();
			logger.info("Closed the Firefox browser");
			Util.sleepTime(1000);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}		
}
