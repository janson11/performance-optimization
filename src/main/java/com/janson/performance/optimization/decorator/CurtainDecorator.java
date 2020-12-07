package com.janson.performance.optimization.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 窗帘装饰类
 * @Author: Janson
 * @Date: 2020/12/7 19:38
 **/
@Slf4j
public class CurtainDecorator extends BaseDecorator {
    public CurtainDecorator(IDecorator decorator) {
        super(decorator);
    }

    @Override
    public void decorate() {
        log.info("窗帘装饰。。。");
        super.decorate();
    }
}
