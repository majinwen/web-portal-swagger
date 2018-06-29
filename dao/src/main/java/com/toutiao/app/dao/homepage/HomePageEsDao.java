package com.toutiao.app.dao.homepage;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;

public interface HomePageEsDao {


    SearchResponse getHomePageEsf(BoolQueryBuilder boolQueryBuilder);

    /**
     * 获取首页主题房
     *
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getHomeThemeHouse(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size);

    /**
     * 首页获取附近小区
     * @param boolQueryBuilder
     * @param size
     * @param sort
     * @return
     */
    SearchResponse getHomePageNearPlot(BoolQueryBuilder boolQueryBuilder,Integer size,GeoDistanceSortBuilder sort);

    /**
     * 首页获取附近的二手房
     * @param boolQueryBuilder
     * @param size
     * @param sort
     * @return
     */
    SearchResponse getHomePageNearEsf(BoolQueryBuilder boolQueryBuilder,Integer size,GeoDistanceSortBuilder sort);

}
