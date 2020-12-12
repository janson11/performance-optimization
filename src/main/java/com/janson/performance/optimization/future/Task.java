package com.janson.performance.optimization.future;

/**
 * @Description: 任务
 * @Author: Janson
 * @Date: 2020/12/12 22:25
 **/
public interface Task<T, P> {
    /**
     * 完成任务
     *
     * @param param 参数
     * @return 结果
     */
    T doTask(P param);
}
