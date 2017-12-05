package com.toutiao.web.common.exceptions;

import lombok.Data;

/**
 * zhangjinglei 2017/9/5 上午11:58
 */
@Data
public class BaseException extends RuntimeException {
    private String code="";
    public BaseException(String code,String msg) {
        super(msg);
        this.setCode(code);
    }
}
