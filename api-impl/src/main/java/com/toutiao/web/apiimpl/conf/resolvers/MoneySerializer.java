package com.toutiao.web.apiimpl.conf.resolvers;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.toutiao.web.common.commonmodel.Money;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * zhangjinglei 2017/9/8 下午6:44
 */
public class MoneySerializer implements ObjectSerializer {
    public final static MoneySerializer instance=new MoneySerializer();
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter writer = serializer.getWriter();
        Money m = (Money) object;
        if(object==null||m.getValue()==null){
            writer.writeNull();
        }
        else {
            writer.write(m.toDecimalString());
        }
    }
}
