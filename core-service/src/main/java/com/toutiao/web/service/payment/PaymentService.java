package com.toutiao.web.service.payment;

import com.toutiao.web.domain.payment.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 支付成功，返回订单信息
     * @param request
     * @param paymentOrderQuery
     * @return
     */
    String paymentSuccess(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery);

    /**
     * 获取用户购买记录
     * @param payUserDo
     * @return
     */
    List<PayBuyRecordDo> getBuyRecordByUserId(PayOrderQuery payOrderQuery,PayUserDo payUserDo);


    /**
     * 我的订单
     */
    List<PayOrderDo> getMyOrder(PayOrderQuery payOrderQuery,PayUserDo payUserDo,Integer type);

    /**
     * 支付
     * @return
     */
    String payment(HttpServletRequest request, PaymentDoQuery paymentDoQuery);
    /**
     * 完成未支付
     */
    String unPayment(HttpServletRequest request, UnpaymentDoQuery unpaymentDoQuery);
}