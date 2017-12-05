package com.toutiao.web.common.constant.base;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * 基于（int Type）的枚举类型接口类
 *
 * 提供统一的查找方法
 * 提供统一的验证方法
 * @Project :core-base-parent
 * @Author : kewei@nash.work
 * @Date : 2017-10-23 上午11:36 星期一
 * @Version : v1.0
 **/
public interface IntBaseTypeSort extends IntBaseType {

    /**
     * 获取全部信息
     * @param clazz 枚举类
     * @return
     */
    static Map<String,Integer> getAllBySort(Class<? extends IType> clazz){
        if (!clazz.isEnum()) {
            throw new InvalidParameterException();
        }
        List<IntBaseTypeSort> sorts = enumSort(clazz) ; //排序结果
        Map sortMap= new LinkedHashMap() ;
        for (IntBaseTypeSort i : sorts) {
            sortMap.put(i.getDesc(),i.getValue()) ;
        }
      //sortMap = sorts.stream().collect(Collectors.toMap(IntBaseTypeSort::getDesc, (p) -> p.getValue()));
        return sortMap;
    }

    /**
     * sort 排序
     * @param clazz
     * @return
     */
    static List<IntBaseTypeSort> enumSort(Class<? extends IType> clazz){
        List<IntBaseTypeSort> sortList= Arrays.asList((IntBaseTypeSort[])clazz.getEnumConstants());
        Collections.sort(sortList, new Comparator<IntBaseTypeSort>() {
            @Override
            public int compare(IntBaseTypeSort o1, IntBaseTypeSort o2) {
                return ((Integer)o1.getSort()).compareTo((Integer) o2.getSort());
            }
        });
        return sortList;
    }


    /* 另一种写法
    static List<IntBaseTypeSort> enumSort(Class<? extends IType> clazz){

        List<IntBaseTypeSort> intBaseTypeSorts = new LinkedList<>() ;
        List<Integer> sortList = baseSort(clazz);
        for (Integer sort:sortList){
            toFind:
            for (IntBaseTypeSort item : (IntBaseTypeSort[]) clazz.getEnumConstants()) {
                 if(sort.equals(item.getSort())){
                     intBaseTypeSorts.add(item) ;
                     break toFind;
                 }
            }
        }
        return intBaseTypeSorts ;
    }

    static List<Integer> baseSort(Class<? extends IType> clazz){

        Set<Integer> set = new LinkedHashSet<>() ;
        for (IntBaseTypeSort item : (IntBaseTypeSort[]) clazz.getEnumConstants()) {
            set.add(item.getSort());
        }

        List<Integer> sortList= new LinkedList<Integer>(set);
        Collections.sort(sortList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        return sortList;
    }
*/

    /**
     * 获取值信息
     */
    int getValue();

    /**
     * 获取值描述
     * @return
     */
    String getDesc();

    /**
     * 获取序号
     * @return
     */
    int getSort();

}

