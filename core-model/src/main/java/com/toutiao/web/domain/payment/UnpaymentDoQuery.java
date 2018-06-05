package com.toutiao.web.domain.payment;

import lombok.Data;

@Data
public class UnpaymentDoQuery {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 成功会跳页面
     */
    private String returnURL;
    /**
     * 支付终端跳转页面
     */
    private String quitURL;
}
