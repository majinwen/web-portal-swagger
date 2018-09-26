package com.toutiao.web.common.util.BeanUtilsConverts;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

public class JsonConvert implements Converter {
    @Override
    public <T> T convert(final Class<T> aClass, Object o) {
        if (o.getClass().equals(String.class)) {
            if (o == null) {
                return null;
            }
            return aClass.cast(JSON.parseObject(o.toString()));
        }
        throw new ConversionException(o.getClass().toString() + " cannt convert to json class");

    }
}
