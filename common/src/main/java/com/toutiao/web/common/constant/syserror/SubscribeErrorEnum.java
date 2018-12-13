package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * Created by wk on 2018/12/11.
 */
public enum SubscribeErrorEnum  implements IntBaseType {

    USER_SUBSCRIBE_NOT_FOUND("用户订阅不存在", 43001)
    ;

    private String desc;
    private int value;

    SubscribeErrorEnum(String desc, int value) {
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
