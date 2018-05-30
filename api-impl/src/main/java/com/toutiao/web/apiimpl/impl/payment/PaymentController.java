package com.toutiao.web.apiimpl.impl.payment;


import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/{citypath}/payment")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;


    /**
     * 生成商品购买订单
     * @param commodityOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/saveCommodityOrder", method = RequestMethod.POST)
    public String saveCommodityOrder(HttpServletRequest request, @RequestBody CommodityOrderQuery commodityOrderQuery, Model model) {

        String result = paymentService.saveCommodityOrder(request,commodityOrderQuery);
        System.out.println(result);
        model.addAttribute("saveOrder",result);
        return "";
    }


}
