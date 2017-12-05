package com.toutiao.web.common.constant.datarule;


import com.toutiao.web.common.constant.base.IType;
import com.toutiao.web.common.constant.base.IntBaseType;

import java.util.Map;

public enum RoleCategoryEnum implements IntBaseType {
    Xianxia("线下",0),
    Zhixiao("直销",1),
    Zhineng("职能",2),
    ;

    RoleCategoryEnum(String description, int value){

        this.desc = description;
        this.value = value;
    }

    private String desc;
    private int value;

    public String getDesc() {
        return desc;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static RoleCategoryEnum getEnum(int type) {
        return IType.get(type,RoleCategoryEnum.class);
    }
    public static RoleCategoryEnum getByStr(String desc) {
        return IType.get(desc,RoleCategoryEnum.class);
    }
    public static Map<String,Integer> getAll(){
        return IType.getAll(RoleCategoryEnum.class);
    }
}
