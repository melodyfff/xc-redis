package com.xinchen.redis;

import org.junit.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/7/14 23:05
 */
public class ContextTest extends AppTest{

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void hello(){
        System.out.println(redissonClient.getBucket("ok").trySet("ok"));
    }
}
