package com.toutiao.web.common.constant.house;

import com.toutiao.web.common.constant.base.StringBaseType;

/**
 * Created by IntelliJ Idea
 * Author: wangzhao
 * Theme:  新房标签
 */
public enum HouseLableEnum implements StringBaseType {

    /**
     * 新房标签枚举
     */

    SALESTATUS_ZS(HouseLableConstant.SALESTATUS_ZS, HouseLableConstant.ICON_URL_SALESTATUS_ZS),
    SALESTATUS_BZS(HouseLableConstant.SALESTATUS_BZS, HouseLableConstant.ICON_URL_SALESTATUS_BZS),
    SALESTATUS_DS(HouseLableConstant.SALESTATUS_DS, HouseLableConstant.ICON_URL_SALESTATUS_DS),
    SALESTATUS_DZ(HouseLableConstant.SALESTATUS_DZ, HouseLableConstant.ICON_URL_SALESTATUS_DZ),
    ISACTIVE(HouseLableConstant.ISACTIVE, HouseLableConstant.ICON_URL_ISACTIVE),
    PROPERTYTYPE_PT(HouseLableConstant.PROPERTYTYPE_ZZ, HouseLableConstant.ICON_URL_PROPERTYTYPE_ZZ),
    PROPERTYTYPE_BS(HouseLableConstant.PROPERTYTYPE_BS, HouseLableConstant.ICON_URL_PROPERTYTYPE_BS),
    CUTPRICE(HouseLableConstant.CUTPRICE, HouseLableConstant.ICON_URL_CUTPRICE),
    LOWPRICE(HouseLableConstant.LOWPRICE, HouseLableConstant.ICON_URL_LOWPRICE),
    MUSTROB(HouseLableConstant.MUSTROB, HouseLableConstant.ICON_URL_MUSTROB);



    private String key;
    private String value;

    HouseLableEnum(String key, String value) {
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
    public static HouseLableEnum getEnumByKey(String key){
        if(null == key){
            return null;
        }
        for(HouseLableEnum temp: HouseLableEnum.values()){
            if(temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }

    public static boolean containKey(String key){
        if(null == key){
            return false;
        }
        for(HouseLableEnum temp: HouseLableEnum.values()){
            if(temp.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }






}
