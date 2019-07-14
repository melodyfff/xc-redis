package com.xinchen.redis.core.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis分布式锁的实现
 *
 * 参考： https://www.cnblogs.com/zuolun2017/p/8028208.html
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/7/14 22:15
 */
@Slf4j
public class RedisLock<T> {

    private volatile boolean locked = false;

    private static final int DEFAULT_ACQUIRE_MILLISECOND = 100;

    /**
     * 锁等待时间
     */
    private int timeoutMillisecond = 10 * 1000;

    /**
     * 锁超时时间
     */
    private int expireMillisecond = 60 * 1000;

    private final RedisTemplate<String, T> redisTemplate;

    /**
     * Lock key path
     */
    private final String lockKey;


    /**
     * Detailed constructor with default acquire timeout 10000 msecs and lock
     *
     * @param redisTemplate RedisTemplate
     * @param lockKey lockKey
     */
    public RedisLock(RedisTemplate<String, T> redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_lock";
    }

    /**
     * Detailed constructor with default lock expiration of 60000 msecs.
     *
     */
    public RedisLock(RedisTemplate<String, T> redisTemplate, String lockKey, int timeoutMillisecond) {
        this(redisTemplate, lockKey);
        this.timeoutMillisecond = timeoutMillisecond;
    }

    public RedisLock(RedisTemplate<String, T> redisTemplate, String lockKey, int timeoutMillisecond, int expireMillisecond) {
        this(redisTemplate, lockKey, timeoutMillisecond);
        this.expireMillisecond = expireMillisecond;
    }


    public String getLockKey() {
        return lockKey;
    }

}

