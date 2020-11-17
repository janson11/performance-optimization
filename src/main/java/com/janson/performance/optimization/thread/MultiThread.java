package com.janson.performance.optimization.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 多线程类
 * @Author: Janson
 * @Date: 2020/11/17 20:54
 **/
@Slf4j
public class MultiThread extends ThreadContextSwitch {
    @Override
    public void start() {
        long start = System.currentTimeMillis();
        MyRunnable myRunnable1 = new MyRunnable();
        Thread[] threads = new Thread[4];
        // 创建多个线程
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(myRunnable1);
            threads[i].start();
        }

        for (int i = 0; i < 4; i++) {
            try {
                // 等待一起执行完
                threads[i].join();
            } catch (InterruptedException e) {
                log.error("error", e);
            }
        }
        long end = System.currentTimeMillis();
        log.info("multi exec time: {} ms", (end - start));
        log.info("counter:{}", counter);
    }
}
