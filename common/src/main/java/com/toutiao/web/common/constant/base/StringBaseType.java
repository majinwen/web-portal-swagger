package com.toutiao.web.common.constant.base;

import java.security.InvalidParameterException;

/**
 * Created with IntelliJ IDEA
 * 基于（String Type）的枚举类型接口类
 *
 * 提供统一的查找方法
 * 提供统一的验证方法
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-10-23 上午11:35 星期一
 * @Version : v1.0
 **/
public interface StringBaseType extends IType {

    /**
     * 从枚举对象中获取值相应类型值的元素
     *
     * @param type 枚举值
     * @param clazz 枚举类
     * @param <T> 用于泛型推断
     * @return type对应的枚举对象, 无元素返回 null
     */
    static <T> T get(String type, Class<? extends IType> clazz) {
        if (!clazz.isEnum()) {
            throw new InvalidParameterException();
        }

        for (StringBaseType item : (StringBaseType[]) clazz.getEnumConstants()) {
            if (item.getValue().equals(type)) {
                return (T) item;
            }
        }
        return null;
    }

    /**
     * 验证枚举中是否存在特定值对象
     *
     * @param type 枚举值
     * @param clazz 枚举类
     * @return clazz中存在type则返回 true, 否则 false
     */
    static boolean contains(String type, Class<? extends IType> clazz) {
        return get(type, clazz) != null;
    }

    /**
     * 验证值 信息是否相同
     * @param type 待验证值
     * @return 相同: true, 不同: false
     */
    default boolean isType(String type) {
       return getValue().equals(type);
    }

    /**
     * 验证枚举是否相同
     * @param type 待验证枚举
     * @return 相同: true, 不同: false
     */
    default boolean isType(StringBaseType type) {
        return this == type;
    }

    /**
     * 获取值信息
     */
    String getValue();

}

