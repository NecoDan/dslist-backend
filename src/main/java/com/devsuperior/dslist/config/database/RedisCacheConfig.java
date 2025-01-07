package com.devsuperior.dslist.config.database;

import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@EnableCaching
@Configuration
@Profile("!test")
@Slf4j
@Generated
public class RedisCacheConfig {

    private static final String PREFIX_CACHE_NAME = "redisdefaultapp_";

    @Bean
    @Primary
    public RedisTemplate<String, Object> customRedisTemplateForObject(LettuceConnectionFactory lettuceConnectionFactory){
        var redisTemplate = new RedisTemplate<String, Object>();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    @Bean
    @Primary
    public CacheManager cacheManager1Minute(RedisConnectionFactory redisConnectionFactory) {
        final var expiration = Duration.ofMinutes(1);
        return this.getCacheManager(redisConnectionFactory, expiration);
    }

    @Bean
    public CacheManager cacheManager2Minutes(RedisConnectionFactory redisConnectionFactory) {
        final var expiration = Duration.ofMinutes(2);
        return this.getCacheManager(redisConnectionFactory, expiration);
    }

    @Bean
    public CacheManager cacheManager5Minutes(RedisConnectionFactory redisConnectionFactory) {
        final var expiration = Duration.ofMinutes(5);
        return this.getCacheManager(redisConnectionFactory, expiration);
    }

    @Bean
    public CacheManager cacheManager10Minutes(RedisConnectionFactory redisConnectionFactory) {
        final var expiration = Duration.ofMinutes(10);
        return this.getCacheManager(redisConnectionFactory, expiration);
    }

    @Bean
    public CacheManager cacheManager15Minutes(RedisConnectionFactory redisConnectionFactory) {
        final var expiration = Duration.ofMinutes(15);
        return this.getCacheManager(redisConnectionFactory, expiration);
    }

    @Bean
    public CacheManager cacheManager30Minutes(RedisConnectionFactory redisConnectionFactory) {
        final var expiration = Duration.ofMinutes(30);
        return this.getCacheManager(redisConnectionFactory, expiration);
    }

    @Bean
    public CacheManager cacheManager1Hour(RedisConnectionFactory redisConnectionFactory) {
        final var expiration = Duration.ofHours(1);
        return this.getCacheManager(redisConnectionFactory, expiration);
    }

    private CacheManager getCacheManager(RedisConnectionFactory redisConnectionFactory,
                                        Duration expirationPeriod) {

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .prefixCacheNameWith(PREFIX_CACHE_NAME)
                        .entryTtl(expirationPeriod))
                .build();
    }
}
