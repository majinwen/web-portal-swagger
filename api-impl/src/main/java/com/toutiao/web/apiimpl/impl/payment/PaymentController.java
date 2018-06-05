package com.toutiao.web.apiimpl.impl.payment;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.api.chance.request.payment.PaymentRequest;
import com.toutiao.web.api.chance.request.payment.RechargeRequest;
import com.toutiao.web.api.chance.request.payment.UnpaymentRequest;
import com.toutiao.web.apiimpl.authentication.UserPay;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.payment.*;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/{citypath}/order")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    /**
     * 生成商品购买订单1
     * @param commodityOrderQuery
     * @param model
     * @return
     */
    @RequestMapping(value = "/buildCommodityOrder", method = RequestMethod.GET)
    public String buildCommodityOrder(HttpServletRequest request, @Validated CommodityOrderQuery commodityOrderQuery, Model model) {

        UserPay user=UserPay.getCurrent();
        if (null==user.getUserId())
        {
            model.addAttribute("backUrl",request.getRequestURL());
            return "/user/login";
        }
        PayUserDo payUserDo=new PayUserDo();
        BeanUtils.copyProperties(user,payUserDo);
        String orderResult = "";
        JSONObject orderObject = null;
        JSONObject orderJson = null;
        if(commodityOrderQuery.getBuildingId()!=null){
            orderResult = paymentService.saveCommodityOrder(commodityOrderQuery, payUserDo);
            orderObject = JSON.parseObject(orderResult);
            orderJson = JSON.parseObject(orderObject.getString("data"));

            //再次购买 详情页
            if(orderJson.getString("status").equals("1")){
                PaymentOrderQuery paymentOrderQuery = new PaymentOrderQuery();
                paymentOrderQuery.setOrderNo(orderJson.getString("orderNo"));
                String order = paymentService.getOrderByOrderNo(paymentOrderQuery,payUserDo);

                String paySuccess = paymentService.paymentSuccess(paymentOrderQuery,payUserDo);
                JSONObject paySuccessObject = JSON.parseObject(paySuccess);
                JSONObject paySuccessJson = (JSONObject) JSON.parseObject(paySuccessObject.getString("data")).getJSONArray("data").get(0);

                JSONObject orderDetail = JSON.parseObject(order);
                JSONObject detailJson = (JSONObject) JSON.parseObject(orderDetail.getString("data")).getJSONArray("data").get(0);

                model.addAttribute("paySuccess",paySuccessJson);
                model.addAttribute("order",detailJson);
                return "order/coupon";
            }


        }else {
            PaymentOrderQuery paymentOrderQuery = new PaymentOrderQuery();
            BeanUtils.copyProperties(commodityOrderQuery,paymentOrderQuery);
            orderResult =paymentService.getOrderByOrderNo(paymentOrderQuery, payUserDo);
            orderObject = JSON.parseObject(orderResult);
            orderJson = (JSONObject) JSON.parseObject(orderObject.getString("data")).getJSONArray("data").get(0);
        }

        String balanceResult = paymentService.getBalanceInfoByUserId(payUserDo);
        JSONObject balanceObject = JSON.parseObject(balanceResult);

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
//    @RequestMapping(value = "/paymentCommodityOrder", method = RequestMethod.GET)
//    public String paymentCommodityOrder(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery, Model model){
//
//        String payOrder = paymentService.paymentCommodityOrder(request, paymentOrderQuery);
//        JSONObject payOrderObject = JSON.parseObject(payOrder);
//        JSONObject payOrderJson = JSON.parseObject(payOrderObject.getString("data"));
//        System.out.println(payOrderJson);
//        model.addAttribute("payOrder",payOrderJson);
//
//        return "";
//    }


    @RequestMapping(value = "/paymentCommodityOrder", method = RequestMethod.GET)
    @ResponseBody
    public NashResult paymentCommodityOrder(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery, Model model){

        String payOrder = paymentService.paymentCommodityOrder(request, paymentOrderQuery);

        return NashResult.build(payOrder);
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

        UserPay user=UserPay.getCurrent();
        if (null==user.getUserId())
        {
            model.addAttribute("backUrl",request.getRequestURL());
            return "/user/login";
        }
        PayUserDo payUserDo=new PayUserDo();
        BeanUtils.copyProperties(user,payUserDo);

        String order = paymentService.getOrderByOrderNo(paymentOrderQuery,payUserDo);

        String paySuccess = paymentService.paymentSuccess(paymentOrderQuery,payUserDo);
        JSONObject paySuccessObject = JSON.parseObject(paySuccess);
        JSONObject paySuccessJson = (JSONObject) JSON.parseObject(paySuccessObject.getString("data")).getJSONArray("data").get(0);

        JSONObject orderObject = JSON.parseObject(order);
        JSONObject orderJson = (JSONObject) JSON.parseObject(orderObject.getString("data")).getJSONArray("data").get(0);

        model.addAttribute("paySuccess",paySuccessJson);
        model.addAttribute("order",orderJson);
        return "order/coupon";
    }

    /**
     * 支付
     * @param request
     * @param paymentRequest
     * @return
     */
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    @ResponseBody
    public Object payment(HttpServletRequest request, @Validated /*@RequestBody*/ PaymentRequest paymentRequest) {
        PaymentDoQuery paymentDoQuery = new PaymentDoQuery();
        BeanUtils.copyProperties(paymentRequest, paymentDoQuery);
        String form = paymentService.payment(request, paymentDoQuery);
        JSONObject jsonObject = JSON.parseObject(form);
        Object data = jsonObject.get("data");
        return data;
    }

    /**
     * 完成未支付的订单
     * @param request
     * @param unpaymentRequest
     * @return
     */
    @RequestMapping(value = "/unPayment",method = RequestMethod.GET)
    @ResponseBody
    public Object unPayment(HttpServletRequest request, @Validated UnpaymentRequest unpaymentRequest){
        UnpaymentDoQuery unpaymentDoQuery = new UnpaymentDoQuery();
        BeanUtils.copyProperties(unpaymentRequest, unpaymentDoQuery);
        String form = paymentService.unPayment(request, unpaymentDoQuery);
        JSONObject jsonObject = JSON.parseObject(form);
        Object data = jsonObject.get("data");
        return data;
    }

    /**
     * 支付页面
     * @return
     */
    @RequestMapping(value = "/recharge",method = RequestMethod.GET)
    public String recharge(@Validated RechargeRequest rechargeRequest, Model model){
        model.addAttribute("recharge",rechargeRequest);
        return "/order/recharge";
    }

    @RequestMapping("/test")
    public String test(){
        return "order/pay";
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
    /**
     * 小鹿测试页面(广告页面流程、入口)
     * @param model
     * @return
     */
    @RequestMapping("/process")
    public String process(Model model) {

        return "order/process";
    }
}
