package com.janson.performance.optimization.goods.vo.service.impl;

import com.janson.performance.optimization.goods.vo.OrderDetail;
import com.janson.performance.optimization.goods.vo.PromotionType;
import com.janson.performance.optimization.goods.vo.SupportPromotions;
import com.janson.performance.optimization.goods.vo.service.IBaseCount;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Description: 计算促销后的支付价格
 * @Author: Janson
 * @Date: 2020/12/7 20:52
 **/
public class PromotionFactory {

    public static BigDecimal getPayMoney(OrderDetail orderDetail) {
        // 获得给商品设定的促销类型
        Map<PromotionType, SupportPromotions> supportPromotions = orderDetail.getMerchandise().getSupportPromotions();
        // 初始化计算
        IBaseCount baseCount = new BaseCount();
        if (supportPromotions != null && supportPromotions.size() > 0) {
            for (PromotionType promotionType : supportPromotions.keySet()) {
                baseCount = promotion(supportPromotions.get(promotionType), baseCount);
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }

    /**
     * 组合促销类型
     *
     * @param supportPromotions
     * @param baseCount
     * @return
     */
    private static IBaseCount promotion(SupportPromotions supportPromotions, IBaseCount baseCount) {
        if (supportPromotions.getPromotoType() == PromotionType.COUPON) {
            baseCount = new CouponDecorator(baseCount);
        } else if (supportPromotions.getPromotoType() == PromotionType.REDPACKET) {
            baseCount = new RedPacketDecorator(baseCount);
        }
        return baseCount;
    }
}
