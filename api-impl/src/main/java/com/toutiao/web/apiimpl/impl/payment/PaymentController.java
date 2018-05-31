package com.toutiao.web.apiimpl.impl.payment;


import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.domain.payment.PaymentOrderQuery;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/{citypath}/order")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;


    /**
     * 生成商品购买订单
     * @param commodityOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/buildCommodityOrder", method = RequestMethod.POST)
    public String buildCommodityOrder(HttpServletRequest request, @RequestBody CommodityOrderQuery commodityOrderQuery, Model model) {

        String orderResult = paymentService.saveCommodityOrder(request,commodityOrderQuery);
        String balanceResult = paymentService.getBalanceInfoByUserId(request);
        System.out.println(orderResult);

        System.out.println(balanceResult);
        model.addAttribute("commodityOrder",orderResult);
        model.addAttribute("balance",balanceResult);
        return "";
    }

    @RequestMapping(value = "/paymentCommodityOrder", method = RequestMethod.POST)
    public String paymentCommodityOrder(HttpServletRequest request, @RequestBody PaymentOrderQuery paymentOrderQuery, Model model){


        String payOrder = paymentService.paymentCommodityOrder(request, paymentOrderQuery);

        System.out.println(payOrder);
        model.addAttribute("payOrder",payOrder);

        return "";
    }







    /**
     * 小鹿测试页面
     * @param request
     * @param commodityOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/www", method = RequestMethod.POST)
    public String www(HttpServletRequest request, @RequestBody CommodityOrderQuery commodityOrderQuery, Model model) {


        return "order/test";
    }


}
