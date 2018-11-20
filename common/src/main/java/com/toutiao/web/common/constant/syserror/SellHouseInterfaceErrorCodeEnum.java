package com.toutiao.web.common.constant.syserror;


import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * 二手房接口服务级错误代码
 * 二手房错误代码起始位60，中间位为接口模块（1 列表，2小区详情，3二手房，4租房，5配套，6收藏，7附近, 8对比），最后两位错误代码，顺序自增
 *
 */
public enum SellHouseInterfaceErrorCodeEnum implements IntBaseType {


    ESF_NOT_FOUND("二手房列表为空", 60101),
    ESF_FAVORITE_NOT_FOUND("二手房收藏列表为空",60601),
    ESF_FAVORITE_ADD_REPEAT("二手房收藏重复",60602),
    ESF_FAVORITE_ADD_ERROR("二手房收藏错误",60603),
    ESF_FAVORITE_DELETE_ERROR("二手房收藏删除错误",60604),
    ESF_COMPARED_ADD_ERROR("二手房对比添加错误",60801),
    ESF_COMPARED_DELETE_ERROR("二手房对比删除错误",60802),
    ESF_COMPARED_NOT_FOUND("二手房对比列表为空",60803);


    private String desc;
    private int value;

    SellHouseInterfaceErrorCodeEnum(String desc, int value) {
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
