package com.lingyun.framework.entity;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil extends PropertyEditorSupport {

    public static final SimpleDateFormat DateYear = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat Datemoth = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat Dateday = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DateHMS = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DateYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat DateYMDHm = new SimpleDateFormat("HH:mm");

    /*
    * 字符串转时间
    * */

    //yyyy
    public static Date ByDateYear(String Date) throws ParseException{
        return DateYear.parse(Date);
    }

    //yyyy-MM
    public static Date ByDatemoth(String Date) throws ParseException{
        return Datemoth.parse(Date);
    }

    //yyyy-MM-dd
    public static Date ByDateday(String Date) throws ParseException{
        return Dateday.parse(Date);
    }

    //HH:mm:ss
    public static Date ByDateHMS(String Date) throws ParseException{
        return DateHMS.parse(Date);
    }

    //yyyy-MM-dd HH:mm:ss
    public static Date ByDateYMDHMS(String Date) throws ParseException{
        return DateYMDHMS.parse(Date);
    }
    //yyyy-MM-dd HH:mm
    public static Date ByDateYMDHm(String Date) throws ParseException{
        return DateYMDHm.parse(Date);
    }
    /*
    *
    * 时间转字符串
    * */

    //yyyy
    public static String ByStringYear(Date Date){
        return DateYear.format(Date);
    }

    //yyyy-MM
    public static String ByStringmoth(Date Date){
        return Datemoth.format(Date);
    }

    //yyyy-MM-dd
    public static String ByStringday(Date Date){
        return Dateday.format(Date);
    }

    //HH:mm:ss
    public static String ByStringHMS(Date Date){
        return DateHMS.format(Date);
    }

    //yyyy-MM-dd HH:mm:ss
    public static String ByStringYMDHMS(Date Date){
        return DateYMDHMS.format(Date);
    }

    //yyyy-MM-dd HH:mm
    public static String ByStringYMDHm(Date Date) {
        return DateYMDHm.format(Date);
    }
}
