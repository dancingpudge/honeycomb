package com.honeycomb.common.feign;

import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @description: com.honeycomb.common.feign <br>
 * @date: 2021/1/19 10:50 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
@ConditionalOnBean(annotation = EnableHoneycombFeignRetry.class)
public class FeignRetryHandler {
    @Autowired
    FeignRetryFactory feignRetryFactory;

    public <T> void recorder(String clzName, String methodName, T data) {
        feignRetryFactory.getRecorderStrategy().saveErrorFile(clzName, methodName, data);
    }
}
