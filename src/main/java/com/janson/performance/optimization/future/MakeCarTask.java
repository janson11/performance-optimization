package com.janson.performance.optimization.future;

/**
 * @Description: 造车任务
 * @Author: Janson
 * @Date: 2020/12/12 22:48
 **/
public class MakeCarTask<T, P> implements Task<T, P> {
    @Override
    public T doTask(P param) {
        String car = param + " is created success";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (T) car;
    }
}
