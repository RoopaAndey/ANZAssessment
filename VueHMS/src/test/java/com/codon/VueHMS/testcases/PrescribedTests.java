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

public class PrescribedTests {


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
	public void testCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="TestsCreation";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String name=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String scheduledmonth=ExcelRead.getCellData(path, sheet, excelrow, 4);
			String scheduleddate=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String scheduledtime=ExcelRead.getCellData(path, sheet, excelrow, 6);





			
			
			
			
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
			
			cons.clickOnTestsNewBtn();
			logger.info("Clicked On New Button");
			Util.sleepTime(1000);
			
			if(name!="") {

				cons.clickOnNameDropdown();
				logger.info("Clicked On Name Dropdown");
				Util.sleepTime(1000);

				cons.selectTestName(name);
				logger.info("Selected Name  :"+name);
				Util.sleepTime(1000);
			}

			if(scheduleddate!="") {

				cons.clickOnScheduledDatePicker();
				logger.info("Clicked On Scheduled date picker");
				Util.sleepTime(1000);

				cons.selectMonth(scheduledmonth);
				logger.info("Selected Month  :"+scheduledmonth);
				Util.sleepTime(1000);

				cons.selectDate(scheduleddate);
				logger.info("Selected date  :"+scheduleddate);
				Util.sleepTime(1000);
			}

			if(scheduledtime!="") {

				cons.clickOnScheduledTimeDropdown();
				logger.info("Clicked On Scheduled Time Dropdown");
				Util.sleepTime(1000);
				
				cons.selectScheduletTime(scheduledtime);
				logger.info("Selected time  :"+scheduledtime);
				Util.sleepTime(1000);
			}
			
			String savebtnstatus=cons.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				cons.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=cons.getAlert();
				if(flag.contains("successfully")) {
				
					ExcelRead.setCellData(path, sheet, excelrow, 7, flag);
					Util.sleepTime(1000);
					
					st=con.createStatement();
					String selectquery="select id,patient_id,appointment_id,name,created_date,schedule_time,schedule_date from prescriptions  where patient_id in (select pat_id from patients  where patient_name='"+patientrecord+"') and type='Tests' and name='"+name+"' ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {
						
						String patientid=resultset.getString("patient_id").concat("  ");
						String appointmentid=resultset.getString("appointment_id").concat("   ");
						String testname=resultset.getString("name").concat("   ");
						String createddate=resultset.getString("created_date").concat("   ");
						String scheduletime=resultset.getString("schedule_time").concat("  ");
						String scheduledate=resultset.getString("schedule_date").concat("    ");
						String result=resultset.getString("id").concat("   ")+patientid+appointmentid+testname+createddate+scheduletime+scheduledate;
						ExcelRead.setCellData(path, sheet, excelrow, 8, result);
							
					}
				
				}else {
					
					ExcelRead.setCellData(path, sheet, excelrow, 7, flag);
					Util.sleepTime(1000);
					
					cons.clickOnTestsCloseIconBtn();
					logger.info("Clicked On Close Icon");
					Util.sleepTime(1500);
				}
			}else {
				
				ExcelRead.setCellData(path, sheet, excelrow, 7, "Save button is disabled");
				Util.sleepTime(1000);
				
				cons.clickOnTestsCloseIconBtn();
				logger.info("Clicked On Close Icon");
				Util.sleepTime(1500);
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
	public void editTests() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="EditTest";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String testrecord=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String scheduledtime=ExcelRead.getCellData(path, sheet, excelrow, 4);





			
			
			
			
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
			
			cons.clickOnTestEditBtn(testrecord);
			logger.info("Clicked On Edit Button");
			Util.sleepTime(1000);
			
			cons.clickOnScheduledTimeDropdown();
			logger.info("Clicked On Scheduled Time dropdown");
			Util.sleepTime(1000);
			
			cons.selectScheduletTime(scheduledtime);
			logger.info("Selected Scheduled time  :"+scheduledtime);
			Util.sleepTime(1000);

			cons.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);
			
			String flag=cons.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
			Util.sleepTime(1000);
			
			if(!flag.contains("successfully")) {
				
				ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
				Util.sleepTime(1000);
				
				cons.clickOnTestsCloseIconBtn();
				logger.info("Clicked On Test Close Icon");
				Util.sleepTime(1500);
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
	public void deleteTests() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="DeleteTests";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String testrecord=ExcelRead.getCellData(path, sheet, excelrow, 3);










			
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
			
			cons.clickOnTestDeleteBtn(testrecord);
			logger.info("Clicked On Delete button  :"+testrecord);
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
	
	


	@Test
	public void uploadTetsOrReports() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="UploadTestsOrReports";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String testrecord=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String filepath=ExcelRead.getCellData(path, sheet, excelrow, 4);





			
			
			
			
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
			
			cons.clickOnUploadTestsAndReportsBtn(testrecord);
			logger.info("Clicked On Upload Tests or Reports button  :"+testrecord);
			Util.sleepTime(1000);
			
			if(filepath!="") {

				cons.enterFilePath(filepath);
				logger.info("Entered File Path  :"+filepath);
				Util.sleepTime(1000);	
			}

			String uploadbtnstatus=cons.getUploadBtnStatus();
			if(!uploadbtnstatus.contains("disabled")) {

				cons.clickOnUploadBtn();
				logger.info("Clicked On Upload button");
				Util.sleepTime(1000);
				
				String flag=cons.getAlert();
				if(flag.contains("successfully")) {
					
					ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
					Util.sleepTime(1000);
					
				}else {
					
					ExcelRead.setCellData(path, sheet, excelrow, 5,flag);
					Util.sleepTime(1000);
					
					cons.clickOnCancelBtn1();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1000);
				}
			}else {
				
				ExcelRead.setCellData(path, sheet, excelrow, 5, "Upload button is disabled");
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
	public void deleteDocuments() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String testrecord=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String docname=ExcelRead.getCellData(path, sheet, excelrow, 4);





			
			
			
			
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
			
			cons.clickOnUploadTestsAndReportsBtn(testrecord);
			Util.sleepTime(1000);
			
			cons.clickOnFileDeleteBtn(docname);
			Util.sleepTime(1000);
			
			cons.clickOnOkBtn();
			Util.sleepTime(1500);
			
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
	public void testComments() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Consultation cons=PageFactory.initElements(driver, Consultation.class);
		String sheet="";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String appointmentrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String testrecord=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String comments=ExcelRead.getCellData(path, sheet, excelrow, 4);





			
			
			
			
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
			
			cons.clickOnUploadTestsAndReportsBtn(testrecord);
			Util.sleepTime(1000);
			
			cons.clickOnCommentsEditBtn();
			Util.sleepTime(1000);
			
			if(comments!="") {
				
				cons.enterComments(comments);
				Util.sleepTime(500);
			}
			
			cons.clickOnSaveBtn();
			Util.sleepTime(1500);
			
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
