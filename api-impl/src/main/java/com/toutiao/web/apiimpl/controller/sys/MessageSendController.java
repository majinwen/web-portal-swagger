package com.toutiao.web.apiimpl.controller.sys;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.toutiao.web.apiimpl.authentication.RedisSession;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@RestController
@RequestMapping(value = "/message")
public class MessageSendController {
    @Autowired
    private RedisSession redisSession;
    /**
     * 短信发送
     *
     * @return
     */
    @RequestMapping("/getCode")
    @ResponseBody
    public NashResult send(HttpServletRequest request, @RequestParam(value = "phone", required = true) String phone) {
        NashResult nashResult = new NashResult();
        try {
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
            SendSmsResponse sendSmsResponse = SMSUtils.sendSms(phone, code);
            if (sendSmsResponse.getCode() != null
                    && sendSmsResponse.getCode().equals("OK")) {
                // 请求成功,将用户的手机号码与短信验证码存入redis缓存中
                redisSession.set2(phone, MD5Util.computeUTF(MD5Util.computeUTF(RedisObjectType.USER_PHONE_VALIDATECODE.getPrefix() + RedisNameUtil.separativeSign
                                + code)),
                        RedisObjectType.USER_PHONE_VALIDATECODE.getExpiredTime());
                //记录每次发送一次验证码，缓存中相应的手机号码个数自增长
                redisSession.incr(phone + RedisNameUtil.separativeSignCount);
            } else if (sendSmsResponse.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")) {
                nashResult = NashResult.Fail("fail", "此号码频繁发送验证码，暂时不能获取！");
                return nashResult;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        /*//获取缓存中同一个电话号码发送次数传递到页面
        String phoneCount = redisSession.getValue(phone + RedisNameUtil.separativeSignCount);

        if (Integer.valueOf(phoneCount) > 3) {
            return NashResult.build(phoneCount);
        }*/
        return null;
    }
}
