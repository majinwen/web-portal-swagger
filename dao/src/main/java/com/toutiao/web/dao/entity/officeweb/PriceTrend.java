package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceTrend {
    /**
     * 楼盘/小区id
     */
    private Integer buildingId;

    /**
     * 物业类型1-新房，0小区
     */
    private Short propertyType;

    /**
     * 价格对比对象0-小区，1-区域，2-商圈
     */
    private Short contrastDA;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 月份
     */
    private Date month;

    /**
     * 小区名称
     */
    private String buildingName;

}