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
    String saveCommodityOrder(CommodityOrderQuery commodityOrderQuery,PayUserDo payUserDo);

    /**
     * 获取用户余额信息
     * @param payUserDo
     * @return
     */
    String getBalanceInfoByUserId(PayUserDo payUserDo);

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
     * 我的订单列表
     */
    List<PayOrderDo> getMyOrder(PayOrderQuery payOrderQuery,PayUserDo payUserDo,Integer type,Integer status);

    /**
     * 根据订单编号获取订单详情
     * @param payUserDo
     * @param paymentOrderQuery
     * @return
     */
    String getOrderByOrderNo(PaymentOrderQuery paymentOrderQuery,PayUserDo payUserDo);

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