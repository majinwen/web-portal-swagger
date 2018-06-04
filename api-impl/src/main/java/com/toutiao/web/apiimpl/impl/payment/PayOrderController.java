package com.toutiao.web.apiimpl.impl.payment;

import com.alibaba.fastjson.JSON;
import com.toutiao.web.apiimpl.authentication.UserPay;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.domain.payment.PayBuyRecordDo;
import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayOrderQuery;
import com.toutiao.web.domain.payment.PayUserDo;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/{citypath}/payOrder")
public class PayOrderController {
    @Autowired
    private PaymentService paymentService;
    //我的订单类型
    private  final  Integer ORDER_TYPE=2;
    //訂單狀態
    private  final  Integer ORDER_STATUS=2;

    /**
     *我的订单
     * @param model
     * @param payOrderQuery
     * @return
     */

    @RequestMapping(value = "order/getMyOrder",method = RequestMethod.GET)
    public  String getOrderByUser(Model model,PayOrderQuery payOrderQuery,HttpServletRequest request)
    {
        UserPay user=UserPay.getCurrent();
        if (null==user.getUserId())
        {
            model.addAttribute("backUrl",request.getRequestURL());
            return "/user/login";
        }
        List<PayOrderDo> payOrderDos=getMyOrder(ORDER_TYPE,payOrderQuery,user,null);
        model.addAttribute("payOrderDos",payOrderDos);
        return "order/order";
    }

    /**
     * 我的明細
     */
    @RequestMapping(value = "order/getMyCharge",method = RequestMethod.GET)
    public String getChargeByUser(Model model, PayOrderQuery payOrderQuery,HttpServletRequest request)
    {
        UserPay user=UserPay.getCurrent();
        if (null==user.getUserId())
        {
            model.addAttribute("backUrl",request.getRequestURL());
            return "/user/login";
        }

        List<PayOrderDo> payOrderDos=getMyOrder(null,payOrderQuery,user,ORDER_STATUS);
        model.addAttribute("payOrderDos",payOrderDos);

        return "order/detailed";
    }

    /**
     * 订单公共方法
     * @param type
     * @param payOrderQuery
     * @return
     */
    private  List<PayOrderDo> getMyOrder(Integer type, PayOrderQuery payOrderQuery,UserPay user,Integer status)
    {

        PayUserDo payUserDo=new PayUserDo();
        BeanUtils.copyProperties(user,payUserDo);
        List<PayOrderDo> payOrderDos=paymentService.getMyOrder(payOrderQuery,payUserDo,type,status);
        return payOrderDos;
    }




}
