package com.toutiao.app.api.chance.request.sys;

import com.toutiao.web.common.assertUtils.PhoneNumber;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户手机+验证码登录
 * @author WuShoulei on 2018/2/5
 */
@Data
public class UserVerifyCodeRequest {

    /**
     * 用户手机号
     */
    @PhoneNumber( message = "手机号不正确")
    private String userName;

    /**
     * 动态验证码
     */
    @NotNull(message = "缺少验证码")
    private String verifyCode;

    /**
     * 登录方式
     */
    @NotNull(message = "登录类型不能为空")
    private Short identityType;

    /**
     * 图形验证码
     */
    private String imageCode;

    private String backUrl;

    private String title;
}
