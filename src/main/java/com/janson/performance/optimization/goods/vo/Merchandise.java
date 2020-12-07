package com.janson.performance.optimization.goods.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Description: 商品
 * @Author: Janson
 * @Date: 2020/12/7 19:52
 **/
@Data
public class Merchandise {
    /**
     * 商品SKU
     */
    private String sku;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 支持促销类型
     */
    private Map<PromotionType, SupportPromotions> supportPromotions;

}
