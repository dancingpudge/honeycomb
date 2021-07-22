package com.honeycomb.common.annotation;

import java.lang.annotation.*;

/**
 * 开启异步线程池配置
 *
 * @Author LiuH
 * @Date 2020/6/24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableHoneycombAsync {
}
