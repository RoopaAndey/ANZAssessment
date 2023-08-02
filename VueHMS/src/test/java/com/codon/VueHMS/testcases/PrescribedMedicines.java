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

public class PrescribedMedicines {


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
	public void addMedicines() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="PrescribedMedicines";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String medicine=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String description=ExcelRead.getCellData(path, sheet, excelrow, 4);
			





			
			
			
			
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
			
			cons.clickOnConsultationTab();
			logger.info("Clicked On Consultation Tab");
			Util.sleepTime(1000);
			
			cons.clickOnMedicinesTab();
			logger.info("Clicked On Medicines Tab");
			Util.sleepTime(1000);
			
			cons.clickOnNewPrescriptionBtn();
			logger.info("Clicked On New Prescription button");
			Util.sleepTime(1000);
			
			if(medicine!="") {

				cons.clickOnPrescribedMedicineDropdown();
				logger.info("Clicked On Prescribed Medicine Dropdown");
				Util.sleepTime(1000);

				cons.selectMedicine(medicine);
				logger.info("Selected medicine  :"+medicine);
				Util.sleepTime(1000);
			}

			cons.enterDescription(description);
			logger.info("Entered Description  :"+description);
			Util.sleepTime(1000);

			String savebtnstatus=cons.getMedicineSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				cons.clickOnMedicineSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(1000);
				
				String flag=cons.getAlert();
				ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
				Util.sleepTime(1000);
				
				st=con.createStatement();
				String selectquery="select id,patient_id,medicine_name,appointment_id,service_code,created_date from main_prescriptions  where patient_id in (select pat_id from patients  where patient_name='"+patientrecord+"') and medicine_name like '"+medicine+"%' ";
				resultset=st.executeQuery(selectquery);
				while(resultset.next()) {
					
					String patientid=resultset.getString("patient_id").concat("  ");
					String appointmentid=resultset.getString("appointment_id").concat("   ");
					String servicecode=resultset.getString("service_code").concat("   ");
					String createddate=resultset.getString("created_date").concat("   ");
					String result=resultset.getString("id").concat("   ")+patientid+appointmentid+servicecode+createddate;
					ExcelRead.setCellData(path, sheet, excelrow, 6, result);
						
				}
				
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
			Util.sleepTime(1000);
		}
	}



	
	@Test
	public void editMedicines() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="EditPrescribedMedicines";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String medicine=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String dosageinstructions=ExcelRead.getCellData(path, sheet, excelrow, 4);
			





			
			
			
			
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
			
			cons.clickOnConsultationTab();
			logger.info("Clicked On Consultation Tab");
			Util.sleepTime(1000);
			
			cons.clickOnMedicinesTab();
			logger.info("Clicked On Medicines Tab");
			Util.sleepTime(1000);
			
			cons.clickOnMedicineEditBtn(medicine);
			logger.info("Clicked On Medicine edit button  :"+medicine);
			Util.sleepTime(1500);
			
			cons.enterDosageInstructions(dosageinstructions);
			logger.info("Entered Dosage Instructions  :"+dosageinstructions);
			Util.sleepTime(1000);
			
			cons.clickOnMedicineSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(1000);
			
			String flag=cons.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
			Util.sleepTime(1000);
			
			cons.clickOnCloseIconBtn();
			logger.info("Clicked On Close Icon");
			Util.sleepTime(1000);
			
			cons.clickOnCloseBtn();
			logger.info("Clicked On Close button");
			Util.sleepTime(1000);
			
		}
	}
	
	
	
	@Test
	public void deleteMedicines() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="DeletePrescribedMedicine";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String medicine=ExcelRead.getCellData(path, sheet, excelrow, 3);
			
			





			
			
			
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
			
			cons.clickOnConsultationTab();
			logger.info("Clicked On Consultation Tab");
			Util.sleepTime(1000);
			
			cons.clickOnMedicinesTab();
			logger.info("Clicked On Medicines Tab");
			Util.sleepTime(1500);
			
			cons.clickOnMedicineDeleteBtn(medicine);
			logger.info("Clicked On Medicine delete button  :"+medicine);
			Util.sleepTime(1500);
			
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
			Util.sleepTime(1000);
			
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
