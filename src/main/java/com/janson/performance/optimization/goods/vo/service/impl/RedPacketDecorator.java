package com.janson.performance.optimization.goods.vo.service.impl;

import com.janson.performance.optimization.goods.vo.OrderDetail;
import com.janson.performance.optimization.goods.vo.PromotionType;
import com.janson.performance.optimization.goods.vo.SupportPromotions;
import com.janson.performance.optimization.goods.vo.service.IBaseCount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Description: 计算使用红包后的金额
 * @Author: Janson
 * @Date: 2020/12/7 20:22
 **/
@Slf4j
public class RedPacketDecorator extends BaseCountDecorator {

    public RedPacketDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        payTotalMoney = super.countPayMoney(orderDetail);
        payTotalMoney = countRedPacketPayMoney(orderDetail);
        return payTotalMoney;
    }


    private BigDecimal countRedPacketPayMoney(OrderDetail orderDetail) {
        SupportPromotions supportPromotions = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.REDPACKET);
        BigDecimal readPacket = supportPromotions.getUserRedPacket().getReadPacket();
        log.info("红包优惠券金额：" + readPacket);
        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(readPacket));
        return orderDetail.getPayMoney();
    }

}
