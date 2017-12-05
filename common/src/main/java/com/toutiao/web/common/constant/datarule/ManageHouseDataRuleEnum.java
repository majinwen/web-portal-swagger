package com.toutiao.web.common.constant.datarule;



import com.toutiao.web.common.constant.base.IType;
import com.toutiao.web.common.constant.base.IntBaseType;

import java.util.Map;

public enum ManageHouseDataRuleEnum implements IntBaseType {
    Studio("工作室",0),
    Space("Space",1)
    ;

    private ManageHouseDataRuleEnum(String description, int value){

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

    public static ManageHouseDataRuleEnum getEnum(int type) {
        return IType.get(type,ManageHouseDataRuleEnum.class);
    }
    public static ManageHouseDataRuleEnum getByStr(String desc) {
        return IType.get(desc,ManageHouseDataRuleEnum.class);
    }
    public static Map<String,Integer> getAll(){
        return IType.getAll(ManageHouseDataRuleEnum.class);
    }
}
