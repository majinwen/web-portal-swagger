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
    CHONGQIN(CityConstant.ABBREVIATION_CHONGQIN,CityConstant.CODE_CHONGQIN,CityConstant.NAME_CHONGQIN),
    ZHUHAI(CityConstant.ABBREVIATION_ZHUHAI,CityConstant.CODE_ZHUHAI,CityConstant.NAME_ZHUHAI),
    DONGGUAN(CityConstant.ABBREVIATION_DONGGUAN,CityConstant.CODE_DONGGUAN,CityConstant.NAME_DONGGUAN),
    CHENGDU(CityConstant.ABBREVIATION_CHENGDU,CityConstant.CODE_CHENGDU,CityConstant.NAME_CHENGDU),
    SHENYANG(CityConstant.ABBREVIATION_SHENYANG,CityConstant.CODE_SHENYANG,CityConstant.NAME_SHENYANG),
    NANJING(CityConstant.ABBREVIATION_NANJING,CityConstant.CODE_NANJING,CityConstant.NAME_NANJING),

    WUXI(CityConstant.ABBREVIATION_WUXI,CityConstant.CODE_WUXI,CityConstant.NAME_WUXI),
    FUZHOU(CityConstant.ABBREVIATION_FUZHOU,CityConstant.CODE_FUZHOU,CityConstant.NAME_FUZHOU),
    CHANGCHUN(CityConstant.ABBREVIATION_CHANGCHUN,CityConstant.CODE_CHANGCHUN,CityConstant.NAME_CHANGCHUN),
    ZHENGZHOU(CityConstant.ABBREVIATION_ZHENGZHOU,CityConstant.CODE_ZHENGZHOU,CityConstant.NAME_ZHENGZHOU),
    JINAN(CityConstant.ABBREVIATION_JINAN,CityConstant.CODE_JINAN,CityConstant.NAME_JINAN),
    QINGDAO(CityConstant.ABBREVIATION_QINGDAO,CityConstant.CODE_QINGDAO,CityConstant.NAME_QINGDAO),
    YANTAI(CityConstant.ABBREVIATION_YANTAI,CityConstant.CODE_YANTAI,CityConstant.NAME_YANTAI),
    HEFEI(CityConstant.ABBREVIATION_HEFEI,CityConstant.CODE_HEFEI,CityConstant.NAME_HEFEI),
    NANNING(CityConstant.ABBREVIATION_NANNING,CityConstant.CODE_NANNING,CityConstant.NAME_NANNING),
    TAIYUAN(CityConstant.ABBREVIATION_TAIYUAN,CityConstant.CODE_TAIYUAN,CityConstant.NAME_TAIYUAN),
    XIAN(CityConstant.ABBREVIATION_XIAN,CityConstant.CODE_XIAN,CityConstant.NAME_XIAN),
    WUHAN(CityConstant.ABBREVIATION_WUHAN,CityConstant.CODE_WUHAN,CityConstant.NAME_WUHAN),
    CHANGSHA(CityConstant.ABBREVIATION_CHANGSHA,CityConstant.CODE_CHANGSHA,CityConstant.NAME_CHANGSHA),
    NANCHANG(CityConstant.ABBREVIATION_NANCHANG,CityConstant.CODE_NANCHANG,CityConstant.NAME_NANCHANG);




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
