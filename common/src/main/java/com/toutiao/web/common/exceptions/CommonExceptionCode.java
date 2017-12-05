package com.toutiao.web.common.exceptions;

public enum CommonExceptionCode {
    NotFound("404",0),
    RequestError("0000",1),
    ;
    private CommonExceptionCode(String description,int value){

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

    public static CommonExceptionCode getEnum(int type) {
        CommonExceptionCode[] status = CommonExceptionCode.values();
        for (int i = 0; i < status.length; i++) {
            if (status[i].getValue() == type) {
                return status[i];
            }
        }
        return null;
    }



}
