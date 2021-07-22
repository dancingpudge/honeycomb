package com.honeycomb.common.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期格式化工具类
 *
 * @author Liuh
 */
public class DateTimeUtil {

    public final static String DATE_TIME_STR_CN = "yyyy年MM月dd日 HH:mm:ss";
    public final static String DATE_TIME_STR = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final static String DATE_STR = "yyyy-MM-dd";
    public final static String MONTH_STR = "yyyy-MM";

    /**
     * 格式 yyyy年MM月dd日 HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getDateTimeDisplayStringCN(LocalDateTime dateTime) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(DATE_TIME_STR_CN);
        String strDate2 = dtf2.format(dateTime);
        return strDate2;
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateTimeDisplayString() {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(DATE_TIME_STR);
        String strDate2 = dtf2.format(LocalDateTime.now());
        return strDate2;
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateTimeDisplayString(long timestamp) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(DATE_TIME_STR);
        String strDate2 = dtf2.format(dateTimeStamp2Time(timestamp));
        return strDate2;
    }

    public static LocalDateTime dateTimeStamp2Time(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 根据指定表达式转换时间
     *
     * @param dateTimeStr
     * @param expression
     * @return
     */
    public static LocalDateTime dateTimeStr2Time(String dateTimeStr, String expression) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(expression);
        return LocalDateTime.parse(dateTimeStr.trim(), df);
    }

    /**
     * 时间转东八区秒
     *
     * @param dateTime
     * @return
     */
    public static Long dateTime2Second(LocalDateTime dateTime) {
        return dateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 时间转东八区豪秒
     *
     * @param dateTime
     * @return
     */
    public static Long dateTime2Milli(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getDateTimeDisplayString(LocalDateTime dateTime) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(DATE_TIME_STR);
        String strDate2 = dtf2.format(dateTime);
        return strDate2;
    }

    /**
     * 时间转字符串 格式 yyyy-MM-dd
     *
     * @return
     */
    public static String getDateDisplayString() {
        return getDateDisplayString(LocalDateTime.now());
    }

    /**
     * 时间转字符串 格式 yyyy-MM-dd
     *
     * @param dateTime
     * @return
     */
    public static String getDateDisplayString(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern(DATE_STR).format(dateTime);
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param timeStr
     * @return LocalDateTime
     */
    public static LocalDateTime convert2Date(String timeStr) {
        return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern(DATE_TIME_STR));
    }

    /**
     * 日期字符串 转 LocalDate
     *
     * @param dateStr
     * @return
     */
    public static LocalDate dateStrToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * LocalDate 转 日期字符串
     *
     * @param localDate
     * @return
     */
    public static String localDateToDateStr(LocalDate localDate) {
        return localDate.format(DATE_FORMATTER);
    }

    /**
     * 获取指定时间某日开始
     *
     * @param timeStr
     * @return
     */
    public static LocalDateTime beginOfDate(String timeStr) {
        return LocalDateTime.of(LocalDate.parse(timeStr, DateTimeFormatter.ofPattern(DATE_STR)), LocalTime.MIN);
    }


    /**
     * 获取指定时间某日开始
     *
     * @return
     */
    public static LocalDateTime beginOfDate() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取指定时间的某日的结束
     *
     * @param timeStr
     * @return
     */
    public static LocalDateTime endOfDate(String timeStr) {
        return LocalDateTime.of(LocalDate.parse(timeStr, DateTimeFormatter.ofPattern(DATE_STR)), LocalTime.MAX);
    }


    /**
     * 获取中文时长 X天X小时X分X秒
     *
     * @param startDatetime 开始时间
     * @param endDatetime   结束时间
     * @return X天X小时X分X秒
     */
    public static String getDurationDescription(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        Duration duration = Duration.between(startDatetime, endDatetime);
        return duration.toDays() + "天" + duration.toHours() % 24 + "小时" + duration.toMinutes() % 60 + "分" + (duration.getSeconds()) % 60 + "秒";
    }

    public static String date2Str(Date date) {
        return date2Str(date, DATE_TIME_STR);
    }


    /**
     * 时间、时间格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

}
