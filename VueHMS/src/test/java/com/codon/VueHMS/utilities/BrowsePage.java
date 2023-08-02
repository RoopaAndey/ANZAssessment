package com.codon.VueHMS.utilities;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowsePage {

	
	
	
      static WebDriver driver;
	
	public static WebDriver startBrowser(String browsername)  {

		if (browsername.equalsIgnoreCase("Firefox")) {
			
			//System.setProperty("webdriver.gecko.driver", "C:\\Users\\rchn\\Desktop\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver","C:\\geckodriver-v0.31.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browsername.equalsIgnoreCase("Chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\rchn\\Documents\\Downloads\\chromedriver.exe");
			driver = new ChromeDriver();
			
		} else if (browsername.equalsIgnoreCase("IE")) {
			
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
	
	
	public static void openUrl(String url) {
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	
	}
	
}
