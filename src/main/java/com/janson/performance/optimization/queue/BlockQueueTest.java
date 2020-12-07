package com.janson.performance.optimization.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: 阻塞队列测试
 * @Author: Janson
 * @Date: 2020/12/7 18:30
 **/
@Slf4j
public class BlockQueueTest {

    /**
     * 最大库存
     */
    private int maxInventory = 10;

    private BlockingQueue<String> product = new LinkedBlockingQueue<>(maxInventory);

    /**
     * 新增商品
     *
     * @param e
     */
    public void produce(String e) {
        try {
            product.put(e);
            log.info("放入一个商品库存，总库存为：{}", product.size());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 消费商品
     *
     * @return
     */
    public String consume() {
        String result = null;
        try {
            result = product.take();
            log.info("消费一个商品，总库存{}", product.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 生产商品
     */
    private class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                produce(" 商品 " + i);
            }
        }
    }

    /**
     * 消费商品
     */
    private class Customer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {
        BlockQueueTest lc = new BlockQueueTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();

    }


}
