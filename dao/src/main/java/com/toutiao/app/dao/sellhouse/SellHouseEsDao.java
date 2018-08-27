package com.toutiao.app.dao.sellhouse;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;


public interface SellHouseEsDao {


    /**
     * 根据houseid获取二手房房源信息
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getSellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder);

    /**
     * 根据小区id获取小区的房源数量
     * @param plotsId
     * @return
     */
    SearchResponse getSellHouseCountByPlotsId(Integer plotsId);

    /**
     * 根据小区id获取小区的房源数量v2(非聚合)
     * @param plotsId
     * @return
     */
    SearchResponse getEsfCountByPlotsId(Integer plotsId);


    SearchResponse getEsfByPlotsIdAndRoom(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize);

    /**
     * 获取二手房列表
     * @param query
     * @param pageNum
     * @param pageSize
     * @return
     */
    SearchResponse getSellHouseList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer pageNum, Integer pageSize);

    /**
     * 获取推荐二手房
     * @param query
     * @param uid
     * @param pageSize
     * @return
     */
    SearchResponse getRecommendSellHouse(FunctionScoreQueryBuilder query, String uid, Integer pageSize);


    SearchResponse getBeSureToSnatchList(BoolQueryBuilder booleanQueryBuilder,Integer pageNum, Integer pageSize,FieldSortBuilder sortFile );





    /**
     * 获取二手房列表
     * @param idsQueryBuilder 房源ID列表
     * @return
     */
    SearchResponse getHouseByIds(IdsQueryBuilder idsQueryBuilder);

    SearchResponse getPlotByKeyWord(BoolQueryBuilder booleanQueryBuilder);

    SearchResponse getPlotByNickNameKeyWord(BoolQueryBuilder booleanQueryBuilder);

    SearchResponse querySellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder);


}
