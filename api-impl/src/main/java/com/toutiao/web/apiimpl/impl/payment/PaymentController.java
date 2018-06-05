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
     * 生成商品购买订单
     * @param commodityOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/buildCommodityOrder", method = RequestMethod.POST)
    public String buildCommodityOrder(HttpServletRequest request, @RequestBody CommodityOrderQuery commodityOrderQuery, Model model) {

        String orderResult = paymentService.saveCommodityOrder(request,commodityOrderQuery);
        String balanceResult = paymentService.getBalanceInfoByUserId(request);

        JSONObject jsonObject = JSON.parseObject(orderResult);
        if(jsonObject.getString("code").equals(String.valueOf(UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue()))){
            return "redirect:/login";
        }
        System.out.println(orderResult);

        System.out.println(balanceResult);
        model.addAttribute("commodityOrder",orderResult);
        model.addAttribute("balance",balanceResult);
        return "order/purchase";
    }

    /**
     * 完成商品购买订单
     * @param request
     * @param paymentOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/paymentCommodityOrder", method = RequestMethod.POST)
    public String paymentCommodityOrder(HttpServletRequest request, @RequestBody PaymentOrderQuery paymentOrderQuery, Model model){


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
        return "order/result";
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
