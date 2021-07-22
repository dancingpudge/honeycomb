package com.honeycomb.common.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: honeycomb-parent
 * @description: 日志推送代理类
 * @author: LiuH
 * @create: 2020-12-03 18:32
 **/
public class LogPusherProxy implements InvocationHandler {

    private Object object;

    public LogPusherProxy(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object, args);
    }
}
