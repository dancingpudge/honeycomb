package com.honeycomb.common.log;

import com.honeycomb.common.annotation.EnableBusinessLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;

/**
 * @program: honeycomb-parent
 * @description: 日志推送处理器
 * @author: LiuH
 * @create: 2020-12-03 18:43
 **/
@Component
@ConditionalOnBean(annotation = EnableBusinessLog.class)
public class LogPusherActuator {

    @Resource
    LogPusher pusher;

    public void doPushLog(BusinessLogVO vo) {
        LogPusher logPusher = (LogPusher) Proxy.newProxyInstance(LogPusher.class.getClassLoader()
                , new Class[]{LogPusher.class}
                , new LogPusherProxy(pusher));
        logPusher.push(vo);
    }
}
