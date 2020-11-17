package com.janson.performance.optimization.sync;

import java.util.concurrent.locks.StampedLock;

/**
 * @Description: 测试StampedLock
 * @Author: Janson
 * @Date: 2020/11/15 16:45
 **/
public class TestStampedLock {
    private double x;
    private double y;
    private final StampedLock s1 = new StampedLock();

    void move(double deltaX, double deltaY) {
        // 获取写锁
        long stamp = s1.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            // 释放写锁
            s1.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin() {
        // 乐观读操作
        long stamp = s1.tryOptimisticRead();
        // 拷贝变量
        double currentX = x;
        double currentY = y;
        // 判断期间释放有写操作
        if (!s1.validate(stamp)) {
            // 升级为悲观读
            stamp = s1.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

}
