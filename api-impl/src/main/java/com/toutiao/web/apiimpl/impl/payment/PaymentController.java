package com.toutiao.web.apiimpl.impl.payment;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.domain.payment.PaymentOrderQuery;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
    public String buildCommodityOrder(HttpServletRequest request, @Validated CommodityOrderQuery commodityOrderQuery, Model model) {

        String orderResult = "";
        JSONObject orderObject = null;
        JSONObject orderJson = null;
        if(commodityOrderQuery.getBuildingId()!=null){
            orderResult = paymentService.saveCommodityOrder(request,commodityOrderQuery);
            orderObject = JSON.parseObject(orderResult);
            orderJson = JSON.parseObject(orderObject.getString("data"));
        }else{
            PaymentOrderQuery paymentOrderQuery = new PaymentOrderQuery();
            BeanUtils.copyProperties(commodityOrderQuery,paymentOrderQuery);
            orderResult =paymentService.getOrderByOrderNo(request, paymentOrderQuery);
            orderObject = JSON.parseObject(orderResult);
            orderJson = (JSONObject) JSON.parseObject(orderObject.getString("data")).getJSONArray("data").get(0);
        }

        String balanceResult = paymentService.getBalanceInfoByUserId(request);
        JSONObject balanceObject = JSON.parseObject(balanceResult);
        if(orderObject.getString("code").equals(String.valueOf(UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue()))){
            return "/user/login";
        }

        JSONObject balanceJson = JSON.parseObject(balanceObject.getString("data"));

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
        JSONObject payOrderObject = JSON.parseObject(payOrder);
        JSONObject payOrderJson = JSON.parseObject(payOrderObject.getString("data"));
        System.out.println(payOrderJson);
        model.addAttribute("payOrder",payOrderJson);

        return "";
    }

    /**
     * 根据订单编号获取购买记录
     * @param request
     * @param paymentOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
    public String paymentSuccess(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery, Model model){

        String order = paymentService.getOrderByOrderNo(request, paymentOrderQuery);

        String paySuccess = paymentService.paymentSuccess(request, paymentOrderQuery);
        JSONObject paySuccessObject = JSON.parseObject(paySuccess);
        if(paySuccessObject.getString("code").equals(String.valueOf(UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue()))){
            return "/user/login";
        }
        JSONObject paySuccessJson = (JSONObject) JSON.parseObject(paySuccessObject.getString("data")).getJSONArray("data").get(0);

        JSONObject orderObject = JSON.parseObject(order);
        JSONObject orderJson = (JSONObject) JSON.parseObject(orderObject.getString("data")).getJSONArray("data").get(0);

        model.addAttribute("paySuccess",paySuccessJson);
        model.addAttribute("order",orderJson);

        return "order/coupon";
    }




    /**
     * 小鹿测试页面(我的订单列表)
     * @param model
     * @return
     */
    @RequestMapping("/order")
    public String order(Model model) {

        return "order/order";
    }
    /**
     * 小鹿测试页面(充值页面)
     * @param model
     * @return
     */
    @RequestMapping("/recharge")
    public String recharge(Model model) {

        return "order/recharge";
    }
    /**
     * 小鹿测试页面(充值结果)
     * @param model
     * @return
     */
    @RequestMapping("/result")
    public String result(Model model) {

        return "order/result";
    }
    /**
     * 小鹿测试页面(购买页面)
     * @param model
     * @return
     */
    @RequestMapping("/purchase")
    public String purchase(Model model) {

        return "order/purchase";
    }
    /**
     * 小鹿测试页面(收支明细)
     * @param model
     * @return
     */
    @RequestMapping("/detailed")
    public String detailed(Model model) {

        return "order/detailed";
    }
    /**
     * 小鹿测试页面(我的优惠卡)
     * @param model
     * @return
     */
    @RequestMapping("/coupon")
    public String coupon(Model model) {

        return "order/coupon";
    }
    /**
     * 小鹿测试页面(我的优惠卡)
     * @param model
     * @return
     */
    @RequestMapping("/center")
    public String center(Model model) {

        return "order/center";
    }
}
