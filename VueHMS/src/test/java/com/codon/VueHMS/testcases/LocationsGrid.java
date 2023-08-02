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
import com.codon.VueHMS.pages.Locations;
import com.codon.VueHMS.pages.LoginPage;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class LocationsGrid {


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
			Locations loc=PageFactory.initElements(driver, Locations.class);

			lp.enterUsernameOrEmail(user);
			logger.info("Entered Username  :"+user);
			Util.sleepTime(1000);

			lp.enterPassword(pass);
			logger.info("Entered Password  :"+pass);
			Util.sleepTime(1000);

			lp.clickOnLoginBtn();
			logger.info("Clicked On Login button");
			Util.sleepTime(2000);

			loc.clickOnAdministrationTab();
			logger.info("Clicked On Administration tab");
			Util.sleepTime(1000);

			loc.clickOnLocations();
			logger.info("Clicked On Locations ");
			Util.sleepTime(1000);

			loc.clickOnRowsPerPageDropdown();
			logger.info("Clicked On Rows per page dropdown");
			Util.sleepTime(1000);

			loc.selecAlltRowsPerPage();
			logger.info("Selected All rows per page");
			Util.sleepTime(1000);

		} catch (ClassNotFoundException |SQLException e ) {

			e.printStackTrace();
		}
	}




	@Test
	public void locationCreation() throws IOException, SQLException, AWTException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="CreateLocation";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String name=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String loctype=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String city=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String state=ExcelRead.getCellData(path, sheet, excelrow, 3);
			String country=ExcelRead.getCellData(path, sheet, excelrow, 4);
			String phoneno=ExcelRead.getCellData(path, sheet, excelrow, 5);
			String pincode=ExcelRead.getCellData(path, sheet, excelrow, 6);
			String address=ExcelRead.getCellData(path, sheet, excelrow, 7);













			loc.clickOnNewBtn();
			logger.info("Clicked On New button");
			Util.sleepTime(1000);

			if(name!="") {

				loc.enterName(name);
				logger.info("Entered name :"+name);
				Util.sleepTime(500);
			}

			if(loctype!="") {

				loc.clickOnLocationTypeDropdown();
				logger.info("Clicked On Location Type Dropdown");
				Util.sleepTime(500);

				loc.selectLocationType(loctype);
				logger.info("Selected Location type :"+loctype);
				Util.sleepTime(500);
			}

			if(city!="") {

				loc.enterCity(city);
				logger.info("Entered city :"+city);
				Util.sleepTime(500);
			}

			if(state!="") {

				loc.enterState(state);
				logger.info("Entered state :"+state);
				Util.sleepTime(500);
			}

			if(country!="") {

				loc.enterCountry(country);
				logger.info("Entered country :"+country);
				Util.sleepTime(500);
			}

			if(phoneno!="") {

				loc.enterPhoneno(phoneno);
				logger.info("Entered phoneno :"+phoneno);
				Util.sleepTime(500);
			}

			if(pincode!="") {

				loc.enterPincode(pincode);
				logger.info("Entered Pincode :"+pincode);
				Util.sleepTime(500);
			}

			if(address!="") {

				loc.enterAddress(address);
				logger.info("Entered Address :"+address);
				Util.sleepTime(500);
			}

			String savebtnstatus=loc.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				loc.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(3000);

				String flag=loc.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 8, flag);
					Util.sleepTime(1500);

					st=con.createStatement();
					String selectquery="select id,location_name,location_type,city,state,createddate from locations where location_name='"+name+"' and city='"+city+"'  ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String locname=resultset.getString("location_name").concat("    ");
						String type=resultset.getString("location_type").concat("      ");
						String City=resultset.getString("city").concat("    ");
						String State=resultset.getString("state").concat("     ");
						String createddate=resultset.getString("createddate").concat("     ");
						String result=resultset.getString("id").concat("     ")+locname+type+City+State+createddate;
						ExcelRead.setCellData(path, sheet, excelrow, 9, result);
					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 8, flag);
					Util.sleepTime(1500);

					loc.clickOnCancelBtn();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1000);

				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 8, "Save button is disabled");
				Util.sleepTime(1500);

				loc.clickOnCancelBtn();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1000);
			}
		}	
	}




	@Test
	public void editlocation() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="EditLocation";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {



			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String phoneno=ExcelRead.getCellData(path, sheet, excelrow, 1);










			loc.clickOnEditButton(locationrecord);
			logger.info("Clicked On Edit button "+locationrecord);
			Util.sleepTime(1000);

			loc.clearPhoneno();
			logger.info("Cleared Phoneno ");
			Util.sleepTime(1000);

			loc.enterPhoneno(phoneno);
			logger.info("Entered Phoneno  :"+phoneno);
			Util.sleepTime(500);

			loc.clickOnSaveBtn();
			logger.info("Clicked On Save btn");
			Util.sleepTime(2000);

			String flag=loc.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
			Util.sleepTime(1500);
		}
	}




	@Test
	public void deleteLocation() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="DeleteLocation";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);









			loc.clickOnDeleteButton(locationrecord);
			logger.info("Clicked On Delete button "+locationrecord);
			Util.sleepTime(1500);

			loc.clickOnOKBtn();
			logger.info("Clicked On OK btn");
			Util.sleepTime(2000);

			String flag=loc.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 1, flag);
			Util.sleepTime(1500);
		}
	}




	@Test
	public void roomCreation() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="RoomDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=2;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String name=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String locationtype=ExcelRead.getCellData(path, sheet, excelrow, 2);









			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);

			loc.clickOnAddBtn();
			logger.info("Clicked On Add button");
			Util.sleepTime(1000);

			if(name!="") {

				loc.enterRoomName(name);
				logger.info("Entered Room Name  :"+name);
				Util.sleepTime(500);
			}

			if(locationtype!="") {

				loc.clickOnLocationTypeDropdown();
				logger.info("Clicked On Location Type Dropdown");
				Util.sleepTime(1000);

				loc.selectLocationType(locationtype);
				logger.info("Selected Location Type :"+locationtype);
				Util.sleepTime(1000);
			}

			String savebtnstatus=loc.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				loc.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=loc.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select location_name,id,location_type,parent_id,created_date  from locations where parent_id in (select id from locations where location_name='"+locationrecord+"') and location_type='42' ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String locname=resultset.getString("location_name").concat("  ");
						String loctype=resultset.getString("location_type").concat("      ");
						String parentid=resultset.getString("parent_id").concat("     ");
						String createddate=resultset.getString("created_date").concat("     ");
						String result=resultset.getString("id").concat("     ")+locname+loctype+parentid+createddate;
						ExcelRead.setCellData(path, sheet, excelrow, 4, result);

					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
					Util.sleepTime(1000);

					loc.clickOnCancelBtn();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1000);
				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 3, "Save button is disabled");
				Util.sleepTime(1000);

				loc.clickOnCancelBtn();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1000);
			}

			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
		}	
	}




	@Test
	public void editRoom() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="EditRoomDetails";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String roomrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String description=ExcelRead.getCellData(path, sheet, excelrow, 2);








			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);

			loc.clickOnRoomEditButton(roomrecord);
			logger.info("Clicked On Edit button  :"+roomrecord);
			Util.sleepTime(1000);

			loc.enterDescription(description);
			logger.info("Entered Description  :"+description);
			Util.sleepTime(500);

			loc.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);

			String flag=loc.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
			Util.sleepTime(1000);

			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
		}	
	}




	@Test
	public void assignCost() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="AssignCost";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String roomrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String servicecode=ExcelRead.getCellData(path, sheet, excelrow, 2);










			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);

			loc.clickOnAssignCostButton(roomrecord);
			logger.info("Clicked On Assign Cost button  :"+roomrecord);
			Util.sleepTime(1000);

			if(servicecode!="") {

				loc.clickOnServiceCodeDropdown();
				logger.info("Clicked On Service Code Dropdown ");
				Util.sleepTime(1000);

				loc.selectServiceCode(servicecode);
				logger.info("Selected Service code  :"+servicecode);
				Util.sleepTime(1000);
			}

			String savebtnstatus=loc.getAlert();
			if(!savebtnstatus.contains("disabled")) {

				loc.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=loc.getAlert();
				ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
				Util.sleepTime(1500);

				loc.expandLocation(locationrecord);
				logger.info("Expanded Location  :"+locationrecord);
				Util.sleepTime(1000);

			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 3, "Save button is disabled");
				Util.sleepTime(1500);
				
				loc.clickOnCancelBtn();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1500);
			}	
		}
	}




	@Test
	public void deleteRoom() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="DeleteRoom";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String roomrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);









			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);

			loc.clickOnRoomDeleteButton(roomrecord);
			logger.info("Clicked On Delete button  :"+roomrecord);
			Util.sleepTime(1000);

			loc.clickOnOKBtn();
			logger.info("Clicked On Ok button");
			Util.sleepTime(2000);

			String flag=loc.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
			Util.sleepTime(1500);

			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
		}
	}



	@Test
	public void createBeds() throws IOException, SQLException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="CreateBeds";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String roomrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String name=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String locationtype=ExcelRead.getCellData(path, sheet, excelrow, 3);









			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
			
			loc.clickOnRoom(roomrecord);
			logger.info("Clicked On Room  :"+roomrecord);
			Util.sleepTime(1000);
			
			driver.switchTo().alert().accept();
			Util.sleepTime(500);

			loc.clickOnAddBtn();
			logger.info("Clicked On Add button");
			Util.sleepTime(1000);

			if(name!="") {

				loc.enterBedName(name);
				logger.info("Entered Bed Name  :"+name);
				Util.sleepTime(500);
			}

			if(locationtype!="") {

				loc.clickOnLocationTypeDropdown();
				logger.info("Clicked On Location Type Dropdown");
				Util.sleepTime(1000);

				loc.selectLocationType(locationtype);
				logger.info("Selected Location Type :"+locationtype);
				Util.sleepTime(1000);
			}

			String savebtnstatus=loc.getSaveBtnStatus();
			if(!savebtnstatus.contains("disabled")) {

				loc.clickOnSaveBtn();
				logger.info("Clicked On Save button");
				Util.sleepTime(2000);

				String flag=loc.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
					Util.sleepTime(1000);

					st=con.createStatement();
					String selectquery="select id,location_name,location_type,parent_id,created_date from locations where parent_id in (select id from locations where location_name='"+roomrecord+"' and parent_id in (select id from locations where location_name='"+locationrecord+"')) ";
					resultset=st.executeQuery(selectquery);
					while(resultset.next()) {

						String locname=resultset.getString("location_name").concat("  ");
						String loctype=resultset.getString("location_type").concat("      ");
						String parentid=resultset.getString("parent_id").concat("     ");
						String createddate=resultset.getString("created_date").concat("     ");
						String result=resultset.getString("id").concat("     ")+locname+loctype+parentid+createddate;
						ExcelRead.setCellData(path, sheet, excelrow, 5, result);

					}

				}else {

					ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
					Util.sleepTime(1000);

					loc.clickOnCancelBtn();
					logger.info("Clicked On Cancel button");
					Util.sleepTime(1000);
				}
			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 4, "Save button is disabled");
				Util.sleepTime(1000);

				loc.clickOnCancelBtn();
				logger.info("Clicked On Cancel button");
				Util.sleepTime(1000);
			}

			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
		}
	}
	
	


	@Test
	public void editBed() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="EditBeds";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String roomrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String bedrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);
			String description=ExcelRead.getCellData(path, sheet, excelrow, 3);








			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);

			loc.clickOnRoom(roomrecord);
			logger.info("Clicked On Room  :"+roomrecord);
			Util.sleepTime(1000);
			
			driver.switchTo().alert().accept();
			Util.sleepTime(500);
			
			loc.clickOnBedEditButton(bedrecord);
			logger.info("Clicked On Edit button  :"+bedrecord);
			Util.sleepTime(1000);

			loc.enterDescription(description);
			logger.info("Entered Description  :"+description);
			Util.sleepTime(500);

			loc.clickOnSaveBtn();
			logger.info("Clicked On Save button");
			Util.sleepTime(2000);

			String flag=loc.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 4, flag);
			Util.sleepTime(1000);

			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
		}	
	}


	

	@Test
	public void deleteBed() throws IOException {

		this.path=CommonUtil.getProperty("config", "path");
		Locations loc=PageFactory.initElements(driver, Locations.class);
		String sheet="DeleteBeds";
		int rowcount=ExcelRead.getRowCount(path, sheet);


		for(int excelrow=1;excelrow<=rowcount;excelrow++) {


			String locationrecord=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String roomrecord=ExcelRead.getCellData(path, sheet, excelrow, 1);
			String bedrecord=ExcelRead.getCellData(path, sheet, excelrow, 2);









			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
			
			loc.clickOnRoom(roomrecord);
			logger.info("Clicked On Room hyper link  :"+roomrecord);
			Util.sleepTime(1000);
			
			driver.switchTo().alert().accept();
			Util.sleepTime(500);

			loc.clickOnBedDeleteButton(bedrecord);
			logger.info("Clicked On Delete button  :"+bedrecord);
			Util.sleepTime(1000);

			loc.clickOnOKBtn();
			logger.info("Clicked On Ok button");
			Util.sleepTime(2000);

			String flag=loc.getAlert();
			ExcelRead.setCellData(path, sheet, excelrow, 3, flag);
			Util.sleepTime(1500);

			loc.expandLocation(locationrecord);
			logger.info("Expanded Location  :"+locationrecord);
			Util.sleepTime(1000);
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
