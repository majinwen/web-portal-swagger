package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;

public enum UserInterfaceErrorCodeEnum implements IntBaseType {

    /**
     * 新房枚举参数
     *
     */
    //系统短信息
    USER_LOGIN_EXCEPTION("用户登录异常", 30001),
    SAVE_COOKIE_ERROR("存储用户信息失败",30002);


    private String desc;
    private int value;

    UserInterfaceErrorCodeEnum(String desc, int value) {
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
