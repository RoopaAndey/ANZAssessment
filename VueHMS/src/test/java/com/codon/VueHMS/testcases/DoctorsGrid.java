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
import com.codon.VueHMS.pages.Doctors;
import com.codon.VueHMS.pages.LoginPage;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class DoctorsGrid {


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
			Doctors dr=PageFactory.initElements(driver, Doctors.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			dr.clickOnAdministrationTab();
			logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);

			dr.clickOnDoctors();
			logger.info("Clicked On Doctors");
			Util.sleepTime(1000);

			dr.clickOnRowsPerPageDropdown();
			logger.info("Clicked On Rows per page dropdown");
			Util.sleepTime(1000);

			dr.selectAllRowsPerPage();
			logger.info("Selected All rows per page");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}




	@Test
	public void doctorCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Doctors dr=PageFactory.initElements(driver, Doctors.class);
		String sheet="DoctorCreation";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String usernameordisplayname=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String firstname=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String lastname=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String age=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String emailid=ExcelRead.getCellData(path, sheet, excelrow, 4);
			String gender=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String department=ExcelRead.getCellData(path, sheet, excelrow, 6);
			String mobileno=ExcelRead.getCellData(path, sheet, excelrow, 7);
			String landline=ExcelRead.getCellData(path, sheet, excelrow, 8);
			String fax=ExcelRead.getCellData(path, sheet, excelrow, 9);
			String city=ExcelRead.getCellData(path, sheet, excelrow, 10);
			String state=ExcelRead.getCellData(path, sheet, excelrow, 11);
			String country=ExcelRead.getCellData(path, sheet, excelrow, 12);
			String zipcode=ExcelRead.getCellData(path, sheet, excelrow, 13);
			String address=ExcelRead.getCellData(path, sheet, excelrow, 14);
			String location=ExcelRead.getCellData(path, sheet, excelrow, 15);
			String servicecodes=ExcelRead.getCellData(path, sheet, excelrow, 16);
			String skills=ExcelRead.getCellData(path, sheet, excelrow, 17);
			String isuser=ExcelRead.getCellData(path, sheet, excelrow, 18);
			String role=ExcelRead.getCellData(path, sheet, excelrow, 19);
			String password=ExcelRead.getCellData(path, sheet, excelrow, 20);
			String confirmpassword=ExcelRead.getCellData(path, sheet, excelrow, 21);











			dr.clickOnNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(2000);

			if(usernameordisplayname!="") {

				dr.enterUsernameOrDisplayname(usernameordisplayname);
				logger.info("Entered User name or display name   :"+usernameordisplayname);
				Util.sleepTime(300);
			}

			if(firstname!="") {

				dr.enterFirstName(firstname);
				logger.info("Entered First name :"+firstname);
				Util.sleepTime(300);
			}

			if(lastname!="") {

				dr.enterLastname(lastname);
				logger.info("Entered Last name :"+lastname);
				Util.sleepTime(300);
			}

			if(age!="") {

				dr.enterAge(age);
				logger.info("Entered age :"+age);
				Util.sleepTime(300);
			}

			if(emailid!="") {

				dr.enterEmailID(emailid);
				logger.info("Entered email id :"+emailid);
				Util.sleepTime(300);
			}

			if(gender!="") {

				dr.clickonGenderDropdown();
				logger.info("Clicked On Gender Dropdown ");
				Util.sleepTime(500);

				dr.selectGender(gender);
				logger.info("Selected gender :"+gender);
				Util.sleepTime(500);
			}

			if(department!="") {

				dr.clickonDepartmentsDropdown();
				logger.info("Clicked On Department Dropdown ");
				Util.sleepTime(1000);

				dr.selectDepartment(department);
				logger.info("Selected department :"+department);
				Util.sleepTime(1000);
			}

			if(mobileno!="") {

				dr.enterMobileNo(mobileno);
				logger.info("Entered Mobile no :"+mobileno);
				Util.sleepTime(300);
			}

			String continuebtn1status=dr.getContinueBtn1Status();
			if(!continuebtn1status.contains("disabled")) {

				dr.clickOnContinueBtn1();
				logger.info("Clicked On Continue button ");
				Util.sleepTime(1500);

				if(landline!="") {

					dr.enterLandLine(landline);
					logger.info("Entered Landline :"+landline);
					Util.sleepTime(300);
				}

				if(fax!="") {

					dr.enterFax(fax);
					logger.info("Entered fax :"+fax);
					Util.sleepTime(300);
				}

				if(city!="") {

					dr.enterCity(city);
					logger.info("Entered City :"+city);
					Util.sleepTime(300);
				}

				if(state!="") {

					dr.enterState(state);
					logger.info("Entered state :"+state);
					Util.sleepTime(300);
				}

				if(country!="") {

					dr.enterCountry(country);
					logger.info("Entered country :"+country);
					Util.sleepTime(300);
				}

				if(zipcode!="") {

					dr.enterZipcode(zipcode);
					logger.info("Entered zipcode :"+zipcode);
					Util.sleepTime(300);
				}

				if(address!="") {

					dr.enterAddress1(address);
					logger.info("Entered address :"+address);
					Util.sleepTime(300);
				}

				if(location!="") {

					dr.clickOnLocationDropdown();
					logger.info("Clicked On Location dropdown");
					Util.sleepTime(500);

					dr.selectLocation(location);
					logger.info("Selected Location  :"+location);
					Util.sleepTime(500);
				}

				String continuebtn2status=dr.getContinueBtn2Status();
				if(!continuebtn2status.contains("disabled")) {

					dr.clickOnContinueBtn2();
					logger.info("Clicked On Continue button");
					Util.sleepTime(1500);

					if(servicecodes!="") {

						dr.clickOnServiceCodesDropdown();
						logger.info("Clicked On Service codes dropdown");
						Util.sleepTime(500);

						dr.selectServiceCodes(servicecodes);
						logger.info("Selected Service codes  :"+servicecodes);
						Util.sleepTime(500);
						
						dr.clickOnServiceCodesDropdown();
						Util.sleepTime(500);

					}

					if(skills!="") {

						dr.clickOnSkillsDropdown();
						logger.info("Clicked On Skills dropdown");
						Util.sleepTime(500);

						dr.selectSkills(skills);
						logger.info("Selected skills :"+servicecodes);
						Util.sleepTime(500);
						
						dr.clickOnSkillsDropdown();
						Util.sleepTime(500);

					}

					if(isuser.contains("Yes")) {

						dr.selectIsUserCheckBox();
						logger.info("Selected Is user check box");
						Util.sleepTime(500);

						if(role!="") {

							dr.clickOnRoleDropdown();
							logger.info("Clicked On Role dropdown");
							Util.sleepTime(500);

							dr.selectRole(role);
							logger.info("Selected role :"+role);
							Util.sleepTime(500);
						}

						if(password!="") {
							
							dr.enterPassword(password);
							logger.info("Entered Password :"+password);
							Util.sleepTime(500);
						}

						if(confirmpassword!="") {

							dr.enterConfirmPasssword(confirmpassword);
							logger.info("Entered Confirm password :"+confirmpassword);
							Util.sleepTime(500);
						}
					}

					String savebtnstatus=dr.getSaveBtnStatus();
					if(!savebtnstatus.contains("disabled")) {

						dr.clickOnSaveBtn();
						logger.info("Clicked On Save button");
						Util.sleepTime(2000);

						String flag=dr.getAlert();
						if(flag.contains("successfully")) {

							ExcelRead.setCellData(path, sheet, excelrow, 22, flag);
							Util.sleepTime(1500);
							
							st=con.createStatement();
							String selectquery="select id,display_name,is_user,created_date,email from doctors_registration where display_name ='"+usernameordisplayname+"' ";
							resultset=st.executeQuery(selectquery);
							while(resultset.next()) {
								
								
								String displayname=resultset.getString("display_name").concat("     ");
								String isUser=resultset.getString("is_user").concat("     ");
								String email=resultset.getString("email").concat("     ");
								String cretaeddate=resultset.getString("created_date").concat("     ");
								String result=resultset.getString("id").concat("     ")+displayname+isUser+email+cretaeddate;
								ExcelRead.setCellData(path, sheet, excelrow, 23, result);
							}
							
						}else {

							ExcelRead.setCellData(path, sheet, excelrow, 22, flag);
							Util.sleepTime(1500);

							dr.clickOnCancelBtn5();
							logger.info("Clicked On Cancel button");
							Util.sleepTime(1500);
						}
					}else {

						ExcelRead.setCellData(path, sheet, excelrow, 22, "Save button is disabled");
						Util.sleepTime(1500);

						dr.clickOnCancelBtn5();
						logger.info("Clicked On Cancel button");
						Util.sleepTime(1500);
					}
				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 22, "Continue button is disabled");
					Util.sleepTime(1000);

					dr.clickOnCancelBtn4();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1500);
				}

			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 22, "Continue button is disabled");
				Util.sleepTime(1000);

				dr.clickOnCancelBtn3();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}
		}
	}


	

	@Test
	public void editDoctorDetails() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Doctors dr=PageFactory.initElements(driver, Doctors.class);
		String sheet="EditDoctorDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String doctorrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String mobileno=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String landline=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String location=ExcelRead.getCellData(path, sheet, excelrow, 3);
		
			
			
			
			
			
			
			
			dr.clickOnEditButton(doctorrecord);
			logger.info("Clicked On Edit button  :"+doctorrecord);
			Util.sleepTime(1000);
			
			dr.clearMobileNo();
			logger.info("Cleared Mobile No ");
			Util.sleepTime(500);
			
			dr.enterMobileNo(mobileno);
			logger.info("Entered Mobile No :"+mobileno);
			Util.sleepTime(1000);
			
			dr.clickOnContinueBtn1();
			logger.info("Clicked On Continue button");
			Util.sleepTime(1000);
			
			dr.clearLandLine();
			logger.info("Cleared LandLine ");
			Util.sleepTime(1000);
			
			dr.enterLandLine(landline);
			logger.info("Entered Landline No :"+landline);
			Util.sleepTime(1000);
			
			dr.clickOnLocationDropdown();
			logger.info("Clicked On Location Dropdown");
			Util.sleepTime(1000);
			
			dr.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);
			
			dr.clickOnContinueBtn2();
			logger.info("Clicked On Continue button");
			Util.sleepTime(1000);
			
			dr.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(1000);
			
			String flag=dr.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
			Util.sleepTime(1500);	
		}
	}
	
	
	
	@Test
	public void deleteDoctor() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Doctors dr=PageFactory.initElements(driver, Doctors.class);
		String sheet="DeleteDoctor";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String doctorname=ExcelRead.getCellData(path, sheet, excelrow, 0);
			
			
			
			
			
			
			
			dr.clickOnDeleteButton(doctorname);
			logger.info("Clicked On Delete button  :"+doctorname);
			Util.sleepTime(1500);
			
			dr.clickOnOkBtn();
			logger.info("Clicked On OK button");
			Util.sleepTime(2000);
			
			String flag=dr.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
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
