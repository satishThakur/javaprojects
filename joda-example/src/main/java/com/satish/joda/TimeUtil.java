package com.satish.joda;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class TimeUtil {

	public static int getElapsedMinutes(Date date1, Date date2){
		if(date1 == null || date2 == null){

			return 0;
		}
		DateTime firstDate = new DateTime(date1);
		DateTime secondDate = new DateTime(date2);
		int elapsedMin =  Minutes.minutesBetween(firstDate, secondDate).getMinutes();
		return Math.abs(elapsedMin);
	}

	public static int getElapsedSeconds(Date date1, Date date2){
		if(date1 == null || date2 == null){

			return 0;
		}
		DateTime firstDate = new DateTime(date1);
		DateTime secondDate = new DateTime(date2);
		int elapsedSec =  Seconds.secondsBetween(firstDate, secondDate).getSeconds();
		return Math.abs(elapsedSec);
	}

	public static int getElapsedHours(Date date1, Date date2){
		if(date1 == null || date2 == null){

			return 0;
		}
		DateTime firstDate = new DateTime(date1);
		DateTime secondDate = new DateTime(date2);
		int elapsedHrs = Hours.hoursBetween(firstDate, secondDate).getHours();
		return Math.abs(elapsedHrs);
	}


}
