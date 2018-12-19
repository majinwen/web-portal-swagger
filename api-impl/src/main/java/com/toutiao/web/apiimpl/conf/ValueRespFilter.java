package com.toutiao.web.apiimpl.conf;

import com.alibaba.fastjson.serializer.ValueFilter;


/**
 * @ClassName ValueRespFilter
 * @Author jiangweilong
 * @Date 2018/11/21 7:16 PM
 * @Description:
 **/
public class ValueRespFilter implements ValueFilter {
    @Override
    public Object process(Object o, String s, Object param) {
        if(param==null){
            try {
                param = o.getClass().getDeclaredField(s).getType().getTypeName();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
//            if (param instanceof Number) {
//                return 0;
//            } else if (param instanceof String) {
//                return "";
//            } else if (param instanceof Boolean) {
//                return false;
//            }

            if(param.equals("java.util.Map")){
                return "";
            }
        }

        return param;
    }
}
