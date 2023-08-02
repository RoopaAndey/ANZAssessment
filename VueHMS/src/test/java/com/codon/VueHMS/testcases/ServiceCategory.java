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
import com.codon.VueHMS.pages.Services;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class ServiceCategory {


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
			Services sr=PageFactory.initElements(driver, Services.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			sr.clickOnAdministrationTab();
			logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);

			sr.clickOnDServicesOffered();
			logger.info("Clicked On Service Offred");
			Util.sleepTime(1000);

			sr.clickOnServiceCategoryBtn();
			logger.info("Clicked On Service category button");
			Util.sleepTime(1000);

			sr.clickOnCatgRowsPerPageDropdown();
			logger.info("Clicked On Rows per page dropdown");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}





	@Test
	public void serviceCatgCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Services sr=PageFactory.initElements(driver, Services.class);
		String sheet="ServiceCategory";
		int rowcount=ExcelRead.getRowCount(path, sheet);

		sr.clickOnServiceCategoryBtn();
		logger.info("Clicked On Service category button");
		Util.sleepTime(1000);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String catgname=ExcelRead.getCellData(path, sheet, excelrow, 0);








			sr.clickOnCatgNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1000);

			if(catgname!="") {

				sr.enterServiceCatgName(catgname);
				logger.info("Entered category name  :"+catgname);
				Util.sleepTime(1000);
			}

			String savebtnstatus=sr.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				sr.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(1500);

				String flag=sr.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select id,category_name from service_category  where category_name='"+catgname+"'";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String categname=resultset.getString("category_name");
						String result=resultset.getString("id").concat("     ")+categname;
						ExcelRead.setCellData(path, sheet, excelrow, 2, result);	
					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
					Util.sleepTime(1500);

					sr.clickOnCancelBtn1();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(2000);
				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 1, "Save button is disabled");
				Util.sleepTime(1500);

				sr.clickOnCancelBtn1();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}	
		}

		sr.clickOnCancelBtn2();
		logger.info("Clicked On Cancel button");
		Util.sleepTime(1500);
	}




	@Test
	public void editServiceCatgDetails() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Services sr=PageFactory.initElements(driver, Services.class);
		String sheet="EditServiceCategory";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String catgname=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String description=ExcelRead.getCellData(path, sheet, excelrow, 1);








			sr.selectCategoryRecord(catgname);
			logger.info("Selected category record  :"+catgname);
			Util.sleepTime(1000);

			sr.clickOnCatgEditBtn();
			logger.info("Clicked On Edit button");
			Util.sleepTime(1000);

			sr.enterDescription(description);
			logger.info("Entered Description  :"+description);
			Util.sleepTime(1000);

			sr.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);

			String flag=sr.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
			Util.sleepTime(1000);
		}
	}




	@Test
	public void deleteCategory() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Services sr=PageFactory.initElements(driver, Services.class);
		String sheet="DeleteCategory";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String catgname=ExcelRead.getCellData(path, sheet, excelrow, 0);








			sr.selectCategoryRecord(catgname);
			logger.info("Selected Category record  :"+catgname);
			Util.sleepTime(1000);

			sr.clickOnCatgDeleteBtn();
			logger.info("Clicked On Delete button");
			Util.sleepTime(1500);

			sr.clickOnOkbtn();
			logger.info("Clicked On Ok button");
			Util.sleepTime(2000);

			String flag=sr.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
			Util.sleepTime(1000);
		}
	}









	@AfterClass
	public void logoutFromApplication() {

		try {

			LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
			Services sr=PageFactory.initElements(driver, Services.class);

			sr.clickOnCancelBtn2();
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

