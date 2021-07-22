package com.honeycomb.common.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.*;

/**
 * redis操作工具类
 *
 * @author sdc
 * @version 1.0
 * @since 1.0
 */
@ConditionalOnProperty(prefix = "spring.redis", value = "enable")
public class JedisClient {

    /**
     * 加锁标志
     */
    public static final String LOCKED = "TRUE";
    /**
     * 随机分布
     */
    public static final Random RANDOM = new Random();
    /**
     * 锁后缀
     */
    public static final String LOCK_SUFFIX = "_lock";

    private static JedisPool jedisPool;

    public static void setJedisPool(JedisPool jedisPool) {
        JedisClient.jedisPool = jedisPool;
    }

    /**
     * 删除模糊匹配的key
     *
     * @param pattern 模糊匹配的key
     * @return 删除成功的条数
     */
    public long delKeysLike(final String pattern) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                Set<String> keys = jedis.keys(pattern);
                long count = 0;
                for (String key : keys) {
                    count += jedis.del(key);
                }
                return count;
            }
        }.getResult();
    }

    public Set<String> keys(final String pattern) {
        return new Executor<Set<String>>(jedisPool) {
            @Override
            Set<String> execute() {
                return jedis.keys(pattern);
            }
        }.getResult();
    }

    /**
     * 获取Jedis对象
     *
     * @return 从jedisPool中获取jedis对象
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 删除给定的一个或多个 key 。
     * <p>
     * 不存在的 key 会被忽略。
     * <p>
     * 可用版本： >= 1.0.0 时间复杂度： O(N)， N 为被删除的 key 的数量。 删除单个字符串类型的 key ，时间复杂度为O(1)。
     * 删除单个列表、集合、有序集合或哈希表类型的 key ，时间复杂度为O(M)， M 为以上数据结构内的元素数量。 返回值：
     *
     * @param keys
     * @return 被删除 key 的数量
     */
    public Long del(final String... keys) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.del(keys);
            }
        }.getResult();
    }

    public long hdel(String key, String field) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.hdel(key, field);
            }
        }.getResult();
    }

    public Long del(final String key) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.del(key);
            }
        }.getResult();
    }

    public Long del(final byte[] key) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.del(key);
            }
        }.getResult();
    }

    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。 在 Redis 中，带有生存时间的 key
     * 被称为『可挥发』(volatile)的。
     *
     * @param key    key
     * @param expire 生命周期，单位为秒
     * @return 1: 设置成功 0: 已经超时或key不存在
     */
    public Long expire(final String key, final int expire) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.expire(key, expire);
            }
        }.getResult();
    }

    public Long expire(final byte[] key, final int expire) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.expire(key, expire);
            }
        }.getResult();
    }

    /**
     * 一个跨jvm的id生成器，利用了redis原子性操作的特点
     *
     * @param key id的key
     * @return 返回生成的Id
     */
    public long makeId(final String key) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                long id = jedis.incr(key);
                if ((id + 75807) >= Long.MAX_VALUE) {
                    // 避免溢出，重置，getSet命令之前允许incr插队，75807就是预留的插队空间
                    jedis.getSet(key, "0");
                }
                return id;
            }
        }.getResult();
    }

    /**
     * SETNX works exactly like {@link #set(String, String) SET} with the only
     * difference that if the key already exists no operation is performed.
     * SETNX actually means "SET if Not eXists".
     * <p>
     * Time complexity: O(1)
     *
     * @param key
     * @param value
     * @return Integer reply, specifically: 1 if the key was set 0 if the key
     * was not set
     */
    public Long setnx(final String key, final String value) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.setnx(key, value);
            }
        }.getResult();
    }

    public String set(final String key, final String value) {
        return new Executor<String>(jedisPool) {
            @Override
            String execute() {
                return jedis.set(key, value);
            }
        }.getResult();
    }

    public String set(final byte[] key, final byte[] value) {
        return new Executor<String>(jedisPool) {
            @Override
            String execute() {
                return jedis.set(key, value);
            }
        }.getResult();
    }

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 expire (以秒为单位)。 如果 key 已经存在， 将覆写旧值。
     * 类似于以下两个命令: SET key value EXPIRE key expire # 设置生存时间
     * 不同之处是这个方法是一个原子性(atomic)操作，关联值和设置生存时间两个动作会在同一时间内完成，在 Redis 用作缓存时，非常实用。
     * 时间复杂度：O(1)
     *
     * @param key    key
     * @param value  string value
     * @param expire 生命周期
     * @return 设置成功时返回 OK 。当 expire 参数不合法时，返回一个错误。
     */
    public String setex(final String key, final String value, final int expire) {
        return new Executor<String>(jedisPool) {

            @Override
            String execute() {
                return jedis.setex(key, expire, value);
            }
        }.getResult();
    }

    public String setex(final byte[] key, final byte[] value, final int expire) {
        return new Executor<String>(jedisPool) {

            @Override
            String execute() {
                return jedis.setex(key, expire, value);
            }
        }.getResult();
    }

    /**
     * 返回 key 所关联的字符串值。如果 key 不存在那么返回特殊值 nil 。 假如 key 储存的值不是字符串类型，返回一个错误，因为
     * getString 只能用于处理字符串值。 时间复杂度: O(1)
     *
     * @param key key
     * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值。如果 key 不是字符串类型，那么返回一个错误。
     */
    public String get(final String key) {
        return new Executor<String>(jedisPool) {

            @Override
            String execute() {
                return jedis.get(key);

            }
        }.getResult();
    }

    public byte[] get(final byte[] key) {
        return new Executor<byte[]>(jedisPool) {

            @Override
            byte[] execute() {
                return jedis.get(key);

            }
        }.getResult();
    }

    /**
     * 批量的 {@link #set(String, String)}
     *
     * @param pairs 键值对数组{数组第一个元素为key，第二个元素为value}
     * @return 操作状态的集合
     */
    public List<Object> batchSetString(final List<Pair<String, String>> pairs) {
        return new Executor<List<Object>>(jedisPool) {

            @Override
            List<Object> execute() {
                Pipeline pipeline = jedis.pipelined();
                for (Pair<String, String> pair : pairs) {
                    pipeline.set(pair.getKey(), pair.getValue());
                }
                return pipeline.syncAndReturnAll();
            }
        }.getResult();
    }

    /**
     * 批量的 {@link #get(String)}
     *
     * @param keys key数组
     * @return value的集合
     */
    public List<String> batchGetString(final String[] keys) {
        return new Executor<List<String>>(jedisPool) {

            @Override
            List<String> execute() {
                Pipeline pipeline = jedis.pipelined();
                List<String> result = new ArrayList<String>(keys.length);
                List<Response<String>> responses = new ArrayList<Response<String>>(keys.length);
                for (String key : keys) {
                    responses.add(pipeline.get(key));
                }
                pipeline.sync();
                for (Response<String> resp : responses) {
                    result.add(resp.get());
                }
                return result;
            }
        }.getResult();
    }

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 hashSet 操作。 如果域
     * field 已经存在于哈希表中，旧值将被覆盖。 时间复杂度: O(1)
     *
     * @param key   key
     * @param field 域
     * @param value string value
     * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回
     * 0 。
     */
    public Long hset(final String key, final String field, final String value) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.hset(key, field, value);
            }
        }.getResult();
    }

    public Set<String> hkeys(final String key) {
        return new Executor<Set<String>>(jedisPool) {

            @Override
            Set<String> execute() {
                return jedis.hkeys(key);
            }
        }.getResult();
    }
    /*
     * ======================================Hashes=============================
     * = ========
     */


    /**
     * 获取hash       len长度
     *
     * @param key
     * @return
     */
    public Long hgLen(final String key) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.hlen(key);
            }
        }.getResult();
    }


    /**
     * 将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 hashSet 操作。 如果域
     * field 已经存在于哈希表中，旧值将被覆盖。
     *
     * @param key    key
     * @param field  域
     * @param value  string value
     * @param expire 生命周期，单位为秒
     * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回
     * 0 。
     */
    public Long hset(final String key, final String field, final String value, final int expire) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                Pipeline pipeline = jedis.pipelined();
                Response<Long> result = pipeline.hset(key, field, value);
                pipeline.expire(key, expire);
                pipeline.sync();
                return result.get();
            }
        }.getResult();
    }

    /**
     * 返回哈希表 key 中给定域 field 的值。 如果哈希表 key 存在，同时设置这个 key 的生存时间
     *
     * @param key    key
     * @param field  域
     * @param expire 生命周期，单位为秒
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
     */
    public String hget(final String key, final String field, final int expire) {
        return new Executor<String>(jedisPool) {

            @Override
            String execute() {
                Pipeline pipeline = jedis.pipelined();
                Response<String> result = pipeline.hget(key, field);
                pipeline.expire(key, expire);
                pipeline.sync();
                return result.get();
            }
        }.getResult();
    }

    public String hget(final String key, final String field) {
        return new Executor<String>(jedisPool) {

            @Override
            String execute() {
                return jedis.hget(key, field);
            }
        }.getResult();
    }

    public String hmset(final String key, final Map<String, String> hash) {
        return new Executor<String>(jedisPool) {
            @Override
            String execute() {
                return jedis.hmset(key, hash);
            }
        }.getResult();
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。同时设置这个 key 的生存时间
     *
     * @param key    key
     * @param hash   field-value的map
     * @param expire 生命周期，单位为秒
     * @return 如果命令执行成功，返回 OK 。当 key 不是哈希表(hash)类型时，返回一个错误。
     */
    public String hmset(final String key, final Map<String, String> hash, final int expire) {
        return new Executor<String>(jedisPool) {

            @Override
            String execute() {
                Pipeline pipeline = jedis.pipelined();
                Response<String> result = pipeline.hmset(key, hash);
                pipeline.expire(key, expire);
                pipeline.sync();
                return result.get();
            }
        }.getResult();
    }

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。如果给定的域不存在于哈希表，那么返回一个 nil 值。 时间复杂度: O(N)
     * (N为fields的数量)
     *
     * @param key    key
     * @param fields field的数组
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    public List<String> hmget(final String key, final String... fields) {
        return new Executor<List<String>>(jedisPool) {

            @Override
            List<String> execute() {
                return jedis.hmget(key, fields);
            }
        }.getResult();
    }

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。如果给定的域不存在于哈希表，那么返回一个 nil 值。 同时设置这个 key 的生存时间
     *
     * @param key    key
     * @param fields field的数组
     * @param expire 生命周期，单位为秒
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
     */
    public List<String> hmget(final String key, final int expire, final String... fields) {
        return new Executor<List<String>>(jedisPool) {

            @Override
            List<String> execute() {
                Pipeline pipeline = jedis.pipelined();
                Response<List<String>> result = pipeline.hmget(key, fields);
                pipeline.expire(key, expire);
                pipeline.sync();
                return result.get();
            }
        }.getResult();
    }

    /**
     * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field
     * name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。 时间复杂度: O(N)
     *
     * @param key key
     * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。
     */
    public Map<String, String> hgetAll(final String key) {
        return new Executor<Map<String, String>>(jedisPool) {

            @Override
            Map<String, String> execute() {
                return jedis.hgetAll(key);
            }
        }.getResult();
    }

    /**
     * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field
     * name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。 同时设置这个 key 的生存时间
     *
     * @param key    key
     * @param expire 生命周期，单位为秒
     * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。
     */
    public Map<String, String> hgetAll(final String key, final int expire) {
        return new Executor<Map<String, String>>(jedisPool) {

            @Override
            Map<String, String> execute() {
                Pipeline pipeline = jedis.pipelined();
                Response<Map<String, String>> result = pipeline.hgetAll(key);
                pipeline.expire(key, expire);
                pipeline.sync();
                return result.get();
            }
        }.getResult();
    }

    public Boolean hexists(final String key, final String field) {
        return new Executor<Boolean>(jedisPool) {
            @Override
            Boolean execute() {
                return jedis.hexists(key, field);
            }
        }.getResult();
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment 不存在自动创建
     *
     * @param key   key
     * @param field field
     * @param value long
     * @return Long
     */
    public Long hincrBy(final String key, final String field, final long value) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.hincrBy(key, field, value);
            }
        }.getResult();
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     *
     * @param key    key
     * @param values value的数组
     * @return 执行 listPushTail 操作后，表的长度
     */
    public Long listPushTail(final String key, final String... values) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.rpush(key, values);
            }
        }.getResult();
    }

    /*
     * ======================================List===============================
     * = ======
     */

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param key   key
     * @param value string value
     * @return 执行 listPushHead 命令后，列表的长度。
     */
    public Long lpush(final String key, final String value) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.lpush(key, value);
            }
        }.getResult();
    }

    public List<String> rpop(final String key, final int lenth) {
        return new Executor<List<String>>(jedisPool) {

            @Override
            List<String> execute() {
                List<String> list = new ArrayList<String>();

                long allLenth = jedis.llen(key);

                for (int i = 0; i < (allLenth > lenth ? lenth : allLenth); i++) {
                    String item = jedis.rpop(key);
                    if (!StringUtils.isEmpty(item)) {
                        list.add(item);
                    }
                }
                return list;
            }
        }.getResult();
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表头, 当列表大于指定长度是就对列表进行修剪(trim)
     *
     * @param key   key
     * @param value string value
     * @param size  链表超过这个长度就修剪元素
     * @return 执行 listPushHeadAndTrim 命令后，列表的长度。
     */
    public Long lpushHeadAndTrim(final String key, final String value, final long size) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                Pipeline pipeline = jedis.pipelined();
                Response<Long> result = pipeline.lpush(key, value);
                // 修剪列表元素, 如果 size - 1 比 end 下标还要大，Redis将 size 的值设置为 end 。
                pipeline.ltrim(key, 0, size - 1);
                pipeline.sync();
                return result.get();
            }
        }.getResult();
    }

    /**
     * @param key    key
     * @param values value的数组
     * @return null
     */
    public Object updateListInTransaction(final String key, final List<String> values) {
        return new Executor<Object>(jedisPool) {

            @Override
            Object execute() {
                Transaction transaction = jedis.multi();
                transaction.del(key);
                for (String value : values) {
                    transaction.rpush(key, value);
                }
                transaction.exec();
                return null;
            }
        }.getResult();
    }

    /**
     * 一次获得多个链表的数据
     *
     * @param keys key的数组
     * @return 执行结果
     */
    public Map<String, List<String>> batchGetAllList(final List<String> keys) {
        return new Executor<Map<String, List<String>>>(jedisPool) {

            @Override
            Map<String, List<String>> execute() {
                Pipeline pipeline = jedis.pipelined();
                Map<String, List<String>> result = new HashMap<String, List<String>>(keys.size());
                List<Response<List<String>>> responses = new ArrayList<Response<List<String>>>(keys.size());
                for (String key : keys) {
                    responses.add(pipeline.lrange(key, 0, -1));
                }
                pipeline.sync();
                for (int i = 0; i < keys.size(); ++i) {
                    result.put(keys.get(i), responses.get(i).get());
                }
                return result;
            }
        }.getResult();
    }

    /*
     * ======================================Pub/Sub============================
     * == ========
     */

    /**
     * 将信息 message 发送到指定的频道 channel。 时间复杂度：O(N+M)，其中 N 是频道 channel 的订阅者数量，而 M
     * 则是使用模式订阅(subscribed patterns)的客户端的数量。
     *
     * @param channel 频道
     * @param message 信息
     * @return 接收到信息 message 的订阅者数量。
     */
    public Long publish(final String channel, final String message) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.publish(channel, message);
            }

        }.getResult();
    }

    /**
     * 订阅给定的一个频道的信息。
     *
     * @param jedisPubSub 监听器
     * @param channel     频道
     */
    public void subscribe(final JedisPubSub jedisPubSub, final String channel) {
        new Executor<Object>(jedisPool) {

            @Override
            Object execute() {
                // 注意subscribe是一个阻塞操作，因为当前线程要轮询Redis的响应然后调用subscribe
                jedis.subscribe(jedisPubSub, channel);
                return null;
            }
        }.getResult();
    }

    /**
     * 取消订阅
     *
     * @param jedisPubSub 监听器
     */
    public void unsubscribe(final JedisPubSub jedisPubSub) {
        jedisPubSub.unsubscribe();
    }

    /*
     * ======================================Sorted
     * set=================================
     */

    /**
     * 将一个 member 元素及其 score 值加入到有序集 key 当中。
     *
     * @param key    key
     * @param score  score 值可以是整数值或双精度浮点数。
     * @param member 有序集的成员
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public Long addWithSortedSet(final String key, final double score, final String member) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.zadd(key, score, member);
            }
        }.getResult();
    }

    /**
     * 删除sortedset内的member
     *
     * @param key     key
     * @param members 有序集的成员列表
     * @return 删除的个数
     */
    public Long zrem(String key, String... members) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.zrem(key, members);
            }
        }.getResult();
    }

    /**
     * 通过分数返回有序集合指定区间内的成员(包含min,max)
     *
     * @param key key
     * @param min score最小值
     * @param max score最大值
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表
     */
    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return new Executor<Set<String>>(jedisPool) {

            @Override
            Set<String> execute() {
                return jedis.zrangeByScore(key, min, max);
            }
        }.getResult();
    }

    /**
     * 通过分数分页返回有序集合指定区间内的成员(包含min,max)
     *
     * @param key    key
     * @param min    score最小值
     * @param max    score最大值
     * @param offset 起始位
     * @param count  数量
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表
     */
    public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return new Executor<Set<String>>(jedisPool) {

            @Override
            Set<String> execute() {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }
        }.getResult();
    }

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。 有序集成员按
     * score 值递减(从大到小)的次序排列。
     *
     * @param key key
     * @param max score最大值
     * @param min score最小值
     * @return 指定区间内，带有 score 值(可选)的有序集成员的列表
     */
    public Set<String> revrangeByScoreWithSortedSet(final String key, final double max, final double min) {
        return new Executor<Set<String>>(jedisPool) {

            @Override
            Set<String> execute() {
                return jedis.zrevrangeByScore(key, max, min);
            }
        }.getResult();
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return new Executor<Boolean>(jedisPool) {
            @Override
            Boolean execute() {
                return jedis.exists(key);
            }
        }.getResult();
    }

    public boolean exists(final byte[] key) {
        return new Executor<Boolean>(jedisPool) {
            @Override
            Boolean execute() {
                return jedis.exists(key);
            }
        }.getResult();
    }

    /**
     * 构造Pair键值对
     *
     * @param key   key
     * @param value value
     * @return 键值对
     */
    public <K, V> Pair<K, V> makePair(K key, V value) {
        return new Pair<K, V>(key, value);
    }

    /*
     * ======================================Other==============================
     * == ======
     */

    /**
     * 将一个或多个值 value 插入到列表 key 的表头 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头：
     * 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH
     * mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。 如果 key 不存在，一个空列表会被创建并执行
     * LPUSH 操作。
     *
     * @param key
     * @param values
     * @return 执行 LPUSH 操作后，列表的长度
     */
    public Long lpush(final String key, final String... values) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.lpush(key, values);
            }
        }.getResult();
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。 如果有多个 value 值，那么各个 value
     * 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，得出的结果列表为 a b c
     * ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。 如果 key
     * 不存在，一个空列表会被创建并执行 RPUSH 操作。
     *
     * @param key
     * @param values
     * @return 执行 RPUSH 操作后，列表的长度
     */
    public Long rpush(final String key, final String... values) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.rpush(key, values);
            }
        }.getResult();
    }

    public String lindex(final String key, final long index) {
        return new Executor<String>(jedisPool) {
            @Override
            String execute() {
                return jedis.lindex(key, index);
            }
        }.getResult();
    }

    /**
     * 返回列表 key 的长度
     *
     * @param key
     * @return
     */
    public Long llen(final String key) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.llen(key);
            }
        }.getResult();
    }

    /**
     * 返回列表 key 中，下标为 index 的元素。 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0
     * 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。 也可以使用负数下标，以 -1 表示列表的最后一个元素， -2
     * 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param start 开始位置（包含）
     * @param end   结束位置（包含）
     * @return
     */
    public List<String> lrange(final String key, final long start, final long end) {
        return new Executor<List<String>>(jedisPool) {
            @Override
            List<String> execute() {
                return jedis.lrange(key, start, end);
            }
        }.getResult();
    }

    /**
     * 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     *
     * @param key   列表key
     * @param count 个数
     * @param value 值
     * @return 移除的元素个数
     */
    public Long lrem(final String key, final long count, final String value) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.lrem(key, count, value);
            }
        }.getResult();
    }

    /**
     * 执行lua脚本
     *
     * @param script   脚本文本
     * @param keyCount 脚本中keys的个数
     * @param params   脚本中keys的值
     * @return 执行返回结果
     */
    public Object eval(final String script, final int keyCount, final String... params) {
        return new Executor<Object>(jedisPool) {
            @Override
            Object execute() {
                return jedis.eval(script, keyCount, params);
            }
        }.getResult();
    }

    public String ltrim(final String key, final long start, final long end) {
        return new Executor<String>(jedisPool) {
            @Override
            String execute() {
                return jedis.ltrim(key, start, end);
            }
        }.getResult();
    }

    public String cleanList(final String key) {
        return new Executor<String>(jedisPool) {
            @Override
            String execute() {
                return jedis.ltrim(key, 1, 0);
            }
        }.getResult();
    }

    /**
     * 返回指定列表的所有元素
     *
     * @param key
     * @return
     */
    public List<String> listAll(final String key) {
        return lrange(key, 0, -1);
    }

    /**
     * 取列表第一个元素
     *
     * @param key
     * @return
     */
    public String lfirst(final String key) {
        return lrange(key, 0, 0).get(0);
    }

    /**
     * 取列表第最后一个元素
     *
     * @param key
     * @return
     */
    public String lend(final String key) {
        return lrange(key, -1, -1).get(0);
    }

    /**
     * 移除列表中与参数 value 相等的元素
     *
     * @param key
     * @param element 要移除的元素
     * @return 被移除元素的数量
     */
    public Long lremove(final String key, final String element) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.lrem(key, 0, element);
            }
        }.getResult();
    }

    /**
     * 遍历元素
     *
     * @param pattern
     * @param scanCount
     * @return 遍历元素集合
     */
    public List<String> scan(String pattern, int scanCount) {
        return new Executor<List<String>>(jedisPool) {
            @Override
            List<String> execute() {
                // 游标初始值为0
                String cursor = ScanParams.SCAN_POINTER_START;
                ScanParams scanParams = new ScanParams();
                scanParams.match(pattern);// 匹配以pattern为前缀的 key
                scanParams.count(scanCount);
                List<String> retList = new ArrayList<>();
                do {
                    //使用scan命令获取数据，使用cursor游标记录位置，下次循环使用
                    ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
                    cursor = scanResult.getCursor();// 返回0 说明遍历完成
                    List<String> list = scanResult.getResult();
                    retList.addAll(list);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (!ScanParams.SCAN_POINTER_START.equals(cursor));
                //返回匹配列表
                return retList;
            }
        }.getResult();
    }

    /**
     * 判断是否正在被锁
     *
     * @param lockKeyPrefix 锁前缀
     * @param elements      锁元素
     * @return true:至少有一个元素正在被锁定，false:全部都未被锁定
     */
    public boolean isLocked(final String lockKeyPrefix, final Object... elements) {
        return new Executor<Boolean>(jedisPool) {
            @Override
            Boolean execute() {
                for (Object el : elements) {
                    String key = lockKeyPrefix;
                    if (key != null) {
                        key += el;
                    }
                    if (jedis.exists(key)) {
                        return true;
                    }
                }
                return false;
            }
        }.getResult();
    }

    /**
     * 锁
     *
     * @param lockKeyPrefix
     * @param lockSeconds
     * @param value
     * @param elements
     */
    public void lock(final String lockKeyPrefix, final int lockSeconds, final String value, final Object... elements) {
        new Executor<String>(jedisPool) {
            @Override
            String execute() {
                String v = value;
                if (v == null) {
                    v = System.currentTimeMillis() + "";
                }
                for (Object el : elements) {
                    String key = lockKeyPrefix + el;
                    jedis.set(key, v);
                    if (lockSeconds > 0) {
                        jedis.expire(key, lockSeconds);
                    }
                }
                return "";
            }
        }.getResult();
    }

    /**
     * 释放锁
     *
     * @param lockKeyPrefix
     * @param elements
     * @return 释放锁的个数
     */
    public Long unlock(final String lockKeyPrefix, final Object... elements) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                long sum = 0;
                for (Object el : elements) {
                    String key = lockKeyPrefix + el;
                    sum += jedis.del(key);
                }
                return sum;
            }
        }.getResult();
    }

    /**
     * 尝试加锁
     *
     * @param name
     * @param waitTime  最多等待时间(秒)
     * @param leaseTime 上锁以后自动解锁时间(秒)
     * @return
     */
    public Boolean tryLock(String name, int waitTime, int leaseTime) {
        return new Executor<Boolean>(jedisPool) {
            @Override
            Boolean execute() {
                long mils = System.currentTimeMillis();
                String lockKey = name + LOCK_SUFFIX;
                try {
                    while ((System.currentTimeMillis() - mils) < waitTime * 1000) {
                        if (jedis.setnx(lockKey, LOCKED) == 1) {
                            jedis.expire(lockKey, leaseTime);
                            return true;
                        }
                        // 短暂休眠，避免出现活锁 ,减少对redis的压力,防止在大并发情况下把redis压垮
                        Thread.sleep(10, RANDOM.nextInt(500));
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Locking error", e);
                }
                return false;
            }
        }.getResult();
    }


    /**
     * 锁释放
     *
     * @param name
     * @return
     */
    public Long unlock(final String name) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                String lockKey = name + LOCK_SUFFIX;
                return jedis.del(lockKey);
            }
        }.getResult();
    }

    /**
     * @param key
     * @return Integer reply, returns the remaining time to live in seconds of a
     * key that has an EXPIRE. In Redis 2.6 or older, if the Key does
     * not exists or does not have an associated expire, -1 is returned.
     * In Redis 2.8 or newer, if the Key does not have an associated
     * expire, -1 is returned or if the Key does not exists, -2 is
     * returned.
     */
    public Long ttl(final String key) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.ttl(key);
            }
        }.getResult();
    }

    public Long ttl(final byte[] key) {
        return new Executor<Long>(jedisPool) {
            @Override
            Long execute() {
                return jedis.ttl(key);
            }
        }.getResult();
    }

    /*
     * ======================================set==============================
     * == ======
     */

    public Long sadd(final String key, final String value) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.sadd(key, value);
            }
        }.getResult();
    }

    public Long srem(final String key, final String... members) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.srem(key, members);
            }
        }.getResult();
    }

    /**
     * 获取集合的成员数
     *
     * @param key key
     * @return 成员个数
     */
    public Long scard(final String key) {
        return new Executor<Long>(jedisPool) {

            @Override
            Long execute() {
                return jedis.scard(key);
            }
        }.getResult();
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     *
     * @param key    key
     * @param member member
     * @return Boolean
     */
    public Boolean sismember(final String key, String member) {
        return new Executor<Boolean>(jedisPool) {

            @Override
            Boolean execute() {
                return jedis.sismember(key, member);
            }
        }.getResult();
    }

    public Set<String> smembers(final String key) {
        return new Executor<Set<String>>(jedisPool) {

            @Override
            Set<String> execute() {
                return jedis.smembers(key);
            }
        }.getResult();
    }

    // /**
    // * 如果当前类中没有适合的方法，你可以使用当前方法
    // * @param executor 执行器
    // * @param <T>
    // * @return 执行结果
    // */
    // public <T> T execute(Executor<T> executor){
    // return executor.getResult();
    // }

    public abstract class Executor<T> {

        Jedis jedis;
        JedisPool jedisPool;

        public Executor(JedisPool jedisPool) {
            this.jedisPool = jedisPool;
            this.jedis = this.jedisPool.getResource();
        }

        @SuppressWarnings("static-access")
        public Executor() {
            this.jedisPool = JedisClient.jedisPool;
            this.jedis = this.jedisPool.getResource();
        }

        /**
         * 回调
         *
         * @return 执行结果
         */
        abstract T execute();

        /**
         * 调用{@link #execute()}并返回执行结果 它保证在执行{@link #execute()}
         * 之后释放数据源returnResource(jedis)
         *
         * @return 执行结果
         */
        public T getResult() {
            T result = null;
            try {
                result = execute();
            } catch (Throwable e) {
                throw new RuntimeException("Redis execute exception", e);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
            return result;
        }
    }

    /**
     * 键值对
     *
     * @param <K> key
     * @param <V> value
     * @author fengjc
     * @version V1.0
     */
    public class Pair<K, V> {

        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
