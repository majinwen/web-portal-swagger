package com.toutiao.web.common.util.converts;

import java.math.BigDecimal;

/**
 * zhangjinglei 2017/9/5 下午4:51
 */
public class BigDecimalConvert {

    /**
     * 把字符串转换成BigDecimal
     *  如果转换失败，返回defaultValue
     * @param value
     * @param defaultValue
     * @return
     */
    public static BigDecimal tryFromString(String value,BigDecimal defaultValue){
        try{
            BigDecimal bd=new BigDecimal(value);
            return bd;
        }
        catch (Exception ex){
            return defaultValue;
        }
    }
}
