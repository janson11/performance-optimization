package com.janson.performance.optimization.goods.vo;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/12/7 19:59
 **/
public enum PromotionType {
    /**
     * 优惠券
     */
    COUPON("1"),
    /**
     * 红包
     */
    REDPACKET("2");


    /**
     * 类型
     */
    private String type;

    PromotionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
