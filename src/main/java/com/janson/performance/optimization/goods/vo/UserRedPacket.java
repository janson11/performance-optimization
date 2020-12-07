package com.janson.performance.optimization.goods.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 红包
 * @Author: Janson
 * @Date: 2020/12/7 20:09
 **/
@Data
public class UserRedPacket {
    /**
     * 红包d
     */
    private int id;
    /**
     * 领取红包用户ID
     */
    private int userId;
    /**
     * 商品SKU
     */
    private String sku;
    /**
     * 领取红包金额
     */
    private BigDecimal readPacket;
}
