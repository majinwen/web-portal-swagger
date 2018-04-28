package com.toutiao.app.service.sys.impl;

import com.aliyuncs.exceptions.ClientException;
import com.toutiao.app.service.sys.ShortMessageService;
import com.toutiao.web.common.constant.syserror.ShortMessageInterfaceErrorCodeEnum;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信验证码接口服务
 *
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ShortMessageServiceImpl.class);
    @Autowired
    private RedisSessionUtils redis;

    @Autowired
    private SMSUtils smsUtils;

    /**
     * 短信验证码发送接口
     * @param phone
     * @return
     */
    @Override
    public NashResult sendVerifyCode(String phone) {
        //生成随机4位短信验证码
        String code = StringUtil.randomFourDigits();
        try {
            String sendResult = smsUtils.sendSms(phone, code);

            if (null != sendResult && "OK".equals(sendResult)){
                //短信验证码发送成功，保存至redis以用于校验，有效期2分钟
                redis.set2(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER+"_"+phone, code, RedisObjectType.USER_PHONE_VALIDATECODE.getExpiredTime());
                redis.incr(phone + RedisNameUtil.separativeSignCount);
                String phoneCount = redis.getValue(phone + RedisNameUtil.separativeSignCount);
                NashResult nashResult = NashResult.build(phoneCount);
                return nashResult;
            }else if ("isv.BUSINESS_LIMIT_CONTROL".equals(sendResult)) {//超出短信发送限制

                return NashResult.Fail(ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_LIMIT.toString());
            }else {//其它返回码

                return NashResult.Fail(ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_SEND_ERROR.toString(),sendResult);
            }
        } catch (ClientException e) {
            logger.error("短信发送失败，请检查短信平台相关配置");
            return NashResult.Fail(ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_PLATFORM_EXCEPTION.toString(),e.getMessage());
        }
    }
}
