package com.toutiao.web.common.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


/**
 * 阿里云短信调用工具类
 */
@Component
public class SMSUtils {

    private SendConfig sendConfig;

    //    private static String accessKeyId="LTAIciCN1xtYlVH5";
//    //密钥
//    private static String accessKeySecret="DW7DJHCHkEZGTuuyV8EExktAoKAXXD";
//    //签名
//    private static String signName="头条房产";
//    //模板id
//    private static String templateCode="SMS_117518379";
    // 产品名称:云通信短信API产品,开发者无需替换
//    static final String product = "Dysmsapi";
//    // 产品域名,开发者无需替换
//    static final String domain = "dysmsapi.aliyuncs.com";
    public SMSUtils(SendConfig sendConfig) {
        this.sendConfig = sendConfig;
    }

    public String sendSms(String phone, String code) throws ClientException {
        SendSmsResponse sendSmsResponse = null;
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                sendConfig.getAccessKeyId(), sendConfig.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", sendConfig.getProduct(),
                sendConfig.getDomain());
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("头条房产");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(sendConfig.getTemplateCode());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null
                && sendSmsResponse.getCode().equals("OK")) {
            //发送成功
            return null;
        }
        return sendSmsResponse.getMessage();
    }

    public static void main(String[] args) throws ClientException,
            InterruptedException {

//        // 发短信
//        SendSmsResponse response = sendSms("15601676403","1234");
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + response.getCode());
//        System.out.println("Message=" + response.getMessage());
//        System.out.println("RequestId=" + response.getRequestId());
//        System.out.println("BizId=" + response.getBizId());

    }
}
