package com.janson.performance.optimization.sync;

/**
 * @Description: 并发测试类
 * @Author: Janson
 * @Date: 2020/11/1 21:34
 **/
public class SyncTest {

    // 关键字再实例方法上，锁为当前实例
    public synchronized void method1() {
        // code
    }

    // 关键字在代码块上，锁为括号里面的对象
    public void method2() {
        Object o = new Object();
        synchronized (o) {
            // code
        }
    }


}
