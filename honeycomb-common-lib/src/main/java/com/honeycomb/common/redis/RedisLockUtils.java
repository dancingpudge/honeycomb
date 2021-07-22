package com.honeycomb.common.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @PackgeName: com.honeycomb.common.redis
 * @Description: 分布式枷锁线程工具类
 * @Author: LiuH
 * @Date 2020-12-16 10:30:40
 **/
@Component
@ConditionalOnProperty(prefix = "spring.redis", value = "enable")
public class RedisLockUtils {

    private static Logger log = LoggerFactory.getLogger(RedisLockUtils.class.getName());

    private final static int LOCK_TIME = 30;
    private final RedissonClient redissonClient;

    @Autowired
    RedisLockUtils(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /***
     * 不做等待的分布式锁
     *
     * @param key
     * @param p
     * @throws InterruptedException
     */
    public void execute(String key, Runnable p) throws InterruptedException {
        RLock rLock = redissonClient.getLock(key);
        if (rLock.isLocked()) {
            log.info("锁被抢占 本次略过 :=====>" + key);
            return;
        }
        rLock.tryLock(0, LOCK_TIME, TimeUnit.SECONDS);
        try {
            p.run();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 等待N秒，超时则返回失败
     *
     * @param key
     * @param waiteTime
     * @param p
     * @throws InterruptedException
     */
    public void execute(String key, int waiteTime, Runnable p) throws InterruptedException {
        RLock rLock = redissonClient.getLock(key);
        if (rLock.isLocked()) {
            log.info("锁被抢占 本次略过 :=====>" + key);
            return;
        }
        rLock.tryLock(waiteTime, LOCK_TIME, TimeUnit.SECONDS);
        try {
            p.run();
        } finally {
            rLock.unlock();
        }
    }
}
