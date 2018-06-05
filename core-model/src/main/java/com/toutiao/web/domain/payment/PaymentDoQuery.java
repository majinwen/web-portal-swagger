package com.toutiao.web.domain.payment;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentDoQuery {
    /**
     * 支付类型(1支付宝 2微信)
     */
    @NotNull
    private Integer payType;
    /**
     * 支付金额
     */
    @NotNull
    private Double payMoney;
    /**
     * 订单类型(1充值 2消费)
     */
    @NotNull
    private Integer type;
    /**
     * 备注
     */
    private String comment;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 商品详情
     */
    private String productDetails;
    /**
     * 成功会跳页面
     */
    private String returnURL;
    /**
     * 支付终端跳转页面
     */
    private String quitURL;
}
