package com.toutiao.web.service.payment.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.service.favorite.impl.FavoriteRestServiceImpl;
import com.toutiao.web.common.httpUtil.HttpUtils;
import com.toutiao.web.common.util.jwt.JsonWebTokenUtil;
import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayBuyRecordQuery;
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
    public List<PayOrderDo> getBuyRecordByUserId(PayBuyRecordQuery payBuyRecordQuery, PayUserDo payUserDo) {
        List<PayOrderDo>  payOrderDos= new ArrayList<>();
        JSONObject jsonObject=new JSONObject();
        String json=JSON.toJSONString(payUserDo);
        String jwttoken = JsonWebTokenUtil.createJWT("xxoo112",json,60000);
        Map<String,String> header = new HashMap<>();
        header.put("toutiaopc",jwttoken);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userIds",payUserDo.getUserId());
        try {
            jsonObject= JSONObject.parseObject(HttpUtils.get(Url+"purchaseHistory/getPurchaseHistoryByUserId",header,paramsMap));
            JSONObject object=(JSONObject) jsonObject.get("data");
            if (object.size()==0)
            {
                payOrderDos=null;
                return payOrderDos;
            }
            payOrderDos =JSON.parseArray(object.get("data").toString(),PayOrderDo.class);

        }catch (Exception e)
        {
            logger.error("获取用户购买记录失败,userId:"+payUserDo.getUserId()+"={}",e.getStackTrace());
        }
        return  payOrderDos;
    }
}
