package com.toutiao.app.service.sys.impl;

import com.aliyuncs.exceptions.ClientException;
import com.toutiao.app.domain.activity.UserNewBuildingActivity;
import com.toutiao.app.service.sys.ShortMessageService;
import com.toutiao.web.common.constant.syserror.ShortMessageInterfaceErrorCodeEnum;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 短信验证码接口服务
 *
 */
@Service
public class ShortMessageServiceImpl implements ShortMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ShortMessageServiceImpl.class);

    @Value("${activity.proj.agent.phones}")
    private String smsPhone;

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

        Integer exceptionCode = 0;
        if(phone.equals("13900000000")){
            NashResult nashResult = NashResult.build("1");
            return nashResult;
        }
        //生成随机4位短信验证码
        String code = StringUtil.randomFourDigits();
        try {
            String sendResult = smsUtils.sendSms(phone, code,ServiceStateConstant.SEND_MESSAGE_APP);

            if (null != sendResult && "OK".equals(sendResult)) {
                //短信验证码发送成功，保存至redis以用于校验，有效期2分钟
                redis.set2(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + "_" + phone, code, RedisObjectType.USER_PHONE_VALIDATECODE.getExpiredTime());
                redis.incr(phone + RedisNameUtil.separativeSignCount);
                String phoneCount = redis.getValue(phone + RedisNameUtil.separativeSignCount);
                NashResult nashResult = NashResult.build(phoneCount);
                return nashResult;
            } else if ("isv.BUSINESS_LIMIT_CONTROL".equals(sendResult)) {//超出短信发送限制
                exceptionCode = ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_LIMIT.getValue();
                return NashResult.Fail(exceptionCode.toString(),"短信验证码发送过于频繁或已超出限制");
            } else {//其它返回码
                exceptionCode = ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_SEND_ERROR.getValue();
                return NashResult.Fail(exceptionCode.toString(), sendResult);
            }
        } catch (ClientException e) {
            logger.error("短信发送失败，请检查短信平台相关配置");
            exceptionCode = ShortMessageInterfaceErrorCodeEnum.SHORT_MESSAGE_PLATFORM_EXCEPTION.getValue();
            return NashResult.Fail(exceptionCode.toString(), e.getMessage());
        }

    }


    /**
     * 发送优惠活动短信信息
     * @param userPhone
     * @param userNewBuildingActivity
     * @return
     */
    @Override
    public NashResult sendSmsByActivity(String userPhone, UserNewBuildingActivity userNewBuildingActivity) {


        String activityBuildingName = userNewBuildingActivity.getActivityBuildingName();
        String userCallName = userNewBuildingActivity.getUserCallName();
        //发送短信
        try {
            //发送短信给用户
            String userCode = smsUtils.sendActivitySms(smsPhone,activityBuildingName,"",1);
            if (null != userCode && "OK".equals(userCode)) {
                logger.info("发送用户短信息成功，电话："+userPhone+"={}");
            }else if ("isv.BUSINESS_LIMIT_CONTROL".equals(userCode)) {
                logger.error("发送用户短信息过于频繁或已超出限制，电话："+userPhone+"={}");
            }else{
                logger.error("发送用户短信息失败，电话："+userPhone+"={}");
            }

            //发送短信给经纪人
            userCallName = userCallName+userNewBuildingActivity.getUserPhone();
            String agentCode = smsUtils.sendActivitySms(smsPhone,activityBuildingName,userCallName,2);
            if (null != agentCode && "OK".equals(agentCode)) {
                logger.info("发送经纪人短信息成功，电话："+userPhone+"={}");
            }else if ("isv.BUSINESS_LIMIT_CONTROL".equals(userCode)) {
                logger.error("发送经纪人短信息过于频繁或已超出限制，电话："+userPhone+"={}");
            }else{
                logger.error("发送经纪人短信息失败，电话："+userPhone+"={}");
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return NashResult.build("");
    }
}
