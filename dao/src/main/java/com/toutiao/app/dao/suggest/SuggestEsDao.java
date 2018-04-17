package com.toutiao.app.dao.suggest;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface SuggestEsDao {
    /**
     * 获取商圈区域联想词
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getAreaAndDistrictSuggest(BoolQueryBuilder booleanQueryBuilder);

    /**
     * 获取关键字联想词
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getKeywordSuggest(BoolQueryBuilder booleanQueryBuilder);
}
