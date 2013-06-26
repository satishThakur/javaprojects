package com.satish.joda;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class TimeUtilTest extends TestCase {
	
	
	public void testElapsedMinutes(){
		
		Calendar calender = Calendar.getInstance();
		calender.set(2013, 1, 1, 1, 1);
		Date startDate = calender.getTime();
		System.out.println(startDate);
		calender.set(2013, 1, 1, 1, 11);
		Date endDate = calender.getTime();
		System.out.println(endDate);
		
		//should give absolute value in both directions
		//we are testing for elapsed time.. in any direction not the diff..
		assertEquals(10, TimeUtil.getElapsedMinutes(startDate, endDate));
		assertEquals(10, TimeUtil.getElapsedMinutes(endDate, startDate));
		
		calender.set(2013, 1, 1, 2, 10);
		Date anotherDate = calender.getTime();
		
		assertEquals(69, TimeUtil.getElapsedMinutes(startDate, anotherDate));
		assertEquals(69, TimeUtil.getElapsedMinutes(anotherDate, startDate));
		
		assertEquals(59, TimeUtil.getElapsedMinutes(endDate, anotherDate));
		assertEquals(59, TimeUtil.getElapsedMinutes(anotherDate, endDate));		
		
		
		//same date..
		assertEquals(0, TimeUtil.getElapsedMinutes(startDate, startDate));
		assertEquals(0, TimeUtil.getElapsedMinutes(endDate, endDate));
		assertEquals(0, TimeUtil.getElapsedMinutes(anotherDate, anotherDate));
		
	}
	
	public void testElapsedSeconds(){
		Calendar calender = Calendar.getInstance();
		calender.set(2013, 1, 1, 1, 1, 1);
		Date startDate = calender.getTime();
		System.out.println(startDate);
		calender.set(2013, 1, 1, 1, 1, 11);
		Date endDate = calender.getTime();
		System.out.println(endDate);
		
		//should give absolute value in both directions
		//we are testing for elapsed time.. in any direction not the diff..
		assertEquals(10, TimeUtil.getElapsedSeconds(startDate, endDate));
		assertEquals(10, TimeUtil.getElapsedSeconds(endDate, startDate));
		
		calender.set(2013, 1, 1, 1, 2,10);
		Date anotherDate = calender.getTime();
		
		assertEquals(69, TimeUtil.getElapsedSeconds(startDate, anotherDate));
		assertEquals(69, TimeUtil.getElapsedSeconds(anotherDate, startDate));
		
		assertEquals(59, TimeUtil.getElapsedSeconds(endDate, anotherDate));
		assertEquals(59, TimeUtil.getElapsedSeconds(anotherDate, endDate));		
		
		
		//same date..
		assertEquals(0, TimeUtil.getElapsedSeconds(startDate, startDate));
		assertEquals(0, TimeUtil.getElapsedSeconds(endDate, endDate));
		assertEquals(0, TimeUtil.getElapsedSeconds(anotherDate, anotherDate));
	}
	
	public void testElapsedHours(){
		Calendar calender = Calendar.getInstance();
		calender.set(2013, 1, 1, 1, 1, 1);
		Date startDate = calender.getTime();
		System.out.println(startDate);
		calender.set(2013, 1, 1, 11, 1, 11);
		Date endDate = calender.getTime();
		System.out.println(endDate);
		
		//should give absolute value in both directions
		//we are testing for elapsed time.. in any direction not the diff..
		assertEquals(10, TimeUtil.getElapsedHours(startDate, endDate));
		assertEquals(10, TimeUtil.getElapsedHours(endDate, startDate));
		
		calender.set(2013, 1, 2, 10, 2,10);
		Date anotherDate = calender.getTime();
		
		assertEquals(33, TimeUtil.getElapsedHours(startDate, anotherDate));
		assertEquals(33, TimeUtil.getElapsedHours(anotherDate, startDate));
		
		assertEquals(23, TimeUtil.getElapsedHours(endDate, anotherDate));
		assertEquals(23, TimeUtil.getElapsedHours(anotherDate, endDate));		
		
		
		//same date..
		assertEquals(0, TimeUtil.getElapsedHours(startDate, startDate));
		assertEquals(0, TimeUtil.getElapsedHours(endDate, endDate));
		assertEquals(0, TimeUtil.getElapsedHours(anotherDate, anotherDate));
	}

}
