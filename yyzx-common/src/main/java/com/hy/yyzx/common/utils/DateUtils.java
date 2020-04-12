package com.hy.yyzx.common.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 时间转换和计算工具
 *
 * @author chg
 */
public class DateUtils {


    /**
     * 日期+几个月
     *
     * @param date  当前日期
     * @param month 几个月
     * @return
     */
    public static Date getDateToMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return new Date(c.getTime().getTime());
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Timestamp date1,Timestamp date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0) //闰年
                {
                    timeDistance += 366;
                }
                else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else //不同年
        {
            return day2-day1;
        }
    }

    /**
     * 日期+几个月
     *
     * @param date  当前日期
     * @param month 几个月
     * @return
     */
    public static Date getDateToMonth(java.util.Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return new Date(c.getTime().getTime());
    }

    /**
     * 时间+几个月
     *
     * @param timestamp 当前时间
     * @param month     几个月
     * @return
     */
    public static Timestamp getTimestampToMonth(Timestamp timestamp, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(timestamp);
        c.add(Calendar.MONTH, month);
        return new Timestamp(c.getTime().getTime());
    }

    /**
     * 日期+天数
     *
     * @param date 当前日期
     * @param day  几天
     * @return
     */
    public static Date getDateToDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return new Date(c.getTime().getTime());
    }

    /**
     * 日期时间+天数
     *
     * @param timestamp 当前时间
     * @param day       几天
     * @return
     */
    public static Timestamp getTimestampToDay(Timestamp timestamp, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(timestamp);
        c.add(Calendar.DATE, day);
        return new Timestamp(c.getTime().getTime());
    }

    /**
     * 日期格式化java.sql.Date
     *
     * @param date 当前日期
     * @param type 转换类型如（yyyy-mm-dd）
     * @return
     */
    public static String formatDate(Date date, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

    /**
     * Timestamp转换java.sql.Date
     *
     * @param time 日期
     * @return
     */
    public static Date sqlDate(Timestamp time) {
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = DateUtils.formatDateYeePay(time);
        java.util.Date date2 = new java.util.Date();
        try {
            date2 = dateformat.parse(timeString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date(date2.getTime());
    }

    /**
     * 时间格式化timestamp
     *
     * @param timestamp 时间
     * @param type      转换类型如（yyyy-mm-dd）
     * @return
     */
    public static String formatTime(Timestamp timestamp, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(timestamp.getTime());
    }

    /**
     * 当前时间日期
     *
     * @param format 转换类型如（yyyy-mm-dd）
     * @return
     */
    public static String dateFormat(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(c.getTime());
    }

    /**
     * 当前时间
     *
     * @return
     */
    public static String currentTimeFormat() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(c.getTime());
    }

    /**
     * 当天最后时间
     *
     * @param date 当前时间
     * @return
     */
    public static String formatDateYeePay(Timestamp date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        return format.format(date);
    }

    /**
     * 时间+分钟
     *
     * @param timestamp 时间
     * @param min       几分
     * @return
     */
    public static Timestamp getTimestampToMin(Timestamp timestamp, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(timestamp);
        c.add(Calendar.MINUTE, min);
        return new Timestamp(c.getTime().getTime());
    }

    /**
     * 时间+分钟
     *
     * @param date 时间
     * @param min       几分
     * @return
     */

    public static java.util.Date getDateToMin(java.util.Date date, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, min);
        return new java.util.Date(c.getTime().getTime());
    }

    /**
     * 计算两个时间的天数
     *
     * @param totime   开始时间
     * @param fromtime 结束时间
     * @return
     */
    public static Integer caculateDay(Timestamp totime, Timestamp fromtime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long to = totime.getTime();
        long from = fromtime.getTime();
        Integer day = new Long((to - from) / (1000 * 60 * 60 * 24)).intValue();
        return day;
    }

    /**
     * sql.Date计算两个时间的天数
     *
     * @param todate   开始日期
     * @param fromdate 结束日期
     * @return
     */
    public static Integer caculateDays(Date todate, Date fromdate) {
        long to = todate.getTime();
        long from = fromdate.getTime();
        Integer day = new Long((to - from) / (1000 * 60 * 60 * 24)).intValue();
        return day;
    }

    /**
     * util.Date计算两个时间的天数
     *
     * @param todate   开始日期
     * @param fromdate 结束日期
     * @return
     */
    public static Integer caculateDays(java.util.Date todate, java.util.Date fromdate) {
        long to = todate.getTime();
        long from = fromdate.getTime();
        Integer day = new Long((to - from) / (1000 * 60 * 60 * 24)).intValue();
        return day;
    }

    /**
     * sql.Date计算两个时间的天数
     *
     * @param todate   开始日期
     * @param fromdate 结束日期
     * @return
     */
    public static Integer caculateDay(Date todate, Date fromdate) {
        Calendar c = Calendar.getInstance();
        c.set(fromdate.getYear(), fromdate.getMonth() - 1, fromdate.getDate());
        long time1 = c.getTimeInMillis();
        c.set(todate.getYear(), todate.getMonth() - 1, todate.getDate());
        long time2 = c.getTimeInMillis();
        long time = (time2 - time1) / (1000 * 60 * 60 * 24) + 1;
        int days = (int) time;
        return days;
    }

    /**
     * 计算获得最近几个周的第一天
     * 最近，包括当前周，如：最近一周就是本周， 最近两周就是，当前周和上一周
     *
     * @param date 日期
     * @param Week 不能为负数
     * @return
     */
    public static Date getRecrntlyWeeK(Date date, int Week) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayWeek = c.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        c.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = c.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (Week > 0) {//时间向后
            c.add(Calendar.DATE, c.getFirstDayOfWeek() - day - ((Week - 1) * 7));//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        } else {//时间向前
            c.add(Calendar.DATE, c.getFirstDayOfWeek() - day - ((Week - 1) * 7) - 1);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        }
        return new Date(c.getTime().getTime());

    }

    /**
     * 算获得当前日期之前之后几周的第一天
     *
     * @param date 日期
     * @param Week 正数向后推，负数向前推
     * @return
     */
    public static Date getStateWeeK(Date date, int Week) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayWeek = c.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        c.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = c.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (Week >= 0) {//时间向后
            c.add(Calendar.DATE, c.getFirstDayOfWeek() - day - (Week * 7));//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        } else {//时间向前
            c.add(Calendar.DATE, c.getFirstDayOfWeek() - day + (-Week * 7));//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        }
        return new Date(c.getTime().getTime());

    }

    /**
     * 计算获得当前日期之前之后几周的最后一天
     *
     * @param date 日期
     * @param Week 正数向后推，负数向前推
     * @return
     */
    public static Date getEndWeeK(Date date, int Week) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayWeek = c.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        c.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = c.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (Week >= 0) {//时间向后
            c.add(Calendar.DATE, c.getFirstDayOfWeek() - day - (Week * 7) + 6);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        } else {//时间向前
            c.add(Calendar.DATE, c.getFirstDayOfWeek() - day + (-Week * 7) + 6);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        }
        return new Date(c.getTime().getTime());

    }

    /**
     * 计算获得最近几个月的第一天
     *
     * @param date  日期
     * @param month 月
     * @return
     */
    public static Date getRecrntlyMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        c.add(Calendar.MONTH, -month + 1);//根据当前月计算前后几个月
        return new Date(c.getTime().getTime());

    }

    /**
     * 转换java.util.Date类型日期
     *
     * @param date 日期
     * @param type 月
     * @return
     */
    public static String stringUtilDate(java.util.Date date, String type) {
        DateFormat df = new SimpleDateFormat(type);
        //System.out.println(df.format(date));
        return df.format(date);
    }

    /**
     * 返回第几个月份，不是几月
     * 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月-1月
     *
     * @param month          几月
     * @param isQuarterStart true 季度初   false 季度末
     * @return
     */
    public static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] = {2, 5, 8, 11};
        if (!isQuarterStart) {
            months = new int[]{4, 7, 10, 1};
        }
        if (month >= 2 && month <= 4)
            return months[0];
        else if (month >= 5 && month <= 7)
            return months[1];
        else if (month >= 8 && month <= 10)
            return months[2];
        else
            return months[3];
    }

    /**
     * 计算指定日期当月的第一天String
     *
     * @param date 日期
     * @param type 格式类型如（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String monthFrist(java.util.Date date, String type) {
        type = type == null ? "yyyy-MM-dd HH:mm:ss" : type;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        return stringUtilDate(c.getTime(), type);
    }

    /**
     * 计算指定日期当月的第一天
     *
     * @param date 日期
     * @return
     */
    public static Date monthFristDate(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        return new Date(c.getTime().getTime());
    }

    /**
     * 计算指定日期当月的最后一天String
     *
     * @param date 日期
     * @param type 格式类型如（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String monthAfter(java.util.Date date, String type) {
        type = type == null ? "yyyy-MM-dd HH:mm:ss" : type;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        c.add(Calendar.DATE, day - 1);//本月分的最后一天
        return stringUtilDate(c.getTime(), type);
    }

    /**
     * 计算指定日期当月的最后一天
     *
     * @param date 日期
     * @return
     */
    public static Date monthAfterDate(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        c.add(Calendar.DATE, day - 1);//本月分的最后一天
        return new Date(c.getTime().getTime());
    }

    /**
     * 计算指定日期季度的第一天String
     *
     * @param date 日期
     * @param type 格式类型如（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String quarterFrist(java.util.Date date, String type) {
        type = type == null ? "yyyy-MM-dd HH:mm:ss" : type;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;//当前月
        int m = getQuarterInMonth(month, true); //当前季度第一个月
        if (month == 1) {
            c.add(Calendar.YEAR, -1); //减一年
        }
        c.set(Calendar.MONTH, m - 1);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        return stringUtilDate(c.getTime(), type);
    }

    /**
     * 计算指定日期季度的第一天
     *
     * @param date 日期
     * @return
     */
    public static Date quarterFristDate(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;//当前月
        int m = getQuarterInMonth(month, true); //当前季度第一个月
        if (month == 1) {
            c.add(Calendar.YEAR, -1); //减一年
        }
        c.set(Calendar.MONTH, m - 1);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        return new Date(c.getTime().getTime());
    }

    /**
     * 计算指定日期季度的最后天String
     *
     * @param date 日期
     * @param type 格式类型如（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public static String quarterAfter(java.util.Date date, String type) {
        type = type == null ? "yyyy-MM-dd HH:mm:ss" : type;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;//当前月
        int m = getQuarterInMonth(month, false);
        if (month >= 11) {
            c.add(Calendar.YEAR, 1); //加一年
        }
        c.set(Calendar.MONTH, m - 1);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        c.add(Calendar.DATE, day - 1);//获得当前月最大一天
        return stringUtilDate(c.getTime(), type);
    }

    /**
     * 计算指定日期季度的最后天
     *
     * @param date 日期
     * @return
     */
    public static Date quarterAfterDate(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH) + 1;//当前月
        int m = getQuarterInMonth(month, false);
        if (month >= 11) {
            c.add(Calendar.YEAR, 1); //加一年
        }
        c.set(Calendar.MONTH, m - 1);
        int dayMonth = c.get(Calendar.DAY_OF_MONTH);//获得当前日期是一个月的第几天
        c.add(Calendar.DATE, -dayMonth + 1);//获得当前月第一天
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        c.add(Calendar.DATE, day - 1);//获得当前月最大一天
        return new Date(c.getTime().getTime());
    }

    /**
     * 计算距指定时间几年后或几年前的当前时间String
     *
     * @param date 日期
     * @param type 格式类型如（yyyy-MM-dd HH:mm:ss）
     * @param year 几年
     * @return
     */
    public static String yearTime(java.util.Date date, String type, int year) {
        type = type == null ? "yyyy-MM-dd HH:mm:ss" : type;
        String d = stringUtilDate(date, type);
        String time = (Integer.parseInt(d.substring(0, 4)) + year) + d.substring(4, d.length());
        return time;
    }

    /**
     * 计算距指定时间年末
     *
     * @param date 日期
     * @return
     */
    public static java.util.Date yearTimeAfter(java.util.Date date) {
        String d = stringUtilDate(date, "yyyy-MM-dd HH:mm:ss");
        String time = (Integer.parseInt(d.substring(0, 4))) + "-12-31 23:59:59";
        return stringToDate(time);
    }

    /**
     * 字符串转换日期
     *
     * @param date 日期
     * @return
     */
    public static java.util.Date stringToDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 字符串转换日期
     *
     * @param strDate 日期
     * @return
     */
    public static Date strToDate(String strDate,String Format){
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat(Format);
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date(d.getTime());
        return date;
    }


    public static java.util.Date stringToDateFormat(String date,String format) {
        DateFormat df = new SimpleDateFormat(format);
        java.util.Date d = null;
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 计算距指定时间几年后的时间
     *
     * @param date 日期
     * @param year 几年
     * @return
     */
    public static Date yearTimeDate(java.util.Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year); //加一年
        return new Date(c.getTime().getTime());
    }

    /**
     * 格式化为xxxx年xx月xx日xx时xx分xx秒
     *
     * @param time 时间
     * @return
     */
    public static String timeToChineseStr(Timestamp time) {
        String str = "";
        Calendar can = Calendar.getInstance();
        can.setTime(time);
        str = can.get(Calendar.YEAR) + "年" +
                can.get(Calendar.MONTH) + "月" +
                can.get(Calendar.DAY_OF_MONTH) + "日" +
                can.get(Calendar.HOUR_OF_DAY) + "时" +
                can.get(Calendar.MINUTE) + "分" +
                can.get(Calendar.SECOND) + "秒";
        return str;
    }

    /**
     * 当天时间的开始时间
     *
     * @param timestamp
     * @return
     */
    public static Date getStateHours(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 当天时间的开始时间
     *
     * @param timestamp
     * @return
     */
    public static Date getEndHours(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Date(calendar.getTime().getTime());
    }


    /**
     * 字符串转换Timestamp
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Timestamp getTimestamp(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Timestamp(sdf.parse(time).getTime());
    }

    /**
     * 获取未来 任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public static List<String> fetureDaysList(int intervals ) {
        List<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i <intervals; i++) {
            fetureDaysList.add(getFetureDate(i));
        }
        return fetureDaysList;
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past-ggggaaa 66 9y76aaasaasaiytewq
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        java.util.Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }


    public static Integer getWeek(String dates) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = f.parse(dates);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        cal.setTime(d);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w;
    }

    public static String getWeekStr(Integer day) {
        String[] weekList = {"周日","周一", "周二", "周三", "周四", "周五", "周六" };
        String week =weekList[day];
        return week;
    }

    public static void main(String[] args) {

     /* Long starttime = System.currentTimeMillis();
      Long endtime = starttime + 60*60*1000;

        System.out.println("starttime="+starttime);
        System.out.println("endtime="+endtime);

        System.out.println(dateFormat(formatDate(new Date(starttime),"yyyy-MM-dd HH:mm:ss")));
        System.out.println(dateFormat(formatDate(new Date(endtime),"yyyy-MM-dd HH:mm:ss")));*/

        System.out.println(getDateToMonth(new java.util.Date(),-1));





    }
}

