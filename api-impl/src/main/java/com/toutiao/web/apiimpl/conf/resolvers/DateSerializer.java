package com.toutiao.web.apiimpl.conf.resolvers;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * zhangjinglei 2017/9/9 下午2:27
 */
public class DateSerializer implements ObjectSerializer {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter writer = serializer.getWriter();

        Date m = (Date) object;
        if(object==null){
            writer.writeNull();
        }
        else {
            writer.writeString(simpleDateFormat.format(m));
        }
    }
}
