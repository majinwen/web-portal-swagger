package com.toutiao.web.service.payment;

import com.toutiao.web.domain.payment.PayBuyRecordDo;

import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayOrderQuery;
import com.toutiao.web.domain.payment.PayUserDo;

import java.util.List;

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
     * @param request
     * @param paymentOrderQuery
     * @return
     */
    String getOrderByOrderNo(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery);





}