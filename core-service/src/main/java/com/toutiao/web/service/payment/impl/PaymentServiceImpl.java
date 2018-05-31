package com.toutiao.web.service.payment.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.httpUtil.HttpUtils;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.*;
import com.toutiao.web.common.util.jwt.JsonWebTokenUtil;
import com.toutiao.web.domain.payment.CommentDo;
import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.domain.payment.PaymentOrderQuery;
import com.toutiao.web.service.payment.PaymentService;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
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
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.newhouse.index}")
    private String newHouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newHouseType;//索引类型

    /**
     * 生成商品购买订单
     * @param commodityOrderQuery
     * @return
     */
    @Override
    public String saveCommodityOrder(HttpServletRequest request, CommodityOrderQuery commodityOrderQuery) {

        //获取用户信息
        TransportClient client = esClientTools.init();
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        String result = "";
        if(StringUtil.isNotNullString(user)){
            Map map = JSON.parseObject(user);
            UserBasicDo userBasic =userBasicInfoService.queryUserBasic(map.get("userId").toString());

            //组装请求header
            Map<String,String> header = new HashMap<>();
            String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),user,ServiceStateConstant.TTLMILLIS);
            header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);

            //组合参数
            Map<String, Object> paramsMap = new HashMap<>();
            GetResponse agentBaseResponse = client.prepareGet(newHouseIndex,newHouseType,commodityOrderQuery.getBuildingId().toString()).execute().actionGet();
            CommentDo commentDo = new CommentDo();
            commentDo.setBuildingId(commodityOrderQuery.getBuildingId());
            commentDo.setBuildingName(agentBaseResponse.getSourceAsMap().get("building_name")==null?"":agentBaseResponse.getSourceAsMap().get("building_name").toString());
            commentDo.setBuildingTitleImg(agentBaseResponse.getSourceAsMap().get("building_title_img")==null?"":agentBaseResponse.getSourceAsMap().get("building_title_img").toString());
            paramsMap.put("comment",JSON.toJSONString(commentDo));
            paramsMap.put("productNo",commodityOrderQuery.getProductNo());
            paramsMap.put("userId",userBasic.getUserId());
            paramsMap.put("userName",userBasic.getUserName());
            paramsMap.put("phone",userBasic.getPhone());
            paramsMap.put("type", ServiceStateConstant.ORDER_TYPR_CONSUNE);

            //发起请求
            result = HttpUtils.post(payDomain+ServiceStateConstant.SAVE_ORDER,header,paramsMap);
        }else{
            Integer noLogin = UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue();
            NashResult<Object> nashResult = NashResult.Fail(noLogin.toString(),"用户未登陆");
            result = JSONObject.toJSONString(nashResult);
        }

        return result;
    }

    /**
     * 获取用户余额信息
     * @param request
     * @return
     */
    @Override
    public String getBalanceInfoByUserId(HttpServletRequest request) {

        //获取用户信息
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        String result = "";
        if(StringUtil.isNotNullString(user)){
            Map map = JSON.parseObject(user);
            if(StringTool.isNotEmpty(map.get("userId"))){

                //组装请求header
                Map<String,String> header = new HashMap<>();
                String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),user,ServiceStateConstant.TTLMILLIS);
                header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);
                //组合参数
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("userId",map.get("userId").toString());

                //发起请求
                result = HttpUtils.get(payDomain+ServiceStateConstant.GET_BALANCEINFO_USERID,header,paramsMap);
            }


        }else{
            Integer noLogin = UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue();
            NashResult<Object> nashResult = NashResult.Fail(noLogin.toString(),"用户未登陆");
            result = JSONObject.toJSONString(nashResult);
        }

        return result;
    }

    /**
     * 完成商品购买订单
     * @param request
     * @param paymentOrderQuery
     * @return
     */
    @Override
    public String paymentCommodityOrder(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery) {

        //获取用户信息
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        String result = "";
        if(StringUtil.isNotNullString(user)){
            Map map = JSON.parseObject(user);
            if(StringTool.isNotEmpty(map.get("userId"))){

                //组装请求header
                Map<String,String> header = new HashMap<>();
                String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),user,ServiceStateConstant.TTLMILLIS);
                header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);
                //组合参数
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("userId",map.get("userId").toString());
                paramsMap.put("orderNo",paymentOrderQuery.getOrderNo());

                //发起请求
                result = HttpUtils.post(payDomain+ServiceStateConstant.PAYMENT_ORDER,header,paramsMap);
            }
        }else{
            Integer noLogin = UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue();
            NashResult<Object> nashResult = NashResult.Fail(noLogin.toString(),"用户未登陆");
            result = JSONObject.toJSONString(nashResult);
        }

        return result;
    }
}
