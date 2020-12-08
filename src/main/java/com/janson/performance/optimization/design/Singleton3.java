package com.janson.performance.optimization.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 饿汉模式 枚举模式
 *
 * @Description: 饿汉模式
 * @Author: Janson
 * @Date: 2020/11/26 20:41
 **/
public enum Singleton3 {

    // 不实例化
    INSTANCE;

    // list 属性
    public List<String> list = null;


    // 2、私有构造器
    private Singleton3() {
        list = new ArrayList<>();
    }

    // 3、向外暴露端点
    public static Singleton3 getInstance() {
        return INSTANCE;
    }
}
