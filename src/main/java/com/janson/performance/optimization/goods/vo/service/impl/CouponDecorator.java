package com.janson.performance.optimization.goods.vo.service.impl;

import com.janson.performance.optimization.goods.vo.OrderDetail;
import com.janson.performance.optimization.goods.vo.PromotionType;
import com.janson.performance.optimization.goods.vo.SupportPromotions;
import com.janson.performance.optimization.goods.vo.service.IBaseCount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description: 计算使用优惠券后的金额
 * @Author: Janson
 * @Date: 2020/12/7 20:22
 **/
@Slf4j
public class CouponDecorator extends BaseCountDecorator {

    public CouponDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        payTotalMoney = super.countPayMoney(orderDetail);
        payTotalMoney = countCouponPayMoney(orderDetail);
        return payTotalMoney;
    }


    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {
        SupportPromotions supportPromotions = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.COUPON);
        BigDecimal coupon = supportPromotions.getUserCoupon().getCoupon();
        log.info("优惠券金额：" + coupon);
        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(coupon));
        return orderDetail.getPayMoney();
    }

}
