package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;


/**
 * 小区接口服务级错误代码
 * 小区错误代码起始位50，中间位为接口模块（1 列表，2小区详情，3二手房，4租房，5配套，6收藏，7附近），最后两位错误代码，顺序自增
 *
 */
public enum PlotsInterfaceErrorCodeEnum implements IntBaseType {


    /**
     * 小区枚举参数
     *
     */
    PLOTS_NOT_FOUND("小区楼盘列表为空！", 50101),
    PLOTS_TRAFFIC_NOT_FOUND("小区交通配套为空!",50501);

    private String desc;
    private int value;

    PlotsInterfaceErrorCodeEnum(String desc, int value) {
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

