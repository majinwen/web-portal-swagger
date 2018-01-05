package com.toutiao.web.domain.intelligenceFh;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntelligenceFhPtDo {


    /**
     * 最低价格
     */
    private Integer minTotalPrice;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 时间
     */
    private String periodicTime;

    /**
     * 市场总价
     */
    private BigDecimal totalPrice;

    /**
     * 最高价格
     */
    private Integer maxTotalPrice;
}
