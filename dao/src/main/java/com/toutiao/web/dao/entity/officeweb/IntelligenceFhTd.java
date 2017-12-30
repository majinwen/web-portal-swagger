package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class IntelligenceFhTd {
    /**
     * 最低价格
     */
    private Integer minTotalPrice;

    /**
     * 单价
     */
    private BigDecimal targetSd;

    /**
     * 时间
     */
    private Date periodicTime;

    /**
     * 市场总价
     */
    private BigDecimal allSd;

    /**
     * 最高价格
     */
    private Integer maxTotalPrice;

}