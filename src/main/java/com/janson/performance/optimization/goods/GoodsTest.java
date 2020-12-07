package com.janson.performance.optimization.goods;

import com.janson.performance.optimization.goods.vo.Merchandise;
import com.janson.performance.optimization.goods.vo.Order;
import com.janson.performance.optimization.goods.vo.OrderDetail;
import com.janson.performance.optimization.goods.vo.PromotionType;
import com.janson.performance.optimization.goods.vo.SupportPromotions;
import com.janson.performance.optimization.goods.vo.UserCoupon;
import com.janson.performance.optimization.goods.vo.service.impl.PromotionFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 商品测试类
 * @Author: Janson
 * @Date: 2020/12/7 21:00
 **/
public class GoodsTest {
    public static void main(String[] args) {
        Order order = new Order();
        init(order);
        for (OrderDetail orderDetail : order.getList()) {
            BigDecimal payMoney = PromotionFactory.getPayMoney(orderDetail);
            orderDetail.setPayMoney(payMoney);
            System.out.println("最终支付金额：" + orderDetail.getPayMoney());
        }
    }

    private static void init(Order order) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPayMoney(new BigDecimal(1900));
        orderDetail.setId(2);
        orderDetail.setOrderId(1);
        Merchandise merchandise = new Merchandise();
        merchandise.setName("牙刷");
        merchandise.setSku("skuuu");
        merchandise.setPrice(new BigDecimal(100));
        Map<PromotionType, SupportPromotions> map = new HashMap<>();
        SupportPromotions supportPromotions = new SupportPromotions();
        supportPromotions.setId(3);
        supportPromotions.setPriortity(0);
        supportPromotions.setPromotoType(PromotionType.COUPON);
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCoupon(new BigDecimal(50));
        userCoupon.setId(4);
        userCoupon.setUserId(10001);
        userCoupon.setSku("sssskkk");
        supportPromotions.setUserCoupon(userCoupon);
        map.put(PromotionType.COUPON, supportPromotions);
        merchandise.setSupportPromotions(map);
        orderDetail.setMerchandise(merchandise);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        order.setId(1);
        order.setOrderNo("ddhdfhfhhfhfhf");
        order.setTotalPayMoney(new BigDecimal(2000));
        order.setList(orderDetails);
    }
}
