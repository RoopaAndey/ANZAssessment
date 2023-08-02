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

import com.codon.VueHMS.pages.Consultation;
import com.codon.VueHMS.pages.LoginPage;
import com.codon.VueHMS.pages.Patients;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class References {


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
			Patients pat=PageFactory.initElements(driver, Patients.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			pat.clickOnPatientsModule();
			logger.info("Clicked On Patients Module");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}




	@Test
	public void addReferences() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="AddReference";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String doctorname=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String reason=ExcelRead.getCellData(path, sheet, excelrow, 4);
			
			
			









			cons.clickOnLocationsDropdown();
			logger.info("Clicked On Location Dropdown");
			Util.sleepTime(1000);

			cons.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			cons.clickOnAppointmentsBtn(patientrecord);
			logger.info("Clicked On Appointments button  :"+patientrecord);
			Util.sleepTime(1500);

			cons.clickOnAppConsultationBtn(appointmentrecord);
			logger.info("Clicked On Appointments consultation button  :"+appointmentrecord);
			Util.sleepTime(1000);

			cons.clickOnTreatmentPlanningsTab();
			logger.info("Clicked On Treatment Plan Tab");
			Util.sleepTime(1000);
			
			cons.clickOnAddBtn();
			logger.info("Clicked On Add button");
			Util.sleepTime(1000);
			
			cons.enterDocctorName(doctorname);
			logger.info("Entered doctor name  :"+doctorname);
			Util.sleepTime(500);
			
			cons.enterReason(reason);
			logger.info("Entered reason  :"+reason);
			Util.sleepTime(500);

			String savebtnstatus=cons.getReferenceSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				cons.clickOnReferenceSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);
				
				String flag=cons.getAlert();
				ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
				Util.sleepTime(1000);
				
			}else {
				
				ExcelRead.setCellData(path, sheet, excelrow, 5, "Save button is disabled");
				Util.sleepTime(1000);
				
				cons.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1000);
			}
			
			cons.clickOnCloseIconBtn();
			logger.info("Clicked On Close Icon");
			Util.sleepTime(1000);

			cons.clickOnCloseBtn();
			logger.info("Clicked On Close button");
			Util.sleepTime(1500);
		}
	}



	@Test
	public void editReferences() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="EditReference";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String referencerecord=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String reason=ExcelRead.getCellData(path, sheet, excelrow, 4);
			
			
			









			cons.clickOnLocationsDropdown();
			logger.info("Clicked On Location Dropdown");
			Util.sleepTime(1000);

			cons.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			cons.clickOnAppointmentsBtn(patientrecord);
			logger.info("Clicked On Appointments button  :"+patientrecord);
			Util.sleepTime(1500);

			cons.clickOnAppConsultationBtn(appointmentrecord);
			logger.info("Clicked On Appointments consultation button  :"+appointmentrecord);
			Util.sleepTime(1000);

			cons.clickOnTreatmentPlanningsTab();
			logger.info("Clicked On Treatment Plan Tab");
			Util.sleepTime(1000);
			
			cons.clickOnReferenceEditBtn(referencerecord);
			logger.info("Clicked On Appointments consultation button  :"+appointmentrecord);
			Util.sleepTime(1000);
			
			cons.clearReason();
			logger.info("Cleared reason");
			Util.sleepTime(1000);
			
			cons.enterReason(reason);
			logger.info("Entered Reason  :"+reason);
			Util.sleepTime(1000);
			
			cons.clickOnReferenceSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);
			
			String flag=cons.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
			Util.sleepTime(1000);
			
			cons.clickOnCloseIconBtn();
			logger.info("Clicked On Close Icon");
			Util.sleepTime(1000);

			cons.clickOnCloseBtn();
			logger.info("Clicked On Close button");
			Util.sleepTime(1500);
		}
	}


	
	
	@Test
	public void deleteReferences() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="DeleteReference";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String referencerecord=ExcelRead.getCellData(path, sheet, excelrow, 3);
			
			
			
			







			cons.clickOnLocationsDropdown();
			logger.info("Clicked On Location Dropdown");
			Util.sleepTime(1000);

			cons.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			cons.clickOnAppointmentsBtn(patientrecord);
			logger.info("Clicked On Appointments button  :"+patientrecord);
			Util.sleepTime(1500);

			cons.clickOnAppConsultationBtn(appointmentrecord);
			logger.info("Clicked On Appointments consultation button  :"+appointmentrecord);
			Util.sleepTime(1000);

			cons.clickOnTreatmentPlanningsTab();
			logger.info("Clicked On Treatment Plan Tab");
			Util.sleepTime(1500);
			
			cons.clickOnReferenceDeleteBtn(referencerecord);
			logger.info("Clicked On Delete button  :"+referencerecord);
			Util.sleepTime(1000);
			
			cons.clickOnOkBtn();
			logger.info("Clicked On Ok button");
			Util.sleepTime(2000);
			
			String flag=cons.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
			Util.sleepTime(1000);
			
			cons.clickOnCloseIconBtn();
			logger.info("Clicked On Close Icon");
			Util.sleepTime(1000);

			cons.clickOnCloseBtn();
			logger.info("Clicked On Close button");
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
			logger.info("Closed Firefox browser");
			Util.sleepTime(1000);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}		
}
