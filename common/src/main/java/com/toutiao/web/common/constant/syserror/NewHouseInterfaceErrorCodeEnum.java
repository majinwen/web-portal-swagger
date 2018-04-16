package com.toutiao.web.common.constant.syserror;


import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * 新房接口服务级错误代码
 * 新房错误代码起始位40，中间位为接口模块（1 推荐列表，2新房详情，3户型，4动态，5配套，6收藏，7附近列表），最后两位错误代码，顺序自增
 *
 */
public enum NewHouseInterfaceErrorCodeEnum implements IntBaseType {


    /**
     * 新房枚举参数
     *
     */
    NEWHOUSE_NOT_FOUND("新房楼盘列表为空！", 40101);



    private String desc;
    private int value;

    NewHouseInterfaceErrorCodeEnum(String desc, int value) {
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
