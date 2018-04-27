package com.toutiao.web.common.constant.syserror;


import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * 租房接口服务级错误代码
 * 租房错误代码起始位70，中间位为接口模块（1 推荐列表，2新房详情，3户型，4动态，5配套，6收藏，7附近列表），最后两位错误代码，顺序自增
 *
 */
public enum RentInterfaceErrorCodeEnum implements IntBaseType {


    RENT_NOT_FOUND("租房推荐列表为空", 70101),

    RECOMMEND_RENT_NOT_FOUND("未查询到租房推优房源", 70102);

    /**
     * 支付模块，模块代码05
     */

    /**
     * 订单模块，模块代码06
     */

    /**
     * 统计模块，模块代码07
     */


    private String desc;
    private int value;

    RentInterfaceErrorCodeEnum(String desc, int value) {
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
