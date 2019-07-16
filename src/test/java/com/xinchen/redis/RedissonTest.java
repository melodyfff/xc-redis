package com.xinchen.redis;

import org.junit.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * 在此单元测试中关闭的时候，netty会报错连接中断
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/7/16 23:51
 */
@ActiveProfiles("redisson")
public class RedissonTest extends AppTest{


    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void hello() throws InterruptedException {
        System.out.println(redissonClient.getBucket("ok").trySet("ok"));


    }
}
