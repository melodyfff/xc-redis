package com.xinchen.redis.examples.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;


/**
 *
 *
 * Redisson的分布式信号量例子
 *
 *
 * JUC中的Semaphore使用： reference -> https://www.cnblogs.com/miller-zou/p/6978422.html
 *
 * @author xinchen
 * @version 1.0
 * @date 20/07/2019 11:30
 */
@Slf4j
public class SemaphoreExamples {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 default
        RedissonClient client = Redisson.create();

        RSemaphore semaphore = client.getSemaphore("semaphore");

        // 先清除之前的
        if (semaphore.isExists()){
            semaphore.delete();
        }

        // 尝试设置许可
        semaphore.trySetPermits(5);

        // 获取3个许可
        // 首先判断semaphore内部的数字是否大于0，如果大于0，才能获得许可.
        // 然后用初始值5减去3，线程才会继续执行
        // 如果没有获取到许可（可能已经有5个线程获取到许可，semaphore内部的数字小于尝试获取许可的值）
        // 线程将阻塞直到已经获取到许可的线程release()释放许可，也就是semaphore + 1 ，直到达到尝试获取的许可数量
        semaphore.acquire(3);



        Thread t = new Thread(() -> {
            RSemaphore s = client.getSemaphore("semaphore");
            log.info("try release");
            s.release();

        });

        Thread t2 = new Thread(() -> {
            RSemaphore s = client.getSemaphore("semaphore");
            log.info("try release");
            s.release();

        });


        t.start();
        t2.start();

        semaphore.acquire(4);

        client.shutdown();

    }
}
