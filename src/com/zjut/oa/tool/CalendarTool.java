package com.zjut.oa.tool;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 * 
 * @author qingtian
 * 
 *         2011-5-11 上午11:22:58
 */
public class CalendarTool {
	private static final Calendar today = Calendar.getInstance();

	public static int getCurrentDate() {
		return today.get(Calendar.DATE);
	}

	public static int getCurrentMonth() {
		return today.get(Calendar.MONTH);
	}

	public static int getCurrentYear() {
		return today.get(Calendar.YEAR);
	}

	public static Timestamp now(){
		return new Timestamp(new Date().getTime());
	}
	public static String customDatetime(Timestamp timestamp){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}
}
