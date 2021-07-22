package com.honeycomb.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * @author admin
 */
@Component
@ConditionalOnProperty(prefix = "spring.redis", value = "enable")
@ConfigurationProperties(prefix = "spring.redis")
public class JedisConfig {
    private String host;
    private String password;
    private Integer port;
    private Integer database;

    private Map<String, Map<String, Integer>> jedis;

    /**
     * 构造jedisPoolConfig bean
     *
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Map<String, Integer> pool = jedis.get("pool");
        jedisPoolConfig.setMaxTotal(pool.get("max-active"));
        jedisPoolConfig.setMaxIdle(pool.get("max-idle"));
        jedisPoolConfig.setMaxWaitMillis(pool.get("max-wait"));
        jedisPoolConfig.setMinIdle(pool.get("min-idle"));
        jedisPoolConfig.setTestOnBorrow(false);
        return jedisPoolConfig;
    }

    /**
     * 构造jedisPool bean
     *
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public JedisPool jedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig) {
        return new JedisPool(jedisPoolConfig, host, port, 15000, password, database);
    }

    /**
     * 构造jedisClient bean
     *
     * @param jedisPool
     * @return
     */
    @Bean
    public JedisClient jedisClient(@Qualifier("jedisPool") JedisPool jedisPool) {
        JedisClient jedisClient = new JedisClient();
        JedisClient.setJedisPool(jedisPool);
        return jedisClient;
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setPassword(password);
        return Redisson.create(config);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public Map<String, Map<String, Integer>> getJedis() {
        return jedis;
    }

    public void setJedis(Map<String, Map<String, Integer>> jedis) {
        this.jedis = jedis;
    }


}
