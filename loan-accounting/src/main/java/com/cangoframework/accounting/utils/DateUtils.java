package com.cangoframework.accounting.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
	public static Date getRelativeDate(Date date, int iYear, int iMonth,
			int iDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(1, iYear);
		gc.add(2, iMonth);
		gc.add(5, iDate);
		return gc.getTime();
	}

	public static String getRelativeDefaultDate(String sDate, int iYear, int iMonth,int iDate) {
		return getRelativeDate(getDate(sDate), iYear ,iMonth , iDate ,DATE_FORMAT_DEFAULT );
	}
	public static String getRelativeDefaultDate(Date date, int iYear, int iMonth,int iDate) {
		return getRelativeDate(date, iYear ,iMonth , iDate ,DATE_FORMAT_DEFAULT );
	}
	public static String getRelativeDate(Date date, int iYear, int iMonth,
			int iDate, String sFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
		return sdf.format(getRelativeDate(date, iYear, iMonth, iDate));
	}
	
	public static String getToDay(String sFormat){
		return getDate(new Date(),sFormat);
	}
	public static String getDate(Date date){
		return getDate(date , DATE_FORMAT_DEFAULT);
	}
	public static String getDate(Date date,String sFormat){
		SimpleDateFormat sf = new SimpleDateFormat(sFormat);
		return sf.format(date);
	}
	public static Date getDate(String sDate){
		return getDate(sDate , DATE_FORMAT_DEFAULT);
	}
	public static Date getDate(String sDate,String sFormat){
		SimpleDateFormat sf = new SimpleDateFormat(sFormat);
		try {
			return sf.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} return null;
	}
	
	public static int getDay(Date date){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		return gc.get(Calendar.DATE);
	}
	public static int getDay(String sDate){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(getDate(sDate , DATE_FORMAT_DEFAULT));
		return gc.get(Calendar.DATE);
	}
	
	public static int compareDate(String dateA, String dateB, String format)
	  {
	    if ((dateA == null) || (dateA.length() == 0) || (dateB == null) || (dateB.length() == 0))
	      return -1;

	    double diffDays = 0D;
	    Date dateOne = null;
	    Date dateTwo = null;
	    try {
	      dateOne = getDate(dateA, format);
	      dateTwo = getDate(dateB, format);
	      double diffMs = dateOne.getTime() - dateTwo.getTime();
	      diffDays = diffMs / 86400000.0D;
	    } catch (Exception pe) {
	      diffDays = -1.0D;
	      System.out.println("日期对比 [" + dateA + "]-[" + dateB + "] 存在非法日期字符。");
	    }

	    return new Double(diffDays).intValue();
	  }

	  public static int compareDate(String dateA, String dateB) {
	    return compareDate(dateA, dateB, DATE_FORMAT_DEFAULT);
	  }
	
	  public static boolean isMonthEnd(String sDate){
		  return isMonthEnd(sDate , DATE_FORMAT_DEFAULT);
	  }
	  public static boolean isMonthEnd(String sDate, String format){
		  String monthEnd = getMonthEnd(sDate, format);
		  return monthEnd.compareTo(sDate) == 0;
	  }
	  public static String getMonthEnd(String date, String format){
	    if (date == null) return date;
	    Date d = getDate(date,format);
	    GregorianCalendar gc = new GregorianCalendar();
	    gc.setTime(d);
	    gc.add(Calendar.MONTH, 1);
	    gc.set(Calendar.DATE, 1);
	    gc.add(Calendar.DATE, -1);
	    return getDate(gc.getTime(),format);
	  }

	  public static String getMonthEnd(String date) {
	    return getMonthEnd(date, DATE_FORMAT_DEFAULT);
	  }
	  
}
