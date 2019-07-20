package com.xinchen.redis.examples.redisson;

import org.redisson.Redisson;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * Redisson的分布式闭锁（CountDownLatch）
 *
 * @author xinchen
 * @version 1.0
 * @date 20/07/2019 10:57
 */

public class CountDownLatchExamples {
    public static void main(String[] args) throws InterruptedException {

        // connects to 127.0.0.1:6379 by default
        RedissonClient redissonClient = Redisson.create();


        ExecutorService executor = Executors.newFixedThreadPool(2);


        // 获取CountDownLatch
        RCountDownLatch latch = redissonClient.getCountDownLatch("latch1");
        // 初始化值，此时可以在redis中查到
        // 通过PUB/SUB订阅channel , 名字大概为： redisson_countdownlatch__channel__{latch1}
        latch.trySetCount(1);


        // 尝试执行countDown
        executor.execute(latch::countDown);

        executor.execute(() -> {
            try {
                latch.await(550, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        executor.shutdown();

        // Blocks until all tasks have completed execution after a shutdown request,
        // or the timeout occurs, or the current thread is interrupted, whichever happens first.
        executor.awaitTermination(10, TimeUnit.SECONDS);

        // 关闭连接
        redissonClient.shutdown();
    }
}
