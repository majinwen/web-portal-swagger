package com.toutiao.web.common.exceptions;

/**
 * Created by jyl on 17/9/8.
 */
public class NashRequestException extends BaseException{

    public NashRequestException(Integer code, String msg) {
        super(code, msg);
    }
}
