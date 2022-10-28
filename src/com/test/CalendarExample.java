package com.test;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CalendarExample {
    public static void main(String[] args) {

        // Current date
        Date now = new Date();
        System.out.println(now);
        // example formatting - for more, refer to Java API for SimpleDateFormat class
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        System.out.println(dateFormatter.format(now));

        // Using Calendar class
        Calendar cal = Calendar.getInstance();
        // get Date from calendar
        Date dateNow = cal.getTime();
        System.out.println(dateFormatter.format(dateNow));
        // extract individual fields from calendar
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);      // NOTE!!! : Month from 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        System.out.printf("Now is %4d/%02d/%02d %02d:%02d:%02d\n",  // Pad with zero
                year, month+1, day, hour, minute, second);
        System.out.println("Now is " + year + "/" + (month + 1) + "/" + day);

        // Manipulating Dates
        Calendar aDate, calTemp;
        aDate = new GregorianCalendar(2013, 3, 5, 13, 30);
        // // NOTE!!! : Month from 0 to 11 => 3 is April
        calTemp = (Calendar) aDate.clone();

        // add time
        calTemp.add(Calendar.MINUTE, 30 );
        System.out.println("30 minutes later, it will be: " + calTemp.getTime());

        // compare calendar
        if (calTemp.after(aDate)) {
            System.out.println(calTemp.getTime() + " is after " + aDate.getTime());
        }

    }
}
