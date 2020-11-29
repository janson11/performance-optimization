package com.janson.performance.optimization.design;

/**
 * @Description: 懒汉模式+synchronized 同步锁 +double check
 * @Author: Janson
 * @Date: 2020/11/29 11:08
 **/
public class Singleton2 {

    // 1、不实例化
    private volatile static Singleton2 instance = null;

    private Singleton2() {
        // 构造函数
    }

    // 2、通过该方法想整个系统提供实例
    public static Singleton2 getInstance() {
        // 第一次判断  instance为null时，则实例化对象，否则直接返回对象。
        if (null == instance) {
            synchronized (Singleton2.class) {
                // 第二次判断
                if (null == instance) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
