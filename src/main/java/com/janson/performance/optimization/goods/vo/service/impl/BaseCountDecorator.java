package com.janson.performance.optimization.goods.vo.service.impl;

import com.janson.performance.optimization.goods.vo.OrderDetail;
import com.janson.performance.optimization.goods.vo.service.IBaseCount;

import java.math.BigDecimal;

/**
 * @Description: 计算支付金额的抽象类
 * @Author: Janson
 * @Date: 2020/12/7 20:17
 **/
public abstract class BaseCountDecorator implements IBaseCount {

    private IBaseCount count;

    public BaseCountDecorator(IBaseCount count) {
        this.count = count;
    }


    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        if (count != null) {
            payTotalMoney = count.countPayMoney(orderDetail);
        }
        return payTotalMoney;
    }
}
