package com.toutiao.app.dao.plot;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
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
     * @param plotScoreSort
     * @return
     */
    SearchResponse queryPlotListByRequirement(Integer from, BoolQueryBuilder boolQueryBuilder,FieldSortBuilder scoreSort,FieldSortBuilder plotScoreSort,Integer size);




}
