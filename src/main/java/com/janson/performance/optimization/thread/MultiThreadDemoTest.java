package com.janson.performance.optimization.thread;

/**
 * @Description: 多线程测试类
 * @Author: Janson
 * @Date: 2020/11/17 20:25
 **/
public class MultiThreadDemoTest {
    public static void main(String[] args) {
        // 运行多线程
        MultiThread multiThread = new MultiThread();
        multiThread.start();

        // 运行单线程
        SerialThread serialThread = new SerialThread();
        serialThread.start();
    }


}
