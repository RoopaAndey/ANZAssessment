package com.anz.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class CommonUtil {



	public static String getProperty(String filename,String key) {

		try { 

			FileInputStream fis=new FileInputStream("C:\\Users\\pc\\eclipse-workspace\\testANZ\\"+filename+".properties");
			Properties pro=new Properties();
			pro.load(fis);

			return pro.getProperty(key);	

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}	

}
