package com.honeycomb.honeycombdemo;

import com.honeycomb.common.base.exception.ApiException;
import com.honeycomb.common.redis.JedisClient;
import com.honeycomb.demo.ReformerDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @PackgeName: com.honeycomb.honeycombdemo
 * @Description: 分布式锁测试
 * @Author: LiuH
 * @Date 2020-12-22 13:50:45
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReformerDemoApplication.class)
public class RedisLockUtilTest {
    /**
     * 幂等锁存储过期时间
     */
    private static int EXPIRE_TIME = 3600000;

    @Resource
    JedisClient jedisClient;

    @Test
    public void lockTest() {
        for (int i = 0; i < 100; i++) {
            jedisClient("jedisClientLockUtil:test" + i);
            System.out.println("jedisClientLockUtil:test\"========>" + i);
        }
    }


    private void jedisClient(String key) {
        try {
            if (!jedisClient.tryLock(key, 1, 300)) {
                throw new ApiException("10000");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("releaseDistributedLock");
            jedisClient.unlock(key);
        }
    }
}
