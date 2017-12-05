package com.toutiao.web.common.util.BeanUtilsConverts;

import com.toutiao.web.common.commonmodel.Money;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * zhangjinglei 2017/9/8 下午8:26
 */
public class MoneyConvert implements Converter {
    @Override
    public <T> T  convert(final Class<T> aClass, Object o) {
        if(o.getClass().equals(Long.class)){
                Long dis = (Long) o;
                if(dis==null){
                    return null;
                }
                return aClass.cast(new Money(dis));
        }
        throw new ConversionException(o.getClass().toString()+" cannt convert to Money class");

    }
}
