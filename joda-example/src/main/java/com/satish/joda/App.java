package com.satish.joda;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        DateTime start = new DateTime(new Date());
        TimeUnit.SECONDS.sleep(10);
        DateTime end = new DateTime(new Date());
        
        Seconds timeElapsed = Seconds.secondsBetween(start, end);
        
        System.out.println("Time elapsed:" + timeElapsed.getSeconds());
    }
}
