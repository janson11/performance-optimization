package com.janson.performance.optimization.goods.vo.service;

import com.janson.performance.optimization.goods.vo.OrderDetail;

import java.math.BigDecimal;

/**
 * @Description: 计算支付金额接口类
 * @Author: Janson
 * @Date: 2020/12/7 20:12
 **/
public interface IBaseCount {
    /**
     * 计算支付
     *
     * @param orderDetail
     * @return
     */
    BigDecimal countPayMoney(OrderDetail orderDetail);
}
