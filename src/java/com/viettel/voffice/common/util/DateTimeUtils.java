/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author sondm2@viettel.com.vn
 * @since Apr,12,2010
 * @version 1.0
 */
public class DateTimeUtils {

    /** .*/
    public static final int CONST3 = 3;
    /** .*/
    public static final int CONST4 = 4;
    /** .*/
    public static final int CONST5 = 5;
    /** .*/
    public static final int CONST6 = 6;
    /** .*/
    public static final int CONST7 = 7;
    /** .*/
    public static final int CONST8 = 8;
    /** .*/
    public static final int CONST9 = 9;
    /** .*/
    public static final int CONST10 = 10;
    /** .*/
    public static final int CONST11 = 11;
    /** .*/
    public static final int CONST12 = 12;
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DateTimeUtils.class);

    /**
     * private constructor
     */
    private DateTimeUtils() {
    }

    /**
     *
     * @param date to convert
     * @param pattern in converting
     * @return date
     */
    public static Date convertStringToTime(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);

        } catch (ParseException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static Date convertStringToDate(String date) throws Exception {
        String pattern = "yyyy-MM-dd";
        return convertStringToTime(date, pattern);
    }

    /**
     *
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static String convertDateToString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            return "";
        }
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String toString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (date == null) {
            return "";
        }
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * @return String
     * @throws Exception if error
     */
    public static String getSysdate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        return convertDateToString(calendar.getTime());
    }

    public static Date getDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    /**
     *
     * @return String
     * @throws Exception if error
     */
    public static String getSysDateTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param pattern to convert
     * @return String
     * @throws Exception if error
     */
    public static String getSysDateTime(String pattern) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static Date convertStringToDateTime(String date) throws Exception {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        return convertStringToTime(date, pattern);
    }

    /**
     *
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static String convertDateTimeToString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (date == null) {
                return "";
            }
            return dateFormat.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param utilDate to convert
     * @return date
     */
    public static java.sql.Date convertToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     *
     * @param monthInput to parse
     * @return String
     */
    public static String parseDate(int monthInput) {
        String dateReturn = "01/01/";
        Calendar cal = Calendar.getInstance();
        switch (monthInput) {
            case 1:
                dateReturn = "01/01/";
                break;
            case 2:
                dateReturn = "01/02/";
                break;
            case CONST3:
                dateReturn = "01/03/";
                break;
            case CONST4:
                dateReturn = "01/04/";
                break;
            case CONST5:
                dateReturn = "01/05/";
                break;
            case CONST6:
                dateReturn = "01/06/";
                break;
            case CONST7:
                dateReturn = "01/07/";
                break;
            case CONST8:
                dateReturn = "01/08/";
                break;
            case CONST9:
                dateReturn = "01/09/";
                break;
            case CONST10:
                dateReturn = "01/10/";
                break;
            case CONST11:
                dateReturn = "01/11/";
                break;
            case CONST12:
                dateReturn = "01/12/";
                break;
        }
        return dateReturn + cal.get(Calendar.YEAR);
    }

    /**
     * Lay ve ngay dau tien cua mot thang nao do
     * @param months 
     * @param days 
     * @return 
     */
    public static Date getFirstDayAMonth(Date date, int months) {

        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        int days = cl.get(Calendar.DATE) - 1;

        // Ngay nay thang truoc
        cl.add(Calendar.MONTH, months);

        // Ngay dau thang truoc
        cl.add(Calendar.DATE, -days);

        return cl.getTime();
    }

    /**
     * Lay ve ngay cach ngay hien tai days ngay months thang
     * @param months 
     * @param days 
     * @return 
     */
    public static Date getAddDate(Date date, int months, int days) {

        Calendar cl = Calendar.getInstance();
        cl.setTime(date);

        cl.add(Calendar.MONTH, months);
        cl.add(Calendar.DATE, days);

        return cl.getTime();
    }

    public static XMLGregorianCalendar dateToXML(Date date) {
        try {
            if(date == null) return null;
            //System.out.println(date.toString());
            GregorianCalendar c = new GregorianCalendar();
            c.setTimeZone(TimeZone.getTimeZone("GMT+07")); 
            c.setTime(date);
            XMLGregorianCalendar dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            //System.out.println(dateXML.toString());
            return dateXML;
        } catch (DatatypeConfigurationException ex) {
        }
        return null;
    }
}