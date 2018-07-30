package com.toutiao.app.dao.newhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface NewHouseLayoutEsDao {

    /**
     * 根据新房id获取该id下所有的户型以及数量
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getLayoutCountByNewHouseId(BoolQueryBuilder booleanQueryBuilder, String userAgent, String city);


    /**
     * 根据新房id获取该户型下的户型列表
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getLayoutListByNewHouseIdAndRoomCount(BoolQueryBuilder booleanQueryBuilder);
}
