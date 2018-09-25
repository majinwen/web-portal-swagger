package com.toutiao.web.common.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.toutiao.web.common.httpUtil.HttpUtils.post;

/**
 * 云片短信调用工具
 * Created by CuiShihao on 2018/9/21
 */
public class YunSMSUtils {

//    @Autowired
//    private SendConfig sendConfig;

    //    @Value("${yunpian.send.apikey}")
    private String apikey = "b24d4af5614de7850e39bbe7ae674f7d";

    //    @Value("${yunpian.send.modelid1}")
    private String modelid1 = "2501662";

    //    @Value("${yunpian.send.modelid2}")
    private String modelid2 = "2501666";

    //    @Value("${yunpian.send.encoding}")
    private String ENCODING = "UTF-8";

    public static void main(String[] args) {
        YunSMSUtils yunSMSUtils = new YunSMSUtils();
        try {
            yunSMSUtils.sendSms("18722079068", 2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //发送短信
    public String sendSms(String mobile, Integer code) throws UnsupportedEncodingException {
        //发送短信
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        /**
         * 1表示用户  2表示销售
         */
        if (code == 1) {
            params.put("tpl_id", modelid1);
            params.put("tpl_value", URLEncoder.encode("#activityBuildingName#", ENCODING) + "=" + URLEncoder.encode("远洋山水", ENCODING));
        } else if (code == 2) {
            params.put("tpl_id", modelid2);
            String tpl_value = URLEncoder.encode("#activityBuildingName#", ENCODING) + "=" +
                    URLEncoder.encode("远洋山水", ENCODING) + "&" + URLEncoder.encode("#userCallName#", ENCODING) + "=" + URLEncoder.encode("张三", ENCODING);
            params.put("tpl_value", tpl_value);
        }
        return post("https://sms.yunpian.com/v2/sms/tpl_batch_send.json", params);
    }
}
