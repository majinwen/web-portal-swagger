package com.toutiao.web.common.constant;

import com.toutiao.web.common.constant.base.IntBaseType;
import com.toutiao.web.common.constant.base.IType;

import java.util.Map;

/**
 * Created by jyl on 17/9/5.
 */
public enum BalconyEnum implements IntBaseType {
    EMPTY("无",0),
    HAS("有",1)
    ;

    private BalconyEnum(String description, int value){

        this.desc = description;
        this.value = value;
    }


    private String desc;
    private int value;

    public String getDesc() {
        return desc;
    }


    public int getValue() {
        return value;
    }

    public static BalconyEnum getEnum(int type) {
        return IType.get(type, BalconyEnum.class);
    }
    public static BalconyEnum getByStr(String desc) {
        return IType.get(desc, BalconyEnum.class);
    }
    public static Map<String,Integer> getAll(){
        return IType.getAll(BalconyEnum.class);
    }

}
