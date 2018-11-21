package com.toutiao.web.apiimpl.conf;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.toutiao.web.common.util.spring.SpringUtil;

import java.util.Date;

public class ValueRespFilter implements ValueFilter {
    @Override
    public Object process(Object o, String s, Object param) {
        if(param==null){
            if (param instanceof Number) {
                return 0;
            } else if (param instanceof String) {
                return "";
            } else if (param instanceof Boolean) {
                return false;
            }
        }

       return param;
    }
}
//SerializerFeature.WriteMapNullValue,
// SerializerFeature.WriteNullStringAsEmpty,
// SerializerFeature.PrettyFormat,
// SerializerFeature.WriteNullListAsEmpty,
// SerializerFeature.WriteNullNumberAsZero