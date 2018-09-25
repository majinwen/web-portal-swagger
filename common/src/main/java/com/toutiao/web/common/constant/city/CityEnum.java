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
    TIANJIN(CityConstant.ABBREVIATION_TIANJIN,CityConstant.CODE_TIANJIN),
    SUZHOU(CityConstant.ABBREVIATION_SUZHOU,CityConstant.CODE_SUZHOU),
    HANGZHOU(CityConstant.ABBREVIATION_HANGZHOU,CityConstant.CODE_HANGZHOU),
    GUANGZHOU(CityConstant.ABBREVIATION_GUANGZHOU,CityConstant.CODE_GUANGZHOU),
    SHENZHEN(CityConstant.ABBREVIATION_SHENZHEN,CityConstant.CODE_SHENZHEN);




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


    public static String getId(Integer id) {
        CityEnum[] cityEnums = values();
        for (CityEnum cityEnum : cityEnums) {
            if (cityEnum.value==id) {
                return cityEnum.desc;
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
