package com.springboot.boot.utils;

import com.springboot.boot.common.exc.BusinessException;
import io.swagger.annotations.ApiModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author:lijx
 * @(#)DateUtils 1.0 2020/3/20
 * <p>
 * Copyright (c) 2020, PICCHEALTH. All rights reserved.
 * PICCHEALTH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
@ApiModel("时间相关工具类")
public class DateUtils {
    final static String DATE_PATTERN ="yyyy-MM-dd HH:mm:ss";
    final static String SHORT_DATE_PATTERN ="yyyy-MM-dd";
    final static String SHORT_DATE_YMD ="yyyyMMdd";
    final static String DATE_HOUR ="HH";
    final static String DATE_PATTERN_FORMAT ="yyyyMMddHHmmss";

    /**
     * Date:"yyyy-MM-dd HH:mm:ss"转String
     * */
    public static String datePatternformat(Date date){
        DateFormat df=new SimpleDateFormat(DATE_PATTERN_FORMAT);
        return df.format(date);
    }
    /**
     * Date:"yyyy-MM-dd HH:mm:ss"转String
     * */
    public static String format(Date date){
        DateFormat df=new SimpleDateFormat(DATE_PATTERN);
        return df.format(date);
    }

    /**
     * Date:"yyyy-MM-dd"转String
     * */
    public static String formatShortDate(Date date){
        DateFormat df=new SimpleDateFormat(SHORT_DATE_PATTERN);
        return df.format(date);
    }
    public static String formatDateYMD(Date date){
        DateFormat df=new SimpleDateFormat(SHORT_DATE_YMD);
        return df.format(date);
    }

    /**
     * 时间字符串转为日期格式"yyyy-MM-dd HH:mm:ss"
     * */
    public static Date parseDate(String dateStr){
        if(dateStr== null){
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, DATE_PATTERN);
        } catch (ParseException e) {
            throw new BusinessException("date format error",e);
        }
    }
    /**
     * 时间字符串转为日期格式"yyyy-MM-dd"
     * */
    public static Date parseShortDate(String dateStr){
        if(dateStr== null){
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, SHORT_DATE_PATTERN);
        } catch (ParseException e) {
            throw new BusinessException("date format error",e);
        }
    }

    /**
     * 将时间字符串转为LocalDateTime格式
     */
    public static LocalDateTime strToLocalDate(String timeStr) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.parse(timeStr, fmt);
    }

    /**
     * 判断两个日期是否为同一天
     * */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }
    /**
     * 判断两个日期是否为同一天
     * */
    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 判断给定的两个日期相差天数
     * */
    public static int differentDays(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        //不同年
        if(year1 != year2) {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                //闰年
                if(i%4==0 && i%100!=0 || i%400==0) {
                    timeDistance += 366;
                }else{
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        }else{
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    /**
     * 判断给定的日期是否为一个月的第一天
     * */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.MONTH)+1);
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }
    /**
     * 获取昨天的日期
     * */
    public static Date getYesterdayStr(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        return time;
    }
    /**
     * 获取时间段内的所有日期
     * */
    public static List<Date> getRangeDate(Date start, Date end) {
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(start);
        min.set(Calendar.HOUR_OF_DAY, 0);
        min.set(Calendar.MINUTE, 0);
        min.set(Calendar.SECOND, 0);
        min.set(Calendar.MILLISECOND, 0);
        max.setTime(end);
        max.set(Calendar.HOUR_OF_DAY, -24);
        max.set(Calendar.MINUTE, 0);
        max.set(Calendar.SECOND, 0);
        max.set(Calendar.MILLISECOND, 0);
        List<Date> dateArrayList = new ArrayList<>();
        Calendar current = min;
        while (current.before(max) || current.equals(max)) {
            dateArrayList.add(current.getTime());
            current.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateArrayList;
    }

    /**
     * 获取小时
     * @param date
     * @return
     */
    public static String formatHour(Date date){
        DateFormat df=new SimpleDateFormat(DATE_HOUR);
        return df.format(date);
    }
    /**
     * 得到当天的初始时间
     * @param date
     * @return
     */
    public static Date getDayStart(Date date){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();

    }


    /**
     * 获取昨天的日期
     * */
    public static Date getNextDay( Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, +24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        return time;
    }






    /**
     * 距离结束时间倒计时 时分秒
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟"+sec+ "秒";

    }
    /**
     * 计算两个时相差几分钟
     * @param endDate  结束时间
     * @param startDate 开始时间
     * @return
     */
    public static String getDatePoorMin(Date startDate, Date endDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        if(day!=0){
            min=min+(day*24*60);
        }
        if(hour!=0){
            min=min+(hour*60);
        }
        //System.out.println("相差分钟数："+min);
        return min+"";

    }



    public static void main(String[] args) {
//        Date date= DateUtil.dateTrans("2020-04-30 15:20:18");
//        Date current=new Date();
//        System.out.println(getDatePoorMin(date,current));
    }
}
