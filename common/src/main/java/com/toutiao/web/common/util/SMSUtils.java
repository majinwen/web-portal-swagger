package com.toutiao.web.common.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


/**
 * 阿里云短信调用工具类
 */
public class SMSUtils {
    //发送短信模板
    //AccessKey
    /*@Value("${send.AccessKeyID}")
    private static String accessKeyId;
    //密钥
    @Value("${send.AccessKey}")
    private static String accessKeySecret;
    //签名
    @Value("${send.SignName}")
    private static String signName;
    //模板id
    @Value("${send.TemplateCode}")
    private static String templateCode;*/
    private static String accessKeyId="LTAIYnJWqB91gkG2";
    //密钥
    private static String accessKeySecret="sGSWWL4g88RkPFNPF99Y4SFaHXrx12";
    //签名
    private static String signName="井潇泽";
    //模板id
    private static String templateCode="SMS_115950496";
    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";





    public static SendSmsResponse sendSms(String phone,String code) throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
                domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        System.out.println(sendSmsResponse.getCode());

        return sendSmsResponse;
    }

    public static void main(String[] args) throws ClientException,
            InterruptedException {

        // 发短信
        SendSmsResponse response = sendSms("15601676403","1234");
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

    }
}
