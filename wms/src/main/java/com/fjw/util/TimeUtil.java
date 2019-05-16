package com.fjw.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public static Date getBeginTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		return calendar.getTime();
	}
	
	public static Date getEndTime(Date date) {
		//结束时间应该是一天的23时59分59秒
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
				0, 0, 0);
		calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}
}
