package com.toutiao.web.apiimpl.impl.payment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{citypath}/payOrder")
public class PayOrderController {


    @RequestMapping(value = "/order/getPayOrder",method = RequestMethod.GET)
    public  String getPayOrder(Model model User)
    {

    }
}
