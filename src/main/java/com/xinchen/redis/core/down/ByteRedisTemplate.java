package com.xinchen.redis.core.down;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 自定义存储byte类型模板
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2018/12/8 0:59
 */
@Component
@Profile("dev")
public class ByteRedisTemplate extends RedisTemplate<String, byte[]> {

    public ByteRedisTemplate(){
        this.setKeySerializer(RedisSerializer.string());
        this.setValueSerializer(ByteRedisSerializer.UTF_8);
        this.setHashKeySerializer(RedisSerializer.string());
        this.setHashValueSerializer(ByteRedisSerializer.UTF_8);
    }

    /**
     * 此处使用@Autowired是为了自动注入RedisConnectionFactory connectionFactory
     * @param connectionFactory connectionFactory
     */
    @Autowired
    public ByteRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection,
                                                   boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
