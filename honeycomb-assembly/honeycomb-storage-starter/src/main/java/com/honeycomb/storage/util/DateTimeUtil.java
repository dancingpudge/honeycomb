package com.honeycomb.storage.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件存储日期格式化工具类
 *
 * @author Liuh
 */
public class DateTimeUtil {

    public final static String DATE_STR = "yyyyMMdd";

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
}
