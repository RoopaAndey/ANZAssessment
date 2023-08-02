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

public class PackTypes {


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

			md.clickOnPackTypesBtn();
			logger.info("Clicked On PackTypes button");
			Util.sleepTime(1000);

			md.clickOnRowsPerPageDropdown();
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
	public void packTypesCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="CreatePackTypes";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String code=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String name=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String quantity=ExcelRead.getCellData(path, sheet, excelrow, 2);









			md.clickOnPackTypeNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1000);

			if(code!="") {

				md.enterPackTypeCode(code);
				logger.info("Entered Code  :"+code);
				Util.sleepTime(300);
			}

			if(name!="") {

				md.enterPackTypeName(name);
				logger.info("Entered Name  :"+name);
				Util.sleepTime(300);
			}

			if(quantity!="") {

				md.enterQuantity(quantity);
				logger.info("Entered Quantity  :"+quantity);
				Util.sleepTime(300);
			}

			String savebtnstatus=md.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				md.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=md.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select pack_id,pack_code,pack_name  from pack_type where pack_name='"+name+"' ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String packtypecode=resultset.getString("pack_code").concat("    ");
						String packtypename=resultset.getString("pack_name").concat("    ");
						String result=resultset.getString("pack_id").concat("    ")+packtypecode+packtypename;
						ExcelRead.setCellData(path, sheet, excelrow, 4, result);

					}
				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
					Util.sleepTime(1000);

					md.clickOnCancelBtn1();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1500);
				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 3, "Save button is disabled");
				Util.sleepTime(1000);

				md.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}
		}
	}



	
	@Test
	public void editPackTypes() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="EditPackTypes";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String packtype=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String qty=ExcelRead.getCellData(path, sheet, excelrow, 1);
			
			
			
			
			
			
			
			
			md.clickOnPackTypeEditButton(packtype);
			logger.info("Clicked On Edit button  :"+packtype);
			Util.sleepTime(1500);
			
			md.clearQuantity();
			logger.info("Cleared Quantity");
			Util.sleepTime(500);
			
			md.enterQuantity(qty);
			logger.info("Entered Quantity  :"+qty);
			Util.sleepTime(500);
			
			md.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);
			
			String flag=md.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
			Util.sleepTime(1000);
		}	
	}

	
	
	@Test
	public void deletePackTypes() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Medicines md=PageFactory.initElements(driver, Medicines.class);
		String sheet="DeletePackTypes";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String packtype=ExcelRead.getCellData(path, sheet, excelrow, 0);
		
		
		
		
			
			
			
			
			md.clickOnPackTypeDeleteButton(packtype);
			logger.info("Clicked On Delete button  :"+packtype);
			Util.sleepTime(1500);
			
			md.clickOnOkBtn();
			logger.info("Clicked On OK button");
			Util.sleepTime(2000);
			
			String flag=md.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
			Util.sleepTime(1500);
		}
	}






	
	

	@AfterClass
	public void logoutFromApplication() {

		try {

			LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
			Medicines md= PageFactory.initElements(driver, Medicines.class);

			md.clickOnCancelBtn2();
			logger.info("Clicked On Cancel button");
			Util.sleepTime(1500);

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
