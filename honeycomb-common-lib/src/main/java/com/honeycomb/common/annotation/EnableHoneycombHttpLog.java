package com.honeycomb.common.annotation;

import java.lang.annotation.*;

/**
 * 开启打印接受到的请求参数的日志，兼容表单和对象传参
 *
 * @Author LiuH
 * @Date 2020/6/24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableHoneycombHttpLog {
}
