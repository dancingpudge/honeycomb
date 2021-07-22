package com.honeycomb.common.feign.service;

import com.alibaba.fastjson.JSONObject;
import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import com.honeycomb.common.config.HoneycombFeignRetryConfig;
import com.honeycomb.common.feign.entity.FeignRetryEnum;
import com.honeycomb.common.feign.entity.ReformerRetryInfo;
import com.honeycomb.common.redis.JedisClient;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @description: com.honeycomb.common.aspect <br>
 * @date: 2021/1/18 11:17 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
@ConditionalOnBean(annotation = EnableHoneycombFeignRetry.class)
@ConditionalOnProperty(value = "spring.redis.enable")
public class RedisRetryDataRecorder implements RetryDataRecorder {
    private static Logger log = LoggerFactory.getLogger(RedisRetryDataRecorder.class.getName());
    private final HoneycombFeignRetryConfig honeycombFeignRetryConfig;
    private final JedisClient jedisClient;

    @Autowired
    RedisRetryDataRecorder(HoneycombFeignRetryConfig honeycombFeignRetryConfig, JedisClient jedisClient) {
        this.honeycombFeignRetryConfig = honeycombFeignRetryConfig;
        this.jedisClient = jedisClient;
    }

    @Override
    public <T> void saveErrorFile(String clzName, String methodName, T data) {
        String value = JSONObject.toJSONString(new ReformerRetryInfo(clzName, methodName, JSONObject.toJSONString(data), data.getClass()));
        jedisClient.set(honeycombFeignRetryConfig.getFileDir() + ":" + MD5Encoder.encode(value.getBytes()), value);
    }

    @Override
    public FeignRetryEnum initStorageMode() {
        return FeignRetryEnum.REDIS_STORAGE;
    }
}
