package com.toutiao.web.service.payment;

import com.toutiao.web.domain.payment.PayBuyRecordDo;

import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayOrderQuery;
import com.toutiao.web.domain.payment.PayUserDo;

import java.util.List;

public interface PaymentService {


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
}