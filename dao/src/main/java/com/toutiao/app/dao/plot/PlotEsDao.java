package com.toutiao.app.dao.plot;

import com.toutiao.app.domain.plot.PlotDetailsDo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Configuration
public interface PlotEsDao {
    /**
     * 通过id查询详情
     * @param
     * @return
     */

    SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder, String city);


    /**
     * 根据坐标和距离查询附近的小区
     * @param
     * @param
     * @return
     */
    SearchResponse queryNearPlotByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,
                                                      GeoDistanceSortBuilder sort, String city);

//    /**
//     * 查询附近小区列表
//     * @param boolQueryBuilder
//     * @param location
//     * @param sort
//     * @return
//     * @throws Exception
//     */
//    SearchResponse queryNearPlotListByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,
//                                                          GeoDistanceSortBuilder sort);
//
//    /**
//     * 查询小区列表
//     * @param from
//     * @param boolQueryBuilder
//     * @param scoreSort
//     * @return
//     */
//    SearchResponse queryPlotListByRequirement(Integer from, BoolQueryBuilder boolQueryBuilder, FieldSortBuilder scoreSort,
//                                              Integer size);
//
//    /**
//     * 查询小区列表
//     * @param from
//     * @param functionScoreQueryBuilder
//     * @return
//     */
//    SearchResponse queryPlotListByRequirementAndKeyword(Integer from, FunctionScoreQueryBuilder functionScoreQueryBuilder,
//                                                        Integer size);

    /**
     * 查询小区列表
     * @param from
     * @param boolQueryBuilder
     * @param size
     * @return
     */
    SearchResponse queryPlotListByRequirementAndKeywordV1(Integer from, BoolQueryBuilder boolQueryBuilder,Integer size,
                    GeoDistanceSortBuilder geoDistanceSort,FieldSortBuilder levelSort,FieldSortBuilder plotScoreSort, String city,String sort);

    /**
     * 普通小区列表(补)
     * @param from
     * @param boolQueryBuilder
     * @param size
     * @return
     */
    SearchResponse queryCommonPlotList(Integer from, BoolQueryBuilder boolQueryBuilder, Integer size, String keyword, String city,String sort);


//    /**
//     * 获取小区收藏列表
//     * @param boolQueryBuilder
//     * @param from
//     * @param size
//     * @return
//     */
//    SearchResponse queryPlotListByPlotIdList(BoolQueryBuilder boolQueryBuilder,Integer from,Integer size);

    /**
     * 获取小区
     * @param idsQueryBuilder 小区ID列表
     * @return
     */
    SearchResponse getPlotByIds(IdsQueryBuilder idsQueryBuilder, String city);

    /**
     * 获取小区top50
     */
    SearchResponse getPlotTop50List(BoolQueryBuilder boolQueryBuilder ,Integer from,Integer size, String city);


    SearchResponse getPlotByRecommendCondition(BoolQueryBuilder boolQueryBuilder, ScriptSortBuilder scrip, String city);


//    SearchResponse getPlotByKeyWord(BoolQueryBuilder booleanQueryBuilder, String city);
//
//
//    SearchResponse getPlotByNickNameKeyWord(BoolQueryBuilder booleanQueryBuilder);

    /**
     * 根据小区id查询小区信息
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse queryPlotByPlotId(BoolQueryBuilder boolQueryBuilder, String city);


    /**
     * 小区综述
     * @param boolQueryBuilder
     * @param city
     * @return
     */
    SearchResponse getReviewById(BoolQueryBuilder boolQueryBuilder, String city);

}
