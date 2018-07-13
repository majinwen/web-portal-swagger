package com.toutiao.app.dao.sellhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface CutPriceSellHouseEsDao {
    SearchResponse getCutPriceSellHouse(BoolQueryBuilder query, Integer sort, Integer pageNum, Integer pageSize);
}
