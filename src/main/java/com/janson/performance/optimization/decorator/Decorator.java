package com.janson.performance.optimization.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 装饰基本类
 * @Author: Janson
 * @Date: 2020/12/7 19:34
 **/
@Slf4j
public class Decorator implements IDecorator {

    /**
     * 基本实现方法
     */
    @Override
    public void decorate() {
        log.info("水电装修、天花板以及粉刷墙。。。");
    }
}
