package com.toutiao.web.common.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SendConfig {

    //发送短信模板
    //AccessKey
    @Value("${send.AccessKeyID}")
    private String accessKeyId;
    //密钥
    @Value("${send.AccessKey}")
    private String accessKeySecret;
    //签名
    @Value("${send.SignName}")
    private String signName;
    //模板id
    @Value("${send.TemplateCode}")
    private String templateCode;
    @Value("${send.product}")
    private String product;
    @Value("${send.domain}")
    private String domain;


    /**
     * 云信sms
     *
     */
    @Value("${yunpian.send.apikey}")
    private String apiKey;

    @Value("${yunpian.user.template}")
    private String userTemplate;

    @Value("${yunpian.agent.template}")
    private String agentTemplate;
}
