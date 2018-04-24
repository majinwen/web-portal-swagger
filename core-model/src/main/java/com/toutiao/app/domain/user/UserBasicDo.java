package com.toutiao.app.domain.user;

import com.toutiao.web.common.assertUtils.First;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserBasicDo {

    /**
     * ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户信息状态 1-有效，0-无效
     */
    private Short userStatus;

    /**
     * 1-WAP，2-APP
     */
    private Short registerSource;

    /**
     * 登录类型 1-手机号，2-邮箱，3-用户名，4-微信
     */
    private Short identityType;

    /**
     * 登录类型标识 e.g.(注册填入的 手机号码，邮箱，用户名称等)
     */
    private String identifier;

    /**
     * 融云token标识
     */
    private String rongCloudToken;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 用户唯一标志
     */
    private String userOnlySign;

    /**
     * 图片验证码
     */
    private String imageCode;

}
