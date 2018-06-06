package com.toutiao.web.api.chance.request.payment;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentRequest {
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
    private String productDetails = "比得屋账户充值";
    /**
     * 成功会跳页面
     */
    private String returnURL = "http://192.168.1.110:8085/bj/order/success";
    /**
     * 支付中断跳转页面
     */
    private String quitURL="http://192.168.1.110:8085/bj/payOrder/order/getMyOrder";
}
