package com.toutiao.web.common.util.BeanUtilsConverts;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;

public class JsonArrayConvert implements Converter {
    @Override
    public <T> T convert(Class<T> aClass, Object o) {
        if (o.getClass().equals(String.class)) {
            if (o == null) {
                return null;
            }
            String res = o.toString();
            if (res.startsWith("{")) {
                res = StringUtils.strip(res, "{}");
                res = "[" + res + "]";
            }
            return aClass.cast(JSON.parseArray(res));
        }
        throw new ConversionException(o.getClass().toString() + " cannt convert to json class");
    }
}
