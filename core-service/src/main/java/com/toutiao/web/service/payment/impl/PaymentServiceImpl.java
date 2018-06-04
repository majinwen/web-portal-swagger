package com.toutiao.web.service.payment.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.httpUtil.HttpUtils;
import com.toutiao.web.common.util.jwt.JsonWebTokenUtil;
import com.toutiao.web.domain.payment.PayBuyRecordDo;
import com.toutiao.web.domain.payment.PayOrderDo;
import com.toutiao.web.domain.payment.PayOrderQuery;
import com.toutiao.web.domain.payment.PayUserDo;

import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.*;
import com.toutiao.web.domain.payment.CommentDo;
import com.toutiao.web.domain.payment.CommodityOrderQuery;
import com.toutiao.web.domain.payment.PaymentOrderQuery;
import com.toutiao.web.service.payment.PaymentService;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


@Service
public class PaymentServiceImpl implements PaymentService {
    private Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
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

    @Override
    public List<PayBuyRecordDo> getBuyRecordByUserId(PayOrderQuery payOrderQuery,PayUserDo payUserDo) {
        List<PayBuyRecordDo>  payOrderDos= new ArrayList<>();
        JSONObject jsonObject=new JSONObject();
        String json=JSON.toJSONString(payUserDo);
        Map<String,String> header = new HashMap<>();
        String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),json,ServiceStateConstant.TTLMILLIS);
        header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userIds",payUserDo.getUserId());
        paramsMap.put("pageNum",payOrderQuery.getPageNum());
        paramsMap.put("pageSize",payOrderQuery.getPageSize());
        try {
            jsonObject= JSONObject.parseObject(HttpUtils.get(payDomain+ServiceStateConstant.BUY_RECORDER,header,paramsMap));
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
        Map<String,String> header = new HashMap<>();
        String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),json,ServiceStateConstant.TTLMILLIS);
        header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userName",payUserDo.getUserName());
        paramsMap.put("type",type);
        paramsMap.put("pageNum",payOrderQuery.getPageNum());
        paramsMap.put("pageSize",payOrderQuery.getPageSize());
        try {
            jsonObject= JSONObject.parseObject(HttpUtils.get(payDomain+ServiceStateConstant.PAY_ORDER,header,paramsMap));
            JSONObject object=(JSONObject) jsonObject.get("data");
            if (object.size()==0)
            {
                payOrderDos=null;
                return payOrderDos;
            }
            payOrderDos =JSON.parseArray(object.get("data").toString(),PayOrderDo.class);
            for(PayOrderDo p :payOrderDos)
            {
                if (p.getType()==2)
                {
                    p.setCommentDo(JSON.parseObject(p.getComment(),CommentDo.class));
                }
            }
        }catch (Exception e)
        {
            logger.error("获取用户订单失败,userId:"+payUserDo.getUserId()+"={}",e.getStackTrace());
        }

       return payOrderDos;
    }




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
            if(result == null){
                logger.error("发起生成商品购买订单请求失败,userId:"+userBasic.getUserId()+"=productNo:"+commodityOrderQuery.getProductNo());
//                NashResult<Object> nashResult = NashResult.Fail("800001","发起生成商品购买订单请求失败,userId:"+userBasic.getUserId()+";productNo:"+commodityOrderQuery.getProductNo());
//                result = JSONObject.toJSONString(nashResult);
//                return result;
            }

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
                if(result == null){
                    logger.error("获取用户余额信息请求失败,userId:"+ map.get("userId"));
                    NashResult<Object> nashResult = NashResult.Fail("800002","获取用户余额信息请求失败,userId:"+map.get("userId"));
                    result = JSONObject.toJSONString(nashResult);
                    return result;
                }
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
                if(result == null){
                    logger.error("发起生成商品购买订单请求失败,userId:"+map.get("userId")+"=orderNo:"+paymentOrderQuery.getOrderNo());
//                    NashResult<Object> nashResult = NashResult.Fail("800003","发起生成商品购买订单请求失败,userId:"+map.get("userId")+";orderNo:"+paymentOrderQuery.getOrderNo());
//                    result = JSONObject.toJSONString(nashResult);
//                    return result;
                }
            }
        }else{
            Integer noLogin = UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue();
            NashResult<Object> nashResult = NashResult.Fail(noLogin.toString(),"用户未登陆");
            result = JSONObject.toJSONString(nashResult);
        }

        return result;
    }


    @Override
    public String getOrderByOrderNo(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery) {
        //获取用户信息
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        String result = "";
        if(StringUtil.isNotNullString(user)){
            Map map = JSON.parseObject(user);

            //组装请求header
            Map<String,String> header = new HashMap<>();
            String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),user,ServiceStateConstant.TTLMILLIS);
            header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);
            //组合参数
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("orderNo",paymentOrderQuery.getOrderNo());

            //发起请求
            result = HttpUtils.get(payDomain+ServiceStateConstant.ORDER_BY_ORDERNO,header,paramsMap);
            if(result == null){
                logger.error("发起根据订单编号获取订单详情请求失败,userId:"+map.get("userId")+"=orderNo:"+paymentOrderQuery.getOrderNo());
//                    NashResult<Object> nashResult = NashResult.Fail("800003","发起生成商品购买订单请求失败,userId:"+map.get("userId")+";orderNo:"+paymentOrderQuery.getOrderNo());
//                    result = JSONObject.toJSONString(nashResult);
//                    return result;
            }

        }else{
            Integer noLogin = UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue();
            NashResult<Object> nashResult = NashResult.Fail(noLogin.toString(),"用户未登陆");
            result = JSONObject.toJSONString(nashResult);
        }

        return result;
    }



    /**
     * 支付成功，返回订单信息
     * @param request
     * @param paymentOrderQuery
     * @return
     */
    @Override
    public String paymentSuccess(HttpServletRequest request, PaymentOrderQuery paymentOrderQuery) {
        //获取用户信息
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        String result = "";
        if(StringUtil.isNotNullString(user)){
            //组装请求header
            Map<String,String> header = new HashMap<>();
            String jwtToken = JsonWebTokenUtil.createJWT(String.valueOf(System.currentTimeMillis()),user,ServiceStateConstant.TTLMILLIS);
            header.put(ServiceStateConstant.PAYMENT_HEADER,jwtToken);
            //组合参数
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("orderNo",paymentOrderQuery.getOrderNo());

            //发起请求
            result = HttpUtils.get(payDomain+ServiceStateConstant.PURCHASE_HISTORY_ORDERNO,header,paramsMap);
            if(result == null){
                logger.error("发起根据订单编号获取购买记录请求失败,orderNo:"+paymentOrderQuery.getOrderNo());
//                NashResult<Object> nashResult = NashResult.Fail("800004","发起根据订单编号获取购买记录请求失败,orderNo:"+paymentOrderQuery.getOrderNo());
//                result = JSONObject.toJSONString(nashResult);
//                return result;
            }

        }else{
            Integer noLogin = UserInterfaceErrorCodeEnum.USER_NO_LOGIN.getValue();
            NashResult<Object> nashResult = NashResult.Fail(noLogin.toString(),"用户未登陆");
            result = JSONObject.toJSONString(nashResult);
        }

        return result;
    }
}
