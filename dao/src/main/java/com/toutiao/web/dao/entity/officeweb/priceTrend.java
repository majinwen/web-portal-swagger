package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class priceTrend {
    /**
     * 楼盘/小区id
     */
    private Integer buildId;

    /**
     * 物业类型1-新房，0小区
     */
    private Short propertyType;

    /**
     * 价格对比对象0-楼盘价格，1-区域，2-商圈
     */
    private Short contrastDA;

    /**
     * 月份
     */
    private Short month;

    /**
     * 金额
     */
    private BigDecimal price;

}