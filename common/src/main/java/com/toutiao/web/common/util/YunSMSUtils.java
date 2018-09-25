package com.toutiao.web.common.util;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import java.util.Map;

/**
 * 云片短信调用工具
 * Created by CuiShihao on 2018/9/21
 */
public class YunSMSUtils {

    //apikey
    private static String apikey = "b24d4af5614de7850e39bbe7ae674f7d";

    public static YunpianClient getInstance() {
        return YunpianClientHolder.instance;
    }

    public static void main(String[] args) {
        YunSMSUtils yunSMSUtils = new YunSMSUtils();
        yunSMSUtils.sendSms("18722079068", "【懂房帝】远洋国际打折案场优惠领取成功。");
    }

    //发送短信
    public Result sendSms(String mobile, String text) {
        //获取YunpianClient实例
        YunpianClient client = YunSMSUtils.getInstance();
        //发送短信
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, mobile);
        param.put(YunpianClient.TEXT, text);
        Result<SmsSingleSend> result = client.sms().single_send(param);
        return result;
    }

    //单例模式获取YunpianClient(静态内部类加载)
    private static class YunpianClientHolder {
        private static YunpianClient instance = new YunpianClient(apikey).init();
    }


}
