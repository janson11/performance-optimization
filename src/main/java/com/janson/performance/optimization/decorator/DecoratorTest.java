package com.janson.performance.optimization.decorator;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/12/7 19:40
 **/
public class DecoratorTest {
    public static void main(String[] args) {
        IDecorator decorator = new Decorator();
        IDecorator curtainDecorator = new CurtainDecorator(decorator);
        curtainDecorator.decorate();
    }
}
