package com.xiaozl.learn.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by DK on 2017/11/11.
 */
@Configuration
@Component
public class RedisConfiguration extends CachingConfigurerSupport{
    @Value("${spring.redis.host}")
    public static  final String HOST = null;
    @Value("${spring.redis.password}")
    public static  final  String PASS_WORD= null;
    @Value("${spring.redis.port}")
    public static final  int PROT = 0;
    @Value("${spring.redis.prefix}")
    public static  final String prefix = "";
    

    @Bean
    @ConfigurationProperties(prefix="spring.redis")
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean()
    @ConfigurationProperties(prefix="spring.redis")
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        factory.setHostName(HOST);
        if(!StringUtil.isBlank(PASS_WORD)){
             factory.setPassword(PASS_WORD);
        }
        factory.setPort(PROT);
        return factory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<?, ?> getRedisTemplate(){
        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
        template.setValueSerializer(new JdkSerializationRedisSerializer());//修改redis 默认的序列化类(这个value就可以存所有序列化后的类,默认的只能存string)
        return template;
    }


    //缓存管理器
    @Bean
    public CacheManager cacheManager( RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        cacheManager.setDefaultExpiration(60*30);
        return cacheManager;
    }




//    @Bean(name = "stringRedisTemplate")
//    public StringRedisTemplate getStringRedisTemplate(){
//        StringRedisTemplate template = new StringRedisTemplate(getConnectionFactory());
//        return template;
//    }
}
