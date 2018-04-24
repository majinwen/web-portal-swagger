package com.toutiao.web.common.constant.syserror;


import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * 系统级错误代码
 * 第1位为1，代表系统级错误，中间2位代表服务模块代码，最后2位为具体错误代码
 * @author WuShoulei on 2018/2/1
 */
public enum SystemExceptionErrorCodeEnum implements IntBaseType {

    CONSTRAINTVIOLATIONEXCEPTION("Bad_request-error", 10001),
    METHODARGUMENTNOTVALIDEXCEPTION("Argument-error|请求参数错误", 10002),
    NOHANDLERFOUNDEXCEPTION("NoHandlerFound-error|接口地址错误", 100003),
    EXCEPTION("server-error|服务器处理异常", 100004),
    RONGCLOUDEXCEPTION("rongCloud服务异常",100005);

    private String desc;
    private int value;

    SystemExceptionErrorCodeEnum(String desc, int value) {
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
