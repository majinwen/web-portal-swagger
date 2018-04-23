package com.toutiao.web.common.exceptions;

import com.toutiao.web.common.constant.base.IntBaseType;
import com.toutiao.web.common.constant.syserror.*;
import lombok.Data;

/**
 * zhangjinglei 2017/9/5 上午11:58
 */
@Data
public class BaseException extends RuntimeException {
    private String msg;
    private Integer code;
    public BaseException(Integer code,String msg) {

        super(msg);
        this.setCode(code);
        this.setMsg(msg);
    }


    public <T extends IntBaseType> BaseException(T exceptionEnum) {

        super(exceptionEnum.getDesc());
        this.setCode(exceptionEnum.getValue());
    }

    public BaseException(NewHouseInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
        this.setMsg(msg);
    }

    public BaseException(PlotsInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
        this.setMsg(msg);
    }

    public BaseException(RentInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
        this.setMsg(msg);
    }

    public BaseException(SellHouseInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
        this.setMsg(msg);
    }

    public BaseException(ShortMessageInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
        this.setMsg(msg);
    }

    public BaseException(UserInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
        this.setMsg(msg);
    }
}
