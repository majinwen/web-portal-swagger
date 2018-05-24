package com.toutiao.web.common.constant.syserror;


import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * 各种请求参数的错误类型
 *
 *
 */
public enum ParamErrorCodeEnum implements IntBaseType {

    NOTNULL("参数不能为空",1001),
    ParaError("Argument-error|请求参数错误",1002);
    private String desc;
    private int value;

    ParamErrorCodeEnum(String desc, int value) {
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
