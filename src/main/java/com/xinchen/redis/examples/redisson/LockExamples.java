package com.xinchen.redis.examples.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author xinchen
 * @version 1.0
 * @date 20/07/2019 11:42
 */
@Slf4j
public class LockExamples {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 default
        RedissonClient client = Redisson.create();

        // 获取锁
        RLock lock = client.getLock("lock");
        lock.lock(2, TimeUnit.SECONDS);

        Thread t = new Thread(() -> {
            RLock lock1 = client.getLock("lock");
            log.info("try lock");
            lock1.lock();
            lock1.unlock();
            log.info("release lock");
        });

        t.start();
        t.join();


        lock.unlock();
        log.info("release lock");

        client.shutdown();
    }
}
