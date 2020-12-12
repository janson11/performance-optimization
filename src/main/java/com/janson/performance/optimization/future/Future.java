package com.janson.performance.optimization.future;

/**
 * @Description: 查询执行结果
 * @Author: Janson
 * @Date: 2020/12/12 22:34
 **/
public interface Future<T> {
    /**
     * 获取返回结果
     *
     * @return
     */
    T get();

    /**
     * 判断是否完成
     *
     * @return
     */
    boolean done();

}
