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

public class ServicesOfferedGrid {


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
			Services service=PageFactory.initElements(driver, Services.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			service.clickOnAdministrationTab();
			logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);

			service.clickOnDServicesOffered();
			logger.info("Clicked On Service Offred");
			Util.sleepTime(1000);

			service.clickOnRowsPerPageDropdown();
			logger.info("Clicked On Rows per page dropdown");
			Util.sleepTime(1000);

			service.selectAllRowsPerPage();
			logger.info("Selected All rows per page");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}





	@Test
	public void serviceCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Services sr=PageFactory.initElements(driver, Services.class);
		String sheet="CreateServices";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String servicename=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String servicecode=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String category=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String price=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String tax=ExcelRead.getCellData(path, sheet, excelrow, 4);








			sr.clickOnNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1500);

			if(servicename!="") {

				sr.enterServiceName(servicename);
				logger.info("Entered Service name  :"+servicename);
				Util.sleepTime(300);
			}

			if(servicecode!="") {

				sr.enterServiceCode(servicecode);
				logger.info("Entered Service code  :"+servicecode);
				Util.sleepTime(300);
			}

			if(category!="") {

				sr.clickOnCatgDropdown();
				logger.info("Clicked On category dropdown");
				Util.sleepTime(1000);

				sr.selectCategory(category);
				logger.info("Selected category  :"+category);
				Util.sleepTime(1000);
			}

			if(price!="") {

				sr.enterPrice(price);
				logger.info("Entered Price  :"+price);
				Util.sleepTime(300);
			}

			if(tax!="") {

				sr.enterTax(tax);
				logger.info("Entered Tax  :"+tax);
				Util.sleepTime(300);
			}

			String savebtnstatus=sr.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				sr.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=sr.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select id,name,code,created_date,created_user from services_offered where name='"+servicename+"' ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String name=resultset.getString("name").concat("    ");
						String code=resultset.getString("code").concat("    ");
						String createddate=resultset.getString("created_date").concat("    ");
						String createduser=resultset.getString("created_user").concat("    ");
						String result=resultset.getString("id").concat("    ")+name+code+createddate+createduser;
						ExcelRead.setCellData(path, sheet, excelrow, 6, result);
					}
				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 5, flag);
					Util.sleepTime(1000);

					sr.clickOnCancelBtn2();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1500);
				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 5, "Save button is disabled");
				Util.sleepTime(1000);

				sr.clickOnCancelBtn2();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}
		}
	}



	@Test
	public void editServices() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Services sr=PageFactory.initElements(driver, Services.class);
		String sheet="EditServices";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String servicename=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String price=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String tax=ExcelRead.getCellData(path, sheet, excelrow, 2);









			sr.clickOnEditButton(servicename);
			logger.info("Clicked On Edit button  :"+servicename);
			Util.sleepTime(1000);

			sr.clearPrice();
			logger.info("Cleared Price");
			Util.sleepTime(300);

			sr.enterPrice(price);
			logger.info("Entered Price  :"+price);
			Util.sleepTime(300);

			sr.clearTax();
			logger.info("Cleared Tax");
			Util.sleepTime(300);

			sr.enterTax(tax);
			logger.info("Entered Tax  :"+tax);
			Util.sleepTime(300);

			sr.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(1500);

			String flag=sr.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
			Util.sleepTime(1500);

		}
	}




	@Test
	public void deleteServices() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Services sr=PageFactory.initElements(driver, Services.class);
		String sheet="DeleteServices";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String servicename=ExcelRead.getCellData(path, sheet, excelrow, 0);		







			sr.clickOnDeleteButton(servicename);
			logger.info("Clicked On Delete button   :"+servicename);
			Util.sleepTime(1500);

			sr.clickOnOkbtn();
			logger.info("Clicked On OK button");
			Util.sleepTime(1500);

			String flag=sr.getAlert();
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
