package com.toutiao.web.dao.entity.officeweb;

import java.math.BigDecimal;
import java.util.Date;

public class IntelligenceFhPricetrend {
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
    private Date periodicTime;

    /**
     * 市场总价
     */
    private BigDecimal totalPrice;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPeriodicTime() {
        return periodicTime;
    }

    public void setPeriodicTime(Date periodicTime) {
        this.periodicTime = periodicTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getMaxTotalPrice() {
        return maxTotalPrice;
    }

    public void setMaxTotalPrice(Integer maxTotalPrice) {
        this.maxTotalPrice = maxTotalPrice;
    }
}