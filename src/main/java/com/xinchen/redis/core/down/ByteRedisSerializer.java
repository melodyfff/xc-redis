package com.xinchen.redis.core.down;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 自定义 byte类型序列化
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2018/12/8 0:45
 */
public class ByteRedisSerializer implements RedisSerializer<byte[]> {
    private final Charset charset;

    public static final ByteRedisSerializer UTF_8;

    public ByteRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public ByteRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(@Nullable byte[] bytes) throws SerializationException {
        return bytes;
    }

    @Override
    public byte[] deserialize(@Nullable byte[] bytes) throws SerializationException {
        return bytes;
    }

    static {
        UTF_8 = new ByteRedisSerializer(StandardCharsets.UTF_8);
    }

}
