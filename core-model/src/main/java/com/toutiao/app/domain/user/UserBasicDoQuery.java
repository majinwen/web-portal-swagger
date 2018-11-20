package com.toutiao.app.domain.user;

import lombok.Data;


@Data
public class UserBasicDoQuery {

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 动态验证码
     */
    private String verifyCode;

    /**
     * 登录方式
     */
    private short identityType;

    /**
     * 图形验证码
     */
    private String imageCode;


    private String backUrl;

    private String title;

}
