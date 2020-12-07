package com.janson.performance.optimization.goods.vo.service.impl;

import com.janson.performance.optimization.goods.vo.OrderDetail;
import com.janson.performance.optimization.goods.vo.service.IBaseCount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description: 支付基本类
 * @Author: Janson
 * @Date: 2020/12/7 20:14
 **/
@Slf4j
public class BaseCount implements IBaseCount {
    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        log.info("商品原单价金额为:{}", orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }
}
