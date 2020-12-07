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
public class LockConditionTest1 {

    /**
     * 商品
     */
    private LinkedList<String> product = new LinkedList();
    /**
     * 最大库存
     */
    private int maxInventory = 10;
    /**
     * 消费资源锁
     */
    private Lock consumerLock = new ReentrantLock();
    /**
     * 生产资源锁
     */
    private Lock productLock = new ReentrantLock();
    /**
     * 库存非空条件
     */
    private Condition notEmptyCondition = consumerLock.newCondition();
    /**
     * 库存非满条件
     */
    private Condition notFullCondition = productLock.newCondition();

    /**
     * 新增商品
     *
     * @param e 商品名称
     */
    public void produce(String e) {
        productLock.lock();
        try {
            while (product.size() == maxInventory) {
                notFullCondition.await();
                log.info("新增商品库存达到最大库存，在阻塞,当前线程：{}", Thread.currentThread().getId());
            }
            product.add(e);
            log.info("放入一个商品库存，总库存为：{}", product.size());
            if (product.size() < maxInventory) {
                notFullCondition.signalAll();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            productLock.unlock();
        }

        if (product.size() > 0) {
            try {
                consumerLock.lockInterruptibly();
                notEmptyCondition.signalAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                consumerLock.unlock();
            }
        }
    }

    /**
     * 消费一个商品
     *
     * @return
     */
    public String consume() {
        String result = null;
        consumerLock.lock();
        try {
            while (product.size() == 0) {
                notEmptyCondition.await();
                log.info("库存已经没有商品了，在阻塞,当前线程：{}", Thread.currentThread().getId());
            }
            result = product.removeLast();
            log.info("消费一个商品，总库存{}", product.size());
            if (product.size() > 0) {
                notEmptyCondition.signalAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumerLock.unlock();
        }

        if (product.size() < maxInventory) {
            try {
                productLock.lockInterruptibly();
                notFullCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                productLock.unlock();
            }
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
        LockConditionTest1 lc = new LockConditionTest1();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();

    }


}
