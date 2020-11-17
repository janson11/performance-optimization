package com.janson.performance.optimization.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 单线程类
 * @Author: Janson
 * @Date: 2020/11/17 20:47
 **/
@Slf4j
public class SerialThread extends ThreadContextSwitch {
    @Override
    public void start() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            increaseCounter();
        }
        long end = System.currentTimeMillis();
        log.info("serial exec time: {} ms", (end - start));
        log.info("counter: {}", counter);
    }

}