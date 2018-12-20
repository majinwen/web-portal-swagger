package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * Created by wk on 2018/11/30.
 */
public enum FavoriteErrorCodeEnum implements IntBaseType {

    ADD_FAVORITE_SUCCESS("收藏成功", 41001),
    ADD_FAVORITE_FAIL("收藏失败", 41002),
    CANCEL_FAVORITE_SUCCESS("取消收藏成功", 41003),
    CANCEL_FAVORITE_FAIL("取消收藏失败", 41004),
    CANCEL_FAVORITE_NOT_FOUND("未收藏该房源", 41004)
    ;

    private String desc;
    private int value;

    FavoriteErrorCodeEnum(String desc, int value) {
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
