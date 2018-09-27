package com.toutiao.web.common.util;


import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.toutiao.web.common.httpUtil.HttpUtils.post;

/**
 * 云片短信调用工具
 * Created by CuiShihao on 2018/9/21
 */
@Component
public class YunSMSUtils {


    private SendConfig sendConfig;

    public YunSMSUtils(SendConfig sendConfig) {
        this.sendConfig = sendConfig;
    }


    private String ENCODING = "UTF-8";


    /**
     * 发送优惠活动短信接口
     * @param mobile  手机号
     * @param code  发送对象表示码
     * @param userCallName  用户称呼名
     * @param activityBuildingName  参与活动楼盘名称
     * @return
     * @throws UnsupportedEncodingException
     */
    public String sendActivitySms(String mobile, Integer code, String userCallName, String activityBuildingName) throws UnsupportedEncodingException {
        //发送短信
        Map<String, String> params = new HashMap<>();
        params.put("apikey", sendConfig.getApiKey());
        params.put("mobile", mobile);
        /**
         * 1表示用户  2表示销售
         */
        if (code == 1) {
            params.put("tpl_id", sendConfig.getUserTemplate());
            params.put("tpl_value", URLEncoder.encode("#activityBuildingName#", ENCODING) + "=" + URLEncoder.encode(activityBuildingName, ENCODING));
        } else if (code == 2) {
            params.put("tpl_id", sendConfig.getAgentTemplate());
            String tpl_value = URLEncoder.encode("#activityBuildingName#", ENCODING) + "=" +
                    URLEncoder.encode(activityBuildingName, ENCODING) + "&" + URLEncoder.encode("#userCallName#", ENCODING) + "=" + URLEncoder.encode(userCallName, ENCODING);
            params.put("tpl_value", tpl_value);
        }
        return post("https://sms.yunpian.com/v2/sms/tpl_batch_send.json", params);
    }
}
