package com.toutiao.web.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.toutiao.web.common.exceptions.NashRequestException;
import com.toutiao.web.common.util.BeanUtilsConverts.JsonArrayConvert;
import com.toutiao.web.common.util.BeanUtilsConverts.JsonConvert;
import com.toutiao.web.common.util.BeanUtilsConverts.StringConvert;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * zhangjinglei 2017/9/9 下午2:00
 */
public class ToutiaoBeanUtils {
    private static boolean hasInit = false;

    public static void copyProperties(Object dest, Object orig) {
        if (!hasInit) {
            hasInit = true;
            ConvertUtils.deregister(Long.class);
            ConvertUtils.register(new JsonConvert(), JSONObject.class);
            ConvertUtils.register(new JsonArrayConvert(), JSONArray.class);
            ConvertUtils.register(new StringConvert(), String.class);

        }
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static <T> List copyPropertiesToList(List<T> resource, Class target) {
        List l2;
        if (resource instanceof Page) {
            Page origin = (Page) resource;

            Page<Object> p = new Page();

            p.setPageNum(origin.getPageNum());
            p.setPageSize(origin.getPageSize());
            p.setTotal(origin.getTotal());

            l2 = p;

        } else {
            l2 = new ArrayList();
        }
        if (!CollectionUtils.isEmpty(resource)) {
            try {
                for (Object n : resource) {
                    Object o = target.newInstance();
                    ToutiaoBeanUtils.copyProperties(o, n);
                    l2.add(o);
//          l2.add(ConvertUtils.convert(n, target));
                }
            } catch (Exception e) {
                throw new NashRequestException(0000, e.getMessage());
            }
        }
        return l2;
    }
}
