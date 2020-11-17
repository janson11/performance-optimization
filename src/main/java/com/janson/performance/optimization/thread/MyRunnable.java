package com.janson.performance.optimization.thread;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/11/17 20:56
 **/
public class MyRunnable extends ThreadContextSwitch implements Runnable {
    @Override
    public void run() {
        while (counter < 100000000) {
            synchronized (this) {
                if (counter < 100000000) {
                    increaseCounter();
                }
            }
        }
    }

    @Override
    public void start() {
        // do nothing
    }
}
