package com.toutiao.app.dao.newhouse;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface NewHouseTopicsEsDao {


    /**
     *  获取五环内最美新房
     * @param boolQueryBuilder
     * @param pageNum
     * @param pageSize
     * @return
     */
    SearchResponse getNewHouseList(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize);
}
