package com.toutiao.web.common.util;

public class ServiceStateConstant {


    /**
     * 短信业务扩展字段————登录/注册
     */
    public static final String ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER = "LOGIN_REGISTER";
    /**
     * 用户基本信息有效性 1,有效 0，无效
     */
    public static final Short USER_BASIC_STATUS = 1;
    /**
     * 用户注册来源  APP
     */
    public static final Short USER_REGISTER_SOURCE_APP = 2;
    /**
     * 用户注册来源  WAP
     */
    public static final Short USER_REGISTER_SOURCE_WAP = 1;
    /**
     * 用户注册类型 手机号
     */
    public static final Short USER_REGISTER_IDENTITY_PHONE  = 1;
    /**
     * 用户注册类型 手机号
     */
    public static final Short USER_REGISTER_IDENTITY_EMAIL  = 2;
    /**
     * 用户注册类型 手机号
     */
    public static final Short USER_REGISTER_IDENTITY_USERNAME = 3;
    /**
     * 用户注册类型 手机号
     */
    public static final Short USER_REGISTER_IDENTITY_WECHAT  = 4;
    /**
     * 系统默认用户头像
     */
    public static final String[] SYS_USER_AVATAR  = {"195.png", "196.png","197.png","198.png","199.png","200.png",};
    /**
     * 系统头像随机值
     */
    public static final Integer RANDOM_AVATAR = 5;

}
