package com.toutiao.web.common.util.elastic;


import com.toutiao.web.common.constant.elastic.ESIndexConstant;
import com.toutiao.web.common.util.StringTool;

/**
 * 获取表名
 * 1)小区信息表含有父子关系的父表有做调整,没有父子关系的表不变
 */
public class ElasticCityUtils {


    public static final String NEWHOUSE_PARENT_NAME = "buildings";

    public static final String NEWHOUSE_CHILD_NAME = "layout";

    public static final String VILLAGES_CHILD_NAME = "sellhouse";

    public static final String VILLAGES_PARENT_NAME = "community";





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
     * 新房表名(子表)
     */
//    public static String getNewHouseChildType(String cityCode){
//        return getESIndexMap(ESIndexConstant.NEW_HOUSE_TYPE_T2,cityCode);
//    }

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

    /**
     * 租房索引
     */
    public static String getRentIndex(String cityCode){
        return getESIndexMap(ESIndexConstant.RENT_INDEX,cityCode);
    }

    /**
     * 搜索索引(区域商圈)
     */
    public static String getSearchScopeIndex(String cityCode){
        return getESIndexMap(ESIndexConstant.SCOPE_INDEX,cityCode);
    }
    /**
     * 搜索表名(区域商圈)
     */
    public static String getSearchScopeType(String cityCode){
        return getESIndexMap(ESIndexConstant.SCOPE_TYPE,cityCode);
    }
    /**
     * 搜索索引(房源)
     */
    public static String getSearchEnginesIndex(String cityCode){
        return getESIndexMap(ESIndexConstant.ENGINES_INDEX,cityCode);
    }
    /**
     * 搜索表名(房源)
     */
    public static String getSearchEnginesType(String cityCode){
        return getESIndexMap(ESIndexConstant.ENGINES_TYPE,cityCode);
    }
    /**
     *  租房表名
     */
    public static String getRentType(String cityCode){
        return getESIndexMap(ESIndexConstant.RENT_TYPE,cityCode);
    }


    /**
     * 获取二手房索引
     */
     public  static  String getEsfHouseIndex(String cityCode)
     {
         return getESIndexMap(ESIndexConstant.ESF_INDEX,cityCode);
     }

    /**
     * 获取二手房
     */
     public static  String getEsfHouseTpye(String cityCode)
     {
         return  getESIndexMap(ESIndexConstant.ESF_TYPE,cityCode);
     }

    /**
     * 获取租房的索引
     */
    public static  String getRentHouseIndex(String cityCode)
    {
        return  getESIndexMap(ESIndexConstant.RENT_INDEX,cityCode);
    }

    /**
     * 获取租房的type
     */
    public  static  String getRentHouseType(String cityCode)
    {
        return getESIndexMap(ESIndexConstant.RENT_TYPE,cityCode);
    }

    /**
     * 获取经纪人表索引
     * @param cityCode
     * @return
     */
    public static  String getAgentIndex(String cityCode)
    {
        return  getESIndexMap(ESIndexConstant.AGENT_INDEX,cityCode);
    }

    /**
     * 获取经纪人的type
     * @param cityCode
     * @return
     */
    public static  String getAgentType(String cityCode)
    {
        return  getESIndexMap(ESIndexConstant.AGENT_TYPE,cityCode);
    }

    /**
     * 获取二手房全量表index
     * @param cityCode
     * @return
     */
    public static String getEsfFullAmountIndex(String cityCode){
        return  getESIndexMap(ESIndexConstant.ESF_FULL_AMOUNT_INDEX,cityCode);
    }

    /**
     * 获取二手房全量表type
     * @param cityCode
     * @return
     */
    public static String getEsfFullAmountType(String cityCode){
        return  getESIndexMap(ESIndexConstant.ESF_FULL_AMOUNT_TYPE,cityCode);
    }

    /**
     * 获取商圈户型index
     * @param cityCode
     * @return
     */
    public static String getAreaRoomIndex(String cityCode){
        return  getESIndexMap(ESIndexConstant.AREA_ROOM_INDEX,cityCode);
    }

    /**
     * 获取商圈户型type
     * @param cityCode
     * @return
     */
    public static String getAreaRoomType(String cityCode){
        return  getESIndexMap(ESIndexConstant.AREA_ROOM_TYPE,cityCode);
    }

    /**
     * 获取区县商圈index
     * @param cityCode
     * @return
     */
    public static String getDbAvgPriceIndex(String cityCode){
        return  getESIndexMap(ESIndexConstant.DISTRICT_BIZCIRCLE_AVERAGE_PRICE_INDEX,cityCode);
    }

    /**
     * 获取区县商圈type
     * @param cityCode
     * @return
     */
    public static String getDbAvgPriceType(String cityCode){
        return  getESIndexMap(ESIndexConstant.DISTRICT_BIZCIRCLE_AVERAGE_PRICE_TYPE,cityCode);
    }


}
