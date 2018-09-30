package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;

public enum ShortMessageInterfaceErrorCodeEnum implements IntBaseType {

    /**
     * 新房枚举参数
     *
     */
    //系统短信息
    IMAGE_CODE_MESSAGE_ERROR("图形验证码错误", 20006),
    SHORT_MESSAGE_ERROR("短信验证码错误", 20007),
    SHORT_MESSAGE_LIMIT("短信验证码发送过于频繁或已超出限制",20008),
    SHORT_MESSAGE_SEND_ERROR("短信验证码发送失败", 20009),
    SHORT_MESSAGE_PLATFORM_EXCEPTION("短信平台异常", 20010),
    SHORT_MESSAGE_QUERY_OVERRUN("短信验证码发送超出APP限制",20011),
    SHORT_MESSAGE_MOBILE_NUMBER_ILLEGAL("非法手机号", 20012);


    private String desc;
    private int value;

    ShortMessageInterfaceErrorCodeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
