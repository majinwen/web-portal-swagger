package com.toutiao.web.common.util;

public class Constant {
    /**
     * 系统标记 (主要用于区分不同系统的缓存键名)
     */


    //常量数字0-9
    public static final int NUM_ZERO = 0;
    public static final int NUM_ONE = 1;
    public static final int NUM_TWO = 2;
    public static final int NUM_THREE = 3;
    public static final int NUM_FOUR = 4;
    public static final int NUM_FIVE = 5;
    public static final int NUM_SIX = 6;
    public static final int NUM_SEVEN = 7;
    public static final int NUM_EIGHT = 8;
    public static final int NUM_NINE = 9;

    public static final String SYS_FLAGS = "_phone_";

    // 字符数字
    public static final String STR_ONE_F = "-1";
    public static final String STR_ZERO = "0";
    public static final String STR_ONE = "1";
    public static final String STR_TWO = "2";
    public static final String STR_THREE = "3";
    public static final String STR_FOUR = "4";
    public static final String STR_FIVE = "5";
    public static final String SUCCESS = "成功";
    public static final String STR_ZERO_One = "01";

    /**
     * 登录错误次数
     */
    public static final Integer LOGIN_ERROR_TIMES = 3;

    /**
     * 返回的信息
     */
    public static final String ATT_MSG = "msg";
    ;


    public static final Integer LOGIN_OK = 0;                                                     //登录成功
    public static final Integer LOGIN_ERROR = 1;                                                     //登录失败，因为系统错误
    public static final Integer LOGIN_NAME_ERROR = 2;                                                     //登录失败，因为账户不存在
    public static final Integer LOGIN_PWD_ERROR = 3;                                                     //登录失败，因为账户和密码错误
    public static final Integer LOGIN_STATUS_ERROR = 4;                                                     //登录失败，账户被冻结
    public static final Integer LOGIN_TIMES_ERROR = 5;                                                     //登录失败，密码输入错误超过指定次数


    /**
     * 违规操作
     */
    public static final String FORGET_PWD_KEY_ERROR = "请输入正确的手机号或邮箱号码";
    public static final String MOBILE_NOT_VALID = "手机号码不合法";
    public static final String EMAIL_NOT_VALID = "邮箱号码不合法";
    public static final String USERNAME_NOT_VALID = "用户名不合法";
    public static final String PWD_NOT_VALID = "密码格式错误";
    public static final String EMAIL_NOT_BIND = "邮箱未绑定到用户";
    public static final String MOBILE_NOT_BIND = "手机未绑定到当前用户";
    public static final String VALICODE_ERROR = "验证码错误";


    /**
     * 通过
     */
    public static final String YES = "yes";
    /**
     * 不通过
     */
    public static final String NO = "no";
    /**
     * 报告页收藏标识
     */
    public static final String report = "428c7ff359c6ddfbd48e1f9eee30c6e4";

    /**
     * 报告页结果页标识
     */
    public static final String report_result = "6eb3e57271a43c300a5c7f49b8c11b24";

}
