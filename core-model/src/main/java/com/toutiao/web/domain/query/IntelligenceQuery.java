package com.toutiao.web.domain.query;

import lombok.Data;

@Data
public class IntelligenceQuery {

    /**
     * 用户类型(1--自住 刚需,2--自住 改善,3--出租 投资)
     */
    private String userType;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 首付(起始)
     */
    private Double beginDownPayment;
    /**
     * 首付(结束)
     */
    private Double endDownPayment;
    /**
     * 月供(起始)
     */
    private Double beginMonthPayment;
    /**
     * 月供（結束）
     */
    private Double endMonthPayment;
    /**
     * 总价（起始）
     */
    private Double beginTotal;
    /**
     * 总价（结束）
     */
    private Double endTotal;



}
