package com.janson.performance.optimization.future;


/**
 * @Description: 提交任务
 * @Author: Janson
 * @Date: 2020/12/12 22:30
 **/
public interface TaskService<T, P> {

    /**
     * 提交任务，不返回结果
     *
     * @param runnable
     * @return
     */
    Future<?> submit(Runnable runnable);

    /**
     * 提交任务，返回结果
     *
     * @param task
     * @param param
     * @return
     */
    Future<?> submit(Task<T, P> task, P param);
}
