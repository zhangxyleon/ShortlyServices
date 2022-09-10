package com.internProject.shortly.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisSequenceServiceImpl implements RedisSequenceService {

    private RedisAtomicLong atomicLong;

    @Autowired
    @Qualifier("script")
    private RedisScript<Long> script;

    private final RedisTemplate<String,Long> redisPersistenceTemplate;

    @Autowired
    private  RedisTemplate<String,String> redisTemplate;

    @Autowired
    public RedisSequenceServiceImpl(@Qualifier("redisPersistenceTemplate") RedisTemplate<String, Long> redisTemplate) {
        redisPersistenceTemplate = redisTemplate;
        atomicLong = new RedisAtomicLong("counter", redisPersistenceTemplate.getConnectionFactory());
    }

    //increase SequenceId by using RedisAtomicLong.incrementAndGET
    public long getSequenceIdByAtomic() {

        Long sequenceId = atomicLong.incrementAndGet();
        redisPersistenceTemplate.getConnectionFactory().getConnection().bgSave();
        return sequenceId;
    }

    //increase SequenceId by using lua script
    public long getSequenceIdByScript() {

        long sequenceId = redisTemplate.execute(script, List.of("counter"));
        redisTemplate.getConnectionFactory().getConnection().bgSave();
        return  sequenceId;
    }



}
