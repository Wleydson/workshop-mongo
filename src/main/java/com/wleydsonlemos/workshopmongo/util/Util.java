package com.wleydsonlemos.workshopmongo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;

public class Util {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	

	public static Date plusDay(Date date, Integer qtdDay ) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE, qtdDay);
		
		return calendar.getTime();
	}
}
