package com.sc.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前时间 
 */
public class GetDate_Util {
	public static String getYM() {
		Date date = new Date(); 
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(date);
	}
	public static String getDHSMS() {
		Date date = new Date(); 
		DateFormat df = new SimpleDateFormat("ddHHmmsss");
		return df.format(date);
	}
}
