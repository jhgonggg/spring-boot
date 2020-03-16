package com.funtl.hello.spring.boot.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import com.funtl.hello.spring.boot.constant.SysConst;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;

/**
 * @author qy
 * @date 2020/3/16 17:11
 * @description
 */
public class DateUtils {

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前周的第一天
     *
     * @param date
     * @return
     */
    public static Date firstDayOfWeekAtStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime dateTime = localDateTime.with(WeekFields.ISO.getFirstDayOfWeek()).with(LocalTime.MIN);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前周的第一天
     *
     * @param date
     * @return
     */
    public static Date lastDayOfWeekAtEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime dateTime = localDateTime.with(WeekFields.SUNDAY_START.getFirstDayOfWeek()).with(LocalTime.MAX);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取月的第一天
     *
     * @param date
     * @return
     */
    public static Date firstDayOfMonthAtStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime dateTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取月的最后一天
     *
     * @param date
     * @return
     */
    public static Date lastDayOfMonthAtEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime dateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前日期年的第一年的第一刻
     *
     * @param date
     * @return
     */
    public static Date firstDayOfYearAtStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime dateTime = localDateTime.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前日期年的最后一刻
     * @param date
     * @return
     */
    public static Date lastDayOfYearAtEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime dateTime = localDateTime.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    public static void main(String[] args) throws ParseException {
        // 当前时间字符串
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(SysConst.DATE_FORMAT));

        // 日期转字符串
        Date date =new Date();
        DateUtil.format(date, DatePattern.NORM_DATETIME_MINUTE_PATTERN);

        // 字符串转日期
        Date timeDate = org.apache.commons.lang3.time.DateUtils.parseDate("2019-09-30 12:11","yyyy-MM-dd HH:mm");

        // 当前时间
        SystemClock.now();
        System.currentTimeMillis();

//        System.out.println(getStartOfDay(new Date()));
//        System.out.println(getEndOfDay(new Date()));
//        System.out.println(firstDayOfWeekAtStartOfDay(new Date()));
//        System.out.println(lastDayOfWeekAtEndOfDay(new Date()));
//        System.out.println(firstDayOfMonthAtStartOfDay(new Date()));
//        System.out.println(lastDayOfMonthAtEndOfDay(new Date()));
//        System.out.println(firstDayOfYearAtStartOfDay(new Date()));
//        System.out.println(lastDayOfYearAtEndOfDay(new Date()));
    }
}
