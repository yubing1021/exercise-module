package com.darben.execdatareactiveredis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description: redis config
 * @author: darben
 * @create: 2020-03-13 15:43
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String,Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory){

        RedisSerializationContext<String,Object> serializationContext = RedisSerializationContext
                .<String,Object>newSerializationContext(new Jackson2JsonRedisSerializer<Object>(Object.class))
                .hashKey(new StringRedisSerializer())
                .hashValue(new Jackson2JsonRedisSerializer<Object>(Object.class))
                .build();

        ReactiveRedisTemplate<String,Object> reactiveRedisTemplate =new ReactiveRedisTemplate<>(factory,serializationContext);
        return reactiveRedisTemplate;
    }

}
