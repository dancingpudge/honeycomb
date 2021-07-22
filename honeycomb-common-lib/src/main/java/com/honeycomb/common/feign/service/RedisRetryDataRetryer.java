package com.honeycomb.common.feign.service;

import com.alibaba.fastjson.JSONObject;
import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import com.honeycomb.common.config.HoneycombFeignRetryConfig;
import com.honeycomb.common.feign.AbstractRetryer;
import com.honeycomb.common.feign.entity.FeignRetryEnum;
import com.honeycomb.common.feign.entity.ReformerRetryInfo;
import com.honeycomb.common.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: com.honeycomb.common.aspect <br>
 * @date: 2021/1/18 11:17 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
@ConditionalOnProperty(value = "spring.redis.enable")
@ConditionalOnBean(annotation = EnableHoneycombFeignRetry.class)
public class RedisRetryDataRetryer extends AbstractRetryer implements RetryDataRetryer {
    private final HoneycombFeignRetryConfig honeycombFeignRetryConfig;
    private final JedisClient jedisClient;

    @Autowired
    RedisRetryDataRetryer(HoneycombFeignRetryConfig honeycombFeignRetryConfig, JedisClient jedisClient) {
        this.honeycombFeignRetryConfig = honeycombFeignRetryConfig;
        this.jedisClient = jedisClient;
    }

    @Override
    public List<ReformerRetryInfo> collect() {
        List<ReformerRetryInfo> honeycombRetryInfos = new ArrayList<>();
        jedisClient.keys(honeycombFeignRetryConfig.getFileDir()).forEach(
                k -> {
                    honeycombRetryInfos.add(JSONObject.parseObject(jedisClient.get(k), ReformerRetryInfo.class));
                    jedisClient.delKeysLike(honeycombFeignRetryConfig.getFileDir());
                });
        return honeycombRetryInfos;
    }

    @Override
    public FeignRetryEnum initStorageMode() {
        return FeignRetryEnum.REDIS_STORAGE;
    }
}
