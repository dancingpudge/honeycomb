<table>
    <tbody>
    <tr>
        <td><strong>Redis命令</strong></td>
        <td><strong>Redisson对象方法</strong></td>
    </tr>
    <tr>
        <td>AUTH</td>
        <td>Config.setPassword();</td>
    </tr>
    <tr>
        <td>BITCOUNT</td>
        <td>RBitSet.cardinality(), RBitSet.cardinalityAsync(), RBitSetReactive.cardinality()</td>
    </tr>
    <tr>
        <td>BITOP</td>
        <td>RBitSet.or(), RBitSet.orAsync(), RBitSetReactive.or();<br> RBitSet.and(), RBitSet.andAsync(),
            RBitSetReactive.and();<br> RBitSet.not();<br> RBitSet.xor(), RBitSet.xorAsync(), RBitSetReactive.xor()
        </td>
    </tr>
    <tr>
        <td>BITPOS</td>
        <td>RBitSet.length(), RBitSet.lengthAsync(), RBitSetReactive.length()</td>
    </tr>
    <tr>
        <td>BLPOP</td>
        <td>RBlockingQueue.take(), RBlockingQueue.takeAsync(), RBlockingQueueReactive.take();<br>
            RBlockingQueue.poll(),
            RBlockingQueue.pollAsync(), RBlockingQueueReactive.poll();<br> RBlockingQueue.pollFromAny(),
            RBlockingQueue.pollFromAnyAsync(), RBlockingQueueReactive.pollFromAny();
        </td>
    </tr>
    <tr>
        <td>BRPOP</td>
        <td>RBlockingDeque.takeLast(), RBlockingDeque.takeLastAsync(), RBlockingDequeReactive.takeLast();</td>
    </tr>
    <tr>
        <td>BRPOPLPUSH</td>
        <td>RBlockingQueue.pollLastAndOfferFirstTo(), RBlockingQueue.pollLastAndOfferFirstToAsync(),
            RBlockingQueueReactive.pollLastAndOfferFirstTo();
        </td>
    </tr>
    <tr>
        <td>CLIENT SETNAME</td>
        <td>Config.setClientName();</td>
    </tr>
    <tr>
        <td>CLUSTER INFO</td>
        <td>ClusterNode.info();</td>
    </tr>
    <tr>
        <td>CLUSTER KEYSLOT</td>
        <td>RKeys.getSlot(), RKeys.getSlotAsync(), RKeysReactive.getSlot();</td>
    </tr>
    <tr>
        <td>CLUSTER NODES</td>
        <td>Used in ClusterConnectionManager</td>
    </tr>
    <tr>
        <td>DBSIZE</td>
        <td>RKeys.count(), RKeys.countAsync(), RKeysReactive.count();</td>
    </tr>
    <tr>
        <td>DECR</td>
        <td>RAtomicLong.decrementAndGet(), RAtomicLong.decrementAndGetAsync(),
            RAtomicLongReactive.decrementAndGetAsync();
        </td>
    </tr>
    <tr>
        <td>DEL</td>
        <td>RObject.delete(), RObject.deleteAsync(), RObjectReactive.delete();<br> RKeys.delete(),
            RKeys.deleteAsync();
        </td>
    </tr>
    <tr>
        <td>STRLEN</td>
        <td>RBucket.size(), RBucket.sizeAsync(), RBucketReactive.size();</td>
    </tr>
    <tr>
        <td>EVAL</td>
        <td>RScript.eval(), RScript.evalAsync(), RScriptReactive.eval();</td>
    </tr>
    <tr>
        <td>CLIENT REPLY</td>
        <td>RBatch.executeSkipResult();</td>
    </tr>
    <tr>
        <td>EVALSHA</td>
        <td>RScript.evalSha(), RScript.evalShaAsync(), RScriptReactive.evalSha();</td>
    </tr>
    <tr>
        <td>EXISTS</td>
        <td>RObject.isExists(), RObject.isExistsAsync(), RObjectReactive.isExists();</td>
    </tr>
    <tr>
        <td>FLUSHALL</td>
        <td>RKeys.flushall(), RKeys.flushallAsync(), RKeysReactive.flushall();</td>
    </tr>
    <tr>
        <td>FLUSHDB</td>
        <td>RKeys.flushdb(), RKeys.flushdbAsync(), RKeysReactive.flushdb();</td>
    </tr>
    <tr>
        <td>GEOADD</td>
        <td>RGeo.add(), RGeo.addAsync(), RGeoReactive.add();</td>
    </tr>
    <tr>
        <td>GEODIST</td>
        <td>RGeo.dist(), RGeo.distAsync(), RGeoReactive.dist();</td>
    </tr>
    <tr>
        <td>GEOHASH</td>
        <td>RGeo.hash(), RGeo.hashAsync(), RGeoReactive.hash();</td>
    </tr>
    <tr>
        <td>GEOPOS</td>
        <td>RGeo.pos(), RGeo.posAsync(), RGeoReactive.pos();</td>
    </tr>
    <tr>
        <td>GEORADIUS</td>
        <td>RGeo.radius(), RGeo.radiusAsync(), RGeoReactive.radius();<br> RGeo.radiusWithDistance(),
            RGeo.radiusWithDistanceAsync(), RGeoReactive.radiusWithDistance();<br> RGeo.radiusWithPosition(),
            RGeo.radiusWithPositionAsync(), RGeoReactive.radiusWithPosition();
        </td>
    </tr>
    <tr>
        <td>GEORADIUSBYMEMBER</td>
        <td>RGeo.radius(), RGeo.radiusAsync(), RGeoReactive.radius();<br> RGeo.radiusWithDistance(),
            RGeo.radiusWithDistanceAsync(), RGeoReactive.radiusWithDistance();<br> RGeo.radiusWithPosition(),
            RGeo.radiusWithPositionAsync(), RGeoReactive.radiusWithPosition();
        </td>
    </tr>
    <tr>
        <td>GET</td>
        <td>RBucket.get(), RBucket.getAsync(), RBucketReactive.get();</td>
    </tr>
    <tr>
        <td>GETBIT</td>
        <td>RBitSet.get(), RBitSet.getAsync(), RBitSetReactive.get();</td>
    </tr>
    <tr>
        <td>GETSET</td>
        <td>RBucket.getAndSet(), RBucket.getAndSetAsync(), RBucketReactive.getAndSet();<br> RAtomicLong.getAndSet(),
            RAtomicLong.getAndSetAsync(), RAtomicLongReactive.getAndSet();<br> RAtomicDouble.getAndSet(),
            RAtomicDouble.getAndSetAsync(), RAtomicDoubleReactive.getAndSet();
        </td>
    </tr>
    <tr>
        <td>HDEL</td>
        <td>RMap.fastRemove(), RMap.fastRemoveAsync(), RMapReactive.fastRemove();</td>
    </tr>
    <tr>
        <td>HEXISTS</td>
        <td>RMap.containsKey(), RMap.containsKeyAsync(), RMapReactive.containsKey();</td>
    </tr>
    <tr>
        <td>HGET</td>
        <td>RMap.get(), RMap.getAsync(), RMapReactive.get();</td>
    </tr>
    <tr>
        <td>HSTRLEN</td>
        <td>RMap.valueSize(), RMap.valueSizeAsync(), RMapReactive.valueSize();</td>
    </tr>
    <tr>
        <td>HGETALL</td>
        <td>RMap.readAllEntrySet(), RMap.readAllEntrySetAsync(), RMapReactive.readAllEntrySet();</td>
    </tr>
    <tr>
        <td>HINCRBY</td>
        <td>RMap.addAndGet(), RMap.addAndGetAsync(), RMapReactive.addAndGet();</td>
    </tr>
    <tr>
        <td>HINCRBYFLOAT</td>
        <td>RMap.addAndGet(), RMap.addAndGetAsync(), RMapReactive.addAndGet();</td>
    </tr>
    <tr>
        <td>HKEYS</td>
        <td>RMap.readAllKeySet(), RMap.readAllKeySetAsync(), RMapReactive.readAllKeySet();</td>
    </tr>
    <tr>
        <td>HLEN</td>
        <td>RMap.size(), RMap.sizeAsync(), RMapReactive.size();</td>
    </tr>
    <tr>
        <td>HMGET</td>
        <td>RMap.getAll(), RMap.getAllAsync(), RMapReactive.getAll();</td>
    </tr>
    <tr>
        <td>HMSET</td>
        <td>RMap.putAll(), RMap.putAllAsync(), RMapReactive.putAll();</td>
    </tr>
    <tr>
        <td>HSET</td>
        <td>RMap.put(), RMap.putAsync(), RMapReactive.put();</td>
    </tr>
    <tr>
        <td>HSETNX</td>
        <td>RMap.fastPutIfAbsent(), RMap.fastPutIfAbsentAsync, RMapReactive.fastPutIfAbsent();</td>
    </tr>
    <tr>
        <td>HVALS</td>
        <td>RMap.readAllValues(), RMap.readAllValuesAsync(), RMapReactive.readAllValues();</td>
    </tr>
    <tr>
        <td>INCR</td>
        <td>RAtomicLong.incrementAndGet(), RAtomicLong.incrementAndGetAsync(),
            RAtomicLongReactive.incrementAndGet();
        </td>
    </tr>
    <tr>
        <td>INCRBY</td>
        <td>RAtomicLong.addAndGet(), RAtomicLong.addAndGetAsync(), RAtomicLongReactive.addAndGet();</td>
    </tr>
    <tr>
        <td>KEYS</td>
        <td>RKeys.findKeysByPattern(), RKeys.findKeysByPatternAsync(), RKeysReactive.findKeysByPattern();<br>
            RedissonClient.findBuckets();
        </td>
    </tr>
    <tr>
        <td>LINDEX</td>
        <td>RList.get(), RList.getAsync(), RListReactive.get();</td>
    </tr>
    <tr>
        <td>LLEN</td>
        <td>RList.size(), RList.sizeAsync(), RListReactive.Size();</td>
    </tr>
    <tr>
        <td>LPOP</td>
        <td>RQueue.poll(), RQueue.pollAsync(), RQueueReactive.poll();</td>
    </tr>
    <tr>
        <td>LPUSH</td>
        <td>RDeque.addFirst(), RDeque.addFirstAsync();<br> RDequeReactive.addFirst(), RDeque.offerFirst(),
            RDeque.offerFirstAsync(), RDequeReactive.offerFirst();
        </td>
    </tr>
    <tr>
        <td>LRANGE</td>
        <td>RList.readAll(), RList.readAllAsync(), RListReactive.readAll();</td>
    </tr>
    <tr>
        <td>LREM</td>
        <td>RList.fastRemove(), RList.fastRemoveAsync(), RList.remove(), RList.removeAsync(),
            RListReactive.remove();<br> RDeque.removeFirstOccurrence(), RDeque.removeFirstOccurrenceAsync(),
            RDequeReactive.removeFirstOccurrence();<br> RDeque.removeLastOccurrence(),
            RDeque.removeLastOccurrenceAsync(), RDequeReactive.removeLastOccurrence();
        </td>
    </tr>
    <tr>
        <td>LSET</td>
        <td>RList.fastSet(), RList.fastSetAsync(), RListReactive.fastSet();</td>
    </tr>
    <tr>
        <td>LTRIM</td>
        <td>RList.trim(), RList.trimAsync(), RListReactive.trim();</td>
    </tr>
    <tr>
        <td>LINSERT</td>
        <td>RList.addBefore(), RList.addBeforeAsync(), RList.addAfter(), RList.addAfterAsync(),
            RListReactive.addBefore(), RListReactive.addAfter();
        </td>
    </tr>
    <tr>
        <td>MGET</td>
        <td>RedissonClient.loadBucketValues();</td>
    </tr>
    <tr>
        <td>MIGRATE</td>
        <td>RObject.migrate(), RObject.migrateAsync();</td>
    </tr>
    <tr>
        <td>MOVE</td>
        <td>RObject.move(), RObject.moveAsync();</td>
    </tr>
    <tr>
        <td>MSET</td>
        <td>RedissonClient.saveBuckets();</td>
    </tr>
    <tr>
        <td>PERSIST</td>
        <td>RExpirable.clearExpire(), RExpirable.clearExpireAsync(), RExpirableReactive.clearExpire();</td>
    </tr>
    <tr>
        <td>PEXPIRE</td>
        <td>RExpirable.expire(), RExpirable.expireAsync(), RExpirableReactive.expire();</td>
    </tr>
    <tr>
        <td>PEXPIREAT</td>
        <td>RExpirable.expireAt(), RExpirable.expireAtAsync(), RExpirableReactive.expireAt();</td>
    </tr>
    <tr>
        <td>PFADD</td>
        <td>RHyperLogLog.add(), RHyperLogLog.addAsync(), RHyperLogLogReactive.add();<br> RHyperLogLog.addAll(),
            RHyperLogLog.addAllAsync(), RHyperLogLogReactive.addAll();
        </td>
    </tr>
    <tr>
        <td>PFCOUNT</td>
        <td>RHyperLogLog.count(), RHyperLogLog.countAsync(), RHyperLogLogReactive.count();<br>
            RHyperLogLog.countWith(),
            RHyperLogLog.countWithAsync(), RHyperLogLogReactive.countWith();
        </td>
    </tr>
    <tr>
        <td>PFMERGE</td>
        <td>RHyperLogLog.mergeWith(), RHyperLogLog.mergeWithAsync(), RHyperLogLogReactive.mergeWith();</td>
    </tr>
    <tr>
        <td>PING</td>
        <td>Node.ping(); NodesGroup.pingAll();</td>
    </tr>
    <tr>
        <td>PSUBSCRIBE</td>
        <td>RPatternTopic.addListener();</td>
    </tr>
    <tr>
        <td>PTTL</td>
        <td>RExpirable.remainTimeToLive(), RExpirable.remainTimeToLiveAsync(),
            RExpirableReactive.remainTimeToLive();
        </td>
    </tr>
    <tr>
        <td>PUBLISH</td>
        <td>RTopic.publish</td>
    </tr>
    <tr>
        <td>PUNSUBSCRIBE</td>
        <td>RPatternTopic.removeListener();</td>
    </tr>
    <tr>
        <td>RANDOMKEY</td>
        <td>RKeys.randomKey(), RKeys.randomKeyAsync(), RKeysReactive.randomKey();</td>
    </tr>
    <tr>
        <td>RENAME</td>
        <td>RObject.rename(), RObject.renameAsync(), RObjectReactive.rename();</td>
    </tr>
    <tr>
        <td>RENAMENX</td>
        <td>RObject.renamenx(), RObject.renamenxAsync(), RObjectReactive.renamenx();</td>
    </tr>
    <tr>
        <td>RPOP</td>
        <td>RDeque.pollLast(), RDeque.pollLastAsync(), RDequeReactive.pollLast();<br> RDeque.removeLast(),
            RDeque.removeLastAsync(), RDequeReactive.removeLast();
        </td>
    </tr>
    <tr>
        <td>RPOPLPUSH</td>
        <td>RDeque.pollLastAndOfferFirstTo(), RDeque.pollLastAndOfferFirstToAsync();</td>
    </tr>
    <tr>
        <td>RPUSH</td>
        <td>RList.add(), RList.addAsync(), RListReactive.add();</td>
    </tr>
    <tr>
        <td>SADD</td>
        <td>RSet.add(), RSet.addAsync(), RSetReactive.add();</td>
    </tr>
    <tr>
        <td>SCARD</td>
        <td>RSet.size(), RSet.sizeAsync(), RSetReactive.size();</td>
    </tr>
    <tr>
        <td>SCRIPT EXISTS</td>
        <td>RScript.scriptExists(), RScript.scriptExistsAsync(), RScriptReactive.scriptExists();</td>
    </tr>
    <tr>
        <td>SCRIPT FLUSH</td>
        <td>RScript.scriptFlush(), RScript.scriptFlushAsync(), RScriptReactive.scriptFlush();</td>
    </tr>
    <tr>
        <td>SCRIPT KILL</td>
        <td>RScript.scriptKill(), RScript.scriptKillAsync(), RScriptReactive.scriptKill();</td>
    </tr>
    <tr>
        <td>SCRIPT LOAD</td>
        <td>RScript.scriptLoad(), RScript.scriptLoadAsync(), RScriptReactive.scriptLoad();</td>
    </tr>
    <tr>
        <td>SDIFFSTORE</td>
        <td>RSet.diff(), RSet.diffAsync(), RSetReactive.diff();</td>
    </tr>
    <tr>
        <td>SELECT</td>
        <td>Config.setDatabase();</td>
    </tr>
    <tr>
        <td>SET</td>
        <td>RBucket.set(); RBucket.setAsync(); RBucketReactive.set();</td>
    </tr>
    <tr>
        <td>SETBIT</td>
        <td>RBitSet.set(); RBitSet.setAsync(); RBitSet.clear(); RBitSet.clearAsync();</td>
    </tr>
    <tr>
        <td>SETEX</td>
        <td>RBucket.set(); RBucket.setAsync(); RBucketReactive.set();</td>
    </tr>
    <tr>
        <td>SETNX</td>
        <td>RBucket.trySet(); RBucket.trySetAsync(); RBucketReactive.trySet();</td>
    </tr>
    <tr>
        <td>SISMEMBER</td>
        <td>RSet.contains(), RSet.containsAsync(), RSetReactive.contains();</td>
    </tr>
    <tr>
        <td>SINTERSTORE</td>
        <td>RSet.intersection(), RSet.intersectionAsync(), RSetReactive.intersection();</td>
    </tr>
    <tr>
        <td>SINTER</td>
        <td>RSet.readIntersection(), RSet.readIntersectionAsync(), RSetReactive.readIntersection();</td>
    </tr>
    <tr>
        <td>SMEMBERS</td>
        <td>RSet.readAll(), RSet.readAllAsync(), RSetReactive.readAll();</td>
    </tr>
    <tr>
        <td>SMOVE</td>
        <td>RSet.move(), RSet.moveAsync(), RSetReactive.move();</td>
    </tr>
    <tr>
        <td>SPOP</td>
        <td>RSet.removeRandom(), RSet.removeRandomAsync(), RSetReactive.removeRandom();</td>
    </tr>
    <tr>
        <td>SREM</td>
        <td>RSet.remove(), RSet.removeAsync(), RSetReactive.remove();</td>
    </tr>
    <tr>
        <td>SUBSCRIBE</td>
        <td>RTopic.addListener(), RTopicReactive.addListener();</td>
    </tr>
    <tr>
        <td>SUNION</td>
        <td>RSet.readUnion(), RSet.readUnionAsync(), RSetReactive.readUnion();</td>
    </tr>
    <tr>
        <td>SUNIONSTORE</td>
        <td>RSet.union(), RSet.unionAsync(), RSetReactive.union();</td>
    </tr>
    <tr>
        <td>TTL</td>
        <td>RExpirable.remainTimeToLive(), RExpirable.remainTimeToLiveAsync(),
            RExpirableReactive.remainTimeToLive();
        </td>
    </tr>
    <tr>
        <td>UNSUBSCRIBE</td>
        <td>RTopic.removeListener(), RTopicReactive.removeListener();</td>
    </tr>
    <tr>
        <td>ZADD</td>
        <td>RScoredSortedSet.add(), RScoredSortedSet.addAsync(), RScoredSortedSetReactive.add();</td>
    </tr>
    <tr>
        <td>ZCARD</td>
        <td>RScoredSortedSet.size(), RScoredSortedSet.sizeAsync(), RScoredSortedSetReactive.size();</td>
    </tr>
    <tr>
        <td>ZINCRBY</td>
        <td>RScoredSortedSet.addScore(), RScoredSortedSet.addScoreAsync(), RScoredSortedSetReactive.addScore();</td>
    </tr>
    <tr>
        <td>ZLEXCOUNT</td>
        <td>RLexSortedSet.lexCount(), RLexSortedSet.lexCountAsync(), RLexSortedSetReactive.lexCount();&nbsp;<br>
            RLexSortedSet.lexCountHead(), RLexSortedSet.lexCountHeadAsync(),
            RLexSortedSetReactive.lexCountHead();<br>
            RLexSortedSet.lexCountTail(), RLexSortedSet.lexCountTailAsync(), RLexSortedSetReactive.lexCountTail();
        </td>
    </tr>
    <tr>
        <td>ZRANGE</td>
        <td>RScoredSortedSet.valueRange(), RScoredSortedSet.valueRangeAsync(),
            RScoredSortedSetReactive.valueRange();
        </td>
    </tr>
    <tr>
        <td>ZREVRANGE</td>
        <td>RScoredSortedSet.valueRangeReversed(), RScoredSortedSet.valueRangeReversedAsync(),
            RScoredSortedSetReactive.valueRangeReversed();
        </td>
    </tr>
    <tr>
        <td>ZUNIONSTORE</td>
        <td>RScoredSortedSet.union(), RScoredSortedSet.unionAsync(), RScoredSortedSetReactive.union();</td>
    </tr>
    <tr>
        <td>ZINTERSTORE</td>
        <td>RScoredSortedSet.intersection(), RScoredSortedSet.intersectionAsync(),
            RScoredSortedSetReactive.intersection();
        </td>
    </tr>
    <tr>
        <td>ZRANGEBYLEX</td>
        <td>RLexSortedSet.lexRange(), RLexSortedSet.lexRangeAsync(), RLexSortedSetReactive.lexRange();&nbsp;<br>
            RLexSortedSet.lexRangeHead(), RLexSortedSet.lexRangeHeadAsync(),
            RLexSortedSetReactive.lexRangeHead();<br>
            RLexSortedSet.lexRangeTail(), RLexSortedSet.lexRangeTailAsync(), RLexSortedSetReactive.lexRangeTail();
        </td>
    </tr>
    <tr>
        <td>ZRANGEBYSCORE</td>
        <td>RScoredSortedSet.valueRange(), RScoredSortedSet.valueRangeAsync(),
            RScoredSortedSetReactive.valueRange();&nbsp;<br>
            RScoredSortedSet.entryRange(), RScoredSortedSet.entryRangeAsync(),
            RScoredSortedSetReactive.entryRange();
        </td>
    </tr>
    <tr>
        <td>TIME</td>
        <td>Node.time();</td>
    </tr>
    <tr>
        <td>ZRANK</td>
        <td>RScoredSortedSet.rank(), RScoredSortedSet.rankAsync(), RScoredSortedSetReactive.rank();</td>
    </tr>
    <tr>
        <td>ZREM</td>
        <td>RScoredSortedSet.remove(), RScoredSortedSet.removeAsync(), RScoredSortedSetReactive.remove();<br>
            RScoredSortedSet.removeAll(), RScoredSortedSet.removeAllAsync(), RScoredSortedSetReactive.removeAll();
        </td>
    </tr>
    <tr>
        <td>ZREMRANGEBYLEX</td>
        <td>RLexSortedSet.removeRangeByLex(), RLexSortedSet.removeRangeByLexAsync(),
            RLexSortedSetReactive.removeRangeByLex();&nbsp;<br> RLexSortedSet.removeRangeHeadByLex(),
            RLexSortedSet.removeRangeHeadByLexAsync(), RLexSortedSetReactive.removeRangeHeadByLex();<br>
            RLexSortedSet.removeRangeTailByLex(), RLexSortedSet.removeRangeTailByLexAsync(),
            RLexSortedSetReactive.removeRangeTailByLex();
        </td>
    </tr>
    <tr>
        <td>ZREMRANGEBYLEX</td>
        <td>RScoredSortedSet.removeRangeByRank(), RScoredSortedSet.removeRangeByRankAsync(),
            RScoredSortedSetReactive.removeRangeByRank();
        </td>
    </tr>
    <tr>
        <td>ZREMRANGEBYSCORE</td>
        <td>RScoredSortedSet.removeRangeByScore(), RScoredSortedSet.removeRangeByScoreAsync(),
            RScoredSortedSetReactive.removeRangeByScore();
        </td>
    </tr>
    <tr>
        <td>ZREVRANGEBYSCORE</td>
        <td>RScoredSortedSet.entryRangeReversed(), RScoredSortedSet.entryRangeReversedAsync(),
            RScoredSortedSetReactive.entryRangeReversed(), RScoredSortedSet.valueRangeReversed(),
            RScoredSortedSet.valueRangeReversedAsync(), RScoredSortedSetReactive.valueRangeReversed();
        </td>
    </tr>
    <tr>
        <td>ZREVRANK</td>
        <td>RScoredSortedSet.revRank(), RScoredSortedSet.revRankAsync(), RScoredSortedSetReactive.revRank();</td>
    </tr>
    <tr>
        <td>ZSCORE</td>
        <td>RScoredSortedSet.getScore(), RScoredSortedSet.getScoreAsync(), RScoredSortedSetReactive.getScore();</td>
    </tr>
    <tr>
        <td>SCAN</td>
        <td>RKeys.getKeys(), RKeysReactive.getKeys();</td>
    </tr>
    <tr>
        <td>SSCAN</td>
        <td>RSet.iterator(), RSetReactive.iterator();</td>
    </tr>
    <tr>
        <td>HSCAN</td>
        <td>RMap.keySet().iterator(), RMap.values().iterator(), RMap.entrySet().iterator(),
            RMapReactive.keyIterator(),
            RMapReactive.valueIterator(), RMapReactive.entryIterator();
        </td>
    </tr>
    <tr>
        <td>ZSCAN</td>
        <td>RScoredSortedSet.iterator(), RScoredSortedSetReactive.iterator();</td>
    </tr>
    </tbody>
</table>