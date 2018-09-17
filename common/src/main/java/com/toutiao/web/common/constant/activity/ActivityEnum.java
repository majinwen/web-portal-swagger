package com.toutiao.web.common.constant.activity;

import com.toutiao.web.common.constant.base.IntBaseType;
import com.toutiao.web.common.constant.city.CityEnum;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-14
 * Time:   15:08
 * Theme:
 */
public enum ActivityEnum implements IntBaseType {


    /**
     * 城市枚举
     */
    DISCOUNT(ActivityConstant.NEWHOUSE_ACTIVITY_TYPE_NAME, ActivityConstant.NEWHOUSE_ACTIVITY_TYPE);


    private String desc;
    private int value;

    ActivityEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }


    public static Integer getValue(String value) {
        ActivityEnum[] activityEnums = values();
        for (ActivityEnum activityEnum : activityEnums) {
            if (activityEnum.desc.equals(value)) {
                return activityEnum.value;
            }
        }
        return null;
    }


    public static String getId(Integer id) {
        ActivityEnum[] activityEnums = values();
        for (ActivityEnum activityEnum : activityEnums) {
            if (activityEnum.value==id) {
                return activityEnum.desc;
            }
        }
        return null;
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
