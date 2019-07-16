package com.xinchen.redis.runner;

import org.redisson.api.RedissonClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * redisson 启动测试
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/7/17 0:09
 */
@Profile("redisson")
@Component
public class RedissonRunner implements CommandLineRunner {

    private final RedissonClient redissonClient;

    public RedissonRunner(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(redissonClient.getBucket("hello").trySet("hello"));
    }
}
