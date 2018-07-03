package com.toutiao.app.dao.homepage;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;

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
}
