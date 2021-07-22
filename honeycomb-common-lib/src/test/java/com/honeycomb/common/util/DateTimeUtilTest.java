package com.honeycomb.common.util;


import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @description: com.honeycomb.common.util <br>
 * @date: 2021/2/22 10:24 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public class DateTimeUtilTest {
    public static void main(String[] args) {
        System.out.println(DateTimeUtil.getDurationDescription(LocalDateTime.now(), LocalDateTime.now().plus(60072, SECONDS)));
    }
}
