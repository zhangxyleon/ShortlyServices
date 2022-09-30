package com.internProject.shortly.RateLimiter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class PermitsBucketRateLimiter implements RateLimiter {

    private final RedisTemplate<String, Serializable> limitRedisTemplate;

    @Autowired
    @Qualifier("limiterScript")
    private RedisScript<Long> redisLimiterScript;

    @Autowired
    public PermitsBucketRateLimiter(RedisTemplate<String, Serializable> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;
    }


    public boolean isRateLimited(String key, RateLimiterVariable rateLimiterVariable) {
        List<String> keys = getKeys(key);
        double permitsPerSecond = rateLimiterVariable.getPermitsPerSecond();
        int capacity = rateLimiterVariable.getPermits();

        int now = Integer.parseInt(String.valueOf(getCurrentTimeStamp()));


        Number count = limitRedisTemplate.execute(
                redisLimiterScript,
                keys,
                permitsPerSecond,
                capacity,
                now,
                1);

        return count.intValue() != 1;
    }

    private static List<String> getKeys(String id) {
        String tokenKey = "rate_limiter.{" + id + "}.tokens";
        String timestampKey = "rate_limiter.{" + id + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

    private static Long getCurrentTimeStamp() {
        Instant instant = Instant.now();
        return instant.getEpochSecond();
    }
}
