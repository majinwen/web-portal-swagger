package com.toutiao.web.common.constant.house;

import com.toutiao.web.common.constant.base.StringBaseType;

/**
 * Created by IntelliJ Idea
 * Author: wangzhao
 * Theme:  新房标签
 */
public enum NewHouseLableEnum implements StringBaseType {

    /**
     * 新房标签枚举
     */

    SALESTATUS_ZS(NewHouseLableConstant.SALESTATUS_ZS, NewHouseLableConstant.ICON_URL_SALESTATUS_ZS),
    SALESTATUS_BZS(NewHouseLableConstant.SALESTATUS_BZS, NewHouseLableConstant.ICON_URL_SALESTATUS_BZS),
    SALESTATUS_DS(NewHouseLableConstant.SALESTATUS_DS, NewHouseLableConstant.ICON_URL_SALESTATUS_DS),
    SALESTATUS_DZ(NewHouseLableConstant.SALESTATUS_DZ, NewHouseLableConstant.ICON_URL_SALESTATUS_DZ),
    ISACTIVE_YES(NewHouseLableConstant.ISACTIVE_YES, NewHouseLableConstant.ICON_URL_ISACTIVE_YES),
    PROPERTYTYPE_PT(NewHouseLableConstant.PROPERTYTYPE_ZZ, NewHouseLableConstant.ICON_URL_PROPERTYTYPE_ZZ),
    PROPERTYTYPE_BS(NewHouseLableConstant.PROPERTYTYPE_BS, NewHouseLableConstant.ICON_URL_PROPERTYTYPE_BS);


    private String key;
    private String value;

    NewHouseLableEnum(String key, String value) {
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
    public static NewHouseLableEnum getEnumByKey(String key){
        if(null == key){
            return null;
        }
        for(NewHouseLableEnum temp:NewHouseLableEnum.values()){
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
        for(NewHouseLableEnum temp:NewHouseLableEnum.values()){
            if(temp.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }






}
