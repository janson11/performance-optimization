package com.janson.performance.optimization.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 测试RRW读写锁
 * 应用场景：读大于写的并发场景中，然而RRW在性能上还有可提升的空间，在读取很多、写入很少的情况下，RRW会使写入线程
 * 遭遇饥饿（Starvation）问题。也就是说写入线程会因迟迟无法竞争到锁而一直处于等待状态。
 * @Author: Janson
 * @Date: 2020/11/15 15:58
 **/
public class TestRTTLock {

    private double x;
    private double y;
    // 读写锁
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 读锁
    private Lock readLock = lock.readLock();

    // 写锁
    private Lock writeLock = lock.writeLock();

    public double read() {
        //获取读锁
        readLock.lock();
        try {
            return Math.sqrt(x * x + y * y);
        } finally {
            //释放读锁
            readLock.unlock();
        }
    }

    public void move(double deltaX, double deltaY) {
        // 获取写锁
        writeLock.lock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            //释放写锁
            writeLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TestRTTLock testRTTLock = new TestRTTLock();
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(() -> {
            testRTTLock.move(1, 1);
            testRTTLock.move(2, 2);
        });
        service.shutdown();
        Thread.sleep(2000);
        System.out.println("获取" + testRTTLock.read());
    }

}
