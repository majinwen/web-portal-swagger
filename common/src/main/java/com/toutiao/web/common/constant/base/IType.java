package com.toutiao.web.common.constant.base;

import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * 枚举类型接口类
 * 标识接口
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-10-23 上午11:35 星期一
 * @Version : v1.0
 **/
public interface IType {

    /**
     * 从枚举对象中获取值相应类型值的元素
     *
     * @param type 枚举值
     * @param clazz 枚举类
     * @param <T> 用于泛型推断
     * @return type对应的枚举对象, 无元素返回 null
     */
    static <T> T get(String type, Class<? extends IType> clazz) {
        return StringBaseType.get(type.trim(), clazz);
    }

    /**
     * 从枚举对象中获取值相应类型值的元素
     *
     * @param type 枚举值
     * @param clazz 枚举类
     * @param <T> 用于泛型推断
     * @return type对应的枚举对象, 无元素返回 null
     */
    static <T> T get(int type, Class<? extends IType> clazz) {
        return IntBaseType.get(type, clazz);
    }

    /**
     * 获取全部信息
     * @param clazz 枚举类
     * @return
     */
    static Map<String,Integer> getAll(Class<? extends IType> clazz){
        return IntBaseType.getAll(clazz);
    }

    /**
     * 跟序号进行排序 返回排序后的信息
     * @param clazz 枚举类
     * @return
     */
    static Map<String,Integer> getAllBySort(Class<? extends IType> clazz){
        return IntBaseTypeSort.getAllBySort(clazz);
    }

    /**
     * 验证枚举中是否存在特定值对象
     *
     * @param type 枚举值
     * @param clazz 枚举类
     * @return clazz中存在type则返回 true, 否则 false
     */
    static boolean contains(String type, Class<? extends IType> clazz) {
        return StringBaseType.contains(type.trim(), clazz);
    }

    /**
     * 验证枚举中是否存在特定值对象
     *
     * @param type 枚举值
     * @param clazz 枚举类
     * @return clazz中存在type则返回 true, 否则 false
     */
    static boolean contains(int type, Class<? extends IType> clazz) {
        return IntBaseType.contains(type, clazz);
    }


}

