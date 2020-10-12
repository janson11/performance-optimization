package com.janson.performance.optimization.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 流式案例:使用一个容器装载100个数字，通过Stream并行处理的方式将容器中为单数的数字转移到容器parallelList。
 * @Author: Janson
 * @Date: 2020/10/12 19:47
 **/
public class StreamList {

    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<Integer>(100);
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }
        System.out.println("integerList:" + integerList);
        System.out.println("integerList size:" + integerList.size());
        List<Integer> parallelList = new ArrayList<Integer>(500);
        integerList.stream()
                .parallel()
                .filter(i -> i % 2 == 1)
                .forEach(i -> parallelList.add(i));
//        parallelList.sort((o1, o2) -> (o1 - o2));
        // parallelList ArrayList是线程不安全的集合，而当前又使用了并行流去处理，所以会出现有异常、少数据或者正常输出的情况。
        System.out.println("parallelList:" + parallelList);
        System.out.println("parallelList size:" + parallelList.size());
    }
}
