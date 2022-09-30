package com.internProject.shortly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;


@Configuration
public class RedisScriptConfig {

    @Bean
    public RedisScript<Long> script() {
        Resource scriptSource = new ClassPathResource("redisCounter.lua");
        return RedisScript.of(scriptSource, Long.class);
    }



    @Bean
    public RedisScript<Long> limiterScript() {
        Resource scriptSource = new ClassPathResource("redisLimiter.lua");
        return RedisScript.of(scriptSource, Long.class);
    }
}
