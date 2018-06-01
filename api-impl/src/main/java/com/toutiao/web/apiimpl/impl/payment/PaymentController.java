package com.toutiao.web.apiimpl.impl.payment;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
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
import java.util.Map;


@Controller
@RequestMapping("/{citypath}/order")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @RequestMapping("")
    public String recharge11(Model model) {

        return "order/testorder";
    }



    /**
     * 生成商品购买订单1
     * @param commodityOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/buildCommodityOrder", method = RequestMethod.GET)
    public String buildCommodityOrder(HttpServletRequest request,CommodityOrderQuery commodityOrderQuery, Model model) {

        String orderResult = paymentService.saveCommodityOrder(request,commodityOrderQuery);
        String balanceResult = paymentService.getBalanceInfoByUserId(request);

        JSONObject orderObject = JSON.parseObject(orderResult);
        JSONObject balanceObject = JSON.parseObject(balanceResult);
        if(orderObject.getString("code").equals(String.valueOf(UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue()))){
            return "/user/login";
        }
        JSONObject orderJson = JSON.parseObject(orderObject.getString("data"));
        JSONObject balanceJson = JSON.parseObject(balanceObject.getString("data"));




        System.out.println(orderJson);

        System.out.println(balanceJson);
        System.out.println(balanceResult);




        model.addAttribute("commodityOrder",orderJson);
        model.addAttribute("balance",balanceJson);
        return "order/purchase";
    }

    /**
     * 完成商品购买订单
     * @param request
     * @param paymentOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/paymentCommodityOrder", method = RequestMethod.GET)
    public String paymentCommodityOrder(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery, Model model){


        String payOrder = paymentService.paymentCommodityOrder(request, paymentOrderQuery);

        System.out.println(payOrder);
        model.addAttribute("payOrder",payOrder);

        return "";
    }

    /**
     *
     * @param request
     * @param paymentOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/paymentSuccess", method = RequestMethod.GET)
    public String paymentSuccess(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery, Model model){


        String paySuccess = paymentService.paymentSuccess(request, paymentOrderQuery);

        System.out.println(paySuccess);
        model.addAttribute("payOrder",paySuccess);

        return "";
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
