package com.codon.VueHMS.testcases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.codon.VueHMS.utilities.CommonUtil;

public class GetDataFromDataBase {

	
	static Connection con;
	static Statement st;
	static  ResultSet resultset ;
	static String bn,rm,url,user,pass,path,dbURL,dbUsername,dbPassword;
	
	
	public static String dataCollection(String patient,String date,String time) throws SQLException, IOException {
		
		try {

			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		 
		 dbURL=CommonUtil.getProperty("config", "dbURL");
		 dbUsername= CommonUtil.getProperty("config", "dbusername");
		 dbPassword= CommonUtil.getProperty("config", "dbpassword");

		con = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
		st=con.createStatement();
		
		
		String selectquery="select id,patient,doctor,appointment_date,time,created_date from appointments where patient in (select pat_id from patients  where patient_name='"+patient+"') and appointment_date='"+date+"' and time='"+time+"' ";
		resultset =st.executeQuery(selectquery);
		
		resultset.next();
		
		String id=resultset.getString("capa_incident_id");
		
		return id;
	}
}
		
	
	
	
	
	

