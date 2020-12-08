package com.janson.performance.optimization.design;

/**
 * @Description: 枚举单例示例
 * @Author: Janson
 * @Date: 2020/12/8 19:54
 **/
public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}
