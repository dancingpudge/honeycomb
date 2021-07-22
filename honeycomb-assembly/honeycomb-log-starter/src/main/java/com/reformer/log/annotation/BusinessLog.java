package com.honeycomb.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * @program: honeycomb-parent
 * @description:
 * @author: LiuH
 * @create: 2020-12-06 21:11
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, PARAMETER})
@Documented
public @interface BusinessLog {
    String systemModule();

    String carParkId() default "0000";

    String msgType() default "0";

    String businessTopic() default "0";
}
