package com.codon.VueHMS.testcases;

import java.awt.AWTException;
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
import com.codon.VueHMS.pages.Users;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class UsersGrid {



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
			Util.sleepTime(1000);	

			LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
			Users users =PageFactory.initElements(driver, Users.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);
			
			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);
			
			users.clickOnAdministrationTab();
			logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);
			
			users.clickOnUsers();
			logger.info("Clicked On Users");
			Util.sleepTime(1000);
			
			users.clickOnRowsPerPageDropdown();
			logger.info("Clicked On Rows per page dropdown");
			Util.sleepTime(1000);
			
			users.selectAllRowsPerPage();
			logger.info("Selected All rows per page");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}


	
	
	@Test
	public void userCreation() throws IOException, SQLException, AWTException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Users users =PageFactory.initElements(driver, Users.class);
		String sheet="CreateUser";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String uname=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String firstname=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String lastname=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String email=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String password=ExcelRead.getCellData(path, sheet, excelrow, 4);
			String confirmpassword=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String activeuser=ExcelRead.getCellData(path, sheet, excelrow, 6);
			String state=ExcelRead.getCellData(path, sheet, excelrow, 7);
			String city=ExcelRead.getCellData(path, sheet, excelrow, 8);
			String country=ExcelRead.getCellData(path, sheet, excelrow, 9);
			String zipcode=ExcelRead.getCellData(path, sheet, excelrow, 10);
			String phoneno=ExcelRead.getCellData(path, sheet, excelrow, 11);
			String fax=ExcelRead.getCellData(path, sheet, excelrow, 12);
			String location=ExcelRead.getCellData(path, sheet, excelrow, 13);
			String role=ExcelRead.getCellData(path, sheet, excelrow, 14);
			String address=ExcelRead.getCellData(path, sheet, excelrow, 15);
		
						
			
			users.clickOnNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1000);
			
			if(uname!="") {
				
				users.enterUserName(uname);
				logger.info("Entered Username :"+uname);
				Util.sleepTime(500);
			}
			
			if(firstname!="") {
				
				users.enterFirstName(firstname);
				logger.info("Entered First name  :"+firstname);
				Util.sleepTime(500);
			}
			
			if(lastname!="") {
				
				users.enterLastName(lastname);
				logger.info("Entered Last name  :"+lastname);
				Util.sleepTime(500);
			}
			
			if(email!="") {
				
				users.enterEmail(email);
				logger.info("Entered email  :"+email);
				Util.sleepTime(500);
			}
			
			if(password!="") {
				
				users.enterPassword(password);
				logger.info("Entered password  :"+password);
				Util.sleepTime(500);
			}
			
			if(confirmpassword!="") {
				
				users.enterConfirmPassword(confirmpassword);
				logger.info("Entered Confirm password  :"+confirmpassword);
				Util.sleepTime(500);
			}
			
			if(activeuser.contains("Yes")) {
				
				users.selectActiveUserCheckBox();
				logger.info("Selected Active user check box");
				Util.sleepTime(500);
			}
			
			String continuebtn=users.getContinueButtonStatus();
			if(!continuebtn.contains("disabled")) {
				
				users.clickOnContinueBtn();
				logger.info("Clicked On Continue button");
				Util.sleepTime(1000);
				
				if(state!="") {
					
					users.enterState(state);
					logger.info("Entered State  :"+state);
					Util.sleepTime(500);
				}
				
				if(city!="") {
					
					users.enterCity(city);
					logger.info("Entered City  :"+city);
					Util.sleepTime(500);
				}
				
				if(country!="") {
					
					users.enterCountry(country);
					logger.info("Entered Country  :"+country);
					Util.sleepTime(500);
				}
				
				if(zipcode!="") {
					
					users.enterZipcode(zipcode);
					logger.info("Entered Zipcode  :"+zipcode);
					Util.sleepTime(500);
				}
				
				if(phoneno!="") {
					
					users.enterPhoneNumber(phoneno);
					logger.info("Entered Phone number  :"+phoneno);
					Util.sleepTime(500);
				}
				
				if(fax!="") {
					
					users.enterFax(fax);
					logger.info("Entered Fax  :"+fax);
					Util.sleepTime(500);
				}
				
				if(location!="") {
					
					users.clickOnLocationDropdown();
					logger.info("Clicked On Location dropdown");
					Util.sleepTime(1000);
					
					users.selectLocation(location);
					logger.info("Selected Location  :"+location);
					Util.sleepTime(1000);
				}
				
				if(role!="") {
					
					users.clickOnRoleDropdown();
					logger.info("Clicked On Role dropdown");
					Util.sleepTime(1000);
				
					users.selectRole(role);
					logger.info("Selected role  :"+role);
					Util.sleepTime(1000);
				}
				
				if(address!="") {
					
					users.enterAddress(address);
					logger.info("Entered address  :"+address);
					Util.sleepTime(500);
				}
				
				String savebtnstatus=users.getSaveBtnStatus();
				if(!savebtnstatus.contains("disabled")) {
					
					users.clickOnSaveBtn();
					logger.info("Clicked On Save button");
					Util.sleepTime(2000);
					
					String flag=users.getAlert();
					if(flag.contains("successfully")) {
						
						ExcelRead.setCellData(path, sheet, excelrow, 16, flag);
						Util.sleepTime(1000);
						
						st=con.createStatement();
						String selectquery="select username,email,is_active_user from login_user where username='"+uname+"' ";
						resultset=st.executeQuery(selectquery);
						while(resultset.next()) {
							
							String isactiveuser=resultset.getString("is_active_user").concat("    ");
							String emailid=resultset.getString("email").concat("     ");
							String result=resultset.getString("username").concat("     ")+emailid+isactiveuser;
							ExcelRead.setCellData(path, sheet, excelrow, 17, result);	
						}
						
					}else {
						
						ExcelRead.setCellData(path, sheet, excelrow, 16, flag);
						Util.sleepTime(1000);
						
						users.clickOnCancelBtn2();
						logger.info("Clicked On Cancel button");
						Util.sleepTime(1000);
					}
					
				}else {
					
					ExcelRead.setCellData(path, sheet, excelrow, 16, "Save button is disabled");
					Util.sleepTime(1000);
					
					users.clickOnCancelBtn2();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1000);
				}
				
			}else {
				
				ExcelRead.setCellData(path, sheet, excelrow, 16, "Continue button is disabled");
				Util.sleepTime(1000);
				
				users.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1000);	
			}
		}
	}




	@Test
	public void editUser() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Users users =PageFactory.initElements(driver, Users.class);
		String sheet="EditUser";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String username=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String phoneno=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String location=ExcelRead.getCellData(path, sheet, excelrow, 2);
			
					
			
			
			users.clickOnEditBtn(username);
			logger.info("Clicked On Edit button  :"+username);
			Util.sleepTime(1500);
			
			users.clickOnContinueBtn();
			logger.info("Clicked On Continue button");
			Util.sleepTime(1000);
			
			users.clearPhoneno();
			logger.info("Cleared Phoneno");
			Util.sleepTime(1000);
			
			users.enterPhoneNumber(phoneno);
			logger.info("Entered Phone Number :"+phoneno);
			Util.sleepTime(1000);
			
			users.clickOnLocationDropdown();
			logger.info("Clicked On Location dropdown");
			Util.sleepTime(1000);
			
			users.selectLocation(location);
			logger.info("Selected Location  :"+location);
			Util.sleepTime(1000);
			
			users.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2500);
			
			String flag=users.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
			Util.sleepTime(1500);	
		}
	}

	
	
	
	@Test
	public void resetPassword() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Users users =PageFactory.initElements(driver, Users.class);
		String sheet="ResetPassword";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String username=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String newpassword=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String confirmpassword=ExcelRead.getCellData(path, sheet, excelrow, 2);
			
			
			
		
			
			
		
			
			users.clickOnResetPasswordBtn(username);
			logger.info("Clicked On Reset Password button");
			Util.sleepTime(1500);

			users.enterNewPassword(newpassword);
			logger.info("Entered New Password  :"+newpassword);
			Util.sleepTime(1000);

			users.enterConfirmNewPassword(confirmpassword);
			logger.info("Entered Confirm Password  :"+confirmpassword);
			Util.sleepTime(1000);

			String savebtnstatus=users.getSaveBtnStatus();

			if(!savebtnstatus.contains("disabled")) {

				users.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(1500);

				String flag=users.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
					Util.sleepTime(1000);
				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
					Util.sleepTime(1000);

					users.clickOnCancelBtn3();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1500);
				}

			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 3, "Save button is disabled");
				Util.sleepTime(1000);
				
				users.clickOnCancelBtn3();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}
		}	
	}
	


	
	@Test
	public void deleteUser() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Users users =PageFactory.initElements(driver, Users.class);
		String sheet="DeleteUser";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String username=ExcelRead.getCellData(path, sheet, excelrow, 0);
			
			
			
			
			
			
			
			
			users.clickOnDeleteBtn(username);
			logger.info("Clicked On Delete button  :"+username);
			Util.sleepTime(2000);
			
			users.clickOnOkBtn();
			logger.info("Clicked On OK button");
			Util.sleepTime(1500);
			
			String flag=users.getAlert();
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