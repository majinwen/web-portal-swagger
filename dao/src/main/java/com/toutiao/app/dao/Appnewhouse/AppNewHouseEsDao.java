package com.toutiao.app.dao.Appnewhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface AppNewHouseEsDao {

    /**
     * 根据新房id获取新房详情
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getNewHouseBulid(BoolQueryBuilder boolQueryBuilder);

    /**
     * 根据新房id获取新房户型
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse  getNewHouseLayout(BoolQueryBuilder boolQueryBuilder);

}
