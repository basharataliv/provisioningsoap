/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.provisioning.gateway.utils;

import java.util.*;
import java.text.*;

/**
 *
 * @author 
 */
public class DateTimeUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final long MILLIS_IN_SECOND = 1000;
    private static final long MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60;
    private static final long MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;
    private static final long MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;
    
   
    public static double howManyHoursPassed(java.util.Date from, java.util.Date to) {
        Calendar cal_From=Calendar.getInstance();
        Calendar cal_To=Calendar.getInstance();
        cal_From.setTimeInMillis(from.getTime());
        cal_To.setTimeInMillis(to.getTime());
        return howManyHoursPassed(cal_From, cal_To);
    }

   
    public static double howManyHoursPassed(Calendar from, Calendar to) {
        double diff = from.getTimeInMillis()- to.getTimeInMillis();
        return (double)(diff / MILLIS_IN_HOUR);
    }
    /**
     * counts how many days passed between two dates, counting days and not "24hs":
     * from 2008/1/1 13:00 to 2008/1/2 11:00 there is 1 day (even if it is only a 22 hour difference)
     * @param from
     * @param to
     * @return
     */
    public static int howManyDaysPassed(java.util.Date from, java.util.Date to) {        
        Calendar cal_From=Calendar.getInstance();
        Calendar cal_To=Calendar.getInstance();
        cal_From.setTimeInMillis(from.getTime());
        cal_To.setTimeInMillis(to.getTime());
        return howManyDaysPassed(cal_From, cal_To);
    }
    /**
     * counts how many days passed between two dates, counting days and not "24hs":
     * from 2008/1/1 13:00 to 2008/1/2 11:00 there is 1 day (even if it is only a 22 hour difference)
     * @param from
     * @param to
     * @return
     */
    public static int howManyDaysPassed(Calendar from, Calendar to) {
        long diff = getCanonicalDay(to).getTimeInMillis()- getCanonicalDay(from).getTimeInMillis();
        return (int)(diff / MILLIS_IN_DAY);
    }
    /**
     * Compares two calendars to determine if they are the same day
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameDay(java.util.Date from, java.util.Date to) {
        Calendar cal_From=Calendar.getInstance();
        Calendar cal_To=Calendar.getInstance();
        cal_From.setTimeInMillis(from.getTime());
        cal_To.setTimeInMillis(to.getTime());
        return isSameDay(cal_From, cal_To);
    }
    /**
     * Compares two calendars to determine if they are the same day
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) ;
    }
    /**
     * Returns the canonical day calendar for a given calendar, which is the first millisecond of
     * the day (2008/03/07 15:23:32 992ms --> 2008/03/07 0:0:0 0ms)
     * @param cal
     * @return
     */
    public static Calendar getCanonicalDay(Calendar cal) {
        Calendar ret = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0 ,0 ,0);
        ret.set(Calendar.MILLISECOND, 0);
        return ret;
    }
    /**
     * Returns the last millisecond of the given day
     * the day (2008/03/07 15:23:32 992ms --> 2008/03/07 23:59:59 999ms)
     * @param cal
     * @return
     */
    public static Calendar getLastMilli(Calendar cal) {
        Calendar ret = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        ret.set(Calendar.MILLISECOND, 999);
        return ret;
    }
    public static long getMilliSecondsDifference_Between_TimeZones(String strTimeZone_From,String strTimeZone_To){
        Calendar calDate_From= Calendar.getInstance(TimeZone.getTimeZone(strTimeZone_From));
        int intZoneOffset_From=calDate_From.get(Calendar.ZONE_OFFSET);
        int intDstOffset_From = calDate_From.get(Calendar.DST_OFFSET);
        //System.out.println("intDstOffset_From: "+intDstOffset_From+" intZoneOffset_From: "+intZoneOffset_From);
        int intOffset_From= intDstOffset_From+intZoneOffset_From;
        long intNumMinutes_From =  intOffset_From;
        //System.out.println("intNumMinutes_From "+intNumMinutes_From);
        Calendar calDate_To = Calendar.getInstance(TimeZone.getTimeZone(strTimeZone_To));
        int intDstOffset_To=calDate_To.get(Calendar.DST_OFFSET);
        int intZoneOffset_To=calDate_To.get(Calendar.ZONE_OFFSET);

        int intOffset_To = intDstOffset_To+intZoneOffset_To;            
        long intNumMinutes_To = intOffset_To;
        //System.out.println("intNumMinutes_To "+intNumMinutes_To);
        long intNumMinutes_Difference = intNumMinutes_To - intNumMinutes_From;
        //System.out.println("intNumMinutes_Difference: "+intNumMinutes_Difference+" difference between "+strTimeZone_To+" "+strTimeZone_From);
        return intNumMinutes_Difference;
    }
    public static long getSecondsDifference_Between_TimeZones(String strTimeZone_From,String strTimeZone_To){
        return getMilliSecondsDifference_Between_TimeZones(strTimeZone_From,strTimeZone_To)/1000;
    }
    public static long getMinutesDifference_Between_TimeZones(String strTimeZone_From,String strTimeZone_To){
        return getSecondsDifference_Between_TimeZones(strTimeZone_From,strTimeZone_To)/60;
    }    
    public static double getHoursDifference_Between_TimeZones(String strTimeZone_From,String strTimeZone_To){
        return getSecondsDifference_Between_TimeZones(strTimeZone_From,strTimeZone_To)/60;
    }
    public static String convTimeZone(final String DATE_TIME_FORMAT,String time, String sourceTZ, String destTZ){
        //final String DATE_TIME_FORMAT = "MM/dd/yy hh:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date specifiedTime = null;
        try{
            if (sourceTZ != null){
                sdf.setTimeZone(TimeZone.getTimeZone(sourceTZ));
                //System.out.print(sdf.getTimeZone().getDSTSavings());
                //TimeZone.
            }
            else{
                sdf.setTimeZone(TimeZone.getDefault());
            }
            // default to server's timezone
            specifiedTime = sdf.parse(time);
        }catch (Exception e1){
            try{
                e1.printStackTrace();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        // switch timezone
        if (destTZ != null){
            sdf.setTimeZone(TimeZone.getTimeZone(destTZ));
        }
        else{
            sdf.setTimeZone(TimeZone.getDefault());
        }
        return sdf.format(specifiedTime);
    }

    public static String last7Days() {
        return subToDate(0, 0, 7);
    }

    public static String[] thisWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String[] Dates = {"",""};

        Calendar today = Calendar.getInstance();
        int dow = today.get(Calendar.DAY_OF_WEEK);
        today.add(Calendar.DAY_OF_WEEK, -(dow-1));
        Dates[0] = sdf.format(today.getTime());

        Dates[1] = tomorrow();
        
        return Dates;
    }

    public static String[] previousWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String[] Dates = {"",""};

        Calendar today = Calendar.getInstance();
        int dow = today.get(Calendar.DAY_OF_WEEK);
        int wnum = today.get(Calendar.WEEK_OF_YEAR);
        int previousWeek = wnum - 1;
        today.set(Calendar.WEEK_OF_YEAR, previousWeek);
        today.add(Calendar.DAY_OF_WEEK, -(dow-1));
        Dates[0] = sdf.format(today.getTime());
        today.add(Calendar.DATE, 7);
        Dates[1] = sdf.format(today.getTime());

        return Dates;
    }

    public static String yesterday() {
        return subToDate(0, 0, 1);
    }

    public static String tomorrow() {
        return addToDate(0, 0, 1, "");
    }

    /**
     * <pre>Add days to today</pre>
     * @param numOfDays
     * @return date
     */
    public static String addDaysToDate(int numOfDays) {
        return addDaysToDate(numOfDays, "");
    }
    /**
     * <pre>Add Days to the date passed.</pre>
     * @param numOfDays
     * @param date
     * @return date
     */
    public static String addDaysToDate(int numOfDays, String date) {
        return addToDate(0, 0, numOfDays, date);
    }

    public static String Today() {
        return Today("");
    }
    public static String Today(String Date_Format) {
        SimpleDateFormat sdf;
        
        if (Date_Format.length() < 2) {
            sdf = new SimpleDateFormat(DATE_FORMAT);
        }
        else {
            sdf = new SimpleDateFormat(Date_Format);
        }
        
        long ts = (new Date()).getTime();
        Date d = new Date(ts);
        return sdf.format(d).toString();
    }
    
    public static boolean isValidDate(int year, int month, int date) {
        return validateAGivenDate(year+"-"+month+"-"+date);
    }
    public static boolean validateAGivenDate(String uDate) {
        return validateAGivenDate(uDate, "");
    }
    public static boolean validateAGivenDate(String uDate, String date_Format) {
        boolean $isValid = true;
        SimpleDateFormat sdf;
        
        if(uDate.equalsIgnoreCase("")) {
            // must set the error msg and then return
            return false;
        }
        
        if (date_Format.equalsIgnoreCase("")) {
            sdf = new SimpleDateFormat(DATE_FORMAT);
        }
        else {
            sdf = new SimpleDateFormat(date_Format);
        }
        
        Date dt1 = null;
        try {
            sdf.setLenient(false);
            dt1 = sdf.parse(uDate);
           // log.info("Date is ok = " + dt1 + "(" + uDate + ")");
        }
        catch (ParseException ex) {
          
            $isValid = false;
        }
        catch (IllegalArgumentException ex) {           
            $isValid = false;
        }
        
        return $isValid;
    }

    public static String subToDate(int Yr, int Mo, int Dt){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();

        // Set calendar to the current dateTime
        long ts = (new Date()).getTime();
        Date d = new Date(ts);
        c1.setTime(d);

        // substract 1 month
        c1.add(Calendar.DATE, -Dt);
        c1.add(Calendar.MONTH, -Mo);
        c1.add(Calendar.YEAR, -Yr);

        return sdf.format(c1.getTime());
    }

    /**
     * <pre>date has to be in yyyy-MM-dd format</pre>
     * @param Yr
     * @param Mo
     * @param Dt
     * @param date
     * @return
     */
    public static String addToDate(int Yr, int Mo, int Dt, String date){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();

        if (date.length() == 10) {
            // Set calender to the date passed
            if (setCalender(c1, date)) {

            }
            else {
                return "Invalid date, Unable to set the calender";
            }
        } else {
            // Set calendar to the current dateTime
            long ts = (new Date()).getTime();
            Date d = new Date(ts);
            c1.setTime(d);
        }

        // substract 1 month
        c1.add(Calendar.DATE, Dt);
        c1.add(Calendar.MONTH, Mo);
        c1.add(Calendar.YEAR, Yr);

        return sdf.format(c1.getTime());
    }

    public static boolean setCalender(Calendar c, String date) {
        String[] date_Parts;
        try {
            date_Parts = date.split("-");
            //check if the date parts of correct length
            if(date_Parts[0].length() == 4 && date_Parts[1].length() == 2 && date_Parts[2].length() == 2) {
                c.set(Integer.parseInt(date_Parts[0]), (Integer.parseInt(date_Parts[1])-1), Integer.parseInt(date_Parts[2]));
                return true;
            }
            return false;
        }
        catch(Exception ex) {
            return false;
        }
    }
    
    public static String timeStamp() {
    	String  TimeStamp = DateTimeUtils.Today("ddMMyyyyHHmm");
   	 Long uniqueID ;
         if (TimeStamp == null || TimeStamp.isEmpty()) {
             uniqueID = Long.parseLong(DateTimeUtils.Today("ddMMyyyyHHmm") + "");
         } else {
             uniqueID = Long.parseLong(TimeStamp);
         }
         return uniqueID+"";
    }
    
    public static String generateOTP() {
		int length = 4;
		String numbers = "123456789";
		Random random = new Random();
		String otp = "";
       
       for (int i = 0; i < length; i++) {
			otp += numbers.charAt(random.nextInt(numbers.length()));
		}
       if(otp.length()!=4) {
    	   generateOTP() ;
       }
		return otp;
	}

}
