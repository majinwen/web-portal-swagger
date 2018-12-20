package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;

public enum UserInterfaceErrorCodeEnum implements IntBaseType {

    /**
     * 新房枚举参数
     *
     */
    //系统短信息
    USER_LOGIN_EXCEPTION("用户登录异常", 30001),
    SAVE_COOKIE_ERROR("存储用户信息失败",30002),
    UPDATE_USER_AVATAR_ERROR("更新用户头像失败",30003),
    QUERY_USER_BASIC_ERROR("用户不存在",30004),
    INVITATION_CODE_NOT_EXITS("邀请码不存在",30006),
    INVITATION_CODE_ADD_ERROR("邀请记录添加失败",30007),
    USER_NO_LOGIN("用户未登陆",30005),
    VERIFY_TOO_FAST("短信验证码发送过于频繁或已超出限制",30008),
    ILLEGAL_PHONE("非法手机号",30009),
    USER_NO_BIND_WX("用户未绑定微信",30010),
    INVITATION_CODE_ADD_YOURSELF("邀请人不能是自己！",30011);


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
