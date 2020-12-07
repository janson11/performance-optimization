package com.janson.performance.optimization.goods.vo;

import lombok.Data;

/**
 * @Description: 促销类型
 * @Author: Janson
 * @Date: 2020/12/7 19:56
 **/
@Data
public class SupportPromotions implements Cloneable {
    /**
     * 该商品促销的ID
     */
    private int id;
    /**
     * 促销类型 1：优惠券 2：红包
     */
    private PromotionType promotoType;
    /**
     * 优先级
     */
    private int priortity;
    /**
     * 用户领取该商品的优惠券
     */
    private UserCoupon userCoupon;
    /**
     * 用户领取该商品的红包
     */
    private UserRedPacket userRedPacket;
}
