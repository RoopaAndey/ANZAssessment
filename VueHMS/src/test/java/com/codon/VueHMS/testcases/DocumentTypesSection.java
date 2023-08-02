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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.codon.VueHMS.pages.DocumentTypes;
import com.codon.VueHMS.pages.LoginPage;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class DocumentTypesSection {


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
			//logger.info("Launched Firefox browser");
			Util.sleepTime(1000);

			BrowsePage.openUrl(url);
			//logger.info("Entered Url :" +url);
			Util.sleepTime(2000);	

			LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
			DocumentTypes doc=PageFactory.initElements(driver, DocumentTypes.class);

			lp.enterUsernameOrEmail(user);
			//logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			//logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			//logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			doc.clickOnAdministrationTab();
			//logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);

			doc.clickOnDocumentTypes();
			//logger.info("Clicked On Document Types section");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}



	@Test
	public void documentTypesCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		DocumentTypes doc=PageFactory.initElements(driver, DocumentTypes.class);
		String sheet="";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String documenttype=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String fields=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String addfields=ExcelRead.getCellData(path, sheet, excelrow, 2);










			doc.clickOnNewBtn();
			//logger.info("Clicked On New Btn");
			Util.sleepTime(1000);

			if(documenttype!="") {

				doc.enterDocumentType(documenttype);
				//logger.info("Entered Document Type  : "+documenttype);
				Util.sleepTime(1000);
			}

			if(addfields!="") {

				String[] fieldsdata=fields.split(",");

				for(String data:fieldsdata) {

					String[] docfields=data.split(":");
					String fieldname=docfields[0];
					String fielddesc=docfields[1];

					
					doc.clickOnAddBtn();
					logger.info("Clicked On Add Btn");
					Util.sleepTime(1000);

					if(fieldname!="") {

						doc.enterField(fieldname);
						//logger.info("Entered Field name  :"+fieldname);
						Util.sleepTime(1000);
					}

					if(fielddesc!="") {

						doc.enterFieldDescription(fielddesc);
						//logger.info("Entered Field Description  : "+fielddesc);
						Util.sleepTime(1000);

					}

					String savebtnstatus=doc.getSaveBtn1Status();
					if(savebtnstatus.contains("disabled")) {

						doc.clickOnFieldsaveBtn();
						Util.sleepTime(1500);

					}else {

						doc.clickOnFieldsCancelBtn();
						Util.sleepTime(1500);
					}
				}
			}

			String savebtnstatus=doc.getSaveBtnStatus();
			if(savebtnstatus.contains("disabled")) {

				doc.clickOnSaveBtn();
				//logger.info("Clicked On Save Btn");
				Util.sleepTime(2000);

				String flag=doc.getAlert();
				if(flag.contains("Successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select id,name,createdby from doc_type ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {


						String name=resultset.getString("name").concat("      ");
						String createdby=resultset.getString("createdby").concat("       ");
						String result=resultset.getString("id").concat("      ")+name+createdby;
						ExcelRead.setCellData(path, sheet, excelrow, 5, result);
					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
					Util.sleepTime(1000);

					doc.clickOnCancelBtn();
					//logger.info("Clicked On Cancel Btn");
					Util.sleepTime(1000);
				}	
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 4, "Save button is disabled");
				Util.sleepTime(1000);

				doc.clickOnCancelBtn();
				//logger.info("Clicked On Cancel Btn");
				Util.sleepTime(1000);
			}
		}
	}




	@Test
	public void editDocumentTypes() throws IOException {
		
		this.path=CommonUtil.getProperty("configg", "path");
		DocumentTypes doc=PageFactory.initElements(driver, DocumentTypes.class);
		String sheet="";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			String documenttype=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String field=ExcelRead.getCellData(path, sheet, excelrow, 1);
			
			
			
			
			
			
			
			
			
			doc.clickOnEditBtn(documenttype);
			Util.sleepTime(1500);
			
			doc.clickOnFieldEditBtn(field);
			Util.sleepTime(1500);
			
			doc.clearField();
			Util.sleepTime(300);
			
			doc.enterField(field);
			Util.sleepTime(500);
			
			doc.clickOnFieldsaveBtn();
			Util.sleepTime(1500);
			
			doc.clickOnSaveBtn();
			Util.sleepTime(2000);
			
			String flag=doc.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
			Util.sleepTime(1500);

		}
	}















































}
