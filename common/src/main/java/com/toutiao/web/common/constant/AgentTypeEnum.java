package com.toutiao.web.common.constant;

import com.toutiao.web.common.constant.base.IntBaseType;
import com.toutiao.web.common.constant.base.IType;

import java.util.Map;

/**
 * 代办人类型：（纳米联系人类型）：1、业主联系人 2、租户联系人 3、渠道联系人
 * Created by jyl on 17/9/21.
 */
public enum AgentTypeEnum implements IntBaseType {
    T1("业主联系人",1),
    T2("租户联系人",2),
    T3("渠道联系人",3);

    private AgentTypeEnum(String description, int value){

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

    public static AgentTypeEnum getEnum(int type) {
        return IType.get(type, AgentTypeEnum.class);
    }
    public static AgentTypeEnum getByStr(String desc) {
        return IType.get(desc, AgentTypeEnum.class);
    }
    public static Map<String,Integer> getAll(){
        return IType.getAll(AgentTypeEnum.class) ;
    }
}
