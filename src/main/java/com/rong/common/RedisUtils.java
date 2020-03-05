package com.rong.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 缓存工具
 * @Author: QR
 * @Date: 2020-01-08 09:16
 **/
@Component
public class RedisUtils {
    @Autowired
    private  RedisTemplate<Object,Object> redisTemplate;

    public RedisUtils(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     *  自增
     * @param key 键名
     * @param liveTime 存活时间
     * @return 自增结果
     */
    public  Long incr(String key, Long liveTime){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        long increment = entityIdCounter.getAndIncrement();

        // 防止过期
        // 当过期时间小于10s 重置过期时间
        if (liveTime < Constants.REDIS_EXPIRE) {
            entityIdCounter.expire(liveTime, TimeUnit.DAYS);
        }

        return increment;
    }

    public  Long incr(String key){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        long increment = entityIdCounter.getAndIncrement();
        return increment;
    }
}
