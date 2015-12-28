package com.fa7.estagio3.podcastmanager.util;

import android.annotation.SuppressLint;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public static String stringTimeToIntegerSeconds(String time){		
		Time t = Time.valueOf(time);
        return String.valueOf(t.getTime());		
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String formatDate(long data, String formatDate){
		Date date = new Date(data);
	    Format format = new SimpleDateFormat(formatDate);
	    return format.format(date);
	}

}
