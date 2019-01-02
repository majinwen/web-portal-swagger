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
    BEIJING(CityConstant.ABBREVIATION_BEIJING,CityConstant.CODE_BEIJING,CityConstant.NAME_BEIJING),
    SHANGHAI(CityConstant.ABBREVIATION_SHANGHAI,CityConstant.CODE_SHANGHAI,CityConstant.NAME_SHANGHAI),
    TIANJIN(CityConstant.ABBREVIATION_TIANJIN,CityConstant.CODE_TIANJIN,CityConstant.NAME_TIANJIN),
    SUZHOU(CityConstant.ABBREVIATION_SUZHOU,CityConstant.CODE_SUZHOU,CityConstant.NAME_SUZHOU),
    HANGZHOU(CityConstant.ABBREVIATION_HANGZHOU,CityConstant.CODE_HANGZHOU,CityConstant.NAME_HANGZHOU),
    GUANGZHOU(CityConstant.ABBREVIATION_GUANGZHOU,CityConstant.CODE_GUANGZHOU,CityConstant.NAME_GUANGZHOU),
    SHENZHEN(CityConstant.ABBREVIATION_SHENZHEN,CityConstant.CODE_SHENZHEN,CityConstant.NAME_SHENZHEN),
    SHIJIAZHUANG(CityConstant.ABBREVIATION_SHIJIAZHUANG,CityConstant.CODE_SHIJIAZHUANG,CityConstant.NAME_SHIJIAZHUANG),
    CHONGQING(CityConstant.ABBREVIATION_CHONGQING,CityConstant.CODE_CHONGQING,CityConstant.NAME_CHONGQING),
    CHENGDU(CityConstant.ABBREVIATION_CHENGDU,CityConstant.CODE_CHENGDU,CityConstant.NAME_CHENGDU),
    XIAN(CityConstant.ABBREVIATION_XIAN,CityConstant.CODE_XIAN,CityConstant.NAME_XIAN),
    WUXI(CityConstant.ABBREVIATION_WUXI,CityConstant.CODE_WUXI,CityConstant.NAME_WUXI),
    WUHAN(CityConstant.ABBREVIATION_WUHAN,CityConstant.CODE_WUHAN,CityConstant.NAME_WUHAN),
    NANJING(CityConstant.ABBREVIATION_NANJING,CityConstant.CODE_NANJING,CityConstant.NAME_NANJING),
    ZHENGZHOU(CityConstant.ABBREVIATION_ZHENGZHOU,CityConstant.CODE_ZHENGZHOU,CityConstant.NAME_ZHENGZHOU),
    JINAN(CityConstant.ABBREVIATION_JINAN,CityConstant.CODE_JINAN,CityConstant.NAME_JINAN);




    private String desc;
    private int value;
    private String name;

    CityEnum(String desc, int value, String name) {
        this.desc = desc;
        this.value = value;
        this.name = name;
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


    public static String getName(Integer id) {
        CityEnum[] cityEnums = values();
        for (CityEnum cityEnum : cityEnums) {
            if (cityEnum.value==id) {
                return cityEnum.name;
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


    public String getName() {
        return name;
    }

}
