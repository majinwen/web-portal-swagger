package com.toutiao.app.dao.plot;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.context.annotation.Configuration;


@Configuration
public interface PlotDao {
    /**
     * 通过id查询详情
     * @param id
     * @return
     */
    SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder) throws Exception;

    /**
     * 根据坐标和距离查询附近的小区
     * @param lat
     * @param lon
     * @return
     */
    SearchResponse queryNearPlotByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort) throws Exception;

    /**
     * 小区条件查询
     * @param plotRequest
     * @return
     */
    SearchResponse queryPlotListByRequirement(String keyword,Integer from, BoolQueryBuilder boolQueryBuilder, FieldSortBuilder avgPriceSort,FieldSortBuilder scoreSort,FieldSortBuilder levelSort,FieldSortBuilder plotScoreSort);
}
