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
import com.codon.VueHMS.pages.Patients;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class Appointments {


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
	public void appointmentCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Patients pat=PageFactory.initElements(driver, Patients.class);
		String sheet="CreateAppointments";
		int rowcount=ExcelRead.getRowCount(path, sheet);



		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String doctorname=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String skill=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String reasonforvisit=ExcelRead.getCellData(path, sheet, excelrow, 4);
			//String appointmentyear=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String appointmentmonth=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String appointmentdate=ExcelRead.getCellData(path, sheet, excelrow, 6);
			String appointmenttime=ExcelRead.getCellData(path, sheet, excelrow, 7);
			String appointmenttype=ExcelRead.getCellData(path, sheet, excelrow, 8);
			String room=ExcelRead.getCellData(path, sheet, excelrow, 9);
			String bed=ExcelRead.getCellData(path, sheet, excelrow, 10);










			pat.clickOnLocationsDropdown();
			logger.info("Clicked On Location Dropdown");
			Util.sleepTime(1000);

			pat.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			pat.clickOnAppointmentsBtn(patientrecord);
			logger.info("Clicked On Appointments button  :"+patientrecord);
			Util.sleepTime(1500);

			pat.clickOnAppointmentsNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1500);

			if(doctorname!="") {

				pat.clickOnDoctorNameDropdown();
				logger.info("Clicked On Doctor Name Dropdown");
				Util.sleepTime(500);

				pat.selectDoctorName(doctorname);
				logger.info("Selected Doctor name  :"+doctorname);
				Util.sleepTime(500);
			}

			if(skill!="") {

				pat.clickOnSkillsDropdown();
				logger.info("Clicked On Skill Dropdown");
				Util.sleepTime(500);

				pat.selectSkills(skill);
				logger.info("Selected skill  :"+skill);
				Util.sleepTime(500);
			}

			if(reasonforvisit!="") {

				pat.enterReasonForVisit(reasonforvisit);
				logger.info("Entered Reason for visit  :"+reasonforvisit);
				Util.sleepTime(500);
			}

			if(appointmentdate!="") {

				pat.clickOnAppointmentDatePicker();
				logger.info("Clicked On Appointment Date picker");
				Util.sleepTime(1000);

				pat.selectMonth(appointmentmonth);
				logger.info("Selected Appointment month  :"+appointmentmonth);
				Util.sleepTime(1000);

				pat.selectDate(appointmentdate);
				logger.info("Selected Appointment date  :"+appointmentdate);
				Util.sleepTime(1000);
			
			}

			if(appointmenttime!="") {

				pat.clickOnAppointmenttimeDropdown();
				logger.info("Clicked On Appointment time Dropdown");
				Util.sleepTime(1000);

				pat.selectAppointmetTime(appointmenttime);
				logger.info("Selected Appointment time  :"+appointmenttime);
				Util.sleepTime(1000);
			}

			if(appointmenttype!="") {

				pat.clickOnAppointmentTypeDropdown();
				logger.info("Clicked On Appointment Type Dropdown");
				Util.sleepTime(1000);

				pat.selectAppointmetType(appointmenttype);
				logger.info("Selected Appointment type  :"+appointmenttype);
				Util.sleepTime(1000);

				if(appointmenttype.contains("IN")) {

					if(room!="") {

						pat.clickOnRoomDropdown();
						logger.info("Clicked On Room Dropdown");
						Util.sleepTime(1000);

						pat.selectRoom(room);
						logger.info("Selected room  :"+room);
						Util.sleepTime(1000);
					}

					if(bed!="") {

						pat.clickOnBedDropdown();
						logger.info("Clicked On Bed Dropdown");
						Util.sleepTime(1000);

						pat.selectBed(bed);
						logger.info("Selected Bed :"+bed);
						Util.sleepTime(1000);
					}
				}
			}
			
			String date=pat.getAppointmentDate();
			System.out.println("date is  :"+date);

			String savebtnstatus=pat.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				pat.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=pat.getAlert();
				if(flag.contains("Successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 11, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select id,patient,doctor,appointment_date,time,created_date from appointments where patient in (select pat_id from patients  where patient_name='"+patientrecord+"') and time='"+appointmenttime+"' ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String patient=resultset.getString("patient").concat("    ");
						String doctor=resultset.getString("doctor").concat("    ");
						//String appointment_date=resultset.getString("appointment_date").concat("    ");
						String time=resultset.getString("time").concat("   ");
						String createddate=resultset.getString("created_date").concat("    ");
						String result=resultset.getString("id").concat("    ")+patient+doctor+time+createddate;
						ExcelRead.setCellData(path, sheet, excelrow, 12, result);

					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 11, flag);
					Util.sleepTime(1000);

					pat.clickOnCancelBtn1();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1500);
				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 11, "Save button is disabled");
				Util.sleepTime(1000);

				pat.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}

			pat.clickOnCloseBtn();
			logger.info("Clicked On Close button");
			Util.sleepTime(1500);
		}
	}



	@Test
	public void editAppointment() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Patients pat=PageFactory.initElements(driver, Patients.class);
		String sheet="EditAppointment";
		int rowcount=ExcelRead.getRowCount(path, sheet);



		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String appointmenttime=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String referredby=ExcelRead.getCellData(path, sheet, excelrow, 4);
		




			



			pat.clickOnLocationsDropdown();
			logger.info("Clicked On Locations dropdown");
			Util.sleepTime(1000);

			pat.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			pat.clickOnAppointmentsBtn(patientrecord);
			logger.info("Clicked On Appointments button  :"+patientrecord);
			Util.sleepTime(1500);

			pat.clickOnAppointmentEditBtn(appointmentrecord);
			logger.info("Clicked On Appointment edit button  :"+appointmentrecord);
			Util.sleepTime(1000);

			pat.clickOnAppointmenttimeDropdown();
			logger.info("Clicked On Appointment time dropdown");
			Util.sleepTime(1000);
			
			pat.selectAppointmetTime(appointmenttime);
			logger.info("Selected Appointment time  :"+appointmenttime);
			Util.sleepTime(1000);
			
			pat.clickOnReferedByDropdown();
			logger.info("Clicked On Referred By dropdown");
			Util.sleepTime(1000);
			
			pat.selectReferedBy(referredby);
			logger.info("Selected Referred By  :"+referredby);
			Util.sleepTime(1000);
			
			pat.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);
			
			String flag=pat.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
			Util.sleepTime(1500);
			
			pat.clickOnCloseBtn();
			logger.info("Clicked On Close button");
			Util.sleepTime(1000);

		}
	}



	@Test
	public void deleteAppointment() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Patients pat=PageFactory.initElements(driver, Patients.class);
		String sheet="DeleteAppointment";
		int rowcount=ExcelRead.getRowCount(path, sheet);



		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			








			pat.clickOnLocationsDropdown();
			logger.info("Clicked On Locations Dropdown");
			Util.sleepTime(1000);

			pat.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			pat.clickOnAppointmentsBtn(patientrecord);
			logger.info("Clicked On Appointments button  :"+patientrecord);
			Util.sleepTime(1500);
			
			pat.clickOnAppointmentDeleteBtn(appointmentrecord);
			logger.info("Clicked On Appointments delete button  :"+appointmentrecord);
			Util.sleepTime(1500);
			
			pat.clickOnOkBtn();
			logger.info("Clicked On OK button");
			Util.sleepTime(2000);
			
			String flag=pat.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow,3, flag);
			Util.sleepTime(1500);
			
			pat.clickOnCloseBtn();
			logger.info("Clicked On Close button");
			Util.sleepTime(1000);
		}
	}
	
	
	
	
	@Test
	public void admitAsINPatient() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Patients pat=PageFactory.initElements(driver, Patients.class);
		String sheet="AdmitAsINPatient";
		int rowcount=ExcelRead.getRowCount(path, sheet);



		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String room=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String bed=ExcelRead.getCellData(path, sheet, excelrow, 4);
		
		
		
			
			
			
			
			
			
			pat.clickOnLocationsDropdown();
			logger.info("Clicked On Locations Dropdown");
			Util.sleepTime(1000);

			pat.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			pat.clickOnAppointmentsBtn(patientrecord);
			logger.info("Clicked On Appointment button  :"+patientrecord);
			Util.sleepTime(1500);
			
			pat.selectAppointmentRecord(appointmentrecord);
			logger.info("Selected Appointment record  :"+appointmentrecord);
			Util.sleepTime(1000);
			
			pat.clickOnAdmitAsINPatientBtn();
			logger.info("Clicked On Admit AS IN Patient");
			Util.sleepTime(1000);

			if(room!="") {

				pat.clickOnRoomDropdown();
				logger.info("Clicked On Room dropdown");
				Util.sleepTime(500);

				pat.selectRoom(room);
				logger.info("Selected room :"+room);
				Util.sleepTime(500);
			}

			if(bed!="") {

				pat.clickOnBedDropdown();
				logger.info("Clicked On Bed dropdown");
				Util.sleepTime(500);

				pat.selectBed(bed);
				logger.info("Selected bed :"+bed);
				Util.sleepTime(500);
			}
			
			String changebtnstatus=pat.getChangeBtnStatus();
			if(!changebtnstatus.contains("disabled")) {
				
				pat.clickOnChangeBtn();
				logger.info("Clicked On Change button");
				Util.sleepTime(2000);
				
				String flag=pat.getAlert();
				ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
				Util.sleepTime(1000);
			
			}else {
				
				ExcelRead.setCellData(path, sheet, excelrow, 5, "Save button is disabed");
				Util.sleepTime(1000);
			
				pat.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1000);
			}
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
