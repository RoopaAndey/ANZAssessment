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
import com.codon.VueHMS.pages.Roles;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class RolesGrid {


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
			Roles roles=PageFactory.initElements(driver, Roles.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			roles.clickOnAdministrationTab();
			logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);

			roles.clickOnRoles();
			logger.info("Clicked On Roles ");
			Util.sleepTime(1000);
			
			roles.clickOnRowsPerPageDropdown();
			logger.info("Clicked On Rows per page dropdown");
			Util.sleepTime(1000);
			
			roles.selectRowsPerPage();
			logger.info("Selected All rows per page ");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}




	@Test
	public void roleCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Roles roles=PageFactory.initElements(driver, Roles.class);
		String sheet="Rolecreation";
		int rowocunt=ExcelRead.getRowCount(path, sheet);
		


		for(int excelrow=1;excelrow<=rowocunt;excelrow++) {

			String rolename=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String userspermisiions=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String doctypepermissions=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String doctorpermissions=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String medicinespermissions=ExcelRead.getCellData(path, sheet, excelrow, 4);









			roles.clickOnNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1000);

			if(rolename!="") {

				roles.enterRoleName(rolename);
				logger.info("Entered Role name  :"+rolename);
				Util.sleepTime(1000);
			}

			if(userspermisiions!="") {

				String[] permissions=userspermisiions.split(",");
				
				int cell=2;

				for(String permission:permissions) {

					if(permission.contains("Yes")) {

						roles.selectUserPermissions(cell);
						logger.info("Selected Users permissions  :"+cell);
						Util.sleepTime(500);
					}

					cell++;
				}
			}

			if(doctypepermissions!="") {

				String[] doctypeperm=doctypepermissions.split(",");
				
				int cell=2;

				for(String docpermission:doctypeperm) {

					if(docpermission.contains("Yes")) {

						roles.selectDocumentTypePermissions(cell);
						logger.info("Selected Document Type permissions  :"+cell);
						Util.sleepTime(500);
					}

					cell++;
				}
			}

			if(doctorpermissions!="") {

				String[] doctorperm=doctorpermissions.split(",");
				
				int cell=2;

				for(String doctorpermission:doctorperm) {

					if(doctorpermission.contains("Yes")) {

						roles.selectDoctorPermissions(cell);
						logger.info("Selected Doctors permissions  :"+cell);
						Util.sleepTime(500);	
					}

					cell++;
				}
			}

			if(medicinespermissions!="") {

				String[] medicinesperm=medicinespermissions.split(",");
				
				int cell=2;

				for(String medcineperm:medicinesperm) {

					if(medcineperm.contains("Yes")) {

						roles.selectMedicinePermissions(cell);
						logger.info("Selected Medicines permissions  :"+cell);
						Util.sleepTime(500);
					}

					cell++;
				}
			}

			String savebtnstatus=roles.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				roles.clickOnSaveBtn();
				logger.info("Clicked On Save Button");
				Util.sleepTime(3000);

				String flag=roles.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select * from role ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String name=resultset.getString("name").concat("   ");
						String result=resultset.getString("id").concat("     ")+name;
						ExcelRead.setCellData(path, sheet, excelrow, 6, result);
					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
					Util.sleepTime(1000);

					roles.clickOnCancelBtn();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1000);	
				}

			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 5, "Save button is disabled");
				Util.sleepTime(1000);

				roles.clickOnCancelBtn();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1000);
			}
		}
	}



	@Test
	public void editRole() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Roles roles=PageFactory.initElements(driver, Roles.class);
		String sheet="EditRole";
		int rowocunt=ExcelRead.getRowCount(path, sheet);
		

		for(int excelrow=1;excelrow<=rowocunt;excelrow++) {

			String rolename=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String userspermissions=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String doctypepermissions=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String doctorpermissions=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String medicinespermissions=ExcelRead.getCellData(path, sheet, excelrow, 4);




			


			roles.clickOnEditButton(rolename);
			logger.info("Clicked On Edit button");
			Util.sleepTime(1000);

			if(userspermissions!="") {
				
				int cell=2;

				String[] permissions=userspermissions.split(",");

				for(String permission:permissions) {

					if(permission.contains("Yes")) {

						roles.selectUserPermissions(cell);
						logger.info("Selected Users permissions  :"+cell);
						Util.sleepTime(500);
					}

					cell++;
				}
			}

			if(doctypepermissions!="") {
				
				int cell=2;

				String[] doctypeperm=doctypepermissions.split(",");

				for(String docpermission:doctypeperm) {

					if(docpermission.contains("Yes")) {

						roles.selectDocumentTypePermissions(cell);
						logger.info("Selected Document Type permissions  :"+cell);
						Util.sleepTime(500);
					}

					cell++;
				}
			}

			if(doctorpermissions!="") {
				
				int cell=2;

				String[] doctorperm=doctorpermissions.split(",");

				for(String doctorpermission:doctorperm) {

					if(doctorpermission.contains("Yes")) {

						roles.selectDoctorPermissions(cell);
						logger.info("Selected Doctors permissions  :"+cell);
						Util.sleepTime(500);	
					}

					cell++;
				}
			}

			if(medicinespermissions!="") {
				
				int cell=2;

				String[] medicinesperm=medicinespermissions.split(",");

				for(String medcineperm:medicinesperm) {

					if(medcineperm.contains("Yes")) {

						roles.selectMedicinePermissions(cell);
						logger.info("Selected Medicines permissions  :"+cell);
						Util.sleepTime(500);
					}

					cell++;
				}
			}

			roles.clickOnSaveBtn();
			logger.info("Clicked On Save Button");
			Util.sleepTime(3000);

			String flag=roles.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
			Util.sleepTime(1000);

		}
	}


	

	@Test
	public void deleteRole() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Roles roles=PageFactory.initElements(driver, Roles.class);
		String sheet="DeleteRole";
		int rowocunt=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowocunt;excelrow++) {

			String rolename=ExcelRead.getCellData(path, sheet, excelrow, 0);
			
			
			
			
			
			
			
			roles.clickOnDeleteButton(rolename);
			logger.info("Clicked On Delete button");
			Util.sleepTime(1000);
			
			roles.clickOnOKBtn();
			logger.info("Clicked On OK button");
			Util.sleepTime(3000);
			
			String flag=roles.getAlert();
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
			logger.info("Closed Firefox browser");
			Util.sleepTime(1000);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}	
}
