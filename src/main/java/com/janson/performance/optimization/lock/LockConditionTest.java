package com.janson.performance.optimization.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 商品库存的生产和消费
 * @Author: Janson
 * @Date: 2020/12/3 19:51
 **/
@Slf4j
public class LockConditionTest {

    /**
     * 商品
     */
    private LinkedList<String> product = new LinkedList();
    /**
     * 最大库存
     */
    private int maxInventory = 10;
    /**
     * 资源锁
     */
    private Lock lock = new ReentrantLock();
    /**
     * 库存非满和非空条件
     */
    private Condition condition = lock.newCondition();

    /**
     * 新增商品
     *
     * @param e 商品名称
     */
    public void produce(String e) {
        lock.lock();
        try {
            while (product.size() == maxInventory) {
                condition.await();
                log.info("新增商品库存达到最大库存，在阻塞,当前线程：{}", Thread.currentThread().getId());
            }
            product.add(e);
            log.info("放入一个商品库存，总库存为：{}", product.size());
            condition.signalAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费一个商品
     *
     * @return
     */
    public String consume() {
        String result = null;
        lock.lock();
        try {
            while (product.size() == 0) {
                condition.await();
                log.info("库存已经没有商品了，在阻塞,当前线程：{}", Thread.currentThread().getId());
            }
            result = product.removeLast();
            log.info("消费一个商品，总库存{}", product.size());
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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
        LockConditionTest lc = new LockConditionTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();

    }


}
