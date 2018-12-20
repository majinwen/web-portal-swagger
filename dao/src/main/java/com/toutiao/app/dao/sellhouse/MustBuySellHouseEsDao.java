package com.toutiao.app.dao.sellhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface MustBuySellHouseEsDao {
    SearchResponse getMustBuySellHouse(BoolQueryBuilder query, Integer sort, Integer pageNum, Integer pageSize, Integer topicType, String city);
}
