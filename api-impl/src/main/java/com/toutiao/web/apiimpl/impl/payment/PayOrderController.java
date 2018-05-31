package com.toutiao.web.apiimpl.impl.payment;

import com.toutiao.web.apiimpl.authentication.UserPay;
import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayBuyRecordQuery;
import com.toutiao.web.domain.payment.PayUserDo;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/{citypath}/payOrder")
public class PayOrderController {
    @Autowired
    private PaymentService paymentService;
    @RequestMapping(value = "order/getBuyRecord",method = RequestMethod.GET)
    public  String getPayOrder(Model model,PayBuyRecordQuery payOrderQuery)
    {
         PayUserDo payUserDo=new PayUserDo();
         BeanUtils.copyProperties(UserPay.getCurrent(),payUserDo);
         List<PayOrderDo> payOrderDos =paymentService.getBuyRecordByUserId(payOrderQuery,payUserDo);
         model.addAllAttributes(payOrderDos);
         return "";
    }
}
