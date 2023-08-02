package com.codon.VueHMS.testcases;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.codon.VueHMS.pages.LoginPage;
import com.codon.VueHMS.utilities.BrowsePage;
import com.codon.VueHMS.utilities.CommonUtil;
import com.codon.VueHMS.utilities.ExcelRead;
import com.codon.VueHMS.utilities.Util;

public class LoginTest {

	
	WebDriver driver;
	String bn,url,un,pass,path;
	Logger logger=Logger.getLogger("VueHMS");
	
	
	@BeforeClass
	public void loginIntoApplication() {
		
		PropertyConfigurator.configure("Log4j.properties");
		this.bn=CommonUtil.getProperty("config", "browser");
		this.url=CommonUtil.getProperty("config", "url");
		
		driver=BrowsePage.startBrowser(bn);
		logger.info("Launched Firefox browser");
		Util.sleepTime(1000);
		
		BrowsePage.openUrl(url);
		logger.info("Entered Url :" +url);
		Util.sleepTime(1000);	
	}
	
	
	
	@Test
	public void loginTest() throws IOException {
		
		this.path=CommonUtil.getProperty("config", "path");
		LoginPage lp=PageFactory.initElements(driver, LoginPage.class);
		String sheet="LoginTest";
		String expectedresult="http://localhost:8080/VueHMS/#/dashboardmodule";
		int rowcount=ExcelRead.getRowCount(path, sheet);
		
		
		
		for(int excelrow=1;excelrow<=rowcount;excelrow++) {
			
			
			String username=ExcelRead.getCellData(path, sheet, excelrow, 0);
			String password=ExcelRead.getCellData(path, sheet, excelrow, 1);
			
			
			
			
			
			
			lp.enterUsernameOrEmail(username);
			logger.info("Entered Username or Email  : "+username);
			Util.sleepTime(1000);
			
			lp.enterPassword(password);
			logger.info("Entered Password  : "+password);
			Util.sleepTime(1000);
			
			String value=lp.getLoginBtnStatus();
			
			if(!value.contains("disabled")) {
			
				lp.clickOnLoginBtn();
				logger.info("Clicked On Login Button");
				Util.sleepTime(2000);

				String flag=lp.getAlert();
				if(flag.contains("successfully")) {

					ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
					Util.sleepTime(1000);

					String actualresult=driver.getCurrentUrl();

					if(expectedresult.equals(actualresult)) {

						ExcelRead.setCellData(path, sheet, excelrow, 3, actualresult);
						Util.sleepTime(2000);
					}
					
					lp.clickOnLogoutBtn();
					logger.info("Clicked On Logout button");
					Util.sleepTime(1500);

				}else {
					
					ExcelRead.setCellData(path, sheet, excelrow, 2, flag);
					Util.sleepTime(1000);

					lp.clickOnResetBtn();
					logger.info("Clicked On reset button");
					Util.sleepTime(1000);
				}

			}else {

				ExcelRead.setCellData(path, sheet, excelrow, 2, "Login button is disabled");
				Util.sleepTime(1000);
				
				lp.clickOnResetBtn();
				logger.info("Clicked On reset button");
				Util.sleepTime(1000);

			}	
		}
	}

	
	
	
	
	
	@AfterClass
	public void logoutFromApplication() {
		
		driver.close();
		logger.info("Firefox browser is closed");
		Util.sleepTime(1000);
	}	
}
