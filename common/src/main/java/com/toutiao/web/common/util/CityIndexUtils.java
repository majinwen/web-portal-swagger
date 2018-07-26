package com.toutiao.web.common.util;

/**
 * 获取表名
 * 1)小区信息表含有父子关系的父表有做调整,没有父子关系的表不变
 */
public class CityIndexUtils {

    /**
     * 城市后缀
     */
    public static String getCitySuffix(){
        return "_sh";
    }

    /**
     * 新房索引
     */
    public static String getNewHouseIndex(){
        return "newhouses_sh";
    }

    /**
     * 新房表名(父表)
     */
    public static String getNewHouseParentTable(){
        return "buildings_sh";
    }

    /**
     * 新房表名(子表)
     */
    public static String getNewHouseChildTable(){
        return "layout_sh";
    }

    /**
     * 小区索引
     */
    public static String getPlotIndex(){
        return "villages_sh";
    }

    /**
     * 小区表名(父表)
     */
    public static String getPlotParentTable(){
        return "plot_sh";
    }

    /**
     * 小区表名(子表)
     */
    public static String getPlotChildTable(){
        return "house_sh";
    }

    /**
     * 小区表名
     */
    public static String getPlotTable(){
        return "polt_sh";
    }

    /**
     * 二手房索引
     */
    public static String getEsfIndex(){
        return "esf_house_sh";
    }

    /**
     *  二手房表名
     */
    public static String getEsfTable(){
        return "esf_type_sh";
    }

    /**
     * 租房索引
     */
    public static String getRentIndex(){
        return "rent_sh";
    }

    /**
     *  租房表名
     */
    public static String getRentTable(){
        return "renthouse_sh";
    }

    /**
     * 认领房源索引
     */
    public static String getClaimIndex(){
        return "claim_esfhouse_sh";
    }

    /**
     * 认领房源表名
     */
    public static String getClaimTable(){
        return "house_resources_sh";
    }

    /**
     * 房源动态索引
     */
    public static String getHouseDynamicIndex(){
        return "house_dynamic_sh";
    }

    /**
     * 房源动态表
     */
    public static String getHouseDynamicTable(){
        return "house_dynamic_sh";
    }

    /**
     * 搜索索引(关键字)
     */
    public static String getSearchEnginesIndex(){
        return "search_engines_sh";
    }

    /**
     * 搜索表(关键字)
     */
    public static String getSearchEnginesTable(){
        return "search_engines_type_sh";
    }

    /**
     * 搜索索引(区域)
     */
    public static String getSearchScopeIndex(){
        return "search_scope_sh";
    }

    /**
     * 搜索表(区域)
     */
    public static String getSearchScopeTable(){
        return "search_scope_type_sh";
    }

}
