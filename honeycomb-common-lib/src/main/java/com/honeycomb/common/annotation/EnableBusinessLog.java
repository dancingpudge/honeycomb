package com.honeycomb.common.annotation;

/**
 * @program: honeycomb-parent
 * @description:
 * @author: LiuH
 * @create: 2020-12-06 21:11
 **/

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableBusinessLog {
}
