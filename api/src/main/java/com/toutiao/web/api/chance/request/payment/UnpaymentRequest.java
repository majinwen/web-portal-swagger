package com.toutiao.web.api.chance.request.payment;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UnpaymentRequest {
    /**
     * 订单编号
     */
    @NotNull
    private String orderNo;
    /**
     * 成功会跳页面
     */
    private String returnURL = "http://192.168.1.110:8085/bj/order/success";
    /**
     * 支付中断跳转页面
     */
    private String quitURL="http://192.168.1.110:8085/bj/payOrder/order/getMyOrder";
}
