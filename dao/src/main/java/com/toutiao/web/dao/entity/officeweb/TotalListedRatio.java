package com.toutiao.web.dao.entity.officeweb;

import java.math.BigDecimal;

public class TotalListedRatio {
    /**
     * 总价
     */
    private BigDecimal total;

    /**
     * 挂牌数
     */
    private Integer listedNum;

    /**
     * 比率
     */
    private BigDecimal ratio;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getListedNum() {
        return listedNum;
    }

    public void setListedNum(Integer listedNum) {
        this.listedNum = listedNum;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}