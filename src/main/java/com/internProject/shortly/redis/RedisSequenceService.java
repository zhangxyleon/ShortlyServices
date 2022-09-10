package com.internProject.shortly.redis;

import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public interface RedisSequenceService {


    public long getSequenceIdByAtomic();


    public long getSequenceIdByScript();



}
