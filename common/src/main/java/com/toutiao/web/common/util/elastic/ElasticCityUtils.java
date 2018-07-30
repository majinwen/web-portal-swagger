package com.toutiao.web.common.util.elastic;


import com.toutiao.web.common.constant.elastic.ESIndexConstant;
import com.toutiao.web.common.util.StringTool;

/**
 * 获取表名
 * 1)小区信息表含有父子关系的父表有做调整,没有父子关系的表不变
 */
public class ElasticCityUtils {



    public static String getESIndexMap(String esName,String cityCode){

        return ElasticIndexInit.esIndexMaps.get(cityCode).get(esName);

       // return ESIndexUtils.esIndexMaps.get(cityCode).get(esName);
    }

    /**
     * 小区索引
     */
    public static String getPlotIndex(String cityCode){
        return getESIndexMap(ESIndexConstant.PLOT_INDEX,cityCode);
    }

    /**
     * 小区表名(父表)
     */
    public static String getPlotParentType(String cityCode){
        return getESIndexMap(ESIndexConstant.PLOT_TYPE_T1,cityCode);
    }

    /**
     * 二手房索引
     */
    public static String getEsfIndex(String cityCode) {
        if (StringTool.isEmpty(cityCode)) {
            cityCode = "bj";
        }
        return getESIndexMap(ESIndexConstant.ESF_INDEX, cityCode);
    }

    /**
     * 二手房表名
     */
    public static String getEsfType(String cityCode) {
        if (StringTool.isEmpty(cityCode)) {
            cityCode = "bj";
        }
        return getESIndexMap(ESIndexConstant.ESF_TYPE, cityCode);
    }

    /**
     * 新房索引
     */
    public static String getNewHouseIndex(String cityCode){
        return getESIndexMap(ESIndexConstant.NEW_HOUSE_INDEX,cityCode);
    }

    /**
     * 新房表名(父表)
     */
    public static String getNewHouseParentType(String cityCode){
        return getESIndexMap(ESIndexConstant.NEW_HOUSE_TYPE_T1,cityCode);
    }

    /**
     * 新房表名(父表)
     */
    public static String getNewHouseChildType(String cityCode){
        return getESIndexMap(ESIndexConstant.NEW_HOUSE_TYPE_T2,cityCode);
    }

    /**
     * 新房动态索引
     */
    public static String getNewHosueDynamicIndex(String cityCode){
        return getESIndexMap(ESIndexConstant.DYNAMIC_INDEX,cityCode);
    }

    /**
     * 新房动态表名
     */
    public static String getNewHosueDynamicType(String cityCode){
        return getESIndexMap(ESIndexConstant.DYNAMIC_TYPE,cityCode);
    }




//
//    public static String getESMap(String esName){
//
//        return ESIndexUtils.esMap.get(esName);
//    }
//
//    /**
//     * 城市后缀
//     */
//    public static String getCitySuffix(){
//        return "_sh";
//    }
//
//
//
//    /**
//     * 新房索引
//     */
////    public static String getNewHouseIndex(){
////        return "newhouses_sh";
////    }
//
//    public static String getNewHouseIndex(){
//        return getESMap(ESIndexConstant.NEW_HOUSE_INDEX);
//    }
//
//    /**
//     * 新房表名(父表)
//     */
//    public static String getNewHouseParentType(){
//        return getESMap(ESIndexConstant.NEW_HOUSE_TYPE_T1);
//    }
//
//    /**
//     * 新房表名(子表)
//     */
//    public static String getNewHouseChildType(){
//        return getESMap(ESIndexConstant.NEW_HOUSE_TYPE_T2);
//    }
//
//    /**
//     * 小区索引
//     */
//    public static String getPlotIndex(){
//        return getESMap(ESIndexConstant.PLOT_INDEX);
//    }
//
//    /**
//     * 小区表名(父表)
//     */
//    public static String getPlotParentType(){
//        return getESMap(ESIndexConstant.PLOT_TYPE_T1);
//    }
//
//    /**
//     * 小区表名(子表)
//     */
//    public static String getPlotChildType(){
//        return getESMap(ESIndexConstant.PLOT_TYPE_T2);
//    }
//
////    /**
////     * 小区表名
////     */
////    public static String getPlotType(){
////        return "polt_sh";
////    }
//
//    /**
//     * 二手房索引
//     */
//    public static String getEsfIndex(){
//        return getESMap(ESIndexConstant.ESF_INDEX);
//    }
//
//    /**
//     *  二手房表名
//     */
//    public static String getEsfType(){
//        return getESMap(ESIndexConstant.ESF_TYPE);
//    }
//
//    /**
//     * 租房索引
//     */
//    public static String getRentIndex(){
//        return getESMap(ESIndexConstant.RENT_INDEX);
//    }
//
//    /**
//     *  租房表名
//     */
//    public static String getRentType(){
//        return getESMap(ESIndexConstant.RENT_TYPE);
//    }
//
//    /**
//     * 认领房源索引
//     */
//    public static String getClaimEsfIndex(){
//        return getESMap(ESIndexConstant.CLAIM_ESF_INDEX);
//    }
//
//    /**
//     * 认领房源表名
//     */
//    public static String getClaimEsfType(){
//        return getESMap(ESIndexConstant.CLAIM_ESF_TYPE);
//    }
//
//    /**
//     * 房源动态索引
//     */
//    public static String getHouseDynamicIndex(){
//        return getESMap(ESIndexConstant.DYNAMIC_INDEX);
//    }
//
//    /**
//     * 房源动态表
//     */
//    public static String getHouseDynamicType(){
//        return getESMap(ESIndexConstant.DYNAMIC_TYPE);
//    }
//
//    /**
//     * 搜索索引(关键字)
//     */
//    public static String getSearchEnginesIndex(){
//        return getESMap(ESIndexConstant.ENGINES_INDEX);
//    }
//
//    /**
//     * 搜索表(关键字)
//     */
//    public static String getSearchEnginesType(){
//        return getESMap(ESIndexConstant.ENGINES_TYPE);
//    }
//
//    /**
//     * 搜索索引(区域)
//     */
//    public static String getSearchScopeIndex(){
//        return getESMap(ESIndexConstant.SCOPE_INDEX);
//    }
//
//    /**
//     * 搜索表(区域)
//     */
//    public static String getSearchScopeType(){
//        return getESMap(ESIndexConstant.SCOPE_TYPE);
//    }

}
