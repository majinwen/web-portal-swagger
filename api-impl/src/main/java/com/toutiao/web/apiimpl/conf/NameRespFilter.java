package com.toutiao.web.apiimpl.conf;

import com.alibaba.fastjson.serializer.NameFilter;
import com.toutiao.web.common.assertUtils.ChangeName;


import java.lang.reflect.Field;

/**
 * 返回值过滤器
 *
 */
public class NameRespFilter implements NameFilter {
    @Override
    public String process(Object object, String name, Object value) {
        try {
            if(!object.getClass().getTypeName().equals("java.util.HashMap")){
                Field declaredField = object.getClass().getDeclaredField(name);

                if(declaredField!=null) {
                    ChangeName annotation = declaredField.getAnnotation(ChangeName.class);
                    if (annotation != null) {
                        return annotation.value();
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }
}
