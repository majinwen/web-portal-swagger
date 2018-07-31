package com.toutiao.web.common.constant.city;

import com.toutiao.web.common.constant.base.IntBaseType;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-31
 * Time:   17:06
 * Theme:  城市枚举
 */
public enum  CityEnum implements IntBaseType {

    /**
     * 城市枚举
     */
    BEIJING(CityConstant.ABBREVIATION_BEIJING,CityConstant.CODE_BEIJING),
    SHANGHAI(CityConstant.ABBREVIATION_SHANGHAI,CityConstant.CODE_SHANGHAI),
    TIANJIN(CityConstant.ABBREVIATION_TIANJIN,CityConstant.CODE_TIANJIN);


    private String desc;
    private int value;

    CityEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }


    public static Integer getValue(String value) {
        CityEnum[] cityEnums = values();
        for (CityEnum cityEnum : cityEnums) {
            if (cityEnum.desc.equals(value)) {
                return cityEnum.value;
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
