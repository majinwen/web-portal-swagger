package com.toutiao.web.dao.entity.officeweb;

import java.math.BigDecimal;
import java.util.Date;

public class IntelligenceFhTd {
    /**
     * 价格
     */
    private Integer totalPriceRange;

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

    public Integer getTotalPriceRange() {
        return totalPriceRange;
    }

    public void setTotalPriceRange(Integer totalPriceRange) {
        this.totalPriceRange = totalPriceRange;
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
}