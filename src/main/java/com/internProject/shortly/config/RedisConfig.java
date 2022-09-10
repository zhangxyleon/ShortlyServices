package com.internProject.shortly.config;

import com.internProject.shortly.entity.Url;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * create a lettuceConnectionFactory for redisTemplate {@link
    # redisPersistenceTemplate()}
     * @return LettuceConnectionFactory, the old version use Jedis
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return  new LettuceConnectionFactory();
    }

    /**
     * Description: aiming to create a template for String and Long
     * @return redisTemplate<String,Long>
     */
    @Bean(name="redisPersistenceTemplate")
    public RedisTemplate<String, Long> redisPersistenceTemplate(){

        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer( new GenericToStringSerializer< Long >( Long.class ) );
        template.setHashValueSerializer( new GenericToStringSerializer< Long >( Long.class ) );
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;

    }


}
