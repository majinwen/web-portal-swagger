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
    PLOTS_TRAFFIC_NOT_FOUND("小区交通配套为空!",50501),
    NEARBY_PLOTS_NOT_FOUND("附近小区楼盘列表为空！",50701),
    PLOTS_ESF_NOT_FOUND("小区没有出售房源信息！",50301),
    PLOTS_RENT_NOT_FOUND("小区没有出租房源信息！",50401),
    PLOTS_DETAILS_NOT_FOUND("小区详情为空！",50201),
    PLOTS_FAVORITE_ADD_REPEAT("添加小区收藏重复",50603),
    PLOTS_FAVORITE_ADD_ERROR("添加小区收藏失败",50602),
    PLOTS_FAVORITE_DELETE_ERROR("删除小区收藏失败",50604),
    PLOTS_FAVORITE_NOT_FOUND("小区收藏列表为空",50601);



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

