package com.toutiao.web.dao.entity.officeweb;

import com.alibaba.fastjson.annotation.JSONField;
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
     * 价格对比对象0-楼盘价格，1-区域，2-圈商
     */
    private Short contrastDA;

    /**
     * 月份
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date month;

    /**
     * 金额
     */
    private BigDecimal price;

}