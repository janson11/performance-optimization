package com.janson.performance.optimization.future;

/**
 * @Description: 查询获取结果实现类
 * @Author: Janson
 * @Date: 2020/12/12 22:39
 **/
public class FutureTask<T> implements Future<T> {
    private T result;
    private boolean isDone = false;
    private final Object LOCK = new Object();

    @Override
    public T get() {
        synchronized (LOCK) {
            while (!isDone) {
                try {
                    // 阻塞等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 获取结果并唤醒阻塞线程
     *
     * @param result
     */
    public void finish(T result) {
        synchronized (LOCK) {
            if (isDone) {
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
