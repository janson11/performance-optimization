package com.janson.performance.optimization.goods.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 优惠券
 * @Author: Janson
 * @Date: 2020/12/7 20:06
 **/
@Data
public class UserCoupon {
    /**
     * 优惠券id
     */
    private int id;
    /**
     * 领取优惠券用户ID
     */
    private int userId;
    /**
     * 商品SKU
     */
    private String sku;
    /**
     * 优惠金额
     */
    private BigDecimal coupon;
}
