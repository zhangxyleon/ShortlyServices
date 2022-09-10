package com.internProject.shortly.redis;

import com.internProject.shortly.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void insertUrl(String shortUrl, String longUrl) {
        stringRedisTemplate.opsForValue().set(shortUrl, longUrl);
    }

    @Override
    public String getUrl(String shortUrl) {
        String url = stringRedisTemplate.opsForValue().get(shortUrl);
        return url;
    }

    @Override
    public void deleteUrl(String shortUrl) {
        stringRedisTemplate.delete(shortUrl);
    }

    @Override
    public void setTimeOut(String key, long timeout){
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
}
