package com.janson.performance.optimization.thread;

/**
 * @Description: 线程上下文切换类
 * @Author: Janson
 * @Date: 2020/11/17 20:26
 **/
public abstract class ThreadContextSwitch {
    public static final int count = 100000000;
    public volatile int counter = 0;

    public int getCount() {
        return this.counter;
    }

    public void increaseCounter() {
        this.counter += 1;
    }

    /**
     * 开始方法
     */
    public abstract void start();
}
