package com.honeycomb.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * @description: com.honeycomb.device.annotation <br>
 * @date: 2021/1/29 2:37 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, PARAMETER})
@Documented
public @interface CheckSign {
}
