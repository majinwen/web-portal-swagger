package com.toutiao.app.dao.sellhouse;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface SellHouseDetailTopicsEsDao {



    SearchResponse getNearbyTopicsSellHouse(BoolQueryBuilder boolQueryBuilder, String[] searchAfterIds, Integer pageSize);
}
