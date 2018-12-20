package com.toutiao.web.common.constant.syserror;


import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * 新房接口服务级错误代码
 * 新房错误代码起始位40，中间位为接口模块（1 推荐列表，2新房详情，3户型，4动态，5配套，6收藏，7附近列表, 8新房活动），最后两位错误代码，顺序自增
 */
public enum NewHouseInterfaceErrorCodeEnum implements IntBaseType {


    /**
     * 新房枚举参数
     */
    NEWHOUSE_NOT_FOUND("新房楼盘列表为空！", 40101),
    //户型为空
    NEWHOUSE_LAYOUT_NOT_FOUND("该新房下不存在户型信息", 40301),
    //调用户型信息异常
    NEWHOUSE_LAYOUT_EXPTION("调用新房信息异常", 40399),
    //新房动态为空
    NEWHOUSE_DYNAMIC_EXPTION("新房动态为空", 40401),
    NEWHOUSE_TRAFFIC_NOT_FOUND("新房交通配套为空", 40501),
    NEWHOUSE_FAVORITE_ADD_REPEAT("添加新房收藏重复", 40603),
    NEWHOUSE_FAVORITE_ADD_ERROR("添加新房收藏错误", 40602),
    NEWHOUSE_ACTIVITY_ALREADY_TOOK("已参与此活动", 40801),
    NEWHOUSE_ACTIVITY_UPDATE_NICKNAME_ERROR("更新用户称呼失败", 40802),
    NEWHOUSE_ACTIVITY_FORM_ADD_ERROR("保存用户活动信息失败", 40803),
    NEWHOUSE_FAVORITE_NOT_FOUND("新房收藏列表为空", 40601),
    NEWHOUSE_FAVORITE_DELETE_ERROR("删除新房收藏错误", 40604);


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
