package com.toutiao.web.common.util;

import com.alibaba.fastjson.JSON;

/**
 * @author WuShoulei on 2017/12/14
 */
public class JSONUtil {

    public static String stringfy(Object parameter) {

        if (null != parameter) {
            return JSON.toJSONString(parameter);
        }

        return null;
    }


    public static Object parse(String jsonStr, Class clazz) {

        if (null == jsonStr || jsonStr.length() == 0) {
            return  null;
        }

        try {
            return JSON.parseObject(jsonStr, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}