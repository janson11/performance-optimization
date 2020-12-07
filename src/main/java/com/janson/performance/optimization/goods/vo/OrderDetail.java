package com.janson.performance.optimization.goods.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 订单详细列表
 * @Author: Janson
 * @Date: 2020/12/7 19:50
 **/
@Data
public class OrderDetail {
    /**
     * 订单详情ID
     */
    private int id;
    /**
     * 主订单ID
     */
    private int orderId;
    /**
     * 商品详情
     */
    private Merchandise merchandise;
    /**
     * 支付单价
     */
    private BigDecimal payMoney;
}
