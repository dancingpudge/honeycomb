package com.honeycomb.common.annotation;


import springfox.documentation.oas.annotations.EnableOpenApi;

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
@EnableOpenApi
@EnableHoneycombApiExp
@EnableHoneycombHttpLog
@EnableHoneycombAsync
@EnableHoneycombFeignRetry
public @interface EnableHoneycombAll {
}
