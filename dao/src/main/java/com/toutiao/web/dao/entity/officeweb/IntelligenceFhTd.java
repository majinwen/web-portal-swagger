package com.toutiao.web.dao.entity.officeweb;

import java.math.BigDecimal;
import java.util.Date;

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

    public Integer getMinTotalPrice() {
        return minTotalPrice;
    }

    public void setMinTotalPrice(Integer minTotalPrice) {
        this.minTotalPrice = minTotalPrice;
    }

    public BigDecimal getTargetSd() {
        return targetSd;
    }

    public void setTargetSd(BigDecimal targetSd) {
        this.targetSd = targetSd;
    }

    public Date getPeriodicTime() {
        return periodicTime;
    }

    public void setPeriodicTime(Date periodicTime) {
        this.periodicTime = periodicTime;
    }

    public BigDecimal getAllSd() {
        return allSd;
    }

    public void setAllSd(BigDecimal allSd) {
        this.allSd = allSd;
    }

    public Integer getMaxTotalPrice() {
        return maxTotalPrice;
    }

    public void setMaxTotalPrice(Integer maxTotalPrice) {
        this.maxTotalPrice = maxTotalPrice;
    }
}