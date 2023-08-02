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

public class Departments {


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
			
		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}




	@Test
	public void departmentCreation() throws IOException, SQLException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Doctors dr=PageFactory.initElements(driver, Doctors.class);
		String sheet="CreateDepartments";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		dr.clickOnDepartmentsBtn();
		logger.info("Clicked On Departments button");
		Util.sleepTime(1000);

		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String deptname=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String description=ExcelRead.getCellData(path, sheet, excelrow, 1);
			
			
			
			
			
			
			
			
			
			dr.clickOnDepartmentsNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1500);
			
			if(deptname!="") {
				
				dr.enterDepartmentName(deptname);
				logger.info("Entered Department name :"+deptname);
				Util.sleepTime(500);
			}

			if(description!="") {

				dr.enterDeptDescription(description);
				logger.info("Entered Description  :"+description);
				Util.sleepTime(500);
			}

			String savebtnstatus=dr.getSaveBtnStatus();

			if(!savebtnstatus.contains("disabled")) {

				dr.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(1500);

				String flag=dr.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
					Util.sleepTime(1500);

					st=con.createStatement();
					String selectquery="select id,department_name from department  where department_name='"+deptname+"' ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

                         String departmentname=resultset.getString("department_name");
                         String result=resultset.getString("id").concat("     ")+departmentname;
                         ExcelRead.setCellData(path, sheet, excelrow, 3, result);
					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
					Util.sleepTime(1500);

					dr.clickOnCancelBtn1();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1500);
				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 2, "Save button is disabled");
				Util.sleepTime(1500);

				dr.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}

		}
		
		dr.clickOnCancelBtn2();
		logger.info("Clicked On Cancel button");
		Util.sleepTime(1500);
	}
	
	
	
	
	@Test
	public void editDepartmentDetails() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Doctors dr=PageFactory.initElements(driver, Doctors.class);
		String sheet="EditDepartments";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		dr.clickOnDepartmentsBtn();
		logger.info("Clicked On Departments button");
		Util.sleepTime(1000);

		dr.clickOnRowsPerPageDropdown();
        logger.info("Clicked On Rows per page dropdown");
		Util.sleepTime(1000);

		dr.selectAllRowsPerPage();
		logger.info("Selected All rows per page");
		Util.sleepTime(1000);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String deptname=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String description=ExcelRead.getCellData(path, sheet, excelrow, 1);
			
			
			
			
			
			
			
			
			dr.selectDepartmentRecord(deptname);
			logger.info("Selected Department record  :"+deptname);
			Util.sleepTime(2000);
			
			dr.clickOnDeptEditBtn();
			logger.info("Clicked On Edit button");
			Util.sleepTime(1000);
			
			dr.clearDescription();
			logger.info("Cleared Description");
			Util.sleepTime(500);
			
			dr.enterDeptDescription(description);
			logger.info("Entered Description  :"+description);
			Util.sleepTime(1000);
			
			dr.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2500);
			
			String flag=dr.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
			Util.sleepTime(2000);
			
		}
		
		dr.clickOnCancelBtn2();
		logger.info("Clicked On Cancel button");
		Util.sleepTime(1500);	
	}
	
	
	
	
	@Test
	public void deleteDepartment() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		Doctors dr=PageFactory.initElements(driver, Doctors.class);
		String sheet="DeleteDepartment";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		dr.clickOnDepartmentsBtn();
		logger.info("Clicked On Departments button");
		Util.sleepTime(1000);

		dr.clickOnRowsPerPageDropdown();
        logger.info("Clicked On Rows per page dropdown");
		Util.sleepTime(1000);

		dr.selectAllRowsPerPage();
		logger.info("Selected All rows per page");
		Util.sleepTime(1000);
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String deptname=ExcelRead.getCellData(path, sheet, excelrow, 0);
			
			
			
			
			
			
			
			dr.selectDepartmentRecord(deptname);
			logger.info("Selected Department name  :"+deptname);
			Util.sleepTime(1000);
			
			dr.clickOnDeptDeleteBtn();
			logger.info("Clicked On Delete button");
			Util.sleepTime(1000);
			
			dr.clickOnOkBtn();
			logger.info("Clicked On Ok button");
			Util.sleepTime(1500);
			
			String flag=dr.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
			Util.sleepTime(1000);
		}
		
		dr.clickOnCancelBtn2();
		logger.info("Clicked On Cancel button");
		Util.sleepTime(1500);	
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
