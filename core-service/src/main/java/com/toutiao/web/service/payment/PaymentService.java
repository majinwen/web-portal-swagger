package com.toutiao.web.service.payment;

import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.domain.payment.PaymentOrderQuery;

import javax.servlet.http.HttpServletRequest;

public interface PaymentService {

    /**
     * 生成商品购买订单
     * @param commodityOrderQuery
     * @return
     */
    String saveCommodityOrder(HttpServletRequest request, CommodityOrderQuery commodityOrderQuery);

    /**
     * 获取用户余额信息
     * @param request
     * @return
     */
    String getBalanceInfoByUserId(HttpServletRequest request);

    /**
     * 完成商品购买订单
     * @param request
     * @param paymentOrderQuery
     * @return
     */
    String paymentCommodityOrder(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery);

}


