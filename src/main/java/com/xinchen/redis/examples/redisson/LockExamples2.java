package com.xinchen.redis.examples.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * Redssion 分布式锁例子2
 *
 * 可再拷贝一份出来同时运行
 *
 * @author xinchen
 * @version 1.0
 * @date 20/07/2019 11:42
 */
@Slf4j
public class LockExamples2 {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 default
        RedissonClient client = Redisson.create();

        ExecutorService executorService = Executors.newFixedThreadPool(1);


        Runnable task = () -> {
            while (true) {
                log.info(String.format("{%s} try lock.", Thread.currentThread().getName()));


                try {
                    // 等待时间 ， 租用时间 ， 单位
                    if (client.getLock("lock").tryLock(2, 6, TimeUnit.SECONDS)) {
                        log.warn(String.format("{%s} get lock.", Thread.currentThread().getName()));

                        // 处理事物
                        TimeUnit.SECONDS.sleep(5);

                        log.warn(String.format("{%s} running down.", Thread.currentThread().getName()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (client.getLock("lock").isHeldByCurrentThread()) {
                        client.getLock("lock").unlock();
                        log.error(String.format("{%s} release lock.", Thread.currentThread().getName()));
                    }
                }
            }

        };

        for (int i = 0; i < 1; i++) {
            executorService.execute(task);
        }


        Thread.currentThread().join();

        client.shutdown();
        System.out.println("Main over");
    }
}
