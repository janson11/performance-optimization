package com.janson.performance.optimization.design;

/**
 * @Description: 懒汉模式
 * @Author: Janson
 * @Date: 2020/11/29 11:08
 **/
public class Singleton1 {

    // 1、不实例化
    private static Singleton1 instance = null;

    private Singleton1() {
        // 构造函数
    }

    // 2、通过该方法想整个系统提供实例
    public static Singleton1 getInstance() {
        // 当instance为null时，则实例化对象，否则直接返回对象。
        if (null == instance) {
            instance = new Singleton1();
        }
        return instance;
    }
}
