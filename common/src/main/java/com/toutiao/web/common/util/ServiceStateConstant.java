package com.toutiao.web.common.util;

public class ServiceStateConstant {


    /**
     * 短信业务扩展字段————登录/注册
     */
    public static final String ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER = "LOGIN_REGISTER";
    /**
     * 短信业务扩展字段————账号注册/登录微信绑定
     */
    public static final String ALIYUN_SHORT_MESSAGE_BIND_WX_REGISTER = "BIND_REGISTER";
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

    /**
     * APP端发送短信
     */
    public static final Integer SEND_MESSAGE_APP = 1;

    /**
     * WAP端发送短信
     */
    public static final Integer SEND_MESSAGE_WAP = 2;

    //==============支付===========
    /**
     * 充值
     */
    public static final Integer ORDER_TYPR_RECHARGE = 1;
    /**
     * 消费
     */
    public static final Integer ORDER_TYPR_CONSUNE = 2;
    /**
     * 支付请求header
     */
    public static final String PAYMENT_HEADER = "toutiaopc";

    /**
     * 生成商品购买订单
     */
    public static final String SAVE_ORDER = "/order/saveOrder";

    /**
     * 获取用户余额信息
     */
    public static final String GET_BALANCEINFO_USERID = "/balance/getBalanceInfoByUserId";


    /**
     * 完成商品购买订单
     */
    public static final String PAYMENT_ORDER = "/order/updateOrderToFinish";


    /**
     * 完成商品购买订单
     */
    public static final String ORDER_BY_ORDERNO = "/order/getOrderByOrderNo";

    /**
     * 完成商品购买订单
     */
    public static final String PURCHASE_HISTORY_ORDERNO= "/purchaseHistory/getPurchaseHistoryByOrderNo";

    /**
     * 请求订单过期时间
     */
    public static final Long TTLMILLIS = 600000L;


    /**
     * 订单
     */
    public static final String  PAY_ORDER = "/order/getHistoricalOrders";

    /**
     * 生成支付订单
     */
    public static final String SAVE_PAY_ORDER="/paycenter/savePayOrder";
    /**
     * 继续未完成支付
     */
    public static final String SAVE_REPAY_ORDER="/paycenter/saveRePayOrder";





}
