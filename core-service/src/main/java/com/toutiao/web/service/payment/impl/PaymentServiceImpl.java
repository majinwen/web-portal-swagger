package com.toutiao.web.service.payment.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.httpUtil.HttpUtils;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.NashBeanUtils;
import com.toutiao.web.common.util.ServiceStateConstant;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.jwt.JsonWebTokenUtil;
import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${tt.payment.domain}")
    private String payDomain;
    @Autowired
    private UserBasicInfoService userBasicInfoService;

    /**
     * 生成商品购买订单
     * @param commodityOrderQuery
     * @return
     */
    @Override
    public String saveCommodityOrder(HttpServletRequest request, CommodityOrderQuery commodityOrderQuery) {

        //获取用户信息
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        String result = "";
        if(StringUtil.isNotNullString(user)){
            Map map = JSON.parseObject(user);
            UserBasicDo userBasic =userBasicInfoService.queryUserBasic(map.get("userId").toString());

            //组装请求header
            Map<String,String> header = new HashMap<>();
            String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),user,600000);
            header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);

            //组合参数
            Map<String, Object> paramsMap = new HashMap<>();
            if(StringUtil.isNotNullString(commodityOrderQuery.getComment())){
                paramsMap.put("comment",commodityOrderQuery.getComment());
            }
            paramsMap.put("productNo",commodityOrderQuery.getProductNo());
            paramsMap.put("userId",userBasic.getUserId());
            paramsMap.put("userName",userBasic.getUserName());
            paramsMap.put("phone",userBasic.getPhone());
            paramsMap.put("type", ServiceStateConstant.ORDER_TYPR_CONSUNE);

            //发起请求
            result = HttpUtils.post(payDomain+ServiceStateConstant.SAVE_ORDER,header,paramsMap);
        }else{
            NashResult<Object> sss = NashResult.Fail("11","22");
            result = JSONObject.toJSONString(sss);
        }

        return result;
    }
}
