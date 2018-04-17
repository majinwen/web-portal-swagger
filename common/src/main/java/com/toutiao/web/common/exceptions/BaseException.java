package com.toutiao.web.common.exceptions;

import com.toutiao.web.common.constant.base.IntBaseType;
import com.toutiao.web.common.constant.syserror.NewHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.PlotsInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.RentInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import lombok.Data;

/**
 * zhangjinglei 2017/9/5 上午11:58
 */
@Data
public class BaseException extends RuntimeException {
    private Integer code;
    public BaseException(Integer code,String msg) {
        super(msg);
        this.setCode(code);
    }


    public <T extends IntBaseType> BaseException(T exceptionEnum) {

        super(exceptionEnum.getDesc());
        this.setCode(exceptionEnum.getValue());
    }

    public BaseException(NewHouseInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
    }

    public BaseException(PlotsInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
    }

    public BaseException(RentInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
    }

    public BaseException(SellHouseInterfaceErrorCodeEnum serviceErrorCodeEnum, String msg) {

        super(msg);
        this.setCode(serviceErrorCodeEnum.getValue());
    }
}
