package com.toutiao.web.apiimpl.impl.payment;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.api.chance.request.payment.PaymentRequest;
import com.toutiao.web.api.chance.request.payment.UnpaymentRequest;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.domain.payment.PaymentDoQuery;
import com.toutiao.web.domain.payment.PaymentOrderQuery;
import com.toutiao.web.domain.payment.UnpaymentDoQuery;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


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
     * 根据订单编号获取购买记录
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
     * 支付
     * @param request
     * @param paymentRequest
     * @return
     */
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @ResponseBody
    public String payment(HttpServletRequest request, @Validated @RequestBody PaymentRequest paymentRequest) {
        PaymentDoQuery paymentDoQuery = new PaymentDoQuery();
        BeanUtils.copyProperties(paymentRequest, paymentDoQuery);
        String form = paymentService.payment(request, paymentDoQuery);
        return form;
    }

    /**
     * 完成未支付的订单
     * @param request
     * @param unpaymentRequest
     * @return
     */
    @RequestMapping(value = "/unPayment",method = RequestMethod.GET)
    @ResponseBody
    public String unPayment(HttpServletRequest request, @Validated UnpaymentRequest unpaymentRequest){
        UnpaymentDoQuery unpaymentDoQuery = new UnpaymentDoQuery();
        BeanUtils.copyProperties(unpaymentRequest, unpaymentDoQuery);
        String form = paymentService.unPayment(request, unpaymentDoQuery);
        return form;
    }

    /**
     * 支付成功
     * @return
     */
    @RequestMapping("/success")
    public String success(){
        return "404";
    }

    /**
     * 支付失败
     * @return
     */
    @RequestMapping("/fails")
    public String fails(){
        return "404";
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
}
