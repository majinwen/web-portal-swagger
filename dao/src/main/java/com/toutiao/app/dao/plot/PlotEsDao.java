package com.toutiao.app.dao.plot;

import com.toutiao.app.domain.plot.PlotTop50ListDoQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.context.annotation.Configuration;


@Configuration
public interface PlotEsDao {
    /**
     * 通过id查询详情
     * @param
     * @return
     */
    SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder);

    /**
     * 根据坐标和距离查询附近的小区
     * @param
     * @param
     * @return
     */
    SearchResponse queryNearPlotByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort);

    /**
     * 查询附近小区列表
     * @param boolQueryBuilder
     * @param location
     * @param sort
     * @return
     * @throws Exception
     */
    SearchResponse queryNearPlotListByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort);

    /**
     * 查询小区列表
     * @param from
     * @param boolQueryBuilder
     * @param scoreSort
     * @return
     */
    SearchResponse queryPlotListByRequirement(Integer from, BoolQueryBuilder boolQueryBuilder, FieldSortBuilder scoreSort, Integer size);

    /**
     * 查询小区列表
     * @param from
     * @param functionScoreQueryBuilder
     * @return
     */
    SearchResponse queryPlotListByRequirementAndKeyword(Integer from, FunctionScoreQueryBuilder functionScoreQueryBuilder,Integer size);


    /**
     * 获取小区收藏列表
     * @param boolQueryBuilder
     * @param from
     * @param size
     * @return
     */
    SearchResponse queryPlotListByPlotIdList(BoolQueryBuilder boolQueryBuilder,Integer from,Integer size);


    /**
     * 获取小区top50
     */
    SearchResponse getPlotTop50List(BoolQueryBuilder boolQueryBuilder ,Integer from,Integer size);
}
