package com.toutiao.web.common.util.BeanUtilsConverts;

import com.toutiao.web.common.commonmodel.Money;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.LongConverter;
/**
 * zhangjinglei 2017/9/8 下午8:38
 */
public class NashLongConverter implements Converter {

    private LongConverter c;

    public NashLongConverter() {
        this.c = new LongConverter();
//        super(false);
    }

//    public NashLongConverter(final Object defaultValue) {
//        super(false, defaultValue);
//    }

    @Override
    public <T> T convert(final Class<T> type, Object value){
        if(value.getClass().equals(Money.class)){
            Money dis = (Money) value;
            if(dis==null && dis.getValue()==null){
                return null;
            }
            //return type.cast(dis.getValue());
            return c.convert(type,dis.getValue());
        }
        else {
            return c.convert(type,value);
        }
    }

//    @Override
//    protected Class<Long> getDefaultType() {
//        return Long.class;
//    }
}