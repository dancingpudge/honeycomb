package com.honeycomb.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.honeycomb.common.annotation.BusinessLog;
import com.honeycomb.common.annotation.EnableBusinessLog;
import com.honeycomb.common.config.BusinessLogConfig;
import com.honeycomb.common.log.BusinessLogBuilder;
import com.honeycomb.common.log.BusinessLogVO;
import com.honeycomb.common.log.LogPusherActuator;
import com.honeycomb.common.util.JoinPointUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author LiuH
 * @Date 2020/8/18
 */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 1) //事务的顺序是Ordered.LOWEST_PRECEDENCE，指定减1代表比事务先执行，后结束
@ConditionalOnBean(annotation = EnableBusinessLog.class)
public class BusinessLogAspect {
    private final BusinessLogConfig businessLogConfig;
    private final LogPusherActuator logPusherActuator;

    @Autowired
    BusinessLogAspect(BusinessLogConfig businessLogConfig, LogPusherActuator logPusherActuator) {
        this.businessLogConfig = businessLogConfig;
        this.logPusherActuator = logPusherActuator;
    }

    @Async
    @AfterReturning(value = "@annotation(businessLog)", returning = "res")
    public void logPush(JoinPoint joinPoint, BusinessLog businessLog, Object res) {
        try {
            BusinessLogVO vo = initVO(businessLog, JoinPointUtils.getParams(joinPoint), res);
            logPusherActuator.doPushLog(vo);
        } finally {

        }
    }

    private BusinessLogVO initVO(BusinessLog businessLog, HashMap params, Object res) {
        BusinessLogVO vo = new BusinessLogBuilder(businessLogConfig, businessLog.carParkId(), businessLog.msgType())
                .setMessage("请求参数: " + JSONObject.toJSONString(params) + "返回结果: " + JSONObject.toJSONString(res))
                .setSystemModule(businessLog.systemModule())
                .setBusinessTopic(businessLog.businessTopic())
                .build();
        return vo;
    }


}
