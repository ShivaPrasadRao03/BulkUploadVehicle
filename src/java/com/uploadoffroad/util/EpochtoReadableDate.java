/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Glodeveloper
 */
public class EpochtoReadableDate {

    private static final SimpleDateFormat monthDayYearformatter = new SimpleDateFormat(
            "MMMMM dd, yyyy");

    public static String convertTime(long epoch) {

        String date = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(epoch * 1000));
        return date;
    }

    public static String convertTimeWithTime(long epoch) {

        String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(epoch * 1000));
        return date;
    }

    long epoch = 0;
    String date = "";

    public long currentEpoch() {
        long epoch = System.currentTimeMillis() / 1000;
        System.out.println(epoch + "=CurrentDateEpoch");

        return epoch;
    }

    public static String convertDate(long epoch) {

        String date = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date(epoch * 1000));
        //System.out.println("Date"+date);
        return date;
    }

    public static String newconvertDate(long epoch) {

        String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(epoch * 1000));
        //System.out.println("Date"+date);
        return date;
    }

    public static String convertDateMonth(long epoch) {

        String date = new java.text.SimpleDateFormat("dd-MMMM-yyyy").format(new java.util.Date(epoch * 1000));
        //System.out.println("Date"+date);
        return date;
    }

    public long datetoEpoch(String date) throws ParseException {
        // System.out.println("date"+date);
        //String str = "12/12/2012 5:35 PM"; //Your String containing a date
        //For time  

        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy"); // The mask

        // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
        Date date1 = dF.parse(date); // parsing the String into a Date using the mask
        //Date.getTime() method gives you the Long with milliseconds since Epoch.
        long datetoepoch = date1.getTime() / 1000;
        // System.out.println("Epoch representation of this date is: " + datetoepoch);
        return datetoepoch;
    }

    public long datetoEpochWithoutTime(String date) throws ParseException {
        // System.out.println("date"+date);
        //String str = "12/12/2012 5:35 PM"; //Your String containing a date
        //For time  
        // DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // The mask

        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy");

        // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
        Date date1 = dF.parse(date); // parsing the String into a Date using the mask
        //Date.getTime() method gives you the Long with milliseconds since Epoch.
        long datetoepoch = date1.getTime() / 1000;
        // System.out.println("Epoch representation of this date is: " + datetoepoch);
        return datetoepoch;
    }

    public long datetoEpochWithTime(String date) throws ParseException {
        // System.out.println("date"+date);
        //String str = "12/12/2012 5:35 PM"; //Your String containing a date
        //For time  
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // The mask

        // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
        Date date1 = dF.parse(date); // parsing the String into a Date using the mask
        //Date.getTime() method gives you the Long with milliseconds since Epoch.
        long datetoepoch = date1.getTime() / 1000;
        // System.out.println("Epoch representation of this date is: " + datetoepoch);
        return datetoepoch;
    }

    public long datetoEpochWithTimeSecs(String date) throws ParseException {
        System.out.println("date" + date);
        //String str = "12/12/2012 5:35 PM"; //Your String containing a date
        //For time  
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // The mask

        // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
        Date date1 = dF.parse(date); // parsing the String into a Date using the mask
        //Date.getTime() method gives you the Long with milliseconds since Epoch.
        long datetoepoch = date1.getTime() / 1000;
        // System.out.println("Epoch representation of this date is: " + datetoepoch);
        return datetoepoch;
    }

    /*
      //Different date format 
      public long newdatetoepoch(String date) throws ParseException 
      {
          DateFormat dF = new SimpleDateFormat("dd/MM/yyyy hh:mm"); // The mask
         // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
        Date date1 = dF.parse(date); // parsing the String into a Date using the mask
        //Date.getTime() method gives you the Long with milliseconds since Epoch.
        long newdatetoepoch = date1.getTime()/1000;
        //System.out.println("Epoch representation of this date is: " + newdatetoepoch);
        return newdatetoepoch;
      }*/
    public long currentDatetoEpoch(String currentdate) throws ParseException {
        //String str = "12/12/2012 5:35 PM"; //Your String containing a date
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy"); // The mask

        // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
        Date date1 = dF.parse(currentdate); // parsing the String into a Date using the mask
        //Date.getTime() method gives you the Long with milliseconds since Epoch.
        long datetoepoch = date1.getTime() / 1000;
        System.out.println("Epoch representation of this date is: " + datetoepoch);
        return datetoepoch;
    }

    public String getDayFromDate(String date) {
        System.out.println(date);
        int day = 0;
        String weekname = "";
        Date date1 = new Date(date);
        SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
        SimpleDateFormat formatNowMonth = new SimpleDateFormat("MMM");
        SimpleDateFormat weekofthemonth = new SimpleDateFormat("F");

        SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");

        String currentDay = formatNowDay.format(date1);
        System.out.println("CurentDay=" + formatNowDay);

        String currentMonth = formatNowMonth.format(date1);
        System.out.println("Month" + currentMonth);
//        String currentYear = formatNowYear.format(date1);

        day = Integer.parseInt(currentDay);
        System.out.println("day=" + day);

        String weekofmonth = weekofthemonth.format(date1);
        System.out.println("Weekofmonth=" + weekofmonth);

        if (weekofmonth.equalsIgnoreCase("1")) {
            weekname = "1stweek-" + currentMonth;
        } else if (weekofmonth.equalsIgnoreCase("2")) {
            weekname = "2ndweek-" + currentMonth;
        } else if (weekofmonth.equalsIgnoreCase("3")) {
            weekname = "3rdweek-" + currentMonth;
        } else if (weekofmonth.equalsIgnoreCase("4")) {
            weekname = "4thweek-" + currentMonth;
        } else if (weekofmonth.equalsIgnoreCase("5")) {
            weekname = "5thweek-" + currentMonth;
        }

        return weekname;
    }

    public String newgetDayFromDate(String date) {
        System.out.println(date);
        int day = 0;
        String monthname = "";
        Date date1 = new Date(date);
        SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
        SimpleDateFormat formatNowMonth = new SimpleDateFormat("MMM");
        SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");
        String currentDay = formatNowDay.format(date1);
        System.out.println("CurentDay=" + formatNowDay);

        String currentMonth = formatNowMonth.format(date1);
        System.out.println("Month" + currentMonth);
//        String currentYear = formatNowYear.format(date1);
        day = Integer.parseInt(currentDay);
        System.out.println("day=" + day);

        return currentMonth;
    }

    public String monthYear(String date) {
        System.out.println(date);
        int day = 0;
        String monthname = "";
        Date date1 = new Date(date);
        SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
        SimpleDateFormat formatNowMonth = new SimpleDateFormat("MMM");
        SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");
        String currentDay = formatNowDay.format(date1);
        System.out.println("CurentDay=" + formatNowDay);

        String currentMonth = formatNowMonth.format(date1);
        System.out.println("Month" + currentMonth);
        String currentYear = formatNowYear.format(date1);
        day = Integer.parseInt(currentDay);
        System.out.println("day=" + day);

        return currentMonth + "#" + currentYear;
    }

    /*   public String epochtoDate(String date) {
        System.out.println("date="+date);
        Date date1 = new Date(date); // 'epoch' in long

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = formatter.format(date1);
        System.out.println(dateString + "=DateString");

        return dateString;
    }
     */
    public long datetoEpoch1(String date) throws ParseException {
        //String str = "12/12/2012 5:35 PM"; //Your String containing a date
        DateFormat dF = new SimpleDateFormat("dd/MM/yyyy"); // The mask

        // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
        Date date1 = dF.parse(date); // parsing the String into a Date using the mask
        //Date.getTime() method gives you the Long with milliseconds since Epoch.
        long datetoepoch = date1.getTime() / 1000;
        System.out.println("Epoch representation of this date is: " + datetoepoch);
        return datetoepoch;
    }

    public static void main(String[] args) throws ParseException {
        EpochtoReadableDate ed = new EpochtoReadableDate();
//        //Current date epoch
//        long epoch = System.currentTimeMillis() / 1000;
//        System.out.println(epoch + "=CurrentDateEpoch");
//
//        //epochtocurrentdate with time
//        String dateTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(epoch * 1000));
//        System.out.println(dateTime + "=EpochtoCurrentDate");//With time
//
//        //epoch to currentdate without time
//        String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date(epoch * 1000));
//        System.out.println(date + "=EpochtoCurrentDate");//Without time
//
//        //Epoch to date
//        String date1 = convertTime(Long.valueOf("718050600"));
//        System.out.println("Date:" + date1);
//
//       
//        long newdateepoch = ed.datetoEpochWithTimeSecs("31/03/2018 23:59:59");
//        System.out.println("Dateepoch" + newdateepoch);

//        String day = ed.newgetDayFromDate("31-Apr-2018");
//        System.out.println("day" + day);
        long epoch = 1550834908;
        String getvalue = ed.convertDate(epoch);
        System.out.println("Getvalue="+getvalue);

//        String monthyear = ed.convertDateMonth(1522520999);
//        System.out.println("Monthyear=" + monthyear);

    }

}
