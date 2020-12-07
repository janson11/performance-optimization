package com.janson.performance.optimization.decorator;

/**
 * @Description: 基础装饰类
 * @Author: Janson
 * @Date: 2020/12/7 19:36
 **/
public abstract class BaseDecorator implements IDecorator {

    private IDecorator decorator;

    public BaseDecorator(IDecorator decorator) {
        this.decorator = decorator;
    }

    /**
     * 调用装饰方法
     */
    @Override
    public void decorate() {
        if (decorator != null) {
            decorator.decorate();
        }
    }
}
