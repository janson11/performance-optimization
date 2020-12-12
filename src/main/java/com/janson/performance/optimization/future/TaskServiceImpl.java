package com.janson.performance.optimization.future;


/**
 * @Description: 任务接口实现类
 * @Author: Janson
 * @Date: 2020/12/12 22:36
 **/
public class TaskServiceImpl<T, P> implements TaskService<T, P> {
    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
        }, Thread.currentThread().getName()).start();
        return future;
    }

    @Override
    public Future<?> submit(Task<T, P> task, P param) {
        final FutureTask<T> future = new FutureTask<T>();
        new Thread(() -> {
            T result = task.doTask(param);
            future.finish(result);
        }, Thread.currentThread().getName()).start();
        return future;
    }
}
