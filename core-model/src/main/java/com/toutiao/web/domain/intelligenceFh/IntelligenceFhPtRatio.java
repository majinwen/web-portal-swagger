package com.toutiao.web.domain.intelligenceFh;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntelligenceFhPtRatio {


    /**
     * 最低成交价格
     */
    private BigDecimal minprice;

    /**
     * 最高成交价格
     */
    private BigDecimal maxprice;

    /**
     * 年平均成交价格
     */
    private BigDecimal avgprice;

    /**
     * 北京市场年平均成交价格
     */
    private BigDecimal avgtotalprice;

    /**
     * 北京市场年最高成交价格
     */
    private BigDecimal maxtotalprice;

    /**
     * 最高涨幅
     */
    private String maxRatio;

    /**
     * 最高跌幅
     */
    private String minRatio;

    /**
     * 北京市平均涨幅
     */
    private String avgRatio;

    /**
     * 0-跌幅高于   1-跌幅低于
     */
    private Integer lowfalg;

    /**
     * 0-涨幅高于   1-涨幅低于
     */
    private Integer rowfalg;







}