package com.honeycomb.common.feign;

import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import com.honeycomb.common.config.HoneycombFeignRetryConfig;
import com.honeycomb.common.feign.entity.FeignRetryEnum;
import com.honeycomb.common.feign.service.RetryDataRecorder;
import com.honeycomb.common.feign.service.RetryDataRetryer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: feign失败信息处理工厂，存取方案必须一致，故一起初始化 <br>
 * @date: 2021/1/19 10:57 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
@ConditionalOnBean(annotation = EnableHoneycombFeignRetry.class)
public class FeignRetryFactory {
    @Autowired
    List<RetryDataRecorder> recorderList;
    @Autowired
    List<RetryDataRetryer> retryerList;
    @Autowired
    HoneycombFeignRetryConfig honeycombFeignRetryConfig;

    private Map<FeignRetryEnum, RetryDataRecorder> recorderMap = new HashMap<>();
    private Map<FeignRetryEnum, RetryDataRetryer> retryerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (RetryDataRecorder retryDataRecorder : recorderList) {
            recorderMap.put(retryDataRecorder.initStorageMode(), retryDataRecorder);
        }
        for (RetryDataRetryer retryDataRetryer : retryerList) {
            retryerMap.put(retryDataRetryer.initStorageMode(), retryDataRetryer);
        }
    }

    /**
     * 获取存储对应策略
     *
     * @return
     */
    public RetryDataRecorder getRecorderStrategy() {
        return recorderMap.get(FeignRetryEnum.getByStorageMode(honeycombFeignRetryConfig.getStorageMode()));
    }

    /**
     * 获取重试对应策略
     *
     * @return
     */
    public RetryDataRetryer getRetryStrategy() {
        return retryerMap.get(FeignRetryEnum.getByStorageMode(honeycombFeignRetryConfig.getStorageMode()));
    }
}
