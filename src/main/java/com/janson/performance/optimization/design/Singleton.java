package com.janson.performance.optimization.design;

/**
 * 通过单例模式，我们可以避免多次创建多个实例，从而节约系统资源
 *
 * @Description: 饿汉模式
 * @Author: Janson
 * @Date: 2020/11/26 20:41
 **/
public class Singleton {

    // 1、自行创建实例：使用static修饰了成员变量instance，在多线程的情况下能保证只实例化了一次。
    private static Singleton instance = new Singleton();

    // 2、私有构造器
    private Singleton() {
    }

    // 3、向外暴露端点
    public static Singleton getInstance() {
        return instance;
    }
}
