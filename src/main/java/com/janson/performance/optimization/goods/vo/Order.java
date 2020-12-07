package com.janson.performance.optimization.goods.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 主订单
 * @Author: Janson
 * @Date: 2020/12/7 19:47
 **/
@Data
public class Order {

    /**
     * 订单ID
     */
    private int id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 总支付金额
     */
    private BigDecimal totalPayMoney;
    /**
     * 详细订单列表
     */
    private List<OrderDetail> list;
}
