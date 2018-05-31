package com.toutiao.web.service.payment;

import com.toutiao.web.domain.payment.CommodityOrderQuery;

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

}


