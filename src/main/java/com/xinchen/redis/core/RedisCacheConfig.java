//package com.xinchen.redis.core;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * redis bean 配置类
// * Created by xinchen on 2017/6/9.
// */
//@Configuration
//@EnableCaching
//public class RedisCacheConfig extends CachingConfigurerSupport{
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory(){
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//        //默认
//        redisConnectionFactory.setHostName("127.0.0.1");
//        redisConnectionFactory.setPort(6379);
//        return redisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory cf){
//        RedisTemplate<String,String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate){
//        RedisCacheManager cacheManager= new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(3000);// Sets the default expire time (in seconds)
//        return cacheManager;
//    }
//
//}