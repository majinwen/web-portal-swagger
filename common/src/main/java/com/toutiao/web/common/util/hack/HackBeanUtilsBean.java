package com.toutiao.web.common.util.hack;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 扩展 common-beanutils convert 功能
 *
 * @author 冰宁 on 2017/1/11.
 */
public class HackBeanUtilsBean extends BeanUtilsBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>Constructs an instance using new property
     * and conversion instances.</p>
     */
    public HackBeanUtilsBean() {
    }

    @Override
    protected Object convert(Object value, Class type) {
        return getConvertUtils().convert(value, type);
    }

    /**
     * 在 BeanUtils.copyProperties 基础上添加 ignore 功能
     * @param dest 目标对象
     * @param orig 源对象
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void copyProperties(Object dest, Object orig, String... ignoreProperties)
            throws IllegalAccessException, InvocationTargetException {

        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException
                    ("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("BeanUtils.copyProperties(" + dest + ", " +
                    orig + ")");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors =
                    ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if (getPropertyUtils().isReadable(orig, name) &&
                        getPropertyUtils().isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    copyProperty(dest, name, value);
                }
            }
        } else if (orig instanceof Map) {
            Iterator entries = ((Map) orig).entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String name = (String)entry.getKey();
                if (getPropertyUtils().isWriteable(dest, name)) {
                    copyProperty(dest, name, entry.getValue());
                }
            }
        } else /* if (orig is a standard JavaBean) */ {
            PropertyDescriptor[] origDescriptors =
                    getPropertyUtils().getPropertyDescriptors(orig);
            List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : Collections.EMPTY_LIST);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                // class 与 ignore 不执行转换
                if ("class".equals(name) || ignoreList.contains(name)) {
                    continue;
                }

                if (getPropertyUtils().isReadable(orig, name) &&
                        getPropertyUtils().isWriteable(dest, name)) {
                    try {
                        Object value =
                                getPropertyUtils().getSimpleProperty(orig, name);
                        copyProperty(dest, name, value);
                    } catch (NoSuchMethodException e) {
                        // Should not happen
                    }
                }
            }
        }

    }
}
