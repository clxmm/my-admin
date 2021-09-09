package org.clxmm.myadminjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author clxmm
 * @Description
 * @create 2021-09-05 12:17 下午
 */
@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key序列化方式
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());//value序列化
        redisTemplate.setConnectionFactory(connectionFactory);

        return redisTemplate;
    }

//    @Bean
//    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(600))   //过期时间600秒
//                // 配置序列化
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
//                .disableCachingNullValues();
//        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .build();
//
//        return redisCacheManager;
//    }
}
