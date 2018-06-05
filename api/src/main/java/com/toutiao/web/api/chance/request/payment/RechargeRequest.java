package com.toutiao.web.api.chance.request.payment;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RechargeRequest {
    /**
     * 订单类型(1充值 2消费)
     */
    @NotNull
    private Integer type;
    /**
     * 商品编号
     */
    @NotNull
    private String productNo;
    /**
     * 商品详情
     */
    @NotNull
    private String productDetails;
    /**
     * 余额
     */
    @NotNull
    private Double totalMoney;
}
