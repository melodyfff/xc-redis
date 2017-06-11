package com.xinchen.redis.service.impl;

import com.xinchen.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Redis接口实现类
 * ValueOperations：简单K-V操作
 * SetOperations：set类型数据操作
 * ZSetOperations：zset类型数据操作
 * HashOperations：针对map类型的数据操作
 * ListOperations：针对list类型的数据操作
 * Created by xinchen on 2017/6/10.
 */
@Service
//@Transactional(readOnly = true)
public class RedisServiceImpl implements RedisService {
    /**
     * 注入redisTemplate
     */
    @Autowired
    @Qualifier( "redisTemplate" )
    private RedisTemplate redisTemplate;
//    @Resource(name = "redisTemplate")
//    private ValueOperations<String, Object> vOps;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    @Override
    public Long leftList(String key, List<?> value) {
       return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Object leftList(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Long rightList(String key, List<?> value) {
        return redisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public Object rightList(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }
}
