package com.funtl.hello.spring.boot.redis;

import com.funtl.hello.spring.boot.util.SpringContextHolder;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @author qy
 * @date 2019/12/27 17:59
 * @description
 */
public class RedisManager {

    private static JedisPool jedisPool = null;
    private static int minute1 = 60; // 设置Key的过期时间，1分钟
    private static int hour1 = minute1 * 60; // 设置Key的过期时间，1小时
    private static int day1 = hour1 * 24; // 设置Key的过期时间，1天
    private static int week1 = day1 * 7; // 1周

    private static JedisPool getJedisPool() {
        if (jedisPool == null) {
            jedisPool = SpringContextHolder.getBean("jedisPool", JedisPool.class);
        }
        return jedisPool;
    }

    public static synchronized Jedis getJedis() {
        return getJedisPool().getResource();
    }

    public static void returnResource(Jedis resource) {
        if (Objects.nonNull(resource)) {
            resource.close();
        }
    }

    // 将哈希表 key 中的字段 field 的值设为 value
    public static void hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.hset(key, field, value);
        } finally {
            returnResource(jedis);
        }
    }

    public static void hset(String key, long id, String value) {
        hset(key, String.valueOf(id), value);
    }

    /**
     * 给hash的一个值+1，用于点击数、分享数等
     */
    public static Long hincr(String key, long id) {
        Jedis jedis = getJedis();
        try {
            return jedis.hincrBy(key, String.valueOf(id), 1);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 给hash的一个值+1，用于点击数、分享数等
     */
    public static Long hincrByString(String key, String id) {
        Jedis jedis = getJedis();
        try {
            return jedis.hincrBy(key, id, 1);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 给hash的一个值+value，用于点击数、分享数等
     */
    public static Long hincr(String key, long id, Long value) {
        Jedis jedis = getJedis();
        try {
            return jedis.hincrBy(key, String.valueOf(id), value);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取存储在哈希表中指定字段【field】的值。
     */
    public static String hget(String key, String field) {
        Jedis jedis = getJedis();
        try {
            if (jedis.hexists(key, field)) {
                return jedis.hget(key, field);
            } else {
                return null;
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * field是整数时，从Redis中取出一个值
     */
    public static String hget(String key, long id) {
        return hget(key, String.valueOf(id));
    }

   // 获取所有哈希表中的字段
    public static Set<String> hkeys(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hkeys(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 删除一个或多个哈希表字段  参数可以多个 field 【见下面方法】
     */
    public static void hclear(String key, String field) {
        Jedis jedis = getJedis();
        try {
            if (jedis.hexists(key, field)) {
                jedis.hdel(key, field);
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 清空一个值
     */
    public static void hclear(String key, long id) {
        hclear(key, String.valueOf(id));
    }

    public static void hclear(String key, String[] field) {
        Jedis jedis = getJedis();
        try {
            jedis.hdel(key, field);
        } finally {
            returnResource(jedis);
        }
    }

    // 获取哈希表中字段的数量
    public static Long hlen(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hlen(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置Key的值，默认保存1小时 需要清理
     */
    public static void setClear(String key, String value) {
        sadd(RedisKey.APP_CLEAR_KEYS_SET, key);
        set(key, value);
    }

    /**
     * 设置Key的值，默认保存1小时
     */
    public static void set(String key, String value) {
        set(key, value, hour1);
    }

    /**
     * 设置Key的值，保存比较长的时间，1天
     */
    public static void setLonger(String key, String value) {
        set(key, value, day1);
    }

    /**
     * 设置Key的值，保存比较长的时间，1天 用于清理
     */
    public static void setLongerClear(String key, String value) {
        sadd(RedisKey.APP_CLEAR_KEYS_SET, key);
        setLonger(key, value);
    }

    /**
     * 设置Key的值，保存1周
     */
    public static void setWeekly(String key, String value) {
        set(key, value, week1);
    }

    /**
     * 设置Key的值，保存1分钟
     */
    public static void setOneMinute(String key, String value) {
        set(key, value, minute1);
    }

    /**
     * 设置Key的值，指定过期时间
     */
    public static void set(String key, String value, int expireTime) {
        Jedis jedis = getJedis();
        try {
            jedis.setex(key, expireTime, value);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置Key的值，无过期时间
     */
    public static void setTimeless(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 从Redis中取出一个值
     */
    public static String get(String key) {
        Jedis jedis = getJedis();
        try {
            if (jedis.exists(key)) {
                return jedis.get(key);
            } else {
                return null;
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 清空一个值
     */
    public static void clear(String key) {
        Jedis jedis = getJedis();
        try {
            if (jedis.exists(key)) {
                jedis.del(key);
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 清空列表keys，一般是多页  以10为一页
     * (redis 存分页列表时 ) 比如 每页10条 ： key = user.list.
     * 第一页 RedisManager.set(key + 10 , list)
     * 第二页 RedisManager.set(key + 20 , list)
     * 清除key 时，直接调用 clearKeys(key)
     */
    public static void clearKeys(String key) {
        if (!key.endsWith(".")) {
            key += ".";
        }
        Jedis jedis = getJedis();
        try {
            int start = 0;
            int count = 10;
            for (int i = 0; i < 20; i++) {
                String key1 = key + start;
                start += count;
                if (jedis.exists(key1)) {
                    jedis.del(key1);
                }
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 彻底清除前缀为key的缓存； 通配符删除 前缀相同的key  【String 类型的】
     *
     * @param key
     */
    public static void clearKeysTotally(String key) {
        if (!key.endsWith(".")) {
            key += ".";
        }
        Jedis jedis = getJedis();
        try {
            clearKeys(key);
            Set<String> keys = jedis.keys(key + "*");
            if (keys.size() > 0) {
                for (String keyElem : keys) {
                    jedis.del(keyElem);
                }
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 彻底清除前缀为key的缓存 清理set
     *
     * @param key
     */
    public static void clearKeysSet(String key) {
        Jedis jedis = getJedis();
        boolean clearFlag = false;
        try {
            //每日key删除执行标志   如果不存在，则又到了删多余key的时候了。
            Long ttl = ttl(RedisKey.CLEAR_KEY_SET_FLAG);
            if (ttl == -1 || ttl == -2) {
                clearFlag = true;
            }
            //  返回set集合中的所有成员
            Set<String> keys = jedis.smembers(RedisKey.APP_CLEAR_KEYS_SET);
            if (keys.size() > 0) {
                for (String keyElem : keys) {
                    if (keyElem.startsWith(key)) {
                        jedis.del(keyElem);
                        jedis.srem(RedisKey.APP_CLEAR_KEYS_SET, keyElem);
                    } else if (clearFlag) {
                        if (!exists(keyElem)) {
                            jedis.srem(RedisKey.APP_CLEAR_KEYS_SET, keyElem);
                        }
                    }
                }
            }
            if (clearFlag) { //1天清一次
                setLonger(RedisKey.CLEAR_KEY_SET_FLAG, "1");
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 清除以key为前缀的所有键,比较慢，建议异步使用
     *  taskExecutor.execute(()->RedisManager.clearKeysByScan(key));
     * @param key
     */
    public static void clearKeysByScan(String key) {
        Jedis jedis = getJedis();
        try {
            String cursor = ScanParams.SCAN_POINTER_START;
            List<String> toBeDel = new ArrayList<>();
            ScanParams params = new ScanParams();
            params.match(key + "*");
            params.count(5000);
            while (true) {
                ScanResult<String> scan = jedis.scan(cursor, params);
                toBeDel.addAll(scan.getResult());
                cursor = scan.getStringCursor();
                if ("0".equals(cursor)) {
                    break;
                }
            }
            if (toBeDel.size() > 0) {
                jedis.del(toBeDel.toArray(new String[0]));
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 清空长列表keys，一个列表按200
     */
    public static void clearLongKeys(String key) {
        if (!key.endsWith(".")) {
            key += ".";
        }

        Jedis jedis = getJedis();
        try {

            int start = 0;
            int count = 200;
            for (int i = 0; i < 5; i++) {
                String key1 = key + start;
                start += count;

                if (jedis.exists(key1)) {
                    jedis.del(key1);
                }
            }
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 判断一个key是否存在
     */
    public static boolean exists(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.exists(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置一个key， 添加一个set
     */
    public static Long sadd(String key, String... member) {
        Jedis jedis = getJedis();
        try {
            return jedis.sadd(key, member);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置一个key， 添加一个set
     */
    public static Long sadd(String key, int seconds, String... member) {
        Jedis jedis = getJedis();
        try {
            long reply = jedis.sadd(key, member);
            jedis.expire(key, seconds);
            return reply;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 判断一个set中的key是否存在
     */
    public static Boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        try {
            return jedis.sismember(key, member);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 返回set集合中的所有成员
     */
    public static Set<String> smembers(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.smembers(key);
        } finally {
            returnResource(jedis);
        }
    }

    // 查看哈希表的指定字段是否存在
    public static Boolean hexists(String key, String field) {
        Jedis jedis = getJedis();
        try {
            return jedis.hexists(key, field);
        } finally {
            returnResource(jedis);
        }
    }

    public static Boolean hexists(String key, long field) {
        return hexists(key, String.valueOf(field));
    }

    //以列表形式返回哈希表的字段及字段值。 若 key 不存在，返回空列表
    public static Map<String, String> hgetAll(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hgetAll(key);
        } finally {
            returnResource(jedis);
        }
    }

   // 同时将多个 field-value (字段-值)对设置到哈希表中
    public static String hmset(String key, Map<String, String> value) {
        Jedis jedis = getJedis();
        try {
            return jedis.hmset(key, value);
        } finally {
            returnResource(jedis);
        }
    }

    public static void hmset(String key, Map<String, String> value, int seconds) {
        Jedis jedis = getJedis();
        try {
            jedis.hmset(key, value);
            jedis.expire(key, seconds);
        } finally {
            returnResource(jedis);
        }
    }

    public static void expire(String key, int seconds) {
        Jedis jedis = getJedis();
        try {
            jedis.expire(key, seconds);
        } finally {
            returnResource(jedis);
        }
    }

    //  返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
    //  其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
    // 以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
    public static List<String> lrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        try {
            return jedis.lrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }

    public static Long rpush(String key, String... strings) {
        Jedis jedis = getJedis();
        try {
            return jedis.rpush(key, strings);
        } finally {
            returnResource(jedis);
        }
    }

    public static void rpush(String key, int expireTime, String... strings) {
        Jedis jedis = getJedis();
        try {
            jedis.rpush(key, strings);
            jedis.expire(key, expireTime);
        } finally {
            returnResource(jedis);
        }
    }

    public static String setex(String key, int seconds, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.setex(key, seconds, value);
        } finally {
            returnResource(jedis);
        }
    }

    public static Boolean setnx(String key, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.setnx(key, value) == 1;
        } finally {
            returnResource(jedis);
        }
    }

    public static Long incr(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.incr(key);
        } finally {
            returnResource(jedis);
        }
    }

    public static Long decr(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.decr(key);
        } finally {
            returnResource(jedis);
        }
    }

    public static Long del(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.del(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置Key的值，指定过期时间
     */
    public static void lpush(String key, int expireTime, String... strings) {
        Jedis jedis = getJedis();
        try {
            jedis.lpush(key, strings);
            jedis.expire(key, expireTime);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 往队列左边入队一个元素
     *
     * @param key
     * @param strings
     */
    public static void lpush(String key, String... strings) {
        Jedis jedis = getJedis();
        try {
            jedis.lpush(key, strings);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 从列左边出队一个元素
     *
     * @param key
     * @return
     */
    public static String lpop(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.lpop(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 从列右边出队一个元素
     * @param key
     * @return
     */
    public static String rpop(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.rpop(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取队列长度
     *
     * @param key
     * @return
     */
    public static Long llen(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.llen(key);
        } finally {
            returnResource(jedis);
        }
    }


    /**
     * 删除key的过期时间
     *
     * @param key
     * @return
     */
    public static Long persist(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.persist(key);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取所有包含某前缀的key的集合
     *
     * @param prefixKey 前缀
     * @return
     */
    public static Set<String> getKeysByPrefix(String prefixKey) {
        if (!prefixKey.endsWith(".")) {
            prefixKey += ".";
        }
        Jedis jedis = getJedis();
        try {
            Set<String> keys = jedis.keys(prefixKey + "*");
            return keys;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 返回set成员长度
     *
     * @param key
     * @return
     */
    public static long scard(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.scard(key);
        } finally {
            returnResource(jedis);
        }
    }

    public static void zadd(String key, double score, String member) {
        Jedis jedis = getJedis();
        try {
            jedis.zadd(key, score, member);
        } finally {
            returnResource(jedis);
        }
    }

    // ZADD KEY_NAME SCORE1 VALUE1.. SCORE2 VALUE2 ..
    public static void zadd(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = getJedis();
        try {
            jedis.zadd(key, scoreMembers);
        } finally {
            returnResource(jedis);
        }
    }

    // 移除有序集合中的一个或多个成员
    public static void zrem(String key, String member) {
        Jedis jedis = getJedis();
        try {
            jedis.zrem(key, member);
        } finally {
            returnResource(jedis);
        }
    }

    // 计算集合中元素的数量。
    public static Long zcard(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.zcard(key);
        } finally {
            returnResource(jedis);
        }
    }

    // 通过索引区间返回有序集合指定区间内的成员 【member】 ，0 表示第一个、-1 表示最后一个、-2 倒数第二个 ..
    public static Set<String> zrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        try {
            return jedis.zrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }

    // 返回有序集中指定区间内的成员，通过索引，分数从高到低 ( Sorted Set 是通过分数【即第二个字段score的值】来进行排序的 )
    public static Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        try {
            return jedis.zrevrange(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }

    // 返回有序集合中指定成员的索引
    public static Long zrank(String key, String member) {
        Jedis jedis = getJedis();
        try {
            return jedis.zrank(key, member);
        } finally {
            returnResource(jedis);
        }
    }

    public static int getMinute1() {
        return minute1;
    }

    public static int getHour1() {
        return hour1;
    }

    public static int getDay1() {
        return day1;
    }

    public static int getWeek1() {
        return week1;
    }

    public static List<byte[]> getBlockList(String key, int timeout) throws Exception {
        Jedis jedis = getJedis();
        try {
// 移出并获取列表的最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
//假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。 反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。 JSON.parseObject(new String(list.get(1), StandardCharsets.UTF_8), EventInfo.class);
            return jedis.brpop(timeout, key.getBytes());
        } finally {
            returnResource(jedis);
        }
    }

    public static List<String> blockRightPop(String key, int timeout){
        Jedis jedis = getJedis();
        try {
            return jedis.brpop(timeout, key);
        } finally {
            returnResource(jedis);
        }
    }

    public static Long setList(String key, String value) {
        Jedis jedis = getJedis();
        try {

            return jedis.lpush(key.getBytes(), value.getBytes());
        } finally {
            returnResource(jedis);
        }
    }

    public static String getSet(String key, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.getSet(key, value);
        } finally {
            returnResource(jedis);
        }
    }

    public static String set(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = getJedis();
        try {
            return jedis.set(key, value, nxxx, expx, time);
        } finally {
            returnResource(jedis);
        }
    }

    public static Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = getJedis();
        try {
            return jedis.eval(script, keys, args);
        } finally {
            returnResource(jedis);
        }
    }

// 命令用于查找所有符合给定模式 pattern 的 key 。。

    /**
     * redis 127.0.0.1:6379> SET runoob1 redis
     * OK
     * redis 127.0.0.1:6379> SET runoob2 mysql
     * OK
     * redis 127.0.0.1:6379> SET runoob3 mongodb
     * OK
     * redis 127.0.0.1:6379> KEYS runoob*
     * 1) "runoob3"
     * 2) "runoob1"
     * 3) "runoob2"
     * <p>
     * 获取 redis 中所有的 key
     * redis 127.0.0.1:6379> KEYS *
     * 1) "runoob3"
     * 2) "runoob1"
     * 3) "runoob2"
     */
    public static Set<String> keys(String pattern) {
        Jedis jedis = getJedis();
        try {
            return jedis.keys(pattern);
        } finally {
            returnResource(jedis);
        }
    }

    // 返回 set 集合中的一个随机元素。
    public static String srandmember(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.srandmember(key);
        } finally {
            returnResource(jedis);
        }
    }

    // 移除集合中的一个或多个成员元素
    public static long srem(String key, String member) {
        Jedis jedis = getJedis();
        try {
            return jedis.srem(key, member);
        } finally {
            returnResource(jedis);
        }
    }


    public static void subscribe(JedisPubSub JedisPubSub, String channel) {
        Jedis jedis = getJedis();
        try {
            jedis.subscribe(JedisPubSub, channel);
        } finally {
            returnResource(jedis);
        }
    }

    // Redis为我们提供了publish/subscribe(发布/订阅)功能。我们可以对某个channel(频道)进行subscribe(订阅)，当有人在这个channel上publish(发布)消息时，redis就会通知我们，这样我们可以收到别人发布的消息。

    public static void publish(String channel, String message) {
        Jedis jedis = getJedis();
        try {
            jedis.publish(channel, message);
        } finally {
            returnResource(jedis);
        }
    }

    // 返回 key 的过期时间 、秒为单位
    // 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。
    public static Long ttl(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.ttl(key);
        } finally {
            returnResource(jedis);
        }
    }

    public static List<String> mget(String... keys) {
        Jedis jedis = getJedis();
        try {
            return jedis.mget(keys);
        } finally {
            returnResource(jedis);
        }
    }

    public static String mset(String... keysvalues) {
        Jedis jedis = getJedis();
        try {
            return jedis.mset(keysvalues);
        } finally {
            returnResource(jedis);
        }
    }

    public static void expireBatch(Set<String> keys, int seconds) {
        Jedis jedis = getJedis();
        try {
            for (String key : keys) {
                jedis.expire(key, seconds);
            }
        } finally {
            returnResource(jedis);
        }
    }

    public static void incrBy(String key, long member) {
        Jedis jedis = getJedis();
        try {
            jedis.incrBy(key, member);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 批量删除
     *
     * @param keys
     * @return
     */
    public static Long del(String... keys) {
        Jedis jedis = getJedis();
        try {
            return jedis.del(keys);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 通过分数返回有序集合指定区间内的成员 从高到低
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        Jedis jedis = getJedis();
        try {
            return jedis.zrevrangeWithScores(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 移除有序集合中给定的分数区间的所有成员
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Long zremrangeByScore(String key, double start, double end) {
        Jedis jedis = getJedis();
        try {
            return jedis.zremrangeByScore(key, start, end);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 通过分数返回有序集合指定区间内的成员
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public static Set<Tuple> zrangeByScore(final String key, final double min, final double max) {
        Jedis jedis = getJedis();
        try {
            return jedis.zrangeByScoreWithScores(key, min, max);
        } finally {
            returnResource(jedis);
        }
    }

}
