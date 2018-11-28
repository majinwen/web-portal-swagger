package com.toutiao.web.common.constant.company;

import com.toutiao.web.common.constant.base.StringBaseType;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-31
 * Time:   17:06
 * Theme:  城市枚举
 */
public enum CompanyIconEnum implements StringBaseType {


    COMPANY_WOAIWOJIA(CompanyConstant.COMPANY_WOAIWOJIA, CompanyConstant.COMPANY_ICON_WOAIWOJIA),
    COMPANY_ZHONGYUANDICHAN(CompanyConstant.COMPANY_ZHONGYUANDICHAN, CompanyConstant.COMPANY_ICON_ZHONGYUANDICHAN),
    COMPANY_HUIZHAOFANG(CompanyConstant.COMPANY_HUIZHAOFANG, CompanyConstant.COMPANY_ICON_HUIZHAOFANG),
    COMPANY_XIANGYU(CompanyConstant.COMPANY_XIANGYU, CompanyConstant.COMPANY_ICON_XIANGYU),
    COMPANY_MOGUZUFANG(CompanyConstant.COMPANY_MOGUZUFANG, CompanyConstant.COMPANY_ICON_MOGUZUFANG),
    COMPANY_JINLEJIA(CompanyConstant.COMPANY_JINLEJIA, CompanyConstant.COMPANY_ICON_JINLEJIA),
    COMPANY_MAITIANFANGCHAN(CompanyConstant.COMPANY_MAITIANFANGCHAN, CompanyConstant.COMPANY_ICON_MAITIANFANGCHAN),
    COMPANY_TAIPINGYANGFANGWU(CompanyConstant.COMPANY_TAIPINGYANGFANGWU, CompanyConstant.COMPANY_ICON_TAIPINGYANGFANGWU);

    private String key;
    private String value;

    CompanyIconEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    //根据key获取枚举
    public static CompanyIconEnum getEnumByKey(String key) {
        if (null == key) {
            return null;
        }
        for (CompanyIconEnum temp : CompanyIconEnum.values()) {
            if (temp.getKey().equals(key)) {
                return temp;
            }
        }
        return null;
    }

    public static boolean containKey(String key) {
        if (null == key) {
            return false;
        }
        for (CompanyIconEnum temp : CompanyIconEnum.values()) {
            if (temp.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static String getValueByKey(String key) {
        if (null == key) {
            return "";
        }
        for (CompanyIconEnum temp : CompanyIconEnum.values()) {
            if (temp.getKey().equals(key)) {
                return temp.getValue();
            }
        }
        return "";
    }

}
