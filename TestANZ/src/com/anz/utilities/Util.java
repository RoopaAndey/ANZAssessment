package com.anz.utilities;

public class Util {
	
	
	public static void sleepTime(long l) {
		try {
			Thread.sleep(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
