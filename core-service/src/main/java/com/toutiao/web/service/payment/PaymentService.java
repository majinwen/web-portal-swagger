package com.toutiao.web.service.payment;

import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayBuyRecordQuery;
import com.toutiao.web.domain.payment.PayUserDo;

import java.util.List;

public interface PaymentService {


    /**
     * 获取用户购买记录
     * @param payOrderQuery
     * @return
     */
    List<PayOrderDo> getBuyRecordByUserId(PayBuyRecordQuery payOrderQuery, PayUserDo payUserDo);
}