package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.apiimpl.authentication.RedisSession;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@RestController
@RequestMapping(value = "/message")
public class MessageSendController {
    @Autowired
    private RedisSession redisSession;
    @Autowired
    private SMSUtils smsUtils;

    /**
     * 短信发送
     *
     * @return
     */
    @RequestMapping("/getCode")
    @ResponseBody
    public NashResult send(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "phone", required = true) String phone) {
        NashResult nashResult = new NashResult();
        String  sendSmsMessage=null;
        try {
            //保存手机到cookie中
            CookieUtils.setCookieexp(request,response,"secondsremainedphone",phone);
            //判断前台页面传递过来的手机号码是否存在
            //判断页面传递过来的电话号码与短信验证码是否为空
            if (StringTool.isBlank(phone)) {
                nashResult = NashResult.Fail("fail", "手机号不能为空！");
                return nashResult;
            }
            //判断传输过来的电话号码是否全是数字并且是否是手机号码
            if (!StringUtil.isCellphoneNo(phone)) {
                nashResult = NashResult.Fail("fail", "手机号格式有误,请重新输入！");
                return nashResult;
            }
            String code = StringUtil.randomFourDigits();
            //获取手机号码
              sendSmsMessage = smsUtils.sendSms(phone, code);
            if (StringTool.isNotEmpty(sendSmsMessage)) {
                // 请求成功,将用户的手机号码与短信验证码存入redis缓存中
                redisSession.set2(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + "_" + phone, code, RedisObjectType.USER_PHONE_VALIDATECODE.getExpiredTime());
                //记录每次发送一次验证码，缓存中相应的手机号码个数自增长
                redisSession.incr(phone + RedisNameUtil.separativeSignCount);
                //处理业务后续改一下
                String phoneCount = redisSession.getValue(phone + RedisNameUtil.separativeSignCount);
                nashResult = NashResult.build(phoneCount);
                return nashResult;
            } else{
                if(sendSmsMessage.equalsIgnoreCase("isv.BUSINESS_LIMIT_CONTROL")){
                    nashResult = NashResult.Fail("fail", "此号码频繁发送验证码，请稍后再试！");
                }else if(sendSmsMessage.equalsIgnoreCase("isv.MOBILE_NUMBER_ILLEGAL")){
                    nashResult = NashResult.Fail("fail", "非法手机号，请检查手机号码！");
                }
                return nashResult = NashResult.Fail("fail", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            nashResult = NashResult.Fail("fail", "短信发送异常");
            return nashResult;
        }
    }
}
