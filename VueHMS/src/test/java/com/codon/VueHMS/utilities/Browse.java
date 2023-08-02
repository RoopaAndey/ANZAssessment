package com.codon.VueHMS.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browse {

	static WebDriver driver;
	
	public static WebDriver startBrowser(String browsername) {
		
		if(browsername.equalsIgnoreCase("Firefox")) {
			
			driver= new FirefoxDriver();
			
		}		
		return driver;
				
	}
	
	
	}
