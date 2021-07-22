package com.honeycomb.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shayne
 * @description
 * @create 2020-11-16 15:14
 */
public class AccountNoUtils {

    public static final String ACCOUNT_CODE_PREFIX = "60";

    public static String generateAccountCode() {
        return ACCOUNT_CODE_PREFIX + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomStringUtils.randomNumeric(4);
    }

}
