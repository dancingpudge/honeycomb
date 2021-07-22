package com.honeycomb.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author LiuH
 * @Date 2020/7/29
 */
public class ApiResultUtil {
    private Properties p = new Properties();
    private static ApiResultUtil instance;

    private ApiResultUtil() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("api-error.properties");
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static ApiResultUtil getInstance() {
        if (instance == null) {
            instance = new ApiResultUtil();
        }
        return instance;
    }

    public String getErrorInfo(String key) {
        return p.getProperty(key);
    }
}
