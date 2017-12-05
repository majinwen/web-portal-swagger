package com.toutiao.web.common.util;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-09-28 下午5:29 星期四
 * @Version : v1.0
 **/
public interface IBeanConvert<T1,T2> {
    /**
     * 转换 origObj 对象到 destObj
     *
     * @param destObj 目标对象
     * @param origObj 源对象
     */
    void convert(T1 destObj, T2 origObj);
}
