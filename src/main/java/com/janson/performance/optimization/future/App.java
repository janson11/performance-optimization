package com.janson.performance.optimization.future;

/**
 * @Description: 测试类
 * @Author: Janson
 * @Date: 2020/12/12 22:50
 **/
public class App {

    public static void main(String[] args) {
        TaskServiceImpl<String, String> taskService = new TaskServiceImpl<>();
        MakeCarTask<String, String> task = new MakeCarTask<>();
        Future<?> future = taskService.submit(task, "car1");
        String result = (String) future.get();
        System.out.println(result);
    }
}
