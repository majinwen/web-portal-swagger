package com.toutiao.web.common.util.BeanUtilsConverts;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringConvert implements Converter {

    private Logger logger = LoggerFactory.getLogger(StringConvert.class);
    private StringConverter converter;

    public StringConvert() {
        converter = new StringConverter();
    }

    @Override
    public <T> T convert(final Class<T> aClass, Object o) {

        try {
            if(o !=null ){
                if (o.getClass().equals(JSONObject.class)) {
                    if (o == null) {
                        return null;
                    }
                    return aClass.cast(((JSON) o).toJSONString());
                } else if (o.getClass().equals(JSONArray.class)) {
                    if (o == null) {
                        return null;
                    }
                    return aClass.cast(((JSONArray) o).toJSONString());
                }
            }

//        else if(o.getClass().equals(PGArray.class)){
//            if(o==null){
//                return null;
//            }
//            String s = ((JSONArray) o).toJSONString();
//            s=StringUtils.strip(s,"[]");
//            s = "{"+s+"}";
//            return aClass.cast(s);
//        }
            return converter.convert(aClass, o);
        }catch (Exception e){
            logger.error("对象转换异常={}",e.getStackTrace());
        }
        return aClass.cast("");
    }
}