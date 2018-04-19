package com.toutiao.web.common.assertUtils;

import com.toutiao.web.common.exceptions.NashRequestException;

/**
 * Created by jyl on 17/9/8.
 */
public class AssertUtils {

    public static void assertTrue(boolean isHasError,Integer code,String msg) throws NashRequestException {
        if(isHasError){
            if (msg==null){
                throw new NashRequestException(code,"msg:null");
            }
            throw new NashRequestException(code,msg);
        }
    }
}
