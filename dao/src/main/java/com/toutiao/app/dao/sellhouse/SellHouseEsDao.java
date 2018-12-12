package com.toutiao.app.dao.sellhouse;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;


public interface SellHouseEsDao {


    /**
     * 根据houseid获取二手房房源信息
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getSellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder,String city);

    /**
     * 根据小区id获取小区的房源数量
     * @param plotsId
     * @return
     */
    SearchResponse getSellHouseCountByPlotsId(Integer plotsId,  String city);

    /**
     * 根据小区id获取小区的房源数量v2(非聚合)
     * @param plotsId
     * @return
     */
    SearchResponse getEsfCountByPlotsId(Integer plotsId, String city);


    SearchResponse getEsfByPlotsIdAndRoom(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize,  String city);

    /**
     * 获取二手房列表
     * @param query
     * @param pageNum
     * @param pageSize
     * @param city
     * @return
     */
    SearchResponse getSellHouseList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer
            pageNum, Integer pageSize, String city, GeoDistanceSortBuilder GeoDistanceSort, String sort);

    SearchResponse getSellHouseList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer
            pageNum, Integer pageSize, String city);

    SearchResponse getSellHouseByCondition(FunctionScoreQueryBuilder query,Integer
            pageNum, Integer pageSize, String city);

    /**
     * 获取推荐二手房
     * @param query
     * @param uid
     * @param pageSize
     * @param city
     * @return
     */
    SearchResponse getRecommendSellHouse(FunctionScoreQueryBuilder query, String uid, Integer pageSize, String city);


    SearchResponse getBeSureToSnatchList(BoolQueryBuilder booleanQueryBuilder,Integer pageNum, Integer pageSize,FieldSortBuilder sortFile, String city);





//    /**
//     * 获取二手房列表
//     * @param idsQueryBuilder 房源ID列表
//     * @return
//     */
//    SearchResponse getHouseByIds(IdsQueryBuilder idsQueryBuilder);
//
    /**
     * 获取对比二手房列表
     * @param idsQueryBuilder
     * @return
     */
    SearchResponse getComparedHouseByIds(IdsQueryBuilder idsQueryBuilder, String city);

//    /**
//     * 根据关键字查询小区
//     * @param booleanQueryBuilder
//     * @return
//     */
//    SearchResponse getPlotByKeyWord(BoolQueryBuilder booleanQueryBuilder);
//
//    /**
//     * 根据小区别名关键字查询小区
//     * @param booleanQueryBuilder
//     * @return
//     */
//    SearchResponse getPlotByNickNameKeyWord(BoolQueryBuilder booleanQueryBuilder);



    SearchResponse querySellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder);


    SearchResponse querySellHouseByHouseIdNew(BoolQueryBuilder boolQueryBuilder, String city);

    SearchResponse querySellHouse(BoolQueryBuilder boolQueryBuilder, String city);

    /**
     * 相似好房
     * @param query
     * @param city
     * @param geoDistanceSort
     * @return
     */
    SearchResponse getSimilarSellHouseList(BoolQueryBuilder query, String city, GeoDistanceSortBuilder geoDistanceSort);


    SearchResponse getEsfCustomConditionDetails(BoolQueryBuilder query, String city);



    SearchResponse getAvgPriceByBizcircle(BoolQueryBuilder query, String city);


}
