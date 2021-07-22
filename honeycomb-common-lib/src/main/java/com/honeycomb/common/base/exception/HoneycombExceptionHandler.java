package com.honeycomb.common.base.exception;

/**
 * @PackgeName: com.honeycomb.common.base.exception
 * @Description: 异常处理接口
 * @Author: LiuH
 * @Date 2020-12-16 10:30:40
 **/
public interface HoneycombExceptionHandler {
    /**
     * 自定义异常处理类
     *
     * @param e
     */
    void handle(Exception e);
}
