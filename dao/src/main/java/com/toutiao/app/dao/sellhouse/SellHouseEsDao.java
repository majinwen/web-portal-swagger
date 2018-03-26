package com.toutiao.app.dao.sellhouse;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;



public interface SellHouseEsDao {


    /**
     * 根据houseid获取二手房房源信息
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getSellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder);

}
