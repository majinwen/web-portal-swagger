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

        String orderResult = paymentService.saveCommodityOrder(request,commodityOrderQuery);
        String balanceResult = paymentService.getBalanceInfoByUserId(request);
        System.out.println(orderResult);

        System.out.println(balanceResult);
        model.addAttribute("commodityOrder",orderResult);
        model.addAttribute("balance",balanceResult);
        return "order/order.ftl";
    }


    /**
     * 小鹿测试页面(我的订单列表)
     * @param model
     * @return
     */
    @RequestMapping("/order/order")
    public String order(Model model) {

        return "order/order";
    }
    /**
     * 小鹿测试页面(充值页面)
     * @param model
     * @return
     */
    @RequestMapping("/order/recharge")
    public String recharge(Model model) {

        return "order/recharge";
    }
    /**
     * 小鹿测试页面(充值结果)
     * @param model
     * @return
     */
    @RequestMapping("/order/result")
    public String result(Model model) {

        return "order/result";
    }
    /**
     * 小鹿测试页面(购买页面)
     * @param model
     * @return
     */
    @RequestMapping("/order/purchase")
    public String purchase(Model model) {

        return "order/purchase";
    }
}
