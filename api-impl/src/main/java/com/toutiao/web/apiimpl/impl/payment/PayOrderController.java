package com.toutiao.web.apiimpl.impl.payment;

import com.toutiao.web.apiimpl.authentication.UserPay;
import com.toutiao.web.domain.payment.PayBuyRecordDo;
import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayOrderQuery;
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
    //我的订单类型
    private  final  Integer ORDER_TYPE=1;
    //我的充值类型
    private  final  Integer CHATGE_TYPE=2;


    /**
     * 购买记录
     * @param model
     * @return
     */
    @RequestMapping(value = "order/getBuyRecord",method = RequestMethod.GET)
    public  String getPayOrder(Model model, PayOrderQuery payOrderQuery)
    {
         PayUserDo payUserDo=new PayUserDo();
         BeanUtils.copyProperties(UserPay.getCurrent(),payUserDo);
         List<PayBuyRecordDo> payOrderDos =paymentService.getBuyRecordByUserId(payOrderQuery,payUserDo);
         model.addAttribute("payOrderDos",payOrderDos);
         return "";
    }


    /**
     *我的订单
     * @param model
     * @param payOrderQuery
     * @return
     */

    @RequestMapping(value = "order/getMyOrder",method = RequestMethod.GET)
    public  String getOrderByUser(Model model, PayOrderQuery payOrderQuery)
    {
        PayUserDo payUserDo=new PayUserDo();
        BeanUtils.copyProperties(UserPay.getCurrent(),payUserDo);
        List<PayOrderDo> payOrderDos=paymentService.getMyOrder(payOrderQuery,payUserDo,ORDER_TYPE);
        model.addAttribute("payOrderDos",payOrderDos);
        return "";
    }

    /**
     * 我的充值记录
     */
    @RequestMapping(value = "order/getMyCharge",method = RequestMethod.GET)
    public String getChargebyUser(Model model, PayOrderQuery payOrderQuery)
    {
        PayUserDo payUserDo=new PayUserDo();
        BeanUtils.copyProperties(UserPay.getCurrent(),payUserDo);
        List<PayOrderDo> payOrderDos=paymentService.getMyOrder(payOrderQuery,payUserDo,CHATGE_TYPE);
        model.addAttribute("payOrderDos",payOrderDos);
        return "";
    }

}
