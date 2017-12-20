package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

import java.math.BigDecimal;
@Data
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


}