package com.toutiao.web.dao.entity.officeweb;

import java.math.BigDecimal;
import java.util.Date;

public class IntelligenceFhPricetrend {
    /**
     * 价格
     */
    private Integer totalPriceRange;

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

    public Integer getTotalPriceRange() {
        return totalPriceRange;
    }

    public void setTotalPriceRange(Integer totalPriceRange) {
        this.totalPriceRange = totalPriceRange;
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
}