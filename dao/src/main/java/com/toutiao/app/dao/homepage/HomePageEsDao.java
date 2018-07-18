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
     * 首页获取降价房8条
     */
    SearchResponse getHomePageCutPrice(BoolQueryBuilder boolQueryBuilder);

    /**
     * 首页获取价格洼地8条
     */
    SearchResponse getHomePageLowerPrice(BoolQueryBuilder boolQueryBuilder);

    /**
     *
     * @param boolQueryBuilder
     * @return 首页top50
     */
    SearchResponse getHomePageTop50(BoolQueryBuilder boolQueryBuilder);

    /**
     * 缝出必抢
     */
    SearchResponse getHomeBeSureToSnatch(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size);
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

    /**
     * 专题着陆页-附近小区
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getPlotSpecialPage(BoolQueryBuilder boolQueryBuilder, GeoDistanceSortBuilder sort);

    /**
     * 专题着陆页-附近二手房
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getEsfSpecialPage(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size);

}
