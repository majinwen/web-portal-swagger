package com.toutiao.web.common.constant.datarule;


import com.toutiao.web.common.constant.base.IntBaseType;
import com.toutiao.web.common.constant.base.IType;

import java.util.Map;

public enum EntranceDataRuleEnum implements IntBaseType {
    Creator("自己创建的",0),
    Owner("自己负责的",1),
    Ltree("数据范围内的",2)
    ;

    private EntranceDataRuleEnum(String description, int value){

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

    public static EntranceDataRuleEnum getEnum(int type) {
        return IType.get(type,EntranceDataRuleEnum.class);
    }
    public static EntranceDataRuleEnum getByStr(String desc) {
        return IType.get(desc,EntranceDataRuleEnum.class);
    }
    public static Map<String,Integer> getAll(){
        return IType.getAll(EntranceDataRuleEnum.class);
    }
}
