package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;

public enum CityReportErrorCodeEnum implements IntBaseType {

    /**
     * 城市数据报告异常枚举
     */
    NO_RECORD("该城市无数据报告", 12001),
    NO_COTENT("数据报告内容为空", 12002),
    PARSE_FAILED("数据解析失败", 12003),
    NO_COMPLETE("数据不完整", 12004);


    CityReportErrorCodeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }


    private String desc;
    private int value;

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
