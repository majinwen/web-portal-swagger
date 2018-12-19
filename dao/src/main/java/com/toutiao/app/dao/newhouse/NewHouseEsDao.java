package com.toutiao.app.dao.newhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;

public interface NewHouseEsDao {

    /**
     * 根据新房id获取新房详情
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getNewHouseBulid(BoolQueryBuilder boolQueryBuilder, String city);


    /**
     * 获取新房列表
     * @param boolQueryBuilder
     * @param pageNum
     * @param pageSize
     * @return
     */
    SearchResponse getNewHouseList(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize,FieldSortBuilder levelSort,FieldSortBuilder buildingSort, String city, String sort);



    SearchResponse getDynamicByNewCode(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize, String city);


    SearchResponse getOneNewHouseByRecommendCondition(BoolQueryBuilder  boolQueryBuilder ,String city);


    SearchResponse getBuildCount(BoolQueryBuilder  boolQueryBuilder ,String city);

    SearchResponse getGuessLikeNewHouseList(BoolQueryBuilder booleanQueryBuilder, String city, Integer pageNum, Integer pageSize);

//    SearchResponse getPlotByKeyWord(BoolQueryBuilder booleanQueryBuilder, String city);
//
//
//    SearchResponse getPlotByNickNameKeyWord(BoolQueryBuilder booleanQueryBuilder, String city);


    SearchResponse getNewHouseCustomList(BoolQueryBuilder builder, Integer pageNum, Integer pageSize, String city);
}
