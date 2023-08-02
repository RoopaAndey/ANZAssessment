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

public class PatientsModule {


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
	public void patientsCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Patients pat=PageFactory.initElements(driver, Patients.class);
		String sheet="PatientDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientname=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String gender=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String bloodgroup=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String age=ExcelRead.getCellData(path, sheet, excelrow, 4);
			String emailid=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String phonenumber=ExcelRead.getCellData(path, sheet, excelrow, 6);
			String emergencycontactname=ExcelRead.getCellData(path, sheet, excelrow, 7);
			String emergencymobileno=ExcelRead.getCellData(path, sheet, excelrow, 8);
			String occupation=ExcelRead.getCellData(path, sheet, excelrow, 9);
			String nationality=ExcelRead.getCellData(path, sheet, excelrow, 10);
			String address=ExcelRead.getCellData(path, sheet, excelrow, 11);












			if(location!="") {

				pat.clickOnLocationsDropdown();
				logger.info("Clicked On Location Dropdown");
				Util.sleepTime(1000);

				pat.selectLocation(location);
				logger.info("Selected Location  :"+location);
				Util.sleepTime(1000);
			}

			String newbtnstatus=pat.getNewBtnStatus();
			if(!newbtnstatus.contains("disabled")) {

				pat.clickOnNewBtn();
				logger.info("Clicked On New button");
				Util.sleepTime(1500);

				if(patientname!="") {

					pat.enterPatientName(patientname);
					logger.info("Entered Patient Name :"+patientname);
					Util.sleepTime(500);
				}

				if(gender!="") {

					pat.clickOnGenderDropdown();
					logger.info("Clicked On Gender Dropdown");
					Util.sleepTime(1000);

					pat.selectGender(gender);
					logger.info("Selected Gender  :"+gender);
					Util.sleepTime(1000);
				}

				if(bloodgroup!="") {

					pat.clickOnBloodGroupDropdown();
					logger.info("Clicked On Blood group Dropdown");
					Util.sleepTime(1000);

					pat.selectBloodGroup(bloodgroup);
					logger.info("Selected Blood group  :"+bloodgroup);
					Util.sleepTime(1000);
				}

				if(age!="") {

					pat.enterAge(age);
					logger.info("Entered Age :"+age);
					Util.sleepTime(500);
				}

				if(emailid!="") {

					pat.enterEmailID(emailid);
					logger.info("Entered Email ID :"+emailid);
					Util.sleepTime(500);
				}

				if(phonenumber!="") {

					pat.enterPhoneNumber(phonenumber);
					logger.info("Entered Phone Number :"+phonenumber);
					Util.sleepTime(500);
				}

				if(emergencycontactname!="") {

					pat.enterEmergencyContactName(emergencycontactname);
					logger.info("Entered Emergency Contact Name :"+emergencycontactname);
					Util.sleepTime(500);
				}

				if(emergencymobileno!="") {

					pat.enterEmergencyMobileNo(emergencymobileno);
					logger.info("Entered Emergency Mobile No  :"+emergencymobileno);
					Util.sleepTime(500);
				}

				if(occupation!="") {

					pat.enterOccupation(occupation);
					logger.info("Entered Occupation :"+occupation);
					Util.sleepTime(500);
				}

				if(nationality!="") {

					pat.enterNationality(nationality);
					logger.info("Entered Nationality :"+nationality);
					Util.sleepTime(500);
				}

				if(address!="") {

					pat.enterAddress(address);
					logger.info("Entered Address :"+address);
					Util.sleepTime(500);
				}

				String savebtnstatus=pat.getSaveBtnStatus();

				if(!savebtnstatus.contains("disabled")) {

					pat.clickOnSaveBtn();
					logger.info("Clicked On Save button");
					Util.sleepTime(2500);

					String flag=pat.getAlert();
					if(flag.contains("successfully")) {

						ExcelRead.setCellData(path, sheet, excelrow, 12, flag);
						Util.sleepTime(1000);

						st=con.createStatement();
						String selectquery="select id,pat_id,patient_name,creation_date  from patients  where patient_name='"+patientname+"' ";
						resultset=st.executeQuery(selectquery);
						while(resultset.next()) {

							String patientid=resultset.getString("pat_id").concat("     ");
							String name=resultset.getString("patient_name").concat("     ");
							String createddate=resultset.getString("creation_date").concat("    ");
							String result=resultset.getString("id").concat("    ")+patientid+name+createddate;
							ExcelRead.setCellData(path, sheet, excelrow, 13, result);

						}	

					}else {

						ExcelRead.setCellData(path, sheet, excelrow, 12, flag);
						Util.sleepTime(1000);

						pat.clickOnCancelBtn1();
						logger.info("Clicked On Cancel button");
						Util.sleepTime(1000);
					}
				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 12, "Save button is disabled");
					Util.sleepTime(1000);

					pat.clickOnCancelBtn1();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1000);
				}

			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 12, "New button is disabled");
				Util.sleepTime(1000);
			}
		}
	}




	@Test
	public void editPatientsDetails() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Patients pat=PageFactory.initElements(driver, Patients.class);
		String sheet="EditPatientDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {

			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String bloodgroup=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String phoneno=ExcelRead.getCellData(path, sheet, excelrow, 3);
			









			pat.clickOnLocationsDropdown();
			logger.info("Clicked On Location Dropdown");
			Util.sleepTime(1000);

			pat.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			pat.clickOnEditBtn(patientrecord);
			logger.info("Clicked On Edit button  :"+patientrecord);
			Util.sleepTime(1500);

			pat.clearPhoneNumber();
			logger.info("Cleared Phone number");
			Util.sleepTime(500);

			pat.enterPhoneNumber(phoneno);
			logger.info("Entered Phone number  :"+phoneno);
			Util.sleepTime(500);

			pat.clickOnBloodGpDropdwon();
			logger.info("Clicked On Blood group Dropdown");
			Util.sleepTime(1000);

			pat.selectBloodGroup(bloodgroup);
			logger.info("Selected Blood group  :"+bloodgroup);
			Util.sleepTime(1000);

			pat.clickOnUpdateBtn();
			logger.info("Clicked On Update button");
			Util.sleepTime(2000);

			String flag=pat.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
			Util.sleepTime(1500);
		}
	}



	
	@Test
	public void deletePatients() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Patients pat=PageFactory.initElements(driver, Patients.class);
		String sheet="DeletePatients";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=9;excelrow<=rowcount;excelrow++) {

			String location=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String patientrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);







			pat.clickOnLocationsDropdown();
			logger.info("Clicked On Location Dropdown");
			Util.sleepTime(1000);

			pat.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);

			pat.clickOnDeleteBtn(patientrecord);
			logger.info("Clicked On Delete button  :"+patientrecord);
			Util.sleepTime(1500);
			
			pat.clickOnOkBtn();
			logger.info("Clicked On Ok button");
			Util.sleepTime(1500);
			
			String flag=pat.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
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
