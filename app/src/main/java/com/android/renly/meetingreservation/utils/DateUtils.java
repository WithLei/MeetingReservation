package com.android.renly.meetingreservation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * 获取当前的小时时间
     * @return
     */
    public static int getHourTimeOfDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    public static long stringToMiles(String str){
        try {
            return stringToDate(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取从startTime到现在的时间
     * @return
     */
    public static String getFromNowOnTime(long startTime){
        long x =  System.currentTimeMillis() - startTime;
        if (x < 0) {
            return "error";
        }

        // 一分钟内
        x /= 60000;
        if (x < 1)
            return "刚刚";

        // 一小时内
        if (x/60 < 1)
            return x%60 + "分钟前";
        x /= 60;

        // 24小时内
        if (x/24 < 1)
            return x%24 + "小时前";
        x /= 24;

        // 一年内
        if (x/365 < 1)
            return x%365 + "天前";

        return x/365 + "年前";
    }

    public static long weekToMiles(int week){
        return week*7*24*3600*1000;
    }
}
