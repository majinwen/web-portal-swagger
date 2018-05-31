package com.toutiao.web.service.payment.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.httpUtil.HttpUtils;
import com.toutiao.web.common.util.jwt.JsonWebTokenUtil;
import com.toutiao.web.domain.payment.PayBuyRecordDo;
import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayOrderQuery;
import com.toutiao.web.domain.payment.PayUserDo;
import com.toutiao.web.service.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private  final String Url="http://47.95.10.4:8087/";

    @Override
    public List<PayBuyRecordDo> getBuyRecordByUserId(PayOrderQuery payOrderQuery,PayUserDo payUserDo) {
        List<PayBuyRecordDo>  payOrderDos= new ArrayList<>();
        JSONObject jsonObject=new JSONObject();
        String json=JSON.toJSONString(payUserDo);
        String jwtToken = JsonWebTokenUtil.createJWT("xxoo112",json,60000);
        Map<String,String> header = new HashMap<>();
        header.put("toutiaopc",jwtToken);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userIds",payUserDo.getUserId());
        paramsMap.put("pageNum",payOrderQuery.getPageNum());
        paramsMap.put("pageSize",payOrderQuery.getPageSize());
        try {
            jsonObject= JSONObject.parseObject(HttpUtils.get(Url+"purchaseHistory/getPurchaseHistoryByUserId",header,paramsMap));
            JSONObject object=(JSONObject) jsonObject.get("data");
            if (object.size()==0)
            {
                payOrderDos=null;
                return payOrderDos;
            }
            payOrderDos =JSON.parseArray(object.get("data").toString(),PayBuyRecordDo.class);

        }catch (Exception e)
        {
            logger.error("获取用户购买记录失败,userId:"+payUserDo.getUserId()+"={}",e.getStackTrace());
        }
        return  payOrderDos;
    }

    /**
     * 我的订单
     * @param payOrderQuery
     * @param payUserDo
     * @return
     */
    @Override
    public List<PayOrderDo> getMyOrder(PayOrderQuery payOrderQuery, PayUserDo payUserDo, Integer type) {

        List<PayOrderDo> payOrderDos=new ArrayList<>();
        JSONObject jsonObject=new JSONObject();
        String json=JSON.toJSONString(payUserDo);
        String jwtToken = JsonWebTokenUtil.createJWT("xxoo112",json,60000);
        Map<String,String> header = new HashMap<>();
        header.put("toutiaopc",jwtToken);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userName",payUserDo.getUserName());
        paramsMap.put("type",type);
        paramsMap.put("pageNum",payOrderQuery.getPageNum());
        paramsMap.put("pageSize",payOrderQuery.getPageSize());
        try {
            jsonObject= JSONObject.parseObject(HttpUtils.get(Url+"/order/getHistoricalOrders",header,paramsMap));
            JSONObject object=(JSONObject) jsonObject.get("data");
            if (object.size()==0)
            {
                payOrderDos=null;
                return payOrderDos;
            }
            payOrderDos =JSON.parseArray(object.get("data").toString(),PayOrderDo.class);
        }catch (Exception e)
        {
            logger.error("获取用户订失败,userId:"+payUserDo.getUserId()+"={}",e.getStackTrace());
        }

       return payOrderDos;
    }
}
